package com.werun.surveypark.service;

import com.werun.surveypark.domain.User;

public interface UserService extends BaseService<User>{

	boolean isRegisted(String email);

	User validateLoginInfo(String email, String md5);

	void updateAuthorize(User model, Integer[] ownRoleIds);

	void clearAuthorize(Integer userId);

}
