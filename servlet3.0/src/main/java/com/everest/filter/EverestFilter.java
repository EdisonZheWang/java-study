package com.everest.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @Date: 2020/9/13
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@WebFilter(value = "/hello")
public class EverestFilter implements Filter {
    @Override public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("EverestFilter is initing");
    }

    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        System.out.println("EverestFilter before filting");
        chain.doFilter(request, response);
        System.out.println("EverestFilter after filting");
    }

    @Override public void destroy() {
        System.out.println("EverestFilter is destorying");
    }
}
