package cn.julong.thread.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 适合多于两个线程打印奇偶数
 */
public class PrintBaseEvenNumLockSupport {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        Thread main = Thread.currentThread();

        Thread t1 = new Thread(() -> {
            while (ai.get() <= 100) {
                LockSupport.park();
                if (ai.get() <= 100) {
                    System.out.println(Thread.currentThread().getId() + " " +ai.getAndAdd(1));
                    LockSupport.unpark(main);
                }

            }
            System.out.println(Thread.currentThread().getId() + " quit!");
        });
        Thread t2 = new Thread(() -> {
            while (ai.get() <= 100) {
                LockSupport.park();
                if (ai.get() <= 100) {
                    System.out.println(Thread.currentThread().getId() + " " +ai.getAndAdd(1));
                    LockSupport.unpark(main);
                }
            }
            System.out.println(Thread.currentThread().getId() + " quit!");
        });

        t1.start();
        t2.start();
        for (; ai.get() <= 100;) {
            if (ai.get() % 2 == 0) {
                LockSupport.unpark(t1);
                System.out.println(Thread.currentThread().getName() + " unpark t1");
            } else {
                LockSupport.unpark(t2);
                System.out.println(Thread.currentThread().getName() + " unpark t2");
            }
            System.out.println(Thread.currentThread().getName() + " park self");
            LockSupport.park();
        }
        System.out.println(Thread.currentThread().getName() + " quit!");
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
    }
}
