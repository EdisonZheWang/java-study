package com.everest.ioc.init;

import com.everest.ioc.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
@Configuration
@ComponentScan(basePackages = "com.everest")
public class InitConfig {

	@Bean
	public Person person() {
		return new Person();
	}
}
