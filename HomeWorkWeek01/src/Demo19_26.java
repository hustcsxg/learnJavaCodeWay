//io 练习
//19. 在D盘创建一个ss的目录，在ss下面创建一个s.txt文件，在文件里边写入Hello World。
//20. 把刚才s.txt文件读取到内存中，显示到控制台。
//21. 统计s.txt中各个字符出现的次数，打印到控制台。
//22. 把s.txt中大写转成小写，小写转成大写，空格转成下划线，再输入到文件中覆盖之前的。
//23. 从控制台输入一个路径，程序循环读取这个路径下有多少文件夹？多少文件？
//24. 拷贝一张图片从D盘到F盘、移动一张图片从D盘到F盘。
//25. 输入两个文件夹名称，将A文件夹内容全部拷贝到B文件夹，要求使用多线程来操作。
//26. 从网络下载一个图片到电脑的D盘。

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Demo19_26 {
    public static void main(String[] args) throws IOException {
//        demo19();
//        demo20();
//        demo21();
//        demo22();
//        demo23();
        demo24();
    }

    public static void demo19() throws IOException {
        //在D盘创建一个ss的目录，在ss下面创建一个s.txt文件，在文件里边写入Hello World。
        String dirPath = "D:/ss";
        File dir = new File(dirPath);
        dir.mkdir();
        File f = new File(dirPath + "/s.txt");
        f.createNewFile();
        try (FileWriter fw = new FileWriter(f)) {
            fw.write("Hello World 张三");
        }

        try (FileReader fd = new FileReader(f)) {
            BufferedReader br = new BufferedReader(fd);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        }
    }

    ;

    public static String demo20() throws IOException {
        File f = new File("D:/ss/s.txt");
        String ret = "";
        try (FileReader fd = new FileReader(f)) {
            BufferedReader br = new BufferedReader(fd);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                ret += line;
            }
            br.close();
            return ret;
        }
    }

    ;

    public static void demo21() throws IOException {
        String a = demo20();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            Character value = a.charAt(i);
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(String.format("字符%s出现%s次", entry.getKey(), entry.getValue()));
        }
    }

    ;

    public static void demo22() throws IOException {
        String data = demo20();
        String afterChange = "";
        for (int i = 0; i < data.length(); i++) {
            Character value = data.charAt(i);
            if (Character.isLowerCase(value)) {
                afterChange += Character.toUpperCase(value);
            } else if (Character.isUpperCase(value)) {
                afterChange += Character.toLowerCase(value);
            } else if (Character.isSpaceChar(value)) {
                afterChange += "_";
            } else {
                afterChange += value;
            }

        }
        System.out.println("替换后内容为" + afterChange);

        try (FileWriter fw = new FileWriter(new File("D:/ss/s.txt"))) {
            fw.write(afterChange);
        }
        System.out.println("写入成功");

    }

    ;

    public static void demo23() {
        File f = new File("D:/ss");
        int[] a = countDirAndFileNum(f);
        System.out.println(String.format("共%s个文件，%s个文件夹", a[0], a[1]));
    }

    ;

    public static int[] countDirAndFileNum(File f) {
        int[] ret = {0, 0};
        int fileNum = 0;
        int dirNum = 0;

        if (!f.exists()) {
            return ret;
        } else {
            if (f.isFile()) {
                fileNum += 1;
                ret[0] = fileNum;
                ret[1] = dirNum;
                return ret;
            } else {
                dirNum += 1;
                File[] flist = f.listFiles();
                for (File _f : flist) {
                    int[] subRet = countDirAndFileNum(_f);
                    fileNum += subRet[0];
                    dirNum += subRet[1];
                }

            }
        }

        ret[0] = fileNum;
        ret[1] = dirNum;
        return ret;
    }

    public static void demo24() throws IOException {
        String srcPath = "D:/ss/bd_logo1.png";
        String tgtPath = "D:/ss/bd.png";
        String renamePath = "D:/ss/bd_rename.png";
        File f = new File(srcPath);
        File tgt = new File(tgtPath);
        File renameFile = new File(renamePath);
        Files.copy(f.toPath(), tgt.toPath());
        Files.move(tgt.toPath(), renameFile.toPath());
    }

}
