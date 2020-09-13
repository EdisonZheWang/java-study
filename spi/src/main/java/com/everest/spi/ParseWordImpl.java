package com.everest.spi;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class ParseWordImpl implements IParseDoc{
    public ParseWordImpl() {

    }

    @Override public IDoc parse() {
        System.out.println("parse Word");
        return new WordImpl();
    }
}
