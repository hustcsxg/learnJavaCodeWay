import java.util.Scanner;

/*
反转一个只有3位数的整数。
从控制台输入321，输出123
*/
public class Demo01 {
    public static void main(String[] args) {
        reverseInput();
    }

    public static void reverseInput() {

        while (true) {
            System.out.println("输入3位整数，输出反转后整数（举例输入123输出321），输入exit退出");
            int ret;
            int i;
            Scanner scan = new Scanner(System.in);
            String str2 = scan.nextLine();
            if (str2.equals("exit")) {
                break;
            }
            try {
                i = Integer.valueOf(str2);
            } catch (NumberFormatException e) {
                System.out.println("请输入合法3位整数！！");
                continue;
            }

            if (i >= 100 && i <= 999 || i >= -999 && i <= -100) {
                ret = intReverse(i);
                System.out.println(ret);
            } else {
                System.out.println("请输入合法3位整数！！");
            }
            // 接收整数

        }
    }

    public static Integer intReverse(Integer num) {
        Integer dealNum;
        dealNum = num;
        if (num < 0) {
            dealNum = -num;
        }


        String a = dealNum.toString();
        StringBuffer sb = new StringBuffer(a);
        String b = sb.reverse().toString();
        Integer ret = Integer.valueOf(b);
        if (num < 0) {
            ret = -ret;
        }
        return ret;
    }
}
