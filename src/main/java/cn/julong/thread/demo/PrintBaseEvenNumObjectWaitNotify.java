package cn.julong.thread.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 只适合两个线程打印奇偶数
 */
public class PrintBaseEvenNumObjectWaitNotify {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        Object locker = 1;

        new Thread(() -> {
            while (ai.get() <= 100) {
                synchronized (locker) {
                    System.out.println(Thread.currentThread().getName() + " " +ai.getAndAdd(1));
                    locker.notifyAll();
                    try {
                        if (ai.get() <= 100)
                            locker.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (ai.get() <= 100) {
                synchronized (locker) {
                    System.out.println(Thread.currentThread().getName() + " " +ai.getAndAdd(1));
                    locker.notifyAll();
                    try {
                        if (ai.get() <= 100)
                            locker.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
