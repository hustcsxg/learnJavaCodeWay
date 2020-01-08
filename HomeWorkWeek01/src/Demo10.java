//编写程序，从键盘输入一个 0~99999 之间的任意数，判断输入的数是几位数？

import java.util.Scanner;

public class Demo10 {
    public static void main(String[] args) {
        while (true) {
            System.out.println("输入0-99999之间的整数，输出是几位数，exit退出");
            Scanner scan = new Scanner(System.in);
            String str2 = scan.nextLine();
            int i;
            if (str2.equals("exit")) {
                break;
            }
            try {
                i = Integer.valueOf(str2);
                int ret = getIntegerLength(i);
                System.out.println(String.format("%s是%s位数", i, ret));
            } catch (NumberFormatException e) {
                System.out.println("请输入0-99999之间的合法整数");
                continue;
            }
        }
    }

    public static int getIntegerLength(int a) {
        String numString = Integer.valueOf(a).toString();
        return numString.length();
    }
}
