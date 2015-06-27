package com.werun.surveypark.service.impl;


import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.werun.surveypark.dao.BaseDao;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.domain.security.Role;
import com.werun.surveypark.service.RoleService;
import com.werun.surveypark.service.UserService;
import com.werun.surveypark.util.StringUtil;
import com.werun.surveypark.util.ValidateUtil;


@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Resource
	private RoleService roleService;
	@Override
	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	@Override
	public boolean isRegisted(String email) {
		String hql="from User u where u.email=?";
		List<User> list=this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list);
	}

	@Override
	public User validateLoginInfo(String email, String md5) {
		String hql="from User u where u.email=? and u.password=?";
		List<User> list=this.findEntityByHQL(hql,email, md5);
		return ValidateUtil.isValid(list)?list.get(0):null ;
	}

	@Override
	public void updateAuthorize(User model, Integer[] ownRoleIds) {
		User uu=this.getEntity(model.getId());
		if(!ValidateUtil.isValid(ownRoleIds))
		{
			uu.getRoles().clear();
		}
		else
		{
			String hql = "from Role r where r.id in ("+StringUtil.arr2Str(ownRoleIds)+")" ;
			List<Role> roles  = roleService.findEntityByHQL(hql);
			uu.setRoles(new HashSet<Role>(roles));
		}
	}

	@Override
	public void clearAuthorize(Integer userId) {
		this.getEntity(userId).getRoles().clear();
	}


}
