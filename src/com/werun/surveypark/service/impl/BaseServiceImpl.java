package com.werun.surveypark.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import com.werun.surveypark.dao.BaseDao;
import com.werun.surveypark.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T>{

	private BaseDao<T> dao;
	
	private Class<T> clazz ;
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	
	

	@Override
	public void saveEntry(T t) {
		
		dao.saveEntity(t);
	}

	@Override
	public void updateEntry(T t) {
		dao.updateEntity(t);
		
	}

	@Override
	public void saveOrUpdateEntry(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T getEntity(Integer id) {
		
		return dao.getEntity(id);
	}

	@Override
	public T loadEntity(Integer id) {
		
		return dao.loadEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		
		return dao.findEntityByHQL(hql, objects);
	}
	

	//单值检索(查询结果有且仅有一条记录)
	public Object uniqueResult(String hql,Object...objects){
		return dao.uniqueResult(hql, objects);
	}

	//查询所有实体
	public List<T> findAllEntities(){
		String hql = "from " + clazz.getSimpleName() ;
		return (List<T>) this.findEntityByHQL(hql);
	}

	@Override
	public void executeSQL(String sql, Object... objects) {
		dao.executeSQL(sql, objects);
	}

	@Override
	public List<T> findObjectBySQL(String sql, Object... objects) {
		return dao.findObjectBySQL(sql, objects);
	}
	
	
}
