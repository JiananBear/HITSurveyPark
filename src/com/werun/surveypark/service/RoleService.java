package com.werun.surveypark.service;

import java.util.List;
import java.util.Set;

import com.werun.surveypark.domain.security.Role;

public interface RoleService extends BaseService<Role> {

	void saveOrUpdateRole(Role model, Integer[] ownRightIds);

	Role getEntityWithChild(Integer roleId);

	List<Role> findRolesNotInRange(Set<Role> roles);
}
