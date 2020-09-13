package com.everest.spi;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class ParseExeclImpl implements IParseDoc {
    public ParseExeclImpl() {
    }

    @Override public IDoc parse() {
        System.out.println("parse Excel");
        return new ExeclImpl();
    }
}
