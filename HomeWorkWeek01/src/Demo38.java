//线程

// 某公司组织年会,会议入场时有两个入口,在入场时每位员工都能获取一张双色球彩票,假设公司有100个员工,利用多线程模拟年会入场过程,
// 并分别统计每个入口入场的人数,以及每个员工拿到的彩票的号码。线程运行后打印格式如下：
//        • 编号为: 2 的员工 从后门 入场! 拿到的双色球彩票号码是: [17, 24, 29, 30, 31, 32, 07]
//        • 编号为: 1 的员工 从后门 入场! 拿到的双色球彩票号码是: [06, 11, 14, 22, 29, 32, 15]
//        • //.....
//        • 从后门入场的员工总共: 13 位员工
//        • 从前门入场的员工总共: 87 位员工"

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo38 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<EmployEnterThread> employs = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            EmployEnterThread eet = new EmployEnterThread(i);
            employs.add(eet);
        }
        for (EmployEnterThread i : employs
        ) {
            i.start();
        }
        for (EmployEnterThread i : employs
        ) {
            i.join();
        }
        System.out.println("全部员工已入场");
        System.out.println(String.format("从前门入场的员工总共%s人\n从后门入场的员工总共%s人", DoorCounter.doorFront, DoorCounter.doorBack));
    }


}

class Lottery {
    public static String getLottery() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            Random r = new Random();
            int num = r.nextInt(33) + 1;
            list.add(num);
        }
        return list.toString();
    }
}

class DoorCounter {
    public static int doorFront;
    public static int doorBack;
    public static final Object lockDoorFront = new Object();
    public static final Object lockDoorBack = new Object();

}

class EmployEnterThread extends Thread {
    private int id;

    EmployEnterThread(int id) {
        this.id = id;
    }

    public void run() {
        Random r = new Random();
        int randomNum = r.nextInt(10);
        Object lock;
        String doorName = "";
        int sleepN = 100 * randomNum;
        //前门 进入速度慢，单次进入耗时长
        if (randomNum >= 7) {
            lock = DoorCounter.lockDoorFront;
            doorName = "前门";

        } else {

            lock = DoorCounter.lockDoorBack;
            doorName = "后门";
        }
        synchronized (lock) {
            String msg = String.format("编号为:%s的员工从(%s)入场! 拿到的双色球彩票号码是:%s,进入及抽奖耗时为%s", id, doorName, Lottery.getLottery(), sleepN);
            try {
                Thread.sleep(sleepN);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (doorName.equals("前门")) {
                DoorCounter.doorFront += 1;
            } else {
                DoorCounter.doorBack += 1;
            }
            System.out.println(msg);
        }
    }
}

