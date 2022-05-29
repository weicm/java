package cn.julong.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheByLinkedHashMap2<K, V> extends LinkedHashMap<K, V> {
    private static float loadFactor = 0.75f;
    private Integer maxCapacity;

    public LRUCacheByLinkedHashMap2(Integer maxCapacity) {
        super((int) (Math.ceil(maxCapacity / loadFactor) + 1), loadFactor, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    public static void main(String[] args) {

    }
}
