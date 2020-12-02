package cn.julong.thread.demo;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 适合多于两个线程打印奇偶数
 */
public class PrintBaseEvenNumObjects {
    private static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {

        Object[] ls = new Object[3];
        for (int i = 0; i < ls.length; i++) {
            ls[i] = i;
            new Thread(new Task(ls, i)).start();
        }
    }

    static class Task implements Runnable {
        private Object[] ls;
        private int index;

        public Task(Object[] ls, int index) {
            this.ls = ls;
            this.index = index;
        }

        @Override
        public void run() {
            while (ai.get() <= 100) {
                if (ai.get() % ls.length == index) {
                    synchronized (ls[index]) {
                        //不能在这里直接自增，否则会死锁
                        // 例如：只有两个线程，当前线程0运行到这里时获取了0锁，此时已经触发1线程执行并获取1锁，0线程下一步需要唤醒1线程时也需要1锁，就造成了死锁
                        System.out.println(Thread.currentThread().getName() + " " + ai.get());
                        //先唤醒下一个线程
                        synchronized (ls[(index+1)%ls.length]) {
                            ls[(index+1)%ls.length].notifyAll();
                        }
                        //唤醒之后，再执行自增
                        ai.getAndIncrement();
                        try {
                            ls[index].wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            synchronized (ls[(index+1)%ls.length]) {
                ls[(index+1)%ls.length].notifyAll();
            }
        }
    }
}
