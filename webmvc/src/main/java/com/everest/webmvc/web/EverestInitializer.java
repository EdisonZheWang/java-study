package com.everest.webmvc.web;

import com.everest.webmvc.config.EverestBootConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class EverestInitializer implements WebApplicationInitializer {
    @Override public void onStartup(ServletContext servletContext) throws ServletException {
        // 手动new一个AnnotationConfigWebApplicationContext，不然无法扫描包，无法把对象置入容器
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(EverestBootConfig.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();

        // 注册DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        ServletRegistration.Dynamic servletRegistration =
            servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
