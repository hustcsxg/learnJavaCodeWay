//设计2个类，要求如下：（知识点：类的继承 方法的覆盖）
//
//        • 1 定义一个汽车类Vehicle， 
//        • 1.1 属性包括：汽车品牌brand（String类型）、颜色color（String类型）和速度speed（double类型）。 
//        • 1.2 至少提供一个有参的构造方法（要求品牌和颜色可以初始化为任意值，但速度的初始值必须为0）。 
//        • 1.3 为属性提供访问器方法。注意：汽车品牌一旦初始化之后不能修改。 
//        • 1.4 定义一个一般方法run()，用打印语句描述汽车奔跑的功能 
//        • 1.5 在main方法中创建一个品牌为―benz‖、颜色为―black‖的汽车。
//
//        • 2 定义一个Vehicle类的子类轿车类Car，要求如下： 
//        • 2.1 轿车有自己的属性载人数loader（int 类型）。
//        • 2.2 提供该类初始化属性的构造方法。 
//        • 2.3 重新定义run()，用打印语句描述轿车奔跑的功能。 
//        • 2.4 在main方法中创建一个品牌为―Honda‖、颜色为―red‖，载人数为2人的轿车。

public class Demo14 {
    public static void main(String[] args) {
        Vehicle car1 = new Vehicle("benz", "black");
        car1.run();
        car1.setSpeed(100.0);
        car1.run();

        Car car2 = new Car("Honda","red", 2);
        car2.run();
    }
}

class Vehicle {
    private String brand;
    private String color;
    private Double speed = 0.0;

    public Vehicle(String brand, String color) {
        this.brand = brand;
        this.color = color;
        this.speed = 0.0;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getColor() {
        return this.color;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void run() {
        System.out.println(String.format("汽车正在运行，颜色%s:品牌:%s速度:%s", this.getColor(), this.getBrand(), this.getSpeed()));
    }
}

class Car extends Vehicle {
    private int loader = 4;

    public Car(String brand, String color) {
        super(brand, color);
    }

    public int getLoader() {
        return this.loader;
    }

    public void setLoader(int loader) {
        this.loader = loader;
    }

    public Car(String brand, String color, int loader) {
        super(brand, color);
        this.loader = loader;
    }

    @Override
    public void run() {
        System.out.println(String.format("Car在运行,颜色：%s:品牌:%s速度:%s，荷载%s人", this.getColor(), this.getBrand(), this.getSpeed(), this.getLoader()));
    }
}