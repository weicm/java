package cn.julong.spi;

import java.util.Arrays;
import java.util.List;

public class DbSearch implements Search{
    @Override
    public List<String> search() {
        return Arrays.asList("dbx");
    }
}
