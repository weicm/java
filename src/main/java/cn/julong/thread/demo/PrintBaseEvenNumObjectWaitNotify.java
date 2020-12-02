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
                        //这里再次判断小于100的目的，避免打印完最后一个数后阻塞，因为没有不会再有下一个线程再来唤醒本线程了
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
