package cn.julong.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class ReferenceTest {

    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    public static void checkQueue() {
        Reference<? extends VeryBig> ref = null;
        while ((ref = rq.poll()) != null) {
            if (ref != null) {
                System.out.println("In queue: " + ((VeryBigWeakReference) (ref)).id);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        //weakRefTest();
        //softRefTest();
        weakMapTest();
    }

    static void weakMapTest() throws Exception{
        WeakHashMap<VeryBig, Integer> weakMap = new WeakHashMap<>();
        for (int i=0, n=3; i<n; i++) {
            weakMap.put(new VeryBig("Weak-" + i), i);
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        //WeakHashMap中被扫描到的记录会自动清除
        System.out.println("weakMap size: " + weakMap.size());
    }

    static void softRefTest() throws Exception{
        int size = 3;
        LinkedList<SoftReference<VeryBig>> softList = new LinkedList<SoftReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            softList.add(new VeryBigSoftReference(new VeryBig("Soft " + i), rq));
            System.out.println("Just created soft: " + softList.getLast());

        }
        System.out.println(rq.poll());

        //软引用对象此时不会被清除, 因为尚有足够内存, GC只有在没有足够内存时才会清除只有软引用指向的对象
        System.gc();
        try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkQueue();
        System.out.println("softList size1: " + softList.size());
        softList.clear();
        //所有VeryBig对象将会被清除,因为只有弱引用指向它们;然后所有SoftReference对象将会被清除,因为这些对象已经没有对象再引用它们了;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("softList size2: " + softList.size());
    }

    static void weakRefTest() throws Exception{
        int size = 3;
        LinkedList<WeakReference<VeryBig>> weakList = new LinkedList<WeakReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            weakList.add(new VeryBigWeakReference(new VeryBig("Weak " + i), rq));
            System.out.println("Just created weak: " + weakList.getLast());

        }
        System.out.println(rq.poll());
        //所有VeryBig对象在下次GC回收周期将会被清理
        System.gc();
        try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkQueue();
        System.out.println("weakList size1: " + weakList.size());
        weakList.clear();
        //此时WeakReference对象被清除,因为没有对象引用它们了
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("weakList size2: " + weakList.size());
    }
}



