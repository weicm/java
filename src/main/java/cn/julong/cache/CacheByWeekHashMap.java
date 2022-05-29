package cn.julong.cache;

import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CacheByWeekHashMap<K, V> {
    private static final float loadFactor = 0.75f;
    private Integer maxCapacity;
    private ConcurrentHashMap<K, V> eden;
    private WeakHashMap<K, V> longterm;

    public CacheByWeekHashMap(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        int finalCapacity = (int) Math.ceil(maxCapacity / loadFactor) + 1;
        eden = new ConcurrentHashMap<>(finalCapacity, loadFactor);
        longterm = new WeakHashMap<>(finalCapacity);
    }

    public V get(K key) {
        // 先从新生代获取
        V val = eden.get(key);
        // 新生代没有，则从老年代获取，并存入新生代
        if (null == val) {
            val = longterm.get(key);
            if (null != val) {
                eden.put(key, val);
            }
        }
        return val;
    }

    public V put(K key, V value) {
        // 如果新生代不存在key
        if (!eden.contains(key)) {
            // 则判断容量是否超限
            if (eden.size() >= maxCapacity) {
                // 超限则把新生代对象移入老年年代，并清空新生代对象
                longterm.putAll(eden);
                eden.clear();
            }

        }
        return eden.put(key, value);
    }



}
