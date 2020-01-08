/*
将一个字符由小写字母转换为大写字母。
从控制台输入 a ，输出A
*/

import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        System.out.println("将一个字符由小写字母转换为大写字母,从控制台输入 a ，输出A");
        Scanner scan = new Scanner(System.in);
        if(scan.hasNext()){
            String nextLine = scan.nextLine();
            Character a = nextLine.charAt(0);
            if(Character.isLetter(a))
                transfer(a);
            else {
                System.out.println("请输入单个字母！");
            }
        }
    }

    public static char transfer(char input) {
        char ret;
        ret = Character.toUpperCase(input);
        System.out.println(ret);
        return ret;
    }
}
