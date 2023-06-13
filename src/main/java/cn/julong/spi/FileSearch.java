package cn.julong.spi;

import java.util.Arrays;
import java.util.List;

public class FileSearch implements Search{
    @Override
    public List<String> search() {
        return Arrays.asList("f1", "f2");
    }
}
