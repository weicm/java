package cn.julong.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by weicm on 2017/8/30.
 */
public class CyclicBarrierTest {
    /**
     * CyclicBarrier Multi-party barrier
     * 模拟跑步选手在多组比赛中的总成绩
     * 每组比赛起点都一样
     * @param args
     */
    public static void main(String[] args) {
        //选手总耗时，耗时最低的胜利
        Map<String, Integer> costMinute = new ConcurrentHashMap<>();
        //第一个参数：栅栏管理的线程数，如果真实的线程（假设每个线程每轮调用一次await）数小于此数，栅栏将永远不会倾倒
        //第二个参数：栅栏倾倒时执行的任务
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            //每到一个栅栏处就执行一次该任务，汇报选手成绩
            @Override
            public void run() {
                System.out.println("Cost time: " + costMinute);
            }
        });

        //参赛选手，传入栅栏，并在到达时调用await（）等待
        new Thread(new Runner(barrier, costMinute, "选手AAA")).start();
        new Thread(new Runner(barrier, costMinute, "选手BBB")).start();
        new Thread(new Runner(barrier, costMinute, "选手CCC")).start();
    }
    static class Runner implements Runnable{
        private String name;
        private CyclicBarrier barrier;
        private Map<String, Integer> costMinute;
        private Random random = new Random();

        public Runner(CyclicBarrier barrier, Map<String, Integer> costMinute, String name) {
            this.barrier = barrier;
            this.costMinute = costMinute;
            this.name = name;
            costMinute.put(name, 0);
        }

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    Integer minute = random.nextInt(8);
                    costMinute.put(name, costMinute.get(name) + minute);
                    Thread.sleep(minute * 1000);
                    System.out.println(name + " -------arrive at barrier------> " + i);
                    int index = barrier.await();
                    //System.out.println(name + " this circle number: " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
