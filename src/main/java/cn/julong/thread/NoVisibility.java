package cn.julong.thread;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by weicm on 2017/6/14.
 */
public class NoVisibility {
    public static class ThreeStooges {
        private final Set<String> stooges = new HashSet<>();

        public ThreeStooges() {
            stooges.add("Moe");
            stooges.add("Larry");
            stooges.add("Curly");
            init(this);
        }

        public boolean isStooge(String name) {
            return stooges.contains(name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreeStooges ts = new ThreeStooges();
        System.out.println(ts.isStooge("Moe"));
    }

    static void init(ThreeStooges ts) {
        ts.stooges.clear();
    }
}
