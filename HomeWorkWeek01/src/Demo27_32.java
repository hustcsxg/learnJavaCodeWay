//java 集合相关练习

import java.security.SecureRandom;
import java.util.*;

public class Demo27_32 {

    public static void main(String[] args) {
//        collectionDemo1();//第27题
//        collectionDemo2();//第28题
//        collectionDemo3();//第29题
//        collectionDemo4();//第30题
//        collectionDemo5();//第31题
        collectionDemo6();//第32题
    }

    public static void collectionDemo1() {
//        创建一个ArrayList集合，输入10个数，将数从大到小输出，从小到大输出，随机输出。
        ArrayList a = new ArrayList();
        SecureRandom sr = new SecureRandom();
        int nextInt;
        for (int i = 0; i < 10; i++) {
            nextInt = sr.nextInt(40);
            a.add(nextInt);
        }
        System.out.println(String.format("排序前ArrayList为：%s", a));
        Collections.sort(a);
        System.out.println(String.format("排序后ArrayList为：%s", a));
        System.out.println("开始正序遍历：");

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        System.out.println("开始倒序遍历：");
        for (int i = a.size() - 1; i >= 0; i--) {
            System.out.println(a.get(i));
        }
        System.out.println("随机输出：");
        List<Integer> indexs = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(indexs);
        for (int i : indexs) {
            System.out.println(String.format("第%s个元素为%s", i + 1, a.get(i)));
        }

    }

    public static void collectionDemo2() {
//        已知有两个容器List，第一个List装有【小编,小王】,第二个容器装有【95分，94分】，请把第二个容器的94分改成95分，通过迭代器在控制打印出：
//          • 小编：95分
//          • 小王：95分
        List<String> a = new ArrayList<String>(Arrays.asList("小编", "小王"));
        List<String> b = new ArrayList<String>(Arrays.asList("95分", "94分"));
        b.set(1, "95分");
        for (int i = 0; i < a.size(); i++) {
            String toPrintStr = String.format("%s:%s", a.get(i), b.get(i));
            System.out.println(toPrintStr);
        }

    }

    public static void collectionDemo3() {
//        创建一个HashMap，里边存有key：username，value:password,的用户密码信息，从控制台输入一个用户和密码，
//        程序在后台判断用户名在map中是否存在，如果不存在，就提示用户名错误，
//        用户正确，再判断当前用户名对应的密码是否和输入的一致，如果一致就提示用户密码正确.

        HashMap<String, String> namePasswdMap = new HashMap<String, String>() {{
            put("eric", "123456");
            put("zhangsan", "123456");
            put("lisi", "123456");
        }};

        System.out.println("请输入用户名:");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();
        System.out.println("请输入密码:");
        String passwd = scan.nextLine();
        if (namePasswdMap.containsKey(userName)) {
            String validPasswd = namePasswdMap.get(userName);
            if (validPasswd.equals(passwd)) {
                System.out.println("用户名密码正确");
            } else {
                System.out.println("用户名或密码错误");
            }
        } else {
            System.out.println("用户名或密码错误");
        }
    }

    public static void collectionDemo4() {
//        有两个list集合，l1数据有1，2，3，4 l2数据有 2，3，4，5，
//        将两个集合中重复的数据移除，并且把不重复的添加到第三个l3集合里边。
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        List<Integer> l3 = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>(Arrays.asList(new Integer[l1.size()]));
        Collections.copy(tmp, l1);
        l1.removeAll(l2);
        l2.removeAll(tmp);
        for (int i : l1) {
            l3.add(i);
        }
        for (int i : l2) {
            l3.add(i);
        }
        System.out.println(l3);
    }

    public static void collectionDemo5() {
//        创建一个List集合，里边有20组数据，在创建一个Map，把List中下标为0的作为map的key，
//        下标为list.length()-1的为map的value，依次类推，最后在控制台打印出map所对应的key和value。
        ArrayList<String> l1 = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            l1.add(Integer.valueOf(i).toString());
        }
        int lenth = l1.size();
        for (int i = 0; i < lenth; i++) {
            map.put(l1.get(i), l1.get(lenth - 1 - i));
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(String.format("key:%s,value:%s", entry.getKey(), entry.getValue()));
        }
    }

    public static void collectionDemo6() {
//        创建一个Map集合，里边有10组数据，假如key为1到10，value为11到20，从控制台接受1到20之间的数，
//        程序后台判断map中key和value有没有对应的，如果有提示当前数字为key，对应的值是多少，
//        如果是值匹配上了，提示匹配到值为xx，对应的key为yy。
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            map.put(i, i + 10);
        }
        System.out.println("从控制台输入1-20之间的数，若存在key等于输入返回对应的value值，若存在value等于输入返回对应的key值");
        while (true) {
            System.out.println("请输入1-20之间数字，输入exit退出");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("退出程序！");
                break;
            }
            try {
                int inputInt = Integer.valueOf(input);
                if (inputInt <= 20 && inputInt >= 1) {
                    if (map.containsKey(inputInt)) {
                        System.out.println(String.format("存在该key=%s,对应value为：%s", inputInt, map.get(inputInt)));
                    } else if (map.containsValue(inputInt)) {
                        for (Map.Entry<Integer, Integer> entry : map.entrySet()
                        ) {
                            if (entry.getValue() == inputInt) {
                                System.out.println(String.format("存在value=%s,对应key为：%s", inputInt, entry.getKey()));
                                break;
                            }
                        }

                    }
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("非法输入，请输入1-20之间数字");
            }
        }
    }
}
