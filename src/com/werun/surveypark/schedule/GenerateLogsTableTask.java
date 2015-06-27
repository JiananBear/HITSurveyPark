package com.werun.surveypark.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.werun.surveypark.service.LogService;
import com.werun.surveypark.util.LogUtil;

public class GenerateLogsTableTask extends QuartzJobBean{
	
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
	
		String tableName=LogUtil.generateLogTableName(1);
		String sql="create table if not exists "+tableName+" like logs";
		logService.executeSQL(sql);
		System.out.println(tableName+"已经创建");
		tableName=LogUtil.generateLogTableName(2);
		sql="create table if not exists "+tableName+" like logs";
		logService.executeSQL(sql);
		System.out.println(tableName+"已经创建");
	}

}
