package com.werun.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {

	private static final long serialVersionUID = -2306871989213766668L;
	
	@Resource
	private SurveyService surveyService;
	
	private User user;
	
	private Integer srcPid ;
	
	private Integer srcSid;
	
	private Integer targPid ;
	
	//位置:0-之前 1-之后
	private int pos ;
	
	private Integer sid ;
	
	private List<Survey> surveys;
	
	
	
	
	public Integer getSrcSid() {
		return srcSid;
	}
	public void setSrcSid(Integer srcSid) {
		this.srcSid = srcSid;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	public Integer getTargPid() {
		return targPid;
	}
	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	} 
	public Integer getSrcPid() {
		return srcPid;
	}
	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}
	
	
	public String toSelectTargetPage()
	{
		System.out.println(this.srcSid);
		this.surveys=surveyService.findSurveysWithPage(user);
		return "moveOrCopyPageListPage";
	}
	
	public String doMoveOrCopyPage()
	{
		surveyService.moveOrCopyPage(srcPid,targPid,pos);
		return "designSurveyAction" ;
	}
	
	@Override
	public void setUser(User u) {
		// TODO Auto-generated method stub
		user=u;
	}
	

}
