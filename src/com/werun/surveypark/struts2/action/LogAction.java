package com.werun.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Log;
import com.werun.surveypark.service.LogService;
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log>{
	private static final long serialVersionUID = -3808519856289029355L;
	@Resource
	private LogService logService;
	
	private List<Log> allLogs;

	
	public List<Log> getAllLogs() {
		return allLogs;
	}

	public void setAllLogs(List<Log> allLogs) {
		this.allLogs = allLogs;
	}

	public String findAllLogs()
	{
		this.allLogs=logService.findNearestLogs();
		return "logListPage";
	}
	
	
	
}
