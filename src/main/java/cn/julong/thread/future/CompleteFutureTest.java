package cn.julong.thread.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompleteFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            sleep();
            say("洗水壶");
            sleep();
            say("烧开水");
            return "圣水";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            sleep();
            say("洗茶壶");
            sleep();
            say("洗茶杯");
            sleep();
            say("拿茶叶");
            return "龙井";
        });

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (v1, v2) -> {
            String chashui = String.format("%s-%s", v1, v2);
            say("泡茶: " + chashui);
            return chashui;
        });

        CompletableFuture.allOf(cf1, cf2).join();

        say(cf3.join());
    }

    static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(4));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void say(String words) {
        System.out.println(String.format("%s @ %s", Thread.currentThread().getName(), words));
    }
}
