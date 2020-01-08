/*
Cola公司的雇员分为以下若干类：(知识点：多态)
        • 1 ColaEmployee ：这是所有员工总的父类，属性：员工的姓名,员工的生日月份。方法：getSalary(int month) 根据参数月份来确定工资，如果该月员工过生日，则公司会额外奖励100 元。
        • 2 SalariedEmployee ： ColaEmployee 的子类，拿固定工资的员工。属性：月薪 
        • 3 HourlyEmployee ：ColaEmployee 的子类，按小时拿工资的员工，每月工作超出160 小时的部分按照1.5 倍工资发放。属性：每小时的工资、每月工作的小时数 
        • 4 SalesEmployee ：ColaEmployee 的子类，销售人员，工资由月销售额和提成率决定。属性：月销售额、提成率 
        • 5 定义一个类Company，在该类中写一个方法，调用该方法可以打印出某月某个员工的工资数额，写一个测试类TestCompany,在main方法，把若干各种类型的员工放在一个ColaEmployee 数组里，并单元出数组中每个员工当月的工资。
*/


public class Demo15 {
    public static void main(String[] args) {
        TestCompany.main(args);
    }
}

class ColaEmployee {
    private String name;
    private int birthMonth;
    private double salary;

    public String getName() {
        return this.name;
    }

    public ColaEmployee(String name, int birthMonth) {
        this.name = name;
        this.birthMonth = birthMonth;
    }

    public Double getSalary() {
        return this.salary;
    }


    public Double getSalary(int month) {

        if (month == this.birthMonth) {
            return getSalary() + 100;
        } else {
            return getSalary();
        }
    }

}


class SalariedEmployee extends ColaEmployee {
    private double salary;

    //todo 重写toString方法，打印员工详细信息入固定工资员工：张三，月工资300
    public SalariedEmployee(String name, int birthMonth, double salary) {
        super(name, birthMonth);
        this.salary = salary;
    }

    @Override
    public Double getSalary() {
        return this.salary;
    }
}

class HourlyEmployee extends ColaEmployee {
    private double workHours;
    private double perHourSalary;
    private double Salary;

    public HourlyEmployee(String name, int birthMonth, double workHours, double perHourSalary) {
        super(name, birthMonth);
        this.workHours = workHours;
        this.perHourSalary = perHourSalary;
    }

    @Override
    public Double getSalary() {
        double ret;
        double other = 0.0;
        if (workHours > 160) {
            other = (workHours - 160) * 0.5 * perHourSalary;
        }

        this.Salary = this.workHours * perHourSalary + other;
        return this.Salary;
    }
}

class SalesEmployee extends ColaEmployee {
    private double monthSale;
    private double salary = 0.0;
    private double commissionRate;

    public SalesEmployee(String name, int birthMonth, double monthSale, double commissionRate) {
        super(name, birthMonth);
        this.monthSale = monthSale;
        this.commissionRate = commissionRate;
    }

    @Override
    public Double getSalary() {
        this.salary = this.monthSale * this.commissionRate;
        return this.salary;
    }

}

class Company {
    public static void printEmployeeSalary(ColaEmployee a, int month) {
        double salary = a.getSalary(month);
        String toPrintStr = String.format("员工姓名:%s,当月(%s月份)工资为%s元.", a.getName(), month, salary);
        System.out.println(toPrintStr);
    }
}

class TestCompany {
    public static void main(String[] args) {
        SalariedEmployee se1 = new SalariedEmployee("固定工资生日3月月薪300", 3, 300);
        SalariedEmployee se2 = new SalariedEmployee("固定工资生日1月_700", 1, 700);
        HourlyEmployee he1 = new HourlyEmployee("10元每小时工1月生人工作150小时", 1, 150, 10);
        HourlyEmployee he2 = new HourlyEmployee("10元每小时工2月生人工作150小时", 2, 150, 10);
        HourlyEmployee he3 = new HourlyEmployee("10元每小时工1月生人工作200小时", 1, 200, 10);
        HourlyEmployee he4 = new HourlyEmployee("10元每小时工2月生人工作200小时", 2, 200, 10);
        SalesEmployee sae1 = new SalesEmployee("销售额30000提成10%，5月生人", 5, 30000, 0.10);
        SalesEmployee sae2 = new SalesEmployee("销售额30000提成10%，1月生人", 1, 30000, 0.10);

        ColaEmployee[] employess = {se1, se2, he1, he2, he3, he4, sae1, sae2};
        int currentMonth = 1;

        System.out.println(String.format("------当前月份为%s,该月过生日的员工一律工资加100------\n", currentMonth));
        for (ColaEmployee e : employess) {
            Company.printEmployeeSalary(e, currentMonth);
        }
    }
}