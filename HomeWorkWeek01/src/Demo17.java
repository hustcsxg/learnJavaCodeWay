/*
定义两个类，描述如下：
  • 定义一个人类Person： 
  • 定义一个方法sayHello()，可以向对方发出问候语“hello,my name is XXX” 
  • 有三个属性：名字、身高、体重 
  • 定义一个PersonCreate类： 
  • 创建两个对象，分别是zhangsan，33，1.73；lishi，44，1.74 
  • 分别调用对象的sayHello()方法。
 */

public class Demo17 {
    public static void main(String[] args) {
        PersonCreate.run();
    }

}

class Person {
    private String name;
    private Double height;
    private Double weight;

    Person(String name, Double height, Double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public void sayHello() {
        String hello = String.format("hello, my name is %s", this.name);
        System.out.println(hello);
    }
}

class PersonCreate {
    public static Person createPerson(String name, Double height, double weight) {
        Person a = new Person(name, height, weight);
        return a;
    }

    public static void run() {
        Person a1 = createPerson("zhangsan", Double.valueOf(33), 1.73);
        a1.sayHello();
        Person a2 = createPerson("lishi", Double.valueOf(44), 1.74);
        a2.sayHello();


    }

}
