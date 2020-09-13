package com.everest.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class SPIMainStarter {
    private static ServiceLoader<IParseDoc> serviceLoader = ServiceLoader.load(IParseDoc.class);
    public static void main(String[] args) {
        Iterator<IParseDoc> it = serviceLoader.iterator();
        while (it.hasNext()) {
            IParseDoc next = it.next();
            next.parse();
        }
    }
}
