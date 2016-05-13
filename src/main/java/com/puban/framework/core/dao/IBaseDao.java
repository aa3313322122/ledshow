package com.puban.framework.core.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.puban.framework.core.page.QueryResult;

public interface IBaseDao<T>
{
	public int save(Object obj);

	public void update(Object obj);

	public void remove(Object obj);

	public int removeAll(Object obj, Serializable[] ids);

	public void saveOrUpdate(Object obj);

	public Object load(Object obj, Serializable id);

	public Object load(Class<T> clazz, Serializable id);

	public Object get(Object obj, Serializable id);

	public Object get(Class<T> clazz, Serializable id);

	/** 普通查询，不分页 **/
	public QueryResult<T> query(Class<T> entityClass);

	/** 分页查询，无条件，无排序 **/
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult);

	/** 分页查询，无条件，有排序 **/
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	/** 分页查询，有条件，无排序 **/
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams);

	/** 分页查询，有条件，有排序 **/
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);

	public QueryResult<T> queryByQBC(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> paramsPropertyAndType, Object[] paramsValue, LinkedHashMap<String, String> orderby);

	public List<?> find(Object obj);

	public List<T> find(Class<T> entityClass);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams);

	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);

	/** 动态条件查询  **/
	public List<?> findByCriteria(DetachedCriteria detachedCriteria);
}
