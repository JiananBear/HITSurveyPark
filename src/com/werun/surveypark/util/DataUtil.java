package com.werun.surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Set;

import com.werun.surveypark.domain.security.Role;


public class DataUtil {
	
	public static String md5(String src)
	{
		try {
			StringBuffer sb=new StringBuffer();
			char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			MessageDigest ms=MessageDigest.getInstance("md5");
			byte[] re=ms.digest(src.getBytes());
			for(byte b: re)
			{
				sb.append(chars[(b >> 4) & 0x0F]);
				sb.append(chars[b  & 0x0F]);
			}
			return sb.toString();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public static Serializable deeplyCopy(Serializable src)  {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			byte[] data = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

	public static String extractEntityIds(Set<Role> roles) {
		if(!ValidateUtil.isValid(roles))
		{
			return "";
		}
		String temp="";
		for (Role role : roles) {
			temp+=role.getId()+",";
		}
		return temp.substring(0, temp.lastIndexOf(","));
	}

}
