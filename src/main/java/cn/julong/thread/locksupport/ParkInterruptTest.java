package cn.julong.thread.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 说明: 可以看到，在主线程调用park阻塞后，在myThread线程中发出了中断信号，此时主线程会继续运行，也就是说明此时interrupt起到的作用与unpark一样。
 */
public class ParkInterruptTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }

    static class MyThread extends Thread {
        private Object object;

        public MyThread(Object object) {
            this.object = object;
        }

        public void run() {
            System.out.println("before interrupt");
            try {
                // 休眠3s
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread thread = (Thread) object;
            // 中断线程
            thread.interrupt();
            System.out.println("after interrupt");
        }
    }
}