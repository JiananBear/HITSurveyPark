package com.werun.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.service.SurveyService;

@Controller("pageAction")
@Scope("prototype")
public class PageAction extends BaseAction<Page>{
	private static final long serialVersionUID = -3941971081585995511L;
	
	@Resource
	private SurveyService surveyService;
	
	private Integer sid;
	
	private Integer pid;

	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String toAddPage(){
		return "addPage";
	}
	
	public String saveOrUpdatePage()
	{
		Survey temp=new Survey();
		temp.setId(sid);
		this.model.setSurvey(temp);
		surveyService.saveOrUpdatePage(this.model);
		return "designSurveyAction";
	}
	
	public String editPage()
	{
		this.model = surveyService.getPage(pid);
		return "editPagePage" ;
	}
	
	public String deletePage()
	{
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
	
}
