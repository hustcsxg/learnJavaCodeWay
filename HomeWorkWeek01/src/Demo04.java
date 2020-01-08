/*
* 输入三个整数x,y,z，请把这三个数由小到大输出。
例如输入：1，3，2  输出  1，2，3
* */

import java.util.Arrays;

public class Demo04 {
    public static void main(String[] args) {
        //todo 控制台获取输入
        int[] a = {3, 9, 1};
        sortInputInt(a);
    }

    public static int[] sortInputInt(int[] input) {
        Arrays.sort(input);
        System.out.println(Arrays.toString(input));
        return input;

    }
}
