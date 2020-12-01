package cn.julong.thread.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 适合多于两个线程打印奇偶数
 */
public class PrintBaseEvenNumReentrantLock {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            while (ai.get() <= 100) {
                if (ai.get() % 3 == 1 && ai.get() <= 100) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (ai.get() <= 100) {
                if (ai.get() % 3 == 2 && ai.get() <= 100) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            while (ai.get() <= 100) {
                if (ai.get() % 3 == 0 && ai.get() <= 100) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                    lock.unlock();
                }
            }
        }).start();
    }
}
