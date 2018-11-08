package cn.julong.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

class VeryBigSoftReference extends SoftReference<VeryBig> {
    public String id;

    public VeryBigSoftReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
        super(big, rq);
        this.id = big.id;
    }

    protected void finalize() {
        System.out.println("Finalizing VeryBigSoftReference " + id);
    }
}  