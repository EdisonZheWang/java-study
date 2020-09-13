package com.everest.ioc;

import com.everest.ioc.init.InitConfig;
import com.everest.ioc.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Date: 2020/9/12
 * @author: <a href="mailto:chrdw.p@gmail.com">Edison Zhe Wang</a>
 */
public class GetBeanStudy {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(InitConfig.class);
		Person person = (Person)ctx.getBean("person");
		System.out.println(person);
	}
}
