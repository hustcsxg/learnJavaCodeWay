/*
* 从控制台输入一个字符串，由字母组成，转换成大写后输出。

   • 如果输入的是小写，就转换成大写。
   • 如果输入的是大写，就转换成小写
   • 例如输入：abCd ，控制台输入 ABcD
* */

import java.util.Scanner;

public class Demo03 {
    public static void main(String[] args) {
        System.out.println("* 从控制台输入一个字符串，由字母组成，转换成大写后输出。");
//        String input = "aBcDeF13f中d";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String input = scanner.nextLine();
            lowUpperTranfer(input);
        }

    }

    public static String lowUpperTranfer(String input) {
        char data[] = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!(Character.isAlphabetic(c))) {
                data[i] = c;
                continue;
            }
            if (Character.isUpperCase(c)) {
                data[i] = Character.toLowerCase(c);
            } else {
                data[i] = Character.toUpperCase(c);
            }

        }
        String ret;
        ret = String.valueOf(data);
        System.out.println(String.format("输入为%s,转换后为%s", input, ret));
        return ret;
    }
}
