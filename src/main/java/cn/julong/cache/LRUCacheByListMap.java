package cn.julong.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCacheByListMap<K, V> {
    private Integer maxCapacity;
    private Map<K, Entry<K, V>> cache;
    private Entry<K, V> first;
    private Entry<K, V> last;

    public LRUCacheByListMap(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        cache = new HashMap<>(maxCapacity);
    }

    public V get(K key) {
        Entry<K, V> e = cache.get(key);
        if (e == null) {
            return null;
        }
        remove2first(e);
        return e.value;
    }

    public V put(K key, V value) {
        Entry<K, V> e = cache.get(key);
        // 已存在，更新值，并移动到链表首部，返回旧值
        if (e != null) {
            V oldValue = e.value;
            e.value = value;
            remove2first(e);
            return oldValue;
        }
        // 不存在，先判断是否超过最大容量，超过则删除末尾元素
        if (cache.size() == maxCapacity) {
            Entry<K, V> lastEntry = removeLast();
            cache.remove(lastEntry.key);
        }
        // 不存在，创建节点，放入缓存，并移动到首部，返回空
        e = new Entry<>(key, value);
        cache.put(key, e);
        remove2first(e);
        return null;
    }

    public V remove(K key) {
        Entry<K, V> e = cache.remove(key);
        if (null == e) {
            return null;
        }

        if (e == first) {
            first = first.next;
        }
        if (e == last) {
            last = last.pre;
        }
        e.pre.next = e.next;
        e.next.pre = e.pre;
        return e.value;
    }

    private Entry<K, V> removeLast() {
        if(last == null) {
            return null;
        }
        Entry<K, V> lastEntry = last;
        // last指针前移
        last = last.pre;
        // last元素后指针置空
        if(null != last) {
            last.next = null;
        }
        return lastEntry;
    }

    private void remove2first(Entry<K, V> e) {
        if (null == first || null == last) {
            first = last = e;
            return;
        }
        if (e == first) {
            return;
        }
        if (e == last) {
            last = last.pre;
        }
        // 断开前指针
        if (null != e.pre) {
            e.pre.next = e.next;
        }
        // 断开后指针
        if (null != e.next) {
            e.next.pre = e.pre;
        }
        // 移动到首部
        e.pre = null;
        e.next = first;
        first.pre = e;
        first = e;
    }

    public List<Entry<K, V>> getAll() {
        List<Entry<K, V>> elements = new ArrayList<>(cache.size());
        for (Entry<K, V> p = last; null != p; p = p.pre) {
            elements.add(p);
        }
        return elements;
    }

    public static class Entry<K, V> {
        private K key;
        private V value;
        private Entry pre;
        private Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", key, value);
        }
    }
}
