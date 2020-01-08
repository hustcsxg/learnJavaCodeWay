//输入一行字符，分别统计出其英文字母、空格、数字和其它字符的个数。
//        • 例如输入 Ab12 sd
//        • 输出字母：4，空格1个，数字2个，这里的数字按0到9算，12是1和2不是12
public class Demo06 {
    public static void main(String[] args) {
        String input = "123aBz @中国";
        cout(input);
    }

    public static void cout(String input) {
        int numCount = 0;
        int alpCount = 0;
        int spaceCount = 0;
        int otherCount = 0;
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (Character.isDigit(c)) {
                numCount += 1;
            } else if (Character.isUpperCase(c)||Character.isLowerCase(c)) {
                alpCount += 1;
            }
            else if (Character.isSpaceChar(c)) {
                spaceCount += 1;
            } else {
                otherCount += 1;
            }

        }
        System.out.println(String.format(
                "字符串：%s\n总长度:%s\n 数字共%s个\n 字母有%s个\n空格有%s个\n 其他字符有%s个", input, input.length(), numCount,
                alpCount, spaceCount, otherCount));
    }
}
