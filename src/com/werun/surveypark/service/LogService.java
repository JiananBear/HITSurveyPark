package com.werun.surveypark.service;

import java.util.List;

import com.werun.surveypark.domain.Log;

public interface LogService extends BaseService<Log>{
	
	
	public List<Log> findNearestLogs();
}
