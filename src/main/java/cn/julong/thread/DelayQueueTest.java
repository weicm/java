package cn.julong.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DelayQueueTest {
    private static AtomicLong al = new AtomicLong();
    private static DelayQueue<Task> dq = new DelayQueue();

    private static ExecutorService es = new ThreadPoolExecutor(
            0, 1,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(1),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws Exception {
        dq.put(new Task("t1", dq));
        dq.put(new Task("t2", dq));
        dq.put(new Task("t3", dq));

        while (true) {
            es.submit(dq.take());
        }

    }

    static class Task implements Delayed, Runnable {
        private static final long intervalMills = 3000;
        private String name = "-";
        private DelayQueue<Task> dq;
        private int cnt = 0;
        private long mills = System.currentTimeMillis() + al.getAndAdd(1000);


        public Task(String name, DelayQueue<Task> dq) {
            this.name = name;
            this.dq = dq;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public long getMills() {
            return mills;
        }

        public void setMills(long mills) {
            this.mills = mills;
        }

        public void reset() {
            this.mills = System.currentTimeMillis() + intervalMills;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    ", cnt=" + cnt +
                    ", mills=" + mills +
                    "thread=" + Thread.currentThread().getName() +
                    '}';
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(mills - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public void run() {
            if (cnt == 5) {
                return;
            }
            cnt += 1;
            reset();
            dq.put(this);
            System.out.println(this);
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(3));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
