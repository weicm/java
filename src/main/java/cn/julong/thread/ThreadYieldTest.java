package cn.julong.thread;

/**
 * Created by weicm on 2017/6/13.
 * 线程执行yield，暂时让出cpu执行权，下次调度仍然会争抢cpu执行权
 * 执行样例：
 *      张三三三三三-----2 *****
 *      张三三三三三-----3
 *      张三三三三三-----4 *****
 *      张三三三三三-----5
 *      张三三三三三-----6 *****
 */
public class ThreadYieldTest extends Thread{
    public ThreadYieldTest(String name) {
        super(name);
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {

            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i %2 == 0) {
                System.out.println("" + this.getName() + "-----" + i + " *****");
                this.yield();
            }else
                System.out.println("" + this.getName() + "-----" + i);
        }
    }

    public static void main(String[] args) {
        ThreadYieldTest yt1 = new ThreadYieldTest("张三三三三三");
        ThreadYieldTest yt2 = new ThreadYieldTest("李四");
        yt1.start();
        yt2.start();
    }
}
