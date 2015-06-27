package com.werun.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.werun.surveypark.dao.BaseDao;
import com.werun.surveypark.domain.security.Right;
import com.werun.surveypark.service.RightService;
import com.werun.surveypark.util.StringUtil;
import com.werun.surveypark.util.ValidateUtil;

/**
 * rightService
 */
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService{
	
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateRight(Right model) {
		
		if(model.getId()==null)
		{
			int rightPos = 0 ;
			long rightCode = 1 ;
			String hql="select max(r.rightPos),max(r.rightCode) from Right r"+
					" where r.rightPos = (select max(rr.rightPos) from Right rr)" ;
			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topRightPos = (Integer) arr[0] ;
			Long topRightCode = (Long) arr[1];
			if(topRightPos == null){
				rightPos = 0 ;
				rightCode = 1 ;
			}
			else{
				if(topRightCode >= (1L << 60)){
					rightPos = topRightPos + 1;
					rightCode = 1 ;
				}
				else{
					rightPos = topRightPos ;
					rightCode = topRightCode << 1 ;
				}
			}
			model.setRightPos(rightPos);
			model.setRightCode(rightCode);
		}
		this.saveOrUpdateEntry(model);
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		if(ValidateUtil.isValid(allRights)){
			String hql = "update Right r set r.rightName = ?,r.common = ? where r.id = ?" ;
			for(Right r : allRights){
				this.batchEntityByHQL(hql,r.getRightName(),r.isCommon(),r.getId());
			}
		}
	}

	@Override
	public void appendRightByURL(String url) {
		String hql = "select count(*) from Right r where r.rightUrl = ?" ;
		Long count = (Long)this.uniqueResult(hql,url);
		if(count == 0){
			Right r = new Right();
			r.setRightUrl(url);
			this.saveOrUpdateRight(r);
		}
	}

	@Override
	public List<Right> findRightsInRange(Integer[] ownRightIds) {
		if(ValidateUtil.isValid(ownRightIds)){
			String hql = "from Right r where r.id in (" + StringUtil.arr2Str(ownRightIds) + ")" ;
			return this.findEntityByHQL(hql);
		}
		return null ;
	}

	@Override
	public List<Right> findRightsNotInRange(Set<Right> rights) {
		if(!ValidateUtil.isValid(rights))
		{
			return this.findAllEntities();
		}
		else 
		{
			String hql="from Right r where r.id not in ("+extractRightIds(rights)+")";
			return this.findEntityByHQL(hql);
		}
		
	}

	private String extractRightIds(Set<Right> rights) {
		String ids="";
		for (Right right : rights) {
			ids+=right.getId()+",";
		}
		return ids.substring(0, ids.length() - 1);
	}

	@Override
	public int getMaxRightPos() {
		String hql ="select max(r.rightPos) from Right r" ;
		Integer max = (Integer) this.uniqueResult(hql);
		return max == null ? 0 : max ;
	}

	
}
