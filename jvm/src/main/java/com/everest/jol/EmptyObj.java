package com.everest.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Date: 2020/9/19
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */


/**
 * 在操作系统层面, 32位系统的指针就是4字节, 而在64位系统下, 指针为48位, 还有16位是保留位, 所以是6字节(windows),
 * Linux不知到
 *
 * 基于:
 * openjdk version "1.8.0_144-1-redhat"
 * OpenJDK Runtime Environment (build 1.8.0_144-1-redhat-b01)
 * OpenJDK 64-Bit Server VM (build 25.144-b01, mixed mode)
 * 在Hotspot中对象头中还有数组长度, 对象头中的对齐填充,但是在OpenJDK的对象头中是没有数组长度和对齐填充的
 *
 * Mark Word 永远占8个字节，跟是否开启指针压缩没关系
 * Kclass pointer是一个引用(reference),开启压缩占4个字节,不开占8字节
 * 引用(reference)开启压缩占4字节,不开占8字节, 见{@link com.everest.jol.CountObjectSize1}
 * 空对象无论是否开启指针压缩都站16个字节
 * 成员变量中包含数组的则在对象头中根本不会有对齐填充,对象头中也没有数组长度 见{@link com.everest.jol.CountObjectSize2}
 * static 成员变量 占用为0, 根本就不会出现在对象中 见{@link com.everest.jol.CountObjectSize3}
 * -XX:+UseCompressedOops
 * -XX:-UseCompressedOops
 * 默认开启指针压缩的情况: 
 * 如果GC堆大小在 4G以下，直接砍掉高32位，避免了编码解码过程；
 * 如果GC堆大小在 4G以上32G以下，则启用 UseCompressedOop；
 * 如果GC堆大小 大于32G，压指失效，使用原来的64位(所以说服务器内存太大不好......)
 * 32G是个近似值，这个临界值跟JVM和平台有关。如果不想精确设置的话，31G是个绝对安全的数值
 */

/**
 * 不开  16 = 8(Mark Word) + 8(Kclass Pointer) + 0(数组长度) + 0(对齐填充)
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           28 30 ff 16 (00101000 00110000 11111111 00010110) (385822760)
 *      12     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 */

/**
 * 开： 16 = 8(Mark Word) + 4(Kclass Pointer) + 0(数组长度) + 4(对齐填充)
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           05 c1 00 20 (00000101 11000001 00000000 00100000) (536920325)
 *      12     4        (loss due to the next object alignment)
 */
public class EmptyObj {

    public static void main(String[] args) {
        EmptyObj emptyObj = new EmptyObj();
        System.out.println(ClassLayout.parseInstance(emptyObj).toPrintable());
    }
}
