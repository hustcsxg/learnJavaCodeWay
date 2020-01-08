//反射
//  1. 定义一个Java类，Person，属性有name、age，使用反射创建一个实例，调用构造函数初始化name和age，并且调用里边的sayHello()方法。
//  2. 定义一个Java类，Person，通过反射给里边所有的字段的赋值。
//  3. 通过反射调用main函数。
//  4. 定义一个Person类，person类继承了Base类，通过反射去查看Person类继承了哪个类。
//  5. 通过反射创建一个ArrayList对象，并且调用里边的方法添加一些数据，循环list，把对象打印到控制台。

import java.lang.reflect.*;
import java.util.ArrayList;

public class Demo33_37 {
    public static void main(String[] args) throws Exception {
//        demo01();
//        demo02();
//        demo03();
//        demo04();
        demo05();

    }

    public static void demo01() throws Exception {
        Constructor c1 = Person1.class.getConstructor(String.class, Integer.class);
        Person1 p = (Person1) c1.newInstance("张三", 18);
        p.sayHello();
    }

    public static void demo02() throws NoSuchFieldException, IllegalAccessException {
        Person1 p = new Person1("Xiao Ming", 30);
        p.sayHello();
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        f.set(p, "李明");
        p.sayHello();
    }

    public static void demo03() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String startingClassName = TestArguments.class.getName();
        Method mainMethod = Class.forName(startingClassName).getMethod("main", String[].class);

        mainMethod.invoke(null, new Object[]{new String[]{"123", "456"}});
    }

    public static void demo04() {
        Class c = MyClass01.class;
        Class d = c.getSuperclass();
        System.out.println(d);
    }

    public static void demo05() throws IllegalAccessException, InstantiationException {
        Class a = ArrayList.class;
        ArrayList<Integer> b = (ArrayList<Integer>) a.newInstance();
        b.add(1);
        b.add(3);
        b.add(5);
        for (Integer i : b) {
            System.out.println(i);
        }

    }
}

class Person1 {
    private String name;
    private int age;

    public void sayHello() {
        System.out.println("hello" + name);
    }

    public Person1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

class TestArguments {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}

class MyBaseClass {
}

class MyClass01 extends MyBaseClass {
}