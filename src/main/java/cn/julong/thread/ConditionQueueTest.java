package cn.julong.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weicm on 2017/11/29.
 */
public class ConditionQueueTest {

    public static void main(String[] args) throws Exception {
        Object o1 = new Object();
        Object o2 = new Object();
        o1.wait();
        o1.notify();

        //ReentrantLock

    }
}
