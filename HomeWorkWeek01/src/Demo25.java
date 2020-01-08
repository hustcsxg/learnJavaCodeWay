import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo25 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        String srcPrefix = "D:/ss";
        String tgtPrefix = "D:/ss_copy";
        File tgt = new File(tgtPrefix);
        if (!tgt.exists()) {
            tgt.mkdir();
        }

        for (File f : new File(srcPrefix).listFiles()) {
            String _fileName = f.getName();
            String _srcPath = srcPrefix + "/" + _fileName;
            String _tgtPath = tgtPrefix + "/" + _fileName;
            es.submit(new Task(_srcPath, _tgtPath));
        }
        // 关闭线程池:
        es.shutdown();

    }
}


class Task implements Runnable {
    private String srcPath;
    private String tgtPath;

    public Task(String srcPath, String tgtPath) {
        this.srcPath = srcPath;
        this.tgtPath = tgtPath;
    }

    @Override
    public void run() {
        Long threadId = Thread.currentThread().getId();
        System.out.println(String.format("线程id:%s执行文件拷贝，src:%s,tgt:%s", threadId, srcPath, tgtPath));
        try {
            Thread.sleep(1000);
            copyFile();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void copyDirectory(String srcPath, String tgtPath) {
        File src = new File(srcPath);
        File tgt = new File(tgtPath);

        if (src.isDirectory()) {
            for (File f : src.listFiles()) {
                File dire = new File(tgt.getAbsolutePath() + "/" + f.getName());// 创建一个文件对象 指向

                String srcFilePath = srcPath + "/" + f.getName();

                // 如果f是原文件的子目录
                if (f.isDirectory()) {
                    dire.mkdir(); // 在复制文件里创建一个文件夹
                    copyDirectory(srcFilePath, dire.toPath().toString());// 拷贝两个子目录的内容
                } else {
                    try {

                        System.out.println(f.toPath());
                        Files.copy(f.toPath(), dire.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            try {
                Files.copy(src.toPath(), tgt.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void copyFile() throws IOException {
        File _src = new File(srcPath);
        File _tgt = new File(tgtPath);
        if (_src.isFile()) {
            Files.copy(_src.toPath(), _tgt.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else {
            copyDirectory(_src.toPath().toString(), _tgt.toPath().toString());
        }

    }
}