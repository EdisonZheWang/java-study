package com.everest.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@WebListener
public class EverestListener implements HttpSessionListener {
    @Override public void sessionCreated(HttpSessionEvent se) {
        System.out.println("EverestListener session created");
    }

    @Override public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("EverestListener session destory");
    }
}
