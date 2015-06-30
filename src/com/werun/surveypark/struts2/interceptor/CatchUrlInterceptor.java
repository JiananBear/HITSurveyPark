package com.werun.surveypark.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.werun.surveypark.service.RightService;
import com.werun.surveypark.util.ValidateUtil;

/**
 * 权限捕获
 * 
 */
public class CatchUrlInterceptor implements Interceptor{

	
	private static final long serialVersionUID = 3306117036174630812L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy=invocation.getProxy();
		String ns=proxy.getNamespace();
		String actionName=proxy.getActionName();
		if(!ValidateUtil.isValid(ns)
				|| ns.equals("/")){
			ns = "" ;
		}
		String url = ns + "/" + actionName ;
		
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac=(ApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
		RightService rs=(RightService) ac.getBean("rightService");
		rs.appendRightByURL(url);
		return invocation.invoke();
	}

}
