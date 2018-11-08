package cn.julong.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Created by weicm on 2017/8/30.
 */
public class ExchangerTest {
    /**
     * Exchange又叫 Two-party barrier
     * 模拟两个家伙到指定地点交换东西
     * @param args
     */
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new Guy(exchanger, "GuyAAA", 111)).start();
        new Thread(new Guy(exchanger, "GuyBBB", 222)).start();
    }
    static class Guy implements Runnable{
        private Exchanger<Integer> exchanger;
        private String name;
        private Integer holder;

        public Guy(Exchanger<Integer> exchanger, String name, Integer holder) {
            this.exchanger = exchanger;
            this.name = name;
            this.holder = holder;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(5) * 1000);
                System.out.println(name + " ---before exchange--> " + holder);
                holder= exchanger.exchange(holder);
                System.out.println(name + " ---after exchange--> " + holder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
