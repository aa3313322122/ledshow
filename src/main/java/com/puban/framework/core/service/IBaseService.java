package com.puban.framework.core.service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.puban.framework.core.page.QueryResult;

public interface IBaseService<T>
{
	public boolean save(Object obj);

	public void update(Object obj);

	public void remove(Object obj);

	public boolean removeAll(Object obj, Serializable[] ids);

	public void saveOrUpdate(Object obj);

	public Object load(Object obj, Serializable id);

	public Object load(Class<T> clazz, Serializable id);

	public Object get(Object obj, Serializable id);

	public Object get(Class<T> clazz, Serializable id);

	public List<?> find(Object obj);

	public List<T> find(Class<T> entityClass);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);

	public QueryResult<T> query(Class<T> entityClass);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);

	public QueryResult<T> queryByQBC(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> paramsPropertyAndType, Object[] paramsValue, LinkedHashMap<String, String> orderby);

	public List<?> findByCriteria(DetachedCriteria detachedCriteria);
}
