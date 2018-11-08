package cn.julong.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by weicm on 2018/11/1.
 */
public class JoinTest {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(() -> {
            System.out.println("OK");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join(1000);
        System.out.println("over");
        System.out.println(thread.isAlive());
    }
}
