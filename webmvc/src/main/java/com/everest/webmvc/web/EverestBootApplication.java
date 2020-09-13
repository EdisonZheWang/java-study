package com.everest.webmvc.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class EverestBootApplication {

    public static void run() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        try {
            tomcat.addWebapp("/", "D:\\Dev\\MyRepo\\spring-framework-study\\webmvc\\target");
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            System.out.println("容器启动失败");
        }
    }
}
