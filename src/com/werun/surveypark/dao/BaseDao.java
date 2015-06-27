package com.werun.surveypark.dao;

import java.util.List;

public interface BaseDao<T> {
		//写操作
		public void saveEntity(T t);
		public void updateEntity(T t);
		public void saveOrUpdateEntity(T t);
		public void deleteEntity(T t);
		//按照hql批处理实体
		public void batchEntityByHQL(String hql,Object...objects);
		public void executeSQL(String sql,Object...objects);
		
		public T getEntity(Integer id);
		public T loadEntity(Integer id);
		public List<T> findEntityByHQL(String hql,Object...objects);
		public Object uniqueResult(String hql,Object...objects);
		
		//按照sql查询
		public List<T> findObjectBySQL(String sql,Object...objects);
}
