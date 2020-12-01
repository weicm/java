package cn.julong.thread.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 适合多于两个线程打印奇偶数
 * Condistion.await/notify & Object.wait/notify必须先获取锁
 */
public class PrintBaseEvenNumReentrantLockCondition {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition[] cs = new Condition[10];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = lock.newCondition();
            new Thread(new Task(lock, cs, i)).start();
        }
    }

    static class Task implements Runnable {
        private ReentrantLock lock;
        private Condition[] cs;
        private int index;

        public Task(ReentrantLock lock, Condition[] cs, int index) {
            this.lock = lock;
            this.cs = cs;
            this.index = index;
        }

        @Override
        public void run() {
            while (ai.get() <= 100) {
                if (index == ai.get() % cs.length) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " " + ai.getAndAdd(1));
                    try {
                        cs[(index+1)%cs.length].signalAll();
                        cs[index].await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }
            lock.lock();
            cs[(index+1)%cs.length].signalAll();
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " quit!");
        }
    }
}
