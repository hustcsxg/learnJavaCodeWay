/*
编写三个系别的学生类：英语系，计算机系，文学系（要求通过继承学生类）
        • 1 各系有以下成绩： 
        • 英语系： 演讲，期末考试，期中考试； 
        • 计算机系：操作能力，英语写作，期中考试，期末考试； 
        • 文学系： 演讲，作品，期末考试，期中考试; 

        • 2 各系总分评测标准： 
        • 英语系： 演讲 50% 
        • 期末考试 25% 
        • 期中考试 25% 

        • 计算机系： 操作能力 40% 
        • 英语写作 20% 
        • 期末考试 20% 
        • 期中考试 20% 

        • 文学系： 演讲 35% 
        • 作品 35% 
        • 期末考试 15% 
        • 期中考试 15% 

        • 3 定义一个可容纳5个学生的学生类数组，使用随机数给该数组装入各系学生的对象，然后按如下格式输出数组中的信息： 
        • 学号:XXXXXXXX 姓名：XXX 性别：X 年龄：XX 综合成绩：XX
*/


public class Demo16 {
    public static void main(String[] args) {
        Student[] students = mockStudent();
        for (Student i : students) {
            System.out.println(i.printStudentInfo());
        }
    }

    public static Student[] mockStudent() {
        CsStudent cs1 = new CsStudent("Cs张三", "CS-001", "男", 20);
        cs1.setScores(90.0, 85.5, 70.0, 80.5);
        CsStudent cs2 = new CsStudent("Cs李四", "CS-002", "女", 19);
        cs1.setScores(80.0, 70.5, 60.0, 80.0);
        EnglishStudent eng1 = new EnglishStudent("Eric", "EN-001", "男", 18);
        eng1.setScores(80.0, 90.0, 100.0);
        ArcStudent arc1 = new ArcStudent("李清照", "AR-001", "女", 23);
        arc1.setScores(100.0, 80.0, 90.0, 85.0);
        ArcStudent arc2 = new ArcStudent("莎士比亚", "AR-001", "男", 22);
        arc1.setScores(90.0, 90.0, 90.0, 100.0);

        Student[] students = {cs1, arc2, eng1, arc1, cs2};
        return students;
    }
}

class Student {
    private String userNo;
    private String name;
    private String male;
    private int age;
    private Double score = 0.0;

    public Student(String name, String userNo, String male, int age) {
        this.name = name;
        this.userNo = userNo;
        this.age = age;
        this.male = male;
        this.score = 0.0;
    }

    public void setScores() {
    }

    public Double getScore() {
        return 0.0;
    }

    public String printStudentInfo() {
        String ret = String.format("学号:%s 姓名:%s 性别:%s 年龄:%s 综合成绩:%s", this.userNo, this.name, this.male, this.age, this.getScore());

        return ret;
    }
}

class EnglishStudent extends Student {
    private double speech = 0.0;
    private double qm = 0.0;
    private double qz = 0.0;

    public EnglishStudent(String name, String userNo, String male, int age) {
        super(name, userNo, male, age);
    }

    public void setScores(double speech, double qm, double qz) {
        this.speech = speech;
        this.qz = qz;
        this.qm = qm;
    }

    @Override
    public Double getScore() {
        double ret = 0.0;
        ret = this.speech * 0.50 + this.qz * 0.25 + this.qm * 0.25;
        return ret;
    }
}

class CsStudent extends Student {
    private double cz;
    private double englishWrite;
    private double qm;
    private double qz;

    public CsStudent(String name, String userNo, String male, int age) {
        super(name, userNo, male, age);
    }

    @Override
    public Double getScore() {
        double ret = 0.0;
        ret = this.cz * 0.40 + this.englishWrite * 0.20 + this.qz * 0.2 + this.qm * 0.2;
        return ret;
    }

    public void setScores(double cz, double englishWrite, double qm, double qz) {
        this.cz = cz;
        this.englishWrite = englishWrite;
        this.qz = qz;
        this.qm = qm;
    }
}

class ArcStudent extends Student {
    private double speech = 0.0;
    private double zp = 0.0;
    private double qm = 0.0;
    private double qz = 0.0;

    @Override
    public Double getScore() {
        double ret = 0.0;
        ret = this.qm * 0.15 + this.qz * 0.15 + this.speech * 0.35 + this.zp * 0.35;
        return ret;
    }

    public ArcStudent(String name, String userNo, String male, int age) {
        super(name, userNo, male, age);
    }

    public void setScores(double speech, double zp, double qm, double qz) {
        this.speech = speech;
        this.zp = zp;
        this.qz = qz;
        this.qm = qm;
    }
}