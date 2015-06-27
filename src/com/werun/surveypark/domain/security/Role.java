package com.werun.surveypark.domain.security;

import java.util.HashSet;
import java.util.Set;

import com.werun.surveypark.domain.BaseEntry;


public class Role extends BaseEntry{
	
	private static final long serialVersionUID = 7739845481442277750L;
	private Integer id;
	private String roleName;
	private String roleValue;
	private String roleDesc;

	
	private Set<Right> rights = new HashSet<Right>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}
