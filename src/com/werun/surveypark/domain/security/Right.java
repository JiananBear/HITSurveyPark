package com.werun.surveypark.domain.security;

import com.werun.surveypark.domain.BaseEntry;


public class Right extends BaseEntry{
	
	private static final long serialVersionUID = -7108288647432534886L;
	private Integer id;
	private String rightName = "未命名";
	private String rightUrl;
	private String rightDesc;
	private long rightCode;
	private int rightPos; 
	
	private boolean common;
	
	public boolean isCommon() {
		return common;
	}

	public void setCommon(boolean common) {
		this.common = common;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public long getRightCode() {
		return rightCode;
	}

	public void setRightCode(long rightCode) {
		this.rightCode = rightCode;
	}

	public int getRightPos() {
		return rightPos;
	}

	public void setRightPos(int rightPos) {
		this.rightPos = rightPos;
	}

}
