package cn.julong.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LRUCacheByLinkedHashMap1<K, V> {
    private static float loadFactor = 0.75f;
    private Integer maxCapacity;
    private Map<K, V> cache;

    public LRUCacheByLinkedHashMap1(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        cache = new LinkedHashMap((int) (Math.ceil(maxCapacity / loadFactor) + 1), loadFactor, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxCapacity;
            }
        };
    }


    public V get(K key) {
        return cache.get(key);
    }

    public V put(K key, V o) {
        return cache.put(key, o);
    }

    public V remove(K key) {
        return cache.remove(key);
    }

    public Integer size() {
        return cache.size();
    }

    public Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
    }

    public void clear() {
        cache.clear();
    }

    public static void main(String[] args) {

    }
}
