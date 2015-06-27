package com.werun.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.datasourse.SurveyToken;
import com.werun.surveypark.domain.Answer;
import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.service.SurveyService;
import com.werun.surveypark.util.StringUtil;
import com.werun.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements SessionAware,ParameterAware{

	
	private static final long serialVersionUID = -8720618560342098629L;
	
	private static final String CURRENT_SURVEY = "current_survey" ;
	
	private static final String ALL_PARAMS_MAP = "all_params_map" ;
	
	@Resource
	private SurveyService surveyService;
	
	private List<Survey> surveys;
	
	private Integer sid;
	
	private Page currPage;
	
	private Integer currPid;


	private Map<String, Object> session;

	private Map<String, String[]> parameters;
	
	public Integer getCurrPid() {
		return currPid;
	}

	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}


	public String findAllAvailableSurveys()
	{
		this.surveys=surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	
	
	public String entry()
	{
		this.currPage=surveyService.getFirstPage(sid);
		session.put(CURRENT_SURVEY, currPage.getSurvey());
		session.put(ALL_PARAMS_MAP, new HashMap<Integer, Map<String,String[]>>());
		return "engageSurveyPage";
	}
	
	
	public String doEngageSurvey()
	{
		String submitName = getSubmitName(); 
		if(submitName.endsWith("pre"))
		{
			mergeParamsIntoSession();
			this.currPage=surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		}
		else if(submitName.endsWith("next"))
		{
			mergeParamsIntoSession();
			this.currPage=surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		}
		else if(submitName.endsWith("done"))
		{
			mergeParamsIntoSession();
			SurveyToken token=new SurveyToken();
			token.setCurrentSurvey(getCurrentSurvey());
			SurveyToken.bindingToken(token);
			surveyService.saveAnswers(processAnswers());
			clearSessionData();
			return "engageSurveyAction";
		}
		else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSurveyAction" ;
		}
		return null ;
	}
	
	private List<Answer> processAnswers() {
		Map<Integer, String> matrixRadioMap=new HashMap<Integer, String>();
		List<Answer> answers=new ArrayList<Answer>();
		Answer a=null;
		String key=null;
		String[] values=null;
		
		for( Map<String, String[]> map: getAllParamsMapInSession().values())
		{
			for (Entry<String, String[]> entry : map.entrySet()) {
				key=entry.getKey();
				values=entry.getValue();
				
				if(key.startsWith("q"))
				{
					if(!key.contains("_")&&!key.contains("other"))
					{
						a=new Answer();
						a.setAnswerIds(StringUtil.arr2Str(values));
						a.setQuestionId(getQid(key));
						a.setSurveyId(getCurrentSurvey().getId());
						String[] otherAnswer=map.get(key+"other");
						a.setOtherAnswer(StringUtil.arr2Str(otherAnswer));
						answers.add(a);
					}

					else if(key.contains("_"))
					{
						Integer qid = getMatrixRadioQid(key);
						String oldValue=matrixRadioMap.get(qid);
						if(oldValue==null)
						{
							matrixRadioMap.put(qid, StringUtil.arr2Str(values));
						}
						else 
						{
							matrixRadioMap.put(qid, oldValue+","+StringUtil.arr2Str(values));
						}
					}
				}
			}
		}
		processMatrixRadioAnswers(answers,matrixRadioMap);
		return answers;
	}

	private void processMatrixRadioAnswers(List<Answer> answers,
			Map<Integer, String> matrixRadioMap) {
		
		Integer key = null ;
		String value = null ;
		Answer a = null ;
		for(Entry<Integer, String> entry : matrixRadioMap.entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerIds(value);//answerids
			a.setQuestionId(key);
			a.setSurveyId(getCurrentSurvey().getId());//surveyid
			answers.add(a);
		}
	}

	private Integer getMatrixRadioQid(String key) {
		
		return  Integer.parseInt(key.substring(1, key.indexOf("_")));
	}

	private Survey getCurrentSurvey() {
		return (Survey) session.get(CURRENT_SURVEY);
	}

	//提取问题id:q12-->12
		private Integer getQid(String key) {
			return Integer.parseInt(key.substring(1));
		}
		
	private void clearSessionData() {
		Map<Integer, Map<String,String[]>> allmap=(Map<Integer, Map<String, String[]>>) session.get(ALL_PARAMS_MAP);
		allmap.remove(CURRENT_SURVEY);
		allmap.remove(ALL_PARAMS_MAP);
	}

	private void mergeParamsIntoSession() {
		Map<Integer, Map<String,String[]>> allmap=(Map<Integer, Map<String, String[]>>) session.get(ALL_PARAMS_MAP);
		allmap.put(currPid, parameters);
		//System.out.println(allmap.get(currPid).get("p1"));
	}
	
	/**
	 * 获得sesson中所有参数的map
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMapInSession() {
		return (Map<Integer, Map<String, String[]>>) session.get(ALL_PARAMS_MAP);
	}

	private String getSubmitName()
	{
		for(String s:  parameters.keySet())
		{
			if(s.startsWith("submit_"))
				return s;
		}
		return null;
	}
	
	
	public String getImageUrl(String logoPhotoPath)
	{
		if(ValidateUtil.isValid(logoPhotoPath))
		{
			String dir=ServletActionContext.getServletContext().getRealPath(logoPhotoPath);
			if(new File(dir).exists())
			{
				return ServletActionContext.getServletContext().getContextPath()+logoPhotoPath;
			}
		}
		return ServletActionContext.getServletContext().getContextPath() + "/question.bmp" ;
	}
	
	

	@Override
	public void setSession(Map<String, Object> arg0) {
		session=arg0;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters=arg0;
		
	}
	
	public String setTag(String name,String value,String tag){
		Integer pid = this.currPage.getId();
		Map<String,String[]> currmap = this.getAllParamsMapInSession().get(pid);
		String[] values=currmap.get(name);
		if(StringUtil.contains(values,value))
		{
			return tag;
		}
		return "";
	}
	
	
	public String setText(String name){
		Integer pid = this.currPage.getId();
		Map<String,String[]> map = this.getAllParamsMapInSession().get(pid);
		String[] oldValues = map.get(name);
		if(ValidateUtil.isValid(oldValues)){
			return  oldValues[0]  ;
		}
		return "" ;
	} 

}
