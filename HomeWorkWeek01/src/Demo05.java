//利用条件运算符的嵌套来完成此题：
//学习成绩>=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。

import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        while (true) {
            System.out.println("输入成绩，0-100，打印等级，exit退出");
            Scanner scan = new Scanner(System.in);
            String next = scan.nextLine();
            if (next.equals("exit")) {
                System.out.println("退出");
                break;
            }
            try {
                Double score = Double.valueOf(next);
                if(score>=0 && score <=100){
                printScoreLevel(score);}
                else{
                    System.out.println("请输入正确的成绩0-100");
                }
            } catch (Exception e) {
                System.out.println("请输入正确的成绩0-100");
            }
        }
    }

    public static void printScoreLevel(Double score) {
        String level;
        if (score >= 90) {
            level = "A";
        } else if (score > 60) {
            level = "B";
        } else {
            level = "C";
        }
        System.out.println(String.format("该生成绩为%s,评分等级为%s", score, level));
    }
}
