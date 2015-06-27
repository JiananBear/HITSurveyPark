package com.werun.surveypark.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;

/**
 * StringUtil 
 */
public class StringUtil {
	public static String[] str2Arr(String str,String tag){
		if(ValidateUtil.isValid(str)){
			return str.split(tag);
		}
		return null ;
	}

	/**
	 * 判断数组中是否含有指定的串
	 */
	public static boolean contains(String[] arr, String value) {
		if(ValidateUtil.isValid(arr)){
			for(String s : arr){
				if(s.equals(value)){
					return true ;
				}
			}
		}
		return false;
	}

	public static String arr2Str(Object[] values) {
		if(ValidateUtil.isValid(values))
		{
			String temp="";
			for(int i =0;i<values.length;i++)
			{	
				if(i!=values.length-1)
					temp+=values[i]+",";
				else
					temp+=values[i];
			}
			return temp;
		}
		else 
			return null;
	}
	
	public static String getDescString(String str){
		if(ValidateUtil.isValid(str) && str.length() > 30){
			return str.substring(0, 29);
		}
		return str ;
	}
	
	public static String getDescString(String str,int len)
	{
		if (len<1)
		{
			len=30;
		}
		if(ValidateUtil.isValid(str) && str.length() > len){
			return str.substring(0, len-1)+"......";
		}
		return str ;
	}
	
	private static JSONArray changeQsm2Json(QuestionStatisticsModel qsm)
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("question",qsm.getQuestion());
		
		return jsonArray;
	}

	
}
