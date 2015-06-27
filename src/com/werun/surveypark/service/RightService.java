package com.werun.surveypark.service;

import java.util.List;
import java.util.Set;

import com.werun.surveypark.domain.security.Right;

public interface RightService extends BaseService<Right> {

	public void saveOrUpdateRight(Right model);

	public void batchUpdateRights(List<Right> allRights);

	public void appendRightByURL(String url);

	public List<Right> findRightsInRange(Integer[] ownRightIds);

	public List<Right> findRightsNotInRange(Set<Right> rights);

	public int getMaxRightPos();


}
