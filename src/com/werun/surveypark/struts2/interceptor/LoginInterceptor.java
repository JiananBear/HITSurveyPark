package com.werun.surveypark.struts2.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.struts2.action.BaseAction;
import com.werun.surveypark.struts2.action.LoginAction;
import com.werun.surveypark.struts2.action.RegAction;
import com.werun.surveypark.struts2.action.UserAware;

public class LoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
	
		
	}

	@Override
	public void init() {
		
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction action=(BaseAction) invocation.getAction();
		if(action instanceof LoginAction || action instanceof RegAction)
		{
			return invocation.invoke();
		}
		else 
		{
			HttpSession session=ServletActionContext.getRequest().getSession();
			User user=(User) session.getAttribute("user");
			if(user==null)
			{
				return "login";
			}
			else 
			{
				if(action instanceof UserAware)
				{
					((UserAware) action).setUser(user);
				}
				return invocation.invoke();
			}
		}
		
	}

}
