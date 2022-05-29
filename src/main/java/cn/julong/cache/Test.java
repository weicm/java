package cn.julong.cache;


public class Test {
    public static void main(String[] args) {
        // testByLinkedHashMap1();

        testByListMap();
    }

    private static void testByListMap() {
        LRUCacheByListMap<Integer, String> cache = new LRUCacheByListMap<>(5);

        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "b");
        cache.put(4, "c");
        cache.put(5, "d");
        cache.put(6, "e");

        System.out.println(cache.getAll());

        System.out.println(cache.get(3));

        System.out.println(cache.getAll());

        System.out.println(cache.remove(4));

        System.out.println(cache.getAll());
    }

    private static void testByLinkedHashMap1() {
        LRUCacheByLinkedHashMap1<Integer, String> cache = new LRUCacheByLinkedHashMap1<>(5);

        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "b");
        cache.put(4, "c");
        cache.put(5, "d");
        cache.put(6, "e");

        System.out.println(cache.getAll());

        System.out.println(cache.get(3));

        System.out.println(cache.getAll());
    }
}
