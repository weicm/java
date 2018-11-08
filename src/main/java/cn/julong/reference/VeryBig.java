package cn.julong.reference;

class VeryBig {
    public String id;
    // 占用空间,让线程进行回收  
    byte[] b = new byte[2 * 1024];

    public VeryBig(String id) {
        this.id = id;
    }

    protected void finalize() {
        System.out.println("Finalizing VeryBig " + id);
    }
}