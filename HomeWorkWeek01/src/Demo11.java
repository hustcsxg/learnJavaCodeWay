//用 while 循环，计算 1~200 之间所有 3 的倍数之和。
public class Demo11 {
    public static void main(String[] args) {
        int ret;
        int startNum = 0;
        int endNum = 200;
        ret = sumMultipleOfN(3, startNum, endNum);
        System.out.println(String.format("%s-%s之间，3的倍数之和为%s", startNum, endNum, ret));
    }

    public static int sumMultipleOfN(int n, int startNum, int endNum) {
        int sum = 0;
        int firstNum = endNum + 1;
        for (int i = startNum; i <= endNum; i++) {
            if (i % n == 0) {
                firstNum = i;
                break;
            }
        }
        int first3N = firstNum;
        while (first3N <= endNum) {
            sum += first3N;
            first3N +=3;
        }
        return sum;
    }

    public static int sumMultipleOfN2(int d, int startNum, int endNum) {
        // 求和公式 n*(a1+an)/2
        int sum = 0;
        int firstNum = 0;
        int lastNum = 0;
        for (int i = startNum; i <= endNum; i++) {
            if (i % d == 0) {
                firstNum = i;
                break;
            }
        }
        for (int i = endNum; i >= startNum; i--) {
            if (i % d == 0) {
                lastNum = i;
                break;
            }
        }

        int nN = (lastNum - firstNum) / d + 1;
        if (nN != 0) {
            sum = (lastNum + firstNum) * nN / 2;
        }
        return sum;

    }
}
