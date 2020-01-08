//计算字符串中子串出现的次数。
public class Demo09 {
    public static void main(String[] args) {
        String input = " hello1 Eric hello hello ";
        String sub = " ";
        int a;
        a = subStrCount(input, sub);
        System.out.println(String.format("从%s中查找子串%s\n共发现%s处", input, sub, a));
    }

    public static int subStrCount(String input, String sub) {
        int count = 0;
        if (sub.length() == 0 || input.length() == 0 || sub.length() > input.length()) {
            return count;
        }
        int start_index = -1;
        for (int i = 0; i < input.length(); i++) {
            int findIndex = input.indexOf(sub, start_index + 1);
            if (findIndex != -1) {
                System.out.println(findIndex);
                count++;
                start_index = findIndex;
            } else {
                break;
            }

        }
        return count;
    }
}
