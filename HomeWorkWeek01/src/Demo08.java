//输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。

import java.util.Arrays;

public class Demo08 {
    public static void main(String[] args) {
        int[] a = {3, 0, 4, 6, 9, 1, 5};
        int[] after_a = {9, 5, 4, 6, 3, 1, 0};
        System.out.println(Arrays.toString(a));
        System.out.println("预期输出为：");
        System.out.println(Arrays.toString(after_a));
        System.out.println("实际输出为：");
        System.out.println(Arrays.toString(mySort(a)));

    }

    public static int[] mySort(int[] input) {
        int length = input.length;
        int min = input[0];
        int max = input[0];
        int maxIndex = 0;
        int minIndex = 0;
        int tmp;
        for (int i = 0; i < length; i++) {
            if (input[i] > max) {
                max = input[i];
                maxIndex = i;
            }
            if (input[i] < min) {
                min = input[i];
                minIndex = i;
            }
        }
        if (minIndex != length - 1) {
            tmp = input[length - 1];
            input[length - 1] = min;
            input[minIndex] = tmp;
        }
        if (maxIndex != 0) {
            tmp = input[0];
            input[0] = max;
            input[maxIndex] = tmp;
        }
        return input;
    }
}

