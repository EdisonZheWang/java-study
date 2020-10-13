package com.everest.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Date: 2020/9/19
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */


/**
 * 开: 24 = 8(Mark Word) + 4(Kclass Pointer) + 4(int) + 4(int) + 4(array? reference) + 0(alignment)
 * OFFSET  SIZE    TYPE DESCRIPTION                               VALUE
 *       0     4         (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4         (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4         (object header)                           05 c1 00 20 (00000101 11000001 00000000 00100000) (536920325)
 *      12     4     int CountObjectSize3.a                        0
 *      16     4     int CountObjectSize3.b                        15
 *      20     4   int[] CountObjectSize3.d                        [1, 3, 4]
 * Instance size: 24 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 *
 * 不开: 32 = 8(Mark Word) + 8(Kclass Pointer) + 4(int) + 4(int) + 8(array? reference) + 0(alignment)
 *  OFFSET  SIZE    TYPE DESCRIPTION                               VALUE
 *       0     4         (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4         (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4         (object header)                           28 30 e6 16 (00101000 00110000 11100110 00010110) (384184360)
 *      12     4         (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *      16     4     int CountObjectSize3.a                        0
 *      20     4     int CountObjectSize3.b                        15
 *      24     8   int[] CountObjectSize3.d                        [1, 3, 4]
 * Instance size: 32 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 */
public class CountObjectSize3 {

    int a = 0;
    int b = 15;
    // static 占用为0
//    static int[] c = {1, 3, 4};
    // 无论有没有new出来这个对象, 引用都是8或4个字节,因为new出来的对象不属于这个对象, 在堆上是另外的对象
//    static Object object = new Object();
//    static Object object;
    int[] d = {1, 3, 4};

    public static void main(String[] args) {
        CountObjectSize3 obj = new CountObjectSize3();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
