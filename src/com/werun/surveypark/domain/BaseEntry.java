package com.werun.surveypark.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;

public class BaseEntry implements Serializable{

	
	private static final long serialVersionUID = 1396588709494514856L;

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		String className=this.getClass().getSimpleName();
		Class clazz=this.getClass();
		sb.append(className+"{");
		try {
			Field[] fields=clazz.getDeclaredFields();
			
			for (Field field : fields) {
				
				if((field.getType().isPrimitive()
						||field.getType()==Integer.class
						||field.getType()==String.class
						||field.getType()==Date.class
						||field.getType()==Long.class)
						&&!Modifier.isStatic(field.getModifiers()))
				{
					field.setAccessible(true);
					Object value=(Object) field.get(this);
					String fName=field.getName();
					if(value!=null)
						sb.append(fName+":"+value.toString()+",");
					else
						sb.append(fName+":"+"null"+",");
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("}");
		return sb.toString();
	}
	
}
