//Java 线程
//利用多线程去扫描统计你的电脑D盘前5个文件目录，并且统计出来各个目录地下有多少目录和文件，并且目录的深度是多少，
//比如有D:/a/b/c/d  这个目录结构相对于a来说，他的深度是3，因为他下面有目录d。统计出a目录下面有多少个目录包括子目录，
//递归统计，有多少个文件，递归统计，各个文件的类型有多少个，比如，txt有10个，exe有2个等。要求使用5个线程，每个线程各统计一个目录。

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo39 {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        File d = new File("D:/");
        int count = 0;
        ArrayList<String> subDirNames = new ArrayList<>();
        for (File f : d.listFiles()) {
            if (f.isDirectory()) {
                subDirNames.add(f.getAbsolutePath().toString());
                count += 1;
                if (count >= 5) {
                    break;
                }
            }
        }
        for (String dirName : subDirNames) {
            es.submit(new DirWalkTask(dirName));
        }
        // 关闭线程池: 否则程序不会终止。
        es.shutdown();
    }
}

class DirWalkTask implements Runnable {
    private String rootDir;

    public DirWalkTask(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void run() {
        Long threadId = Thread.currentThread().getId();
        try {
            Thread.sleep(1000);
            DirWalk dwInstance = new DirWalk();
            HashMap<String, String> ret = dwInstance.countNum(rootDir);
            String printMsg = String.format("线程id:%s 正在遍历目录:%s, 有%s个目录，%s个文件，深度为：%s，文件类型信息为：%s",
                    threadId, rootDir,
                    ret.get("dirNum"), ret.get("fileNum"), ret.get("deep"), ret.get("fileTypeInfo"));
            System.out.println(printMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DirWalk {
    public HashMap<String, String> countNum(String filePath) {
        //    {"fileNum":10,"contextNum":1,"deep":3,"fileTypeInfo":"txt:10,exe:3"}
        HashMap<String, String> map = new HashMap();
        File f = new File(filePath);
        HashMap<String, Object> retMap = walk(f);
        ArrayList<String> fileList = (ArrayList<String>) retMap.get("fileNames");
        ArrayList<String> fileTypeList = fileTypeList(fileList);
        HashMap<String, Integer> typeNums = countKeyNum(fileTypeList);
        String fileTypeinfos = "";
        for (Map.Entry entry : typeNums.entrySet()
        ) {
            fileTypeinfos += String.format("%s有%s个", entry.getKey(), entry.getValue());
        }
        map.put("fileNum", retMap.get("fileNum").toString());
        map.put("dirNum", retMap.get("dirNum").toString());
        map.put("deep", retMap.get("deep").toString());
        map.put("fileTypeInfo", fileTypeinfos);
        return map;
    }

    public static ArrayList<String> fileTypeList(ArrayList<String> fileNames) {
        ArrayList<String> ret = new ArrayList<>();
        for (String i : fileNames) {
            String[] splited = i.split("\\.");
            if (splited.length >= 2) {
                ret.add(splited[splited.length - 1]);
            } else {
                ret.add("无类型");
            }
        }
        return ret;
    }


    public static HashMap<String, Integer> countKeyNum(ArrayList<String> arrayList) {
        HashMap<String, Integer> ret = new HashMap<>();
        for (String i : arrayList) {
            if (ret.containsKey(i)) {
                ret.put(i, ret.get(i) + 1);
            } else {
                ret.put(i, 1);
            }
        }
        return ret;
    }

    public static HashMap<String, Object> walk(File rootFile) {
        int fileNum = 0;
        int dirNum = 0;
        int deep = 1;
        HashMap<String, Object> ret = new HashMap<>();
        ArrayList<String> fileNames = new ArrayList<>();
        if (rootFile.exists()) {
            if (rootFile.isFile()) {
                fileNum += 1;
                deep = 0;
                fileNames.add(rootFile.getName());
            } else {
                dirNum += 1;
                File[] flist = rootFile.listFiles();
                int childMaxDeep = 0;
                for (File _f : flist) {
                    HashMap<String, Object> subRet = walk(_f);
                    int childDeep = (int) subRet.get("deep");
                    childMaxDeep = childDeep > childMaxDeep ? childDeep : childMaxDeep;
                    fileNum += (int) subRet.get("fileNum");
                    dirNum += (int) subRet.get("dirNum");
                    for (String _i : (ArrayList<String>) subRet.get("fileNames")) {
                        fileNames.add(_i);
                    }
                }
                deep = childMaxDeep + 1;
            }
        }
        ret.put("fileNum", fileNum);
        ret.put("dirNum", dirNum);
        ret.put("deep", deep);
        ret.put("fileNames", fileNames);
        return ret;
    }
}