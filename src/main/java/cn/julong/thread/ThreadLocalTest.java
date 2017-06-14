package cn.julong.thread;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by weicm on 2017/6/14.
 * ThreadLocal.initValue方法返回当前线程的局部变量
 * 这个方法将在本地线程第一次调用ThreadLocal.get方法时被调用，除非之前调用过ThreadLocal.set方法
 * ThreadLocal.initValue方法一般值调用一次，除非在调用了ThreadLocal.remove方法后再次调用ThreadLocal.get方法，则将再次调用ThreadLocal.initValue方法
 *
 * 在当前线程中操作ThreadLocal.get和ThreadLocal.set方法都是操作线程相关的局部变量
 *
 * 测试样例：
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 *      11--6
 *      12--7
 */
public class ThreadLocalTest extends Thread {
    private static final Random random = new Random();
    //不需要为每个线程都定义一个ThreadLocal变量
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            //为每个线程初始化一个随机整数
            return random.nextInt(10);
        }
    };

    @Override
    public void run() {
        //每个线程打印10次线程本地变量
        IntStream.range(0, 10).forEach(i -> {
            System.out.println(Thread.currentThread().getId() + "--" + threadLocal.get());
        });
    }

    public static void main(String[] args) {
        //创建并启动两个测试线程
        new ThreadLocalTest().start();
        new ThreadLocalTest().start();
    }
}
