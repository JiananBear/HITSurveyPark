package com.werun.surveypark.service;

import java.util.List;

public interface BaseService<T> {
	
	public void saveEntry(T t);
	public void updateEntry(T t);
	public void saveOrUpdateEntry(T t);
	public void deleteEntity(T t);
	//按照hql批处理实体
	public void batchEntityByHQL(String hql,Object...objects);
	public void executeSQL(String sql, Object... objects);
	public T getEntity(Integer id);
	public T loadEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	public Object uniqueResult(String hql,Object...objects);
	public List<T> findAllEntities();
	public List<T> findObjectBySQL(String sql, Object... objects);
}
