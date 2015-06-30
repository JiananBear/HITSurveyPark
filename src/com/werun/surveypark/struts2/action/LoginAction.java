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
			return "checkSurvey";  //是管理员 跳转到检查审批调查界面
		}
		else
		{
			this.randomSurveys=surveyService.findRandomNSurveys(4);//随机找4份调查
			return SUCCESS;
		}
	}
	
	/**
	 * 执行此方法前 会优先执行validateDoLogin()方法
	 * @return
	 */
	public String doLogin()
	{
		return "toIndex";//跳转到toIndex 方法
	}
	/**
	 * 登出
	 * @return
	 */
	public String loginOut()
	{
		sessionMap.remove("user"); //将user 从suer 里面删除
		return "login";
	}
	/**
	 * 登录的时候对用户信息惊醒校验
	 */
	public void validateDoLogin()
	{
		//检查用户密码是否正确
		User user=userService.validateLoginInfo(this.getModel().getEmail(),DataUtil.md5(this.getModel().getPassword()));
		if(user==null)
		{
			addActionError("email/password wrong"); //不正确 就提示错误
		}
		else
		{
			int maxRightPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxRightPos + 1]);//为用户设置权限码
			user.calculateRightSum();
			//计算用户的权限总和
			sessionMap.put("user", user);//将user 放到 session中
		}
	}
	/**
	 * 得到图片路径
	 * @param logoPhotoPath
	 * @return
	 */
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
