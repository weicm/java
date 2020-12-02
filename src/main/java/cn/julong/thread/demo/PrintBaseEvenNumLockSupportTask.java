package cn.julong.thread.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 适合多于两个线程打印奇偶数
 * 工作线程相互协调
 */
public class PrintBaseEvenNumLockSupportTask {
    private static AtomicInteger ai = new AtomicInteger(1);


    public static void main(String[] args) {

        Thread[] ts = new Thread[5];

        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(new Task(ts, i));
        }

        for (Thread t : ts) {
            t.start();
        }

    }

    static class Task implements Runnable {
        private Thread[] ts;
        private int i;

        public Task(Thread[] ts, int i) {
            this.ts = ts;
            this.i = i;
        }

        @Override
        public void run() {
            while (ai.get() <= 100) {
                if (i == ai.get() % ts.length) {
                    //不能再这里直接自增，否则就没有顺序保证，因为LockSupport.unpark操作会提前生效
                    //例如：只有两个线程，0线程执行到这里自增，这时已经唤醒1线程，0线程和1线程竞争输出，就没有顺序了
                    System.out.println(Thread.currentThread().getName() + " " + ai.get());
                    //先唤醒下一个线程
                    LockSupport.unpark(ts[(i + 1) % ts.length]);
                    //再触发自增
                    ai.getAndIncrement();
                    LockSupport.park(this);
                }
            }
            LockSupport.unpark(ts[(i + 1) % ts.length]);
        }
    }
}
