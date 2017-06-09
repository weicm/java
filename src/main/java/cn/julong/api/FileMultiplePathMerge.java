package cn.julong.api;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by weicm on 2017/6/7.
 */
public class FileMultiplePathMerge {
    static String SEPERATOR = "\r\n";

    public static void main(String[] args) throws Exception {
        /*writeAscIntFile("C:\\tmp\\data\\a.txt", 8888888);
        writeAscIntFile("C:\\tmp\\data\\b.txt", 5333222);
        writeAscIntFile("C:\\tmp\\data\\c.txt", 1099999);*/



        String outFile = "C:\\tmp\\data\\file.txt";
        String[] inFiles = {
                "C:\\tmp\\data\\a.txt",
                "C:\\tmp\\data\\b.txt",
                "C:\\tmp\\data\\c.txt"
        };
        fileMultiblePathMerge(outFile, inFiles);
    }

    public static void writeAscIntFile(String file, int lines) throws IOException {
        File f = new File(file);
        if(!f.exists())
            f.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        Integer num = 0;
        Random random = new Random();
        for (int i=0; i<lines; i++) {
            num = 10 * i + random.nextInt(10);
            out.write(num.toString());
            out.newLine();
        }
        out.close();
    }

    public static void fileMultiblePathMerge(String outFile, String[] inFiles) throws Exception {
        File oFile = new File(outFile);
        if(!oFile.exists())
            oFile.createNewFile();
        //输出化指针
        BufferedWriter out = new BufferedWriter(new FileWriter(oFile));
        List<BufferedReader> ins = Arrays.stream(inFiles).map(inFile -> {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(inFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return in;
        }).collect(Collectors.toList());
        try {
            //记录每路最小值
            HashMap<BufferedReader, Integer> minVals = new HashMap<>();

            //下一行非空字符串
            String nextLine = null;

            //初始化
            for (BufferedReader br : ins) {
                nextLine = readNotEmptyLine(br);
                minVals.put(br, null == nextLine ? null : Integer.valueOf(nextLine));
            }

            //开头值最小的路径，默认取第一条
            BufferedReader minStartValuePath = ins.get(0);
            while (true) {
                List<BufferedReader> notOverIns = ins.stream().filter(br -> minVals.get(br) != null).collect(Collectors.toList());
                //归并结束则跳出循环
                if (notOverIns.size() == 0)
                    break;

                //求所有路的开头值最小的路径
                minStartValuePath = notOverIns.remove(0);

                for (BufferedReader br : notOverIns) {
                    if (minVals.get(br) < minVals.get(minStartValuePath)) {
                        minStartValuePath = br;
                    }
                }

                out.write(minVals.get(minStartValuePath).toString());
                out.newLine();

                nextLine = readNotEmptyLine(minStartValuePath);
                minVals.put(minStartValuePath, null == nextLine ? null : Integer.valueOf(nextLine));
            }
        } finally {
            //关闭所有的流
            if (null != out)
                out.close();
            ins.stream().forEach(br -> {
                try {
                    if (null != br)
                        br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static String readNotEmptyLine(BufferedReader br) throws IOException {
        String line = null;
        while (true) {
            line = br.readLine();
            if (null == line)
                break;
            if (!line.trim().equals("")) {
                line = line.trim();
                break;
            } else
                continue;
        }
        return line;
    }
}
