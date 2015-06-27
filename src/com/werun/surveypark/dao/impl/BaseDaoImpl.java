package com.werun.surveypark.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.werun.surveypark.dao.BaseDao;



public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource
	private SessionFactory sf;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl()
	{
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}

	@Override
	public void updateEntity(T t) {
		
		sf.getCurrentSession().update(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		
		sf.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		Query q= sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	
	@Override
	public T getEntity(Integer id) {
		return (T) sf.getCurrentSession().get(clazz, id);
	}

	@Override
	public T loadEntity(Integer id) {
		return (T) sf.getCurrentSession().load(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {
		Query q=sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
	}

	@Override
	public void executeSQL(String sql, Object... objects) {
		SQLQuery q=sf.getCurrentSession().createSQLQuery(sql);	
		for(int i =0;i<objects.length;i++)
		{
			q.setParameter(i, objects[i]);
		}
		 q.executeUpdate();
	}

	@Override
	public List<T> findObjectBySQL(String sql, Object... objects) {
		SQLQuery q=sf.getCurrentSession().createSQLQuery(sql);	
		for(int i =0;i<objects.length;i++)
		{
			q.setParameter(i, objects[i]);
		}
		q.addEntity(clazz);
		return q.list();
	}
	

	
}
