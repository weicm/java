package cn.julong.thread;

import java.util.concurrent.*;

/**
 * Created by weicm on 2018/11/2.
 */
public class InterruptTest {
    static ExecutorService es = Executors.newSingleThreadExecutor();
    public static void main(String[] args) {

        try {
            /*Object o = timedRun(() -> {
                return "OK";
            }, 10, TimeUnit.SECONDS);
            System.out.println(o);*/

            /*Object o = timedRun(() -> {
                TimeUnit.SECONDS.sleep(2);
                return "TIMEOUT";
            }, 1, TimeUnit.SECONDS);
            System.out.println(o);*/

            Thread ct = Thread.currentThread();
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ct.interrupt();
            }).run();
            Object o = timedRun(() -> {
                TimeUnit.SECONDS.sleep(3);
                return "ITERRUPTED";
            }, 5, TimeUnit.SECONDS);
            System.out.println(o);
        } finally {
            es.shutdown();
        }

    }

    static <T> T timedRun(Callable task, long time, TimeUnit timeUnit) {
        Future<?> future = es.submit(task);
        T result = null;
        try {
            result = (T) future.get(time, timeUnit);
        } catch (InterruptedException e) {
            System.out.println("Task is interrupted!");
        } catch (TimeoutException e) {
            System.out.println("Task is time out!");
        } catch (ExecutionException e) {
            throw e;
        } finally {
            future.cancel(true);
            return result;
        }
    }
}
