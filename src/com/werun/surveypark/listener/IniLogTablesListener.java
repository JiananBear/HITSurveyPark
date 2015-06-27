package com.werun.surveypark.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.werun.surveypark.service.LogService;
import com.werun.surveypark.util.LogUtil;

@Component
public class IniLogTablesListener implements ApplicationListener {

	@Resource
	private LogService logService;
	

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		if(arg0 instanceof ContextRefreshedEvent)
		{
			String tableName = LogUtil.generateLogTableName(0);
			String sql = "create table if not exists " + tableName + " like logs";
			logService.executeSQL(sql);
			
			sql = "create table if not exists " + LogUtil.generateLogTableName(1) + " like logs";
			logService.executeSQL(sql);
			
			sql = "create table if not exists " + LogUtil.generateLogTableName(2) + " like logs";
			logService.executeSQL(sql);
			System.out.println("日志表-"+tableName+",初始化完成");
		}
		
	}

}
