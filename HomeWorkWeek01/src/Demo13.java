//写一个猜拳游戏，从控制台输入石头、剪刀、布，后台随机生成对应和输入的做比较，看看谁能赢，输，和平局。

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class Demo13 {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        String[] validInput = {"石头", "剪刀", "布"};
        while (true) {
            System.out.println("开始石头剪刀布游戏，请输入（石头、剪刀、布）输入exit退出");
            Scanner scan = new Scanner(System.in);
            String str2 = scan.nextLine();
            if (str2.equals("exit")) {
                break;
            }
            try {
                if (Arrays.asList(validInput).contains(str2)) {
                    int userInput = userInput2num(str2);
                    int systemInput = systemRandom();
                    String ret = judgeAndPrint(userInput, systemInput);
                    System.out.println(ret);
                } else {
                    System.out.println(String.format("不支持的输入%s请输入（石头、剪刀、布）", str2));
                }
            } catch (NumberFormatException e) {
                System.out.println(String.format("不支持的输入%s请输入（石头、剪刀、布）", str2));
                continue;
            }
        }
    }


    public static int userInput2num(String input) {
        String[] validInput = {"石头", "剪刀", "布"};
        int index = Arrays.asList(validInput).indexOf(input);
        return index;
    }

    public static String judgeAndPrint(int userInput, int systemInput) {
        final String[] validInput = {"石头", "剪刀", "布"};
        String userStr = validInput[userInput];
        String systemStr = validInput[systemInput];
        int result = judge(userInput, systemInput);
        String resutlStr = "";
        if (result == 0) {
            resutlStr = "平局";
        } else if (result == 1) {
            resutlStr = "你赢了";

        } else {
            resutlStr = "你输了";
        }

        String ret = String.format("你出:%2s,我出%2s,%s", userStr, systemStr, resutlStr);
        return ret;
    }


    public static int systemRandom() {
        // 返回一个 0，1，2 中任意一个数
        SecureRandom sr = new SecureRandom();
        int ret = sr.nextInt(100);
        ret = ret % 3;
        return ret;
    }

    public static int judge(int userInput, int systemRandom) {
        // 石头0 剪刀1 布2
        //     石头   剪刀 布
        //石头  0     1    -1
        //剪刀  -1    0     1
        //布    1    -1    0
        final int[][] judgeArray = {{0, 1, -1}, {-1, 0, 1}, {1, -1, 0}};
        return judgeArray[userInput][systemRandom];
    }
}
