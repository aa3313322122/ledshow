package com.puban.framework.core.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.IBaseService;

public class BaseServiceImpl<T> implements IBaseService<T>
{
	@Resource
	private IBaseDao<T> baseDao;

	public void setBaseDao(IBaseDao<T> baseDao)
	{
		this.baseDao = baseDao;
	}

	public boolean save(Object obj)
	{
		int backNumber = baseDao.save(obj);

		if (backNumber > 0)
		{
			return true;
		}

		return false;
	}

	@Override
	public void update(Object obj)
	{
		this.baseDao.update(obj);
	}

	@Override
	public void remove(Object obj)
	{
		this.baseDao.remove(obj);
	}

	@Override
	public void saveOrUpdate(Object obj)
	{
		this.baseDao.saveOrUpdate(obj);
	}

	@Override
	public Object load(Object obj, Serializable id)
	{
		return this.baseDao.load(obj, id);
	}

	@Override
	public Object get(Object obj, Serializable id)
	{
		return this.baseDao.get(obj, id);
	}

	@Override
	public boolean removeAll(Object obj, Serializable[] ids)
	{
		int backNumber = this.baseDao.removeAll(obj, ids);
		if (backNumber > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<?> find(Object obj)
	{
		return baseDao.find(obj);
	}

	@Override
	public Object load(Class<T> clazz, Serializable id)
	{
		return baseDao.load(clazz, id);
	}

	@Override
	public Object get(Class<T> clazz, Serializable id)
	{
		return baseDao.get(clazz, id);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass)
	{
		return baseDao.query(entityClass);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult)
	{
		return baseDao.query(entityClass, firstindex, maxresult);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return baseDao.query(entityClass, firstindex, maxresult, orderby);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return baseDao.query(entityClass, firstindex, maxresult, wherejpql, queryParams);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		return baseDao.query(entityClass, firstindex, maxresult, wherejpql, queryParams, orderby);
	}

	@Override
	public QueryResult<T> queryByQBC(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> paramsPropertyAndType, Object[] paramsValue, LinkedHashMap<String, String> orderby)
	{
		return baseDao.queryByQBC(entityClass, firstindex, maxresult, paramsPropertyAndType, paramsValue, orderby);
	}

	@Override
	public List<T> find(Class<T> entityClass)
	{
		return baseDao.find(entityClass);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult)
	{
		return baseDao.find(entityClass, firstindex, maxresult);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return baseDao.find(entityClass, firstindex, maxresult, orderby);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return baseDao.find(entityClass, firstindex, maxresult, wherejpql, queryParams);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		return baseDao.find(entityClass, firstindex, maxresult, wherejpql, queryParams, orderby);
	}
	
	@Override
	public List<?> findByCriteria(DetachedCriteria detachedCriteria)
	{
		return baseDao.findByCriteria(detachedCriteria);
	}
}
