package com.puban.framework.core.dao.impl;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.framework.core.page.QueryResult;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class BaseDaoImpl<T> implements IBaseDao<T>
{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(Object obj)
	{
		int backNumber = (Integer) getSession().save(obj);
		return backNumber;
	}

	@Override
	public void update(Object obj)
	{
		getSession().update(obj);
	}

	@Override
	public void remove(Object obj)
	{
		getSession().delete(obj);
	}

	@Override
	public int removeAll(Object obj, Serializable[] ids)
	{
		StringBuffer idHql = new StringBuffer();
		for (int i = 0; i < ids.length; i++)
		{
			if (i == 0)
			{
				idHql.append(" fdId=").append(ids[i]);
			}
			else
			{
				idHql.append(" or fdId=").append(ids[i]);
			}
		}

		String objName = obj.getClass().getSimpleName();
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from ").append(objName);
		hql.append(" where ").append(idHql.toString());
		return getSession().createQuery(hql.toString()).executeUpdate();
	}

	@Override
	public void saveOrUpdate(Object obj)
	{
		getSession().saveOrUpdate(obj);
	}

	@Override
	public Object load(Object obj, Serializable id)
	{
		return getSession().get(obj.getClass(), id);
	}

	@Override
	public Object get(Object obj, Serializable id)
	{
		return getSession().get(obj.getClass(), id);
	}

	@Override
	public List<?> find(Object obj)
	{
		List<?> list = getSession().createCriteria(obj.getClass()).addOrder(Order.asc("fdId")).list();
		return list;
	}

	@Override
	public Object load(Class<T> clazz, Serializable id)
	{
		return getSession().get(clazz, id);
	}

	@Override
	public Object get(Class<T> clazz, Serializable id)
	{
		return getSession().get(clazz, id);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass)
	{
		return this.query(entityClass, -1, -1);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult)
	{
		return this.query(entityClass, firstindex, maxresult, null, null, null);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return this.query(entityClass, firstindex, maxresult, null, null, orderby);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return this.query(entityClass, firstindex, maxresult, wherejpql, queryParams, null);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		Session session = getSession();
		QueryResult<T> qr = new QueryResult<T>();
		String entiryName = this.getEntityName(entityClass);
		Query query = session.createQuery(" select o from " + entiryName + " o " + (wherejpql == null ? "" : " where " + wherejpql) + " " + buildOrderBy(orderby));
		this.setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1)
		{
			query.setFirstResult(firstindex);

			query.setMaxResults(maxresult);
		}

		@SuppressWarnings("unchecked")
		List<T> resultlist = query.list();
		qr.setResultlist(resultlist);
		query = session.createQuery(" select count(o) from " + entiryName + " o " + (wherejpql == null ? "" : " where " + wherejpql) + " " + buildOrderBy(orderby));
		this.setQueryParams(query, queryParams);
		qr.setTotalrecord(resultlist.size());
		return qr;
	}

	@Override
	public QueryResult<T> queryByQBC(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> paramsPropertyAndType, Object[] paramsValue, LinkedHashMap<String, String> orderby)
	{
		QueryResult<T> queryResult = new QueryResult<T>();

		Criteria criteria = getSession().createCriteria(entityClass);

		if (firstindex != -1 && maxresult != -1)
		{
			criteria.setFirstResult(firstindex);
			criteria.setMaxResults(maxresult);
		}

		if (paramsPropertyAndType != null && !paramsPropertyAndType.isEmpty())
		{
			int count = -1;
			for (String paramsName : paramsPropertyAndType.keySet())
			{
				String paramsType = paramsPropertyAndType.get(paramsName);
				if (paramsType.toLowerCase().equals("eq"))
				{
					count++;
					criteria.add(Restrictions.eq(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("ne"))
				{
					count++;
					criteria.add(Restrictions.ne(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("gt"))
				{
					count++;
					criteria.add(Restrictions.gt(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("ge"))
				{
					count++;
					criteria.add(Restrictions.ge(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("lt"))
				{
					count++;
					criteria.add(Restrictions.lt(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("le"))
				{
					count++;
					criteria.add(Restrictions.le(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("like"))
				{
					count++;
					criteria.add(Restrictions.like(paramsName, paramsValue[count]));
				}
				else if (paramsType.toLowerCase().equals("isnull"))
				{
					criteria.add(Restrictions.isNull(paramsName));
				}
				else if (paramsType.toLowerCase().equals("isnotnull"))
				{
					criteria.add(Restrictions.isNotNull(paramsName));
				}
				else if (paramsType.toLowerCase().equals("isempty"))
				{
					criteria.add(Restrictions.isEmpty(paramsName));
				}
				else if (paramsType.toLowerCase().equals("isnotempty"))
				{
					criteria.add(Restrictions.isNotEmpty(paramsName));
				}
				else if (paramsType.toLowerCase().equals("between"))
				{
					count++;
					Object lo = paramsValue[count];
					count++;
					Object hi = paramsValue[count];
					criteria.add(Restrictions.between(paramsName, lo, hi));
				}
				else if (paramsType.toLowerCase().equals("notbetween"))
				{
					count++;
					Object lo = paramsValue[count];
					count++;
					Object hi = paramsValue[count];
					criteria.add(Restrictions.not(Restrictions.between(paramsName, lo, hi)));
				}
			}
		}

		if (orderby != null && !orderby.isEmpty())
		{
			for (String orderProperty : orderby.keySet())
			{
				String orderType = orderby.get(orderProperty);
				if (orderType.toLowerCase().equals("asc"))
				{
					criteria.addOrder(Order.asc(orderProperty));
				}
				if (orderType.toLowerCase().equals("desc"))
				{
					criteria.addOrder(Order.desc(orderProperty));
				}
			}
		}

		@SuppressWarnings("unchecked")
		List<T> resultlist = criteria.list();
		queryResult.setResultlist(resultlist);
		Integer size = resultlist.size();
		Long totalRecord = Long.valueOf(String.valueOf(size));
		queryResult.setTotalrecord(totalRecord);
		return queryResult;
	}

	@Override
	public List<T> find(Class<T> entityClass)
	{
		return this.find(entityClass, -1, -1);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult)
	{
		return this.find(entityClass, firstindex, maxresult, null, null, null);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return this.find(entityClass, firstindex, maxresult, null, null, orderby);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return this.find(entityClass, firstindex, maxresult, wherejpql, queryParams, null);
	}

	@Override
	public List<T> find(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		String entiryName = this.getEntityName(entityClass);
		Query query = getSession().createQuery(" select o from " + entiryName + " o " + (wherejpql == null ? "" : " where " + wherejpql) + " " + buildOrderBy(orderby));
		this.setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1)
		{
			query.setFirstResult(firstindex);
			query.setMaxResults(maxresult);
		}

		@SuppressWarnings("unchecked")
		List<T> resultlist = query.list();
		return resultlist;
	}

	@Override
	public List<?> findByCriteria(DetachedCriteria detachedCriteria)
	{
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

	protected String buildOrderBy(LinkedHashMap<String, String> orderby)
	{
		StringBuffer orderbyhql = new StringBuffer(" ");
		if (orderby != null && orderby.size() > 0)
		{
			orderbyhql.append(" order by ");
			for (String key : orderby.keySet())
			{
				orderbyhql.append(" o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyhql.deleteCharAt(orderbyhql.length() - 1);
		}
		return orderbyhql.toString();
	}

	protected void setQueryParams(Query query, Object[] queryParams)
	{
		if (queryParams != null && queryParams.length > 0)
		{
			for (int i = 0; i < queryParams.length; i++)
			{
				query.setParameter("p" + (i + 1), queryParams[i]);
			}
		}
	}

	protected String getEntityName(Class<T> entityClass)
	{
		String entiryName = entityClass.getSimpleName();

		return entiryName;
	}

}
