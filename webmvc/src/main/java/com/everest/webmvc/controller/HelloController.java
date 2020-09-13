package com.everest.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
