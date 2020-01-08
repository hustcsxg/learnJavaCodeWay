//有五个学生，每个学生有3门课的成绩，从键盘输入以上数据（包括学生号，姓名，三门课成绩），
// 计算出平均成绩，将原有的数据和计算出的平均分数存放在磁盘文件"stud"中。

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Demo12 {


    public static void main(String[] args) {
        String[] studentsInfo = new String[5];
        System.out.println("学生信息写入到文件stud.txt");
        try {
            studentsInfo = inputStudenInfo();
            storeScore(studentsInfo);
        } catch (Exception e) {
            System.out.println("写入失败");
            System.out.println(e.getStackTrace());
        }


    }

    public static String[] inputStudenInfo() {
        System.out.println("请依次输入五位学生的信息，学生号，姓名，语数外三门课成绩\n示例如下： CS1103,张三,80.5,90.6,100.0\n" +
                "信息间用英文逗号隔开,成绩在0-100之间");
        String[] ret = new String[5];
        for (int i = 0; i < 5; i++) {
            while (true) {
                System.out.println(String.format("请输入第%s位学生的信息。", i + 1));
                Scanner scan = new Scanner(System.in);
                try {
                    String input = scan.nextLine();
                    String[] inputArgs = input.split(",");
                    if (inputArgs.length != 5) {
                        String[] errMsgs = {"输入数据格式非法",};
                        throw new InvalidArgumentException(errMsgs);
                    }
                    String studentInfo = "";
                    Double sum = 0.0;
                    try {
                        for (int j = 0; j < 5; j++) {
                            studentInfo += inputArgs[j];
                            studentInfo += ',';
                            if (j >= 2) {
                                sum += Double.valueOf(inputArgs[j]);
                            }
                        }
                        studentInfo += Double.valueOf(sum / 3.0).toString();
                        ret[i] = studentInfo;
                        break;
                    } catch (Exception e) {
                        System.out.println("");
                        System.out.println(e.toString());

                    }

                } catch (Exception e) {
                    System.out.println("输入信息有误，请重新输入。");
                    System.out.println(e.toString());
                }

            }

        }
        return ret;

    }

    public static void storeScore(String args[]) throws IOException {
        File file = new File("stud.txt");
        // 创建文件
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);
        // 向文件写入内容
        for (String line : args
        ) {
            writer.write(line);
            writer.write("\n");
            writer.flush();
        }

        writer.close();
    }

}


