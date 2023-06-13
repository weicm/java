package cn.julong.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIDriver {
    public static void main(String[] args) {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);

        Iterator<Search> it = loader.iterator();
        while (it.hasNext()) {
            Search service = it.next();
            System.out.println(service.search());
        }
    }
}
