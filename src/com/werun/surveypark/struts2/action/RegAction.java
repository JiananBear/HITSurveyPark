package com.werun.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.User;
import com.werun.surveypark.service.UserService;
import com.werun.surveypark.util.DataUtil;
import com.werun.surveypark.util.ValidateUtil;

@Controller("regAction")
@Scope("prototype")
public class RegAction extends BaseAction<User>{

	private static final long serialVersionUID = -6172154117475822663L;
	
	@Resource
	private UserService service;
	
	//确认密码
	private String confirmPassword ;
		
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	private User user=new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	@SkipValidation
	public String toRegPage()
	{
		return "regPage";
	}
	
	public String doReg()
	{
		User temp=new User();
		BeanUtils.copyProperties(this.getModel(), temp);
		temp.setPassword(DataUtil.md5(temp.getPassword()));
		service.saveEntry(temp);
		return SUCCESS;
	}

	@Override
	//所有的方法将被执行
	public void validate() {
		if(!ValidateUtil.isValid(user.getEmail())){
			addFieldError("email", "email是必填项!");
		}
		if(!ValidateUtil.isValid(user.getPassword())){
			addFieldError("password", "password是必填项!");
		}
		if(!ValidateUtil.isValid(user.getNickName())){
			addFieldError("nickName", "nickName是必填项!");
		}
		//判断是是否有错误
		if(this.hasErrors())
		{
			return ;
		}
		
		if(!confirmPassword.equals(user.getPassword()))
		{
			addFieldError("password", "密码输入不一致!");
			return ;
		}
		
		//3.email是否占用
		boolean b = service.isRegisted(user.getEmail());
		if(b){
			addFieldError("email", "邮箱已占用!");
		}
		
	} 
	
	
	
}
