package com.werun.surveypark.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.werun.surveypark.domain.User;
import com.werun.surveypark.service.UserService;
import com.werun.surveypark.util.LogUtil;

public class testUserService {
	
	@Test
	public void test()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		UserService service=(UserService) context.getBean("userService");
		User user=new User();
		
		user.setNickName("hahah");
		user.setPassword("666");
		service.saveOrUpdateEntry(user);
	}
	
	@Test
	public void test01()
	{
		User user=new User();
		user.setEmail("dsfs");
		user.setId(12);
		user.setRegDate(new Date());
		System.out.println(user.toString());
		
		System.out.println(LogUtil.generateLogTableName(0));
		System.out.println(LogUtil.generateLogTableName(1));
		System.out.println(LogUtil.generateLogTableName(2));
		
	}
	
}
