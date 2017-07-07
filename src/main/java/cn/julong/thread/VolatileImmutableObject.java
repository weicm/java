package cn.julong.thread;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by weicm on 2017/7/7.
 * 不用同步锁解决线程安全问题的方法：
 *      使用Volatile类型来发布不可变对象，解决多个非原子操作的线程安全问题
 * 引用Java并发编程实战中原话解释：
 *      通过使用包含多个状态变量的容器对象来维持不变性条件，并使用一个volatile类型的引用来确保可见性，从而是的SafeFactorizer在没有显示的使用锁的情况下仍然是线程安全的
 */
public class VolatileImmutableObject {
    /**
     * 线程安全的因式分解示例
     */
    static class SafeFactorizer{
        private volatile CacheValue cache;

        public Integer[] getFactors(Integer num) {
            Integer[] factors = cache.getFactors(num);
            //缓存有，直接返回
            if (null != factors) {
                return factors;
            }
            //缓存没有，计算，并缓存下来再返回计算结果
            Integer[] fs = factors(num);
            //将多个操作用volatile变量的不可变对象封装起来
            cache = new CacheValue(num, fs);
            return fs;
        }
        static class CacheValue {
            private Integer cacheNumber;
            private Integer[] cacheFactors;

            public CacheValue(Integer number, Integer[] factors) {
                this.cacheNumber = number;
                this.cacheFactors = Arrays.copyOf(factors, factors.length);
            }

            Integer[] getFactors(Integer num) {
                if (null == cacheNumber || !cacheNumber.equals(num))
                    return null;
                else
                    //确保发布出去的结果被别的线程更改的前提下缓存依旧是正确的
                    return Arrays.copyOf(cacheFactors, cacheFactors.length);
            }
        }
    }
    /**
     * 线程不安全因式分解示例
     */
    static class UnsafeFactorizer {
        //缓存的当前因式分解的数字
        private Integer cacheNumber;
        //缓存当前因式分解的质因子
        private Integer[] cacheFactors;

        /**
         * 获取数字的因式分解的质因子
         * @param num
         * @return
         */
        public Integer[] getFactors(Integer num) {
            //缓存有，直接返回
            if (num.equals(cacheNumber)) {
                return cacheFactors;
            }
            //缓存没有，计算，并缓存下来再返回计算结果
            //下面两行为多次操作，在多线程中存在安全问题
            cacheNumber = num;
            cacheFactors = factors(num);

            return cacheFactors;
        }
    }

    /**
     * 因式分解
     * 此方法是线程安全的，因为方法使用的变量都是栈封闭的
     * @param num
     * @return
     */
    static Integer[] factors(Integer num) {
        ArrayList<Integer> list = new ArrayList<>();
        // 定义最小素数
        int i = 2;
        // 进行辗转相除法
        while (i <= num) {
            // 若num 能整除 i ，则i 是num 的一个因数
            if (num % i == 0) {
                list.add(i);
                num = num / i;
                i = 2;
            } else {
                i++;
            }
        }
        return list.toArray(new Integer[list.size()]);
    }
}
