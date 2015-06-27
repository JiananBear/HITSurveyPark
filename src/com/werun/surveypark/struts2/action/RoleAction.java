package com.werun.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.security.Right;
import com.werun.surveypark.domain.security.Role;
import com.werun.surveypark.service.RightService;
import com.werun.surveypark.service.RoleService;


@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	private static final long serialVersionUID = 2204584457428070381L;

	private List<Role> allRoles;
	
	private List<Right> noOwnRights;
	
	private Integer[] ownRightIds;
	
	private Integer roleId ;
	
	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}

	public void setOwnRightIds(Integer[] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Resource
	private RoleService roleService;
	@Resource
	private RightService rightService;
	
	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}

	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public String findAllRoles()
	{
		this.allRoles=roleService.findAllEntities();
		return "roleListPage";
	}
	
	public String toAddRolePage()
	{
		this.noOwnRights=rightService.findAllEntities();
		return "addRolePage";
	}
	
	/**
	 * 保存/更新角色
	 */
	public String saveOrUpdateRole(){
		roleService.saveOrUpdateRole(model,ownRightIds);
		return "findAllRolesAction" ;
	}
	
	/**
	 * 编辑角色
	 */
	public String editRole(){
		this.model = roleService.getEntityWithChild(roleId);
		this.noOwnRights = rightService.findRightsNotInRange(model.getRights());
		return "editRolePage" ;
	}
	
	public String deleteRole()
	{
		Role role=new Role();
		role.setId(roleId);
		roleService.deleteEntity(role);
		return "findAllRolesAction";
	}
	

	
	
}
