//打印出如下图案（菱形）
//   *
//  ***
// ******
//********
// ******
//  ***
//   *

import java.util.Scanner;

public class Demo07 {
    public static void main(String[] args) {
        while (true){
            System.out.println("请输入菱形长度（2-100）,exit退出");
            Scanner scan= new Scanner(System.in);
            String str2 = scan.nextLine();
            int i;
            if (str2.equals("exit")) {
                break;
            }
            try {
                i = Integer.valueOf(str2);
                if (i>=2 && i<=100){
                printSquer(i);}
                else {
                    System.out.println("请输入合法长度（2-100）");
                }
            } catch (NumberFormatException e) {
                System.out.println("请输入合法长度（2-100）");
                continue;
            }
        }
    }

    public static void printSquer(int row) {
        // 正向
        Character data[][] = new Character[row][2 * row - 1];
        for (int i = 0; i < row; i++) {
            int startIndex = row - (i + 1);
            int endIndex = startIndex + 2 * i;
            for (int j = 0; j < startIndex; j++) {
                data[i][j] = ' ';
            }
            for (int j = startIndex; j <= endIndex; j++) {
                data[i][j] = '*';
            }
            for (int j = endIndex + 1; j < 2 * row - 1; j++) {
                data[i][j] = ' ';
            }
        }
        for (int i = 0; i < row; i++) {
            myPrint(data[i]);
        }
        for (int i = row - 2; i >= 0; i--) {
            myPrint(data[i]);
        }
    }

    public static void myPrint(Character[] input) {
        for (Character i : input) System.out.print(i);
        System.out.println();
    }

}


