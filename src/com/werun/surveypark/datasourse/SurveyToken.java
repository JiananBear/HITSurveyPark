package com.werun.surveypark.datasourse;

import com.werun.surveypark.domain.Survey;

public class SurveyToken {
	
	private Survey currentSurvey;
	private static ThreadLocal<SurveyToken> t= new ThreadLocal<SurveyToken>();;

	public Survey getCurrentSurvey() {
		return currentSurvey;
	}

	public void setCurrentSurvey(Survey currentSurvey) {
		this.currentSurvey = currentSurvey;
	}
	
	
	/**
	 * 将令牌对象绑定到当前线程
	 */
	public static void bindingToken(SurveyToken token){
		t.set(token);
	}
	
	/**
	 * 从当前线程取得绑定的令牌对象
	 */
	public static SurveyToken getCurrentToken(){
		return t.get() ;
	}
	
	/**
	 * 解除令牌的绑定
	 */
	public static void unbindToken(){
		t.remove() ;
	}
	
	
}
