package com.werun.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.User;
import com.werun.surveypark.domain.security.Role;
import com.werun.surveypark.service.RoleService;
import com.werun.surveypark.service.UserService;

@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User>{

	private static final long serialVersionUID = 1214696686677691191L;
	
	private List<User> allUsers;
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	private Integer userId;
	
	private List<Role> noOwnRoles;
	
	private Integer[] ownRoleIds ;
	
	
	

	public Integer[] getOwnRoleIds() {
		return ownRoleIds;
	}

	public void setOwnRoleIds(Integer[] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}

	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}

	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	public String findAllUsers()
	{
		this.allUsers=userService.findAllEntities();
		return "userAuthorizeListPage";
	}
	
	
	
	public String editAuthorize()
	{
		this.model=userService.getEntity(userId);
		this.noOwnRoles=roleService.findRolesNotInRange(model.getRoles());
		return "userAuthorizePage";
	}
	
	
	public String updateAuthorize()
	{
		userService.updateAuthorize(model,ownRoleIds);
		return "findAllUsersAction";
	}
	
	
	public String clearAuthorize()
	{
		userService.clearAuthorize(userId);
		return "findAllUsersAction";
	}

}
