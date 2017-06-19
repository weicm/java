package cn.julong.practice;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by weicm on 2017/6/19.
 */
public class YangHuiTriangle {
    public static void main(String[] args) {
        ArrayList<Integer[]> lines = printYangHuiTriangle(15);
        lines.forEach(line -> System.out.println(Arrays.asList(line)));
    }

    public static ArrayList<Integer[]> printYangHuiTriangle(Integer lineNum) {
        ArrayList<Integer[]> lines = new ArrayList<>();
        for (int i = 0; i < lineNum; i++) {
            Integer[] line = new Integer[i + 1];
            lines.add(line);
            if (i > 0) {
                Integer[] previusLine = lines.get(i - 1);
                for (int j = 0; j < line.length; j++) {
                    if (0 == j)
                        line[j] = previusLine[j];
                    else if (j > 0 && j < line.length - 1)
                        line[j] = previusLine[j - 1] + previusLine[j];
                    else
                        line[j] = previusLine[j - 1];
                }
            } else {
                line[0] = 1;
            }
        }
        return lines;
    }
}
