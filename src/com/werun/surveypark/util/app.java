package com.werun.surveypark.util;

import java.security.MessageDigest;


public class app {
	
	public static void main(String[] args) throws Exception {
		/*String str="123";
		StringBuffer sb=new StringBuffer();
		char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		MessageDigest ms=MessageDigest.getInstance("md5");
		byte[] re=ms.digest(str.getBytes());
		for(byte b: re)
		{
			sb.append(chars[(b >> 4) & 0x0F]);
			sb.append(chars[b  & 0x0F]);
		}
		System.out.println(sb.toString());*/
		System.out.println(System.nanoTime());
	}

}
