package com.everest.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Date: 2020/9/19
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */


/**
 * 开: 40 = 8(Mark Word) + 4(Kclass Pointer) + 4(int) + 8(long) + 8(double) + 4(array? reference) + 0(alignment)
 *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
 *       0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4          (object header)                           05 c1 00 20 (00000101 11000001 00000000 00100000) (536920325)
 *      12     4      int CountObjectSize2.a                        0
 *      16     8     long CountObjectSize2.b                        15
 *      24     8   double CountObjectSize2.c                        0.5
 *      32     4    int[] CountObjectSize2.e                        [0, 0, 0]
 *      36     4          (loss due to the next object alignment)
 * Instance size: 40 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * 不开: 48 = 8(Mark Word) + 8(Kclass Pointer) + 4(int) + 8(long) + 8(double) + 8(array? reference) + 4(alignment)
 *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
 *       0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4          (object header)                           28 30 ed 16 (00101000 00110000 11101101 00010110) (384643112)
 *      12     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *      16     8     long CountObjectSize2.b                        15
 *      24     8   double CountObjectSize2.c                        0.5
 *      32     4      int CountObjectSize2.a                        0
 *      36     4          (alignment/padding gap)
 *      40     8    int[] CountObjectSize2.d                        [1, 5, 8]
 * Instance size: 48 bytes
 * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
 */
public class CountObjectSize2 {

    int a = 0;
    long b = 15L;
    double c = 0.5d;
    int[] e = new int[3];

    public static void main(String[] args) {
        CountObjectSize2 obj = new CountObjectSize2();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
