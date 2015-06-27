package com.werun.surveypark.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.werun.surveypark.service.RightService;



public class GenerateAllRightsUtil {
	
	public static void main(String[] args) {
		
		try {
			ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
			RightService rs=(RightService) ac.getBean("rightService");
			URL url =GenerateAllRightsUtil.class.getClassLoader().getSystemResource("cn/itcast/surveypark/struts2/action");
			File dir=new File(url.toURI());
			//System.out.println(url);
			File[] files=dir.listFiles();
			String fname="";
			
			for (File f : files) {
				fname=f.getName();
				if(fname.endsWith("class")
						&& !fname.equals("BaseAction.class")
						&& !fname.endsWith("UserAware.class"))
				{
					processClass(fname,rs);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}

	private static void processClass(String fname, RightService rs) throws Exception {
		String pkgName = "cn.itcast.surveypark.struts2.action" ;
		String simpleClassName = fname.substring(0, fname.indexOf("."));
		Class clazz=Class.forName(pkgName+"."+simpleClassName);
		
		Method[] ms= clazz.getDeclaredMethods();
		
		String mname="";
		Class retType=null;
		Class[] paramType=null;
		String url="";
		
		for(Method m:ms)
		{
			mname=m.getName();
			retType=m.getReturnType();
			paramType=m.getParameterTypes();
			if((retType==String.class)
					&&!ValidateUtil.isValid(paramType)
					&&Modifier.isPublic(m.getModifiers()))
			{
				if(mname.equals("execute")){
					url = "/" + simpleClassName;
				}
				else{
					url = "/" + simpleClassName + "_" + mname ;
				}
				rs.appendRightByURL(url);
			}
			
			
		}
		
		
		
	}

}
