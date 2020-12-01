package cn.julong.thread.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 适合多于两个线程打印奇偶数
 */
public class PrintBaseEvenNumSemaphore {
    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger(1);
        Semaphore s = new Semaphore(1);
        new Thread(() -> {
            while (ai.get() <= 100) {
                try {
                    if (ai.get() % 2 == 0 && ai.get() <= 100) {
                        s.acquire(1);
                        System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                        s.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (ai.get() <= 100) {
                try {
                    if (ai.get() % 2 == 1 && ai.get() <= 100) {
                        s.acquire(1);
                        System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                        s.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
