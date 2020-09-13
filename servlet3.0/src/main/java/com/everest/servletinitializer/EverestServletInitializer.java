package com.everest.servletinitializer;

import com.everest.filter.SnowFilter;
import com.everest.handletypes.IEverest;
import com.everest.listener.SnowListener;
import com.everest.servlet.SnowServlet;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@HandlesTypes(value = {IEverest.class})
public class EverestServletInitializer implements ServletContainerInitializer {

    @Override public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ArrayList<IEverest> everestArrayList = new ArrayList<>();
        if (null != c) {
            for (Class<?> clazz : c) {
                if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
                    try {
                        everestArrayList.add((IEverest)clazz.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (IEverest iEverest : everestArrayList) {
            iEverest.sayEverestHello();
        }
        // 通过ServletContext注册Listener
        ctx.addListener(SnowListener.class);

        // 注册servlet
        ServletRegistration.Dynamic servletRegistration = ctx.addServlet("snowServlet", SnowServlet.class);
        servletRegistration.addMapping("/snow");

        // 注册filter
        FilterRegistration.Dynamic filterRegistration = ctx.addFilter("snowFilter", SnowFilter.class);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,"/*");
    }
}
