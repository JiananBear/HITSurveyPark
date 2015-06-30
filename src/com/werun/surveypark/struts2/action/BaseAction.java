package com.werun.surveypark.struts2.action;

import java.lang.reflect.ParameterizedType;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * 
 * @author LuckBear
 *
 * @param <T>
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,Preparable{

	private static final long serialVersionUID = -2202395472487014615L;

	public T model;
	
	public BaseAction()
	{
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			@SuppressWarnings("unchecked")
			Class<T> clazz = ((Class<T>) type.getActualTypeArguments()[0]);
			model=clazz.newInstance();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void prepare() throws Exception {
	}
	
	public  T getModel(){
		return model;
	}
	
	public String getXmlurl() {
		return ServletActionContext.getServletContext().getRealPath("/tmp/surveyTemplate.xml");
	}
	
	
	
	
}
