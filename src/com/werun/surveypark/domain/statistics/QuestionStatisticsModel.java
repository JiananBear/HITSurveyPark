package com.werun.surveypark.domain.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.werun.surveypark.domain.Question;

public class QuestionStatisticsModel implements Serializable{
	
	private Question question;
	
	private int count;
	
	private List<OptionStatisticsModel> osms=new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsms() {
		return osms;
	}

	public void setOsms(List<OptionStatisticsModel> osms) {
		this.osms = osms;
	}
	
	

}
