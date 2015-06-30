package com.werun.surveypark.struts2.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.werun.surveypark.struts2.action.BaseAction;
import com.werun.surveypark.util.ValidateUtil;
/**
 * 权限拦截器
 * 
 */
public class RightFilterInterceptor implements Interceptor{

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
		
		BaseAction action = (BaseAction) invocation.getAction() ;
		
		ActionProxy proxy = invocation.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();
		if(ValidateUtil.hasRight(ns, actionName, ServletActionContext.getRequest(), action)){
			return invocation.invoke();
		}
		else{
			return "no_right_error" ;
		}
		
	}

}
