package com.werun.surveypark.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.werun.surveypark.domain.security.Right;
import com.werun.surveypark.service.RightService;

@Component
public class IniRightListener implements ApplicationListener,ServletContextAware {

	@Resource
	private RightService rs ;
	
	//接受servletContext对象
	private ServletContext sc;
	
	@Override
	public void setServletContext(ServletContext arg0) {
		System.out.println("注入sc");
		sc=arg0;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		if(arg0 instanceof ContextRefreshedEvent)
		{
			List<Right> list = rs.findAllEntities();
			Map<String, Right> map=new HashMap<String, Right>();
			for (Right right : list) {
				map.put(right.getRightUrl(), right);
			}
			if(sc!=null)
			{
				sc.setAttribute("all_rights_map", map);
				System.out.println("权限初始化完成了!!");
			}
			
		}
	}

}
