package com.werun.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.service.SurveyService;

@Controller("questionAction")
@Scope("prototype")
public class QuestionAction extends BaseAction<Question>{

	private static final long serialVersionUID = -2703362769461607251L;
	
	@Resource
	private SurveyService surService;
	
	private Integer sid;
	private Integer pid;
	private Integer qid;
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public String toDesignQuestionPage(){
		return "" + model.getQuestionType() ;
	}
	
	public String toSelectQuestionType()
	{
		return "selectQuestionTypePage";
	}
	
	public String saveOrUpdateQuestion()
	{
		Page p=new Page();
		p.setId(pid);
		this.model.setPage(p);
		surService.saveOrUpdateQuestion(this.model);
		return "designSurveyAction";
	}
	
	public String editQuestion()
	{
		this.model=surService.getQuestion(qid);
		return "" + model.getQuestionType() ;
	}
	
	
	public String deleteQueston()
	{
		surService.deleteQueston(qid);
		return "designSurveyAction";
	}

}
