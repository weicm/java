package cn.julong.thread;

/**
 * Created by weicm on 2018/11/6.
 */
public class DoubleLock {
    public static void main(String[] args) {
        Object lock = new Object();

        //证明Java内置锁的可重入性
        synchronized (lock) {
            synchronized (lock) {
                System.out.println("OK");
            }
        }
    }
}
