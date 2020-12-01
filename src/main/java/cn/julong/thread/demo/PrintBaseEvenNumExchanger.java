package cn.julong.thread.demo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 只适合两个线程打印奇偶数
 */
public class PrintBaseEvenNumExchanger {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                while (ai.get() <= 100) {
                    if (ai.get() % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                        exchanger.exchange(0);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (ai.get() <= 100) {
                    if (ai.get() % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                        exchanger.exchange(1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
