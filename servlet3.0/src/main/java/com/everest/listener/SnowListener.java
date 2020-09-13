package com.everest.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class SnowListener implements HttpSessionListener {
    @Override public void sessionCreated(HttpSessionEvent se) {
        System.out.println("SnowListener sessionCreated");
    }

    @Override public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("SnowListener sessionDestroyed");

    }
}
