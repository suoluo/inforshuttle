package com.inforshuttle.mongo.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springconfig1.xml");// 读取bean.xml中的内容
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.inforshuttle.mongo.config");
		Counter c = context.getBean("counter", Counter.class);// 创建bean的引用对象
		System.out.println(c.getMultiplier());
		System.out.println(c.getSong());
		System.out.println(c.getInstrument().getName());
		context.close();
	}
}