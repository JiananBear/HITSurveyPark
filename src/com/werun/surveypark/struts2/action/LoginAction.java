package com.werun.surveypark.struts2.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.service.RightService;
import com.werun.surveypark.service.SurveyService;
import com.werun.surveypark.service.UserService;
import com.werun.surveypark.util.DataUtil;
import com.werun.surveypark.util.ValidateUtil;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = 6137748476714769541L;
	
	@Resource
	private UserService userService;
	@Resource
	private RightService rightService;
	@Resource
	private SurveyService surveyService;
	//session存值
	private Map<String, Object> sessionMap;
	
	private List<Survey> randomSurveys;
	
	
	public List<Survey> getRandomSurveys() {
		return randomSurveys;
	}

	public void setRandomSurveys(List<Survey> randomSurveys) {
		this.randomSurveys = randomSurveys;
	}

	public String toLoginPage(){
		return "loginPage" ;
	}
	

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=map;
	} 
	
	public String toIndex()
	{
		User u=(User) sessionMap.get("user");
		if (u.isSuperAdmin())
		{
			return "checkSurvey";
		}
		else
		{
			this.randomSurveys=surveyService.findRandomNSurveys(4);
			return SUCCESS;
		}
	}
	
	
	public String doLogin()
	{
		return "toIndex";
	}
	
	public String loginOut()
	{
		sessionMap.remove("user");
		return "login";
	}
	
	public void validateDoLogin()
	{
		User user=userService.validateLoginInfo(this.getModel().getEmail(),DataUtil.md5(this.getModel().getPassword()));
		if(user==null)
		{
			addActionError("email/password wrong");
		}
		else
		{
			int maxRightPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxRightPos + 1]);
			user.calculateRightSum();
			//计算用户的权限总和
			sessionMap.put("user", user);
		}
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
}
