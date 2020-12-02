package cn.julong.thread.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 适合多于两个线程打印奇偶数
 */
public class PrintBaseEvenNumObjectSynchronized {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        Object locker = 1;

        new Thread(() -> {
            while (ai.get() <= 100) {
                if (ai.get() % 2 == 0 && ai.get() <= 100) {
                    synchronized (locker) {
                        System.out.println(Thread.currentThread().getName() + " " +ai.getAndAdd(1));
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (ai.get() <= 100) {
                if (ai.get() % 2 == 1 && ai.get() <= 100) {
                    synchronized (locker) {
                        System.out.println(Thread.currentThread().getName() + " " +ai.getAndAdd(1));
                    }
                }
            }
        }).start();


    }
}
