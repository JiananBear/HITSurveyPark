package com.werun.surveypark.service;

import java.util.List;

import com.werun.surveypark.domain.Answer;
import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.domain.User;

public interface SurveyService {

	public Survey newSurvey(User u);
	public List<Survey> findMySurveys(User u);
	public Survey getSurveyWithChildren(Integer sid);
	public Survey getSurvey(Integer sid);
	public void updateSurvey(Survey model);
	public void saveOrUpdatePage(Page temp);
	public Page getPage(Integer pid);
	public void saveOrUpdateQuestion(Question model);
	public Question getQuestion(Integer qid);
	public void deleteQueston(Integer qid);
	public void deletePage(Integer pid);
	public void deleteSurvey(Integer sid);
	public void clearAnswers(Integer sid);
	public void toggleStatus(Integer sid);
	public void updateLogoPhotoPath(Integer sid, String string);
	public List<Survey> findSurveysWithPage(User user);
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos);
	public List<Survey> findAllAvailableSurveys();
	public Page getFirstPage(Integer sid);
	public Page getPrePage(Integer currPid);
	public Page getNextPage(Integer currPid);
	public void saveAnswers(List<Answer> processAnswers);
	public List<Question> getQuestions(Integer sid);
	public List<Answer> findAnswers(Integer sid);
	public List<Survey> findRandomNSurveys(int n);
	public Survey saveSurveyTemplate(User u,String path,Integer tmpIndex);
	public Survey getBlockSurvey(User u);
	public Survey getSurveyTemplate(User u,String path,Integer tmpIndex);
	public void saveSurvey(Survey s);
	public List<Survey> getALLCheckingSurveys();
	public void checkSurvey(Integer sid, Integer check);
	
}
