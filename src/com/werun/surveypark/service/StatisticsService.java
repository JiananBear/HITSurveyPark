package com.werun.surveypark.service;

import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;

public interface StatisticsService {
	public QuestionStatisticsModel statistics(Integer qid);
}
