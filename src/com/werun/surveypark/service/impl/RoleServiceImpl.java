package com.werun.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.werun.surveypark.dao.BaseDao;
import com.werun.surveypark.domain.security.Right;
import com.werun.surveypark.domain.security.Role;
import com.werun.surveypark.service.RightService;
import com.werun.surveypark.service.RoleService;
import com.werun.surveypark.util.DataUtil;
import com.werun.surveypark.util.ValidateUtil;

/**
 * roleService
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService{

	@Resource
	private RightService rightService ;
	
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds) {
		if(!ValidateUtil.isValid(ownRightIds))
		{
			model.getRights().clear();
		}
		else
		{
			List<Right> rights=rightService.findRightsInRange(ownRightIds);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntry(model);
	}

	@Override
	public Role getEntityWithChild(Integer roleId) {
		Role r=this.getEntity(roleId);
		r.getRights().size();
		return r;
	}

	@Override
	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(!ValidateUtil.isValid(roles))
		{
			return this.findAllEntities();
		}
		else
		{
			String hql="from Role r where r.id not in ("+DataUtil.extractEntityIds(roles)+")";
			return this.findEntityByHQL(hql);
		}
	}
	
	
}
