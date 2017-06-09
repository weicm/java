package cn.julong.api;

import java.io.*;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by weicm on 2017/6/5.
 */
public class RandomReadFile {
    private static byte[] SEPARATOR = "\r\n".getBytes(Charset.forName("UTF-8"));

    //查找行开始位置
    static long findLineStartIndex(RandomAccessFile r) throws IOException {
        //保存当前位置
        long currentIndex = r.getFilePointer();
        //否则往前推 SEPARATOR.length 个字节同过找行结尾位置来当做当前行开始位置
        long endIndex = currentIndex;
        for(int i=1; endIndex >= currentIndex; i++) {
            long decrease = currentIndex - SEPARATOR.length * i;
            if (decrease < 0)
                return 0;
            r.seek(decrease);
            endIndex = findLineEndIndex(r);
        }
        //恢复文件当前位置
        r.seek(currentIndex);
        return endIndex;
    }
    //查找行结束位置
    static long findLineEndIndex(RandomAccessFile r) throws IOException {
        long currentIndex = r.getFilePointer();
        //行分隔符结束位置，默认为文件结束位置
        long index = r.length();

        byte[] buf = new byte[SEPARATOR.length];
        while (true) {
            //一次去读 SEPARATOR.length 个字节
            int length = r.read(buf);

            //行尾不在文件结尾
            if (length == SEPARATOR.length) {
                //找到文件标志，默认找到
                boolean finded = true;
                for (int i = 0; i< buf.length; i++) {
                    //没找到
                    if(buf[i] != SEPARATOR[i]) {
                        finded = false;
                        break;
                    }
                }
                //找到则记录行结束位置
                if (finded) {
                    index = r.getFilePointer();
                    break;
                }else {//没找到，则指针后移一位，继续循环
                    r.seek(r.getFilePointer() - SEPARATOR.length + 1);
                }
            }else {//文件结尾就是行尾
                break;
            }
        }
        //恢复文件当前位置
        r.seek(currentIndex);
        return index;
    }

    //获取当前部分的起始行位置
    static long getPointer(RandomAccessFile r, int parts, int whichPart) throws IOException {
        long pointer = r.length() / parts * whichPart;
        r.seek(pointer);
        return findLineStartIndex(r);
    }


    //打印RandomAccessFile指针所在的行
    static void printCurrentLine(RandomAccessFile r, long pointer) throws IOException {
        long start = pointer;
        r.seek(start);
        System.out.println(r.getFilePointer());
        long startIndex = findLineStartIndex(r);
        long endIndex = findLineEndIndex(r);
        r.seek(startIndex);
        System.out.println(startIndex + "--" + endIndex);
        byte[] bytes = new byte[(int) (endIndex - startIndex)];
        r.read(bytes);
        System.out.println(new String(bytes));
    }

    //打印指定部分的内容
    static void pringPartContent(RandomAccessFile r, long[] pointers, int partIndex) throws IOException {
        System.out.println("======================" + partIndex + "=======================");
        long endPointer = r.length();
        if (partIndex < pointers.length - 1) {
            endPointer = pointers[partIndex + 1];
        }
        r.seek(pointers[partIndex]);
        byte[] buf = new byte[(int) (endPointer - pointers[partIndex])];
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        r.read(buf);
        System.out.println(new String(buf));
    }

    public static void main(String[] args) throws Exception {

        RandomAccessFile r = new RandomAccessFile("src/main/resources/file.txt", "r");

        //各部分起始行位置
        long[] pointers = new long[]{getPointer(r, 4, 0), getPointer(r, 4, 1), getPointer(r, 4, 2), getPointer(r, 4, 3)};

        //读取第一部分
        new Thread(() -> {
            try {
                RandomAccessFile ra = new RandomAccessFile("src/main/resources/file.txt", "r");
                pringPartContent(ra, pointers, 0);
                ra.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //读取第二部分
        new Thread(() -> {
            try {
                RandomAccessFile ra = new RandomAccessFile("src/main/resources/file.txt", "r");
                pringPartContent(ra, pointers, 1);
                ra.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //读取第三部分
        new Thread(() -> {
            try {
                RandomAccessFile ra = new RandomAccessFile("src/main/resources/file.txt", "r");
                pringPartContent(ra, pointers, 2);
                ra.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //读取第四部分
        new Thread(() -> {
            try {
                RandomAccessFile ra = new RandomAccessFile("src/main/resources/file.txt", "r");
                pringPartContent(ra, pointers, 3);
                ra.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        r.close();
    }

    //创建测试文件
    static void writeTestFile() throws Exception {
        File file = new File("src/main/resources/file.txt");
        if (!file.exists())
            file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i=1; i<=100; i++) {
            bw.write(i + "\t" + UUID.randomUUID() + SEPARATOR);
        }
        bw.close();
    }
}
