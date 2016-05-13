package com.puban.weixin.declare.dao.impl;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.weixin.declare.dao.IOrderDao;
import com.puban.weixin.declare.model.Declare;

/**
 * @author zengyong@puban.com
 * @ClassName: OrderDaoImpl
 * @Description:
 * @date: 2016/3/24
 */

public class OrderDaoImpl extends BaseDaoImpl<Declare> implements IOrderDao
{

	@Override
	public String updateOrderStatus(String applyId,Integer status)
	{
		Declare declare = (Declare) getSession().createQuery(" from  Declare  where applyId=:p1").setString("p1", applyId).uniqueResult();
		if (declare == null)
		{
			return "-1";
		}
		else
		{
			String hql = "update Declare a set a.fdStatus=:p1 where a.applyId=:p2";
			int s = getSession().createQuery(hql).setInteger("p1", status).setString("p2", applyId).executeUpdate();
			if (s > 0)
			{
				return "1";
			}
			else
			{
				return "0";
			}
		}

	}
}
