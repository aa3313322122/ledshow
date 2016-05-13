package com.puban.weixin.declare.service.impl;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.declare.dao.IOrderDao;
import com.puban.weixin.declare.exception.OrderException;
import com.puban.weixin.declare.model.Declare;
import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.declare.service.IOrderService;

import javax.annotation.Resource;

import java.util.*;

/**
 * @author zengyong@puban.com
 * @ClassName: OrderServiceImpl
 * @Description: 订单service层实现w
 * @date: 2016/3/24
 */

public class OrderServiceImpl extends BaseServiceImpl<Declare> implements IOrderService
{

	@Resource(name = "declareService")
	private IDeclareService declareService;

	@Resource(name = "orderDao")
	private IOrderDao orderDao;

	@Override
	public PageView<Declare> queryIndexOrder(Integer userId, String fdCustomerName, String currentpage) throws OrderException
	{
		int pageSize = 6;
		int pageNow = 0;
		if (currentpage == null || currentpage.equals(""))
		{
			pageNow = 1;
		}
		else
		{
			try
			{
				pageNow = Integer.parseInt(currentpage);
			}
			catch (Exception e)
			{
				pageNow = 1;
			}
		}
		List<Object> queryParams=new ArrayList<Object>();
		StringBuffer sb=new StringBuffer("");
		sb.append(" 1=1" );
		if(userId>0)
		{
			int index=queryParams.size()+1;
			sb.append(" and user.fdId=:p"+index).append(" ");
			queryParams.add(userId);
		}
		if(fdCustomerName!=null&&!"".equals(fdCustomerName))
		{
			int index=queryParams.size()+1;
			sb.append(" and fdCustomerName like :p"+index).append(" ");
			queryParams.add("%"+fdCustomerName+"%");
		}
		PageView<Declare> pageView = new PageView<Declare>(pageSize, pageNow);
		int firstindex = (pageView.getCurrentpage() - 1) * pageView.getMaxresult();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdId", "desc");
		QueryResult<Declare> qr = declareService.query(Declare.class, firstindex, pageView.getMaxresult(),sb.toString(),queryParams.toArray(), orderby);
		pageView.setQueryResult(qr);
		return pageView;
	}

	@Override
	public Map<String, Integer> findOrderCount(Integer userId) throws OrderException
	{
		Integer[] status = new Integer[4];
		status[0] = 0;
		status[1] = 1;
		status[2] = 2;
		status[3] = 3;

		Map<String, Integer> map = new HashMap<String, Integer>();

		/** 查询条件 **/
		List<Object> paramterList = new ArrayList<Object>();

		/** where **/
		StringBuffer wherejpql = new StringBuffer(" user.fdId=:p1 ");
		paramterList.add(userId);


		for (int t = 0; t < status.length; t++)
		{
			if (t == 0)
			{
				StringBuffer sbAllOrder = new StringBuffer();
				sbAllOrder.append(wherejpql);
				int countAllOrder = declareService.find(Declare.class, -1, -1, sbAllOrder.toString(), paramterList.toArray(), null).size();
				map.put("allOrder", countAllOrder);
			}
			
			if (t == 1)
			{
				StringBuffer sbUntreated = new StringBuffer();
				sbUntreated.append(wherejpql);
				sbUntreated.append("  and fdStatus=1 ");
				int countUntreated = declareService.find(Declare.class, -1, -1, sbUntreated.toString(), paramterList.toArray(), null).size();
				map.put("untreated", countUntreated);
			}
			if (t == 2)
			{
				StringBuffer sbAlread = new StringBuffer();
				sbAlread.append(wherejpql);
				sbAlread.append("  and fdStatus=2 ");
				int countAlread = declareService.find(Declare.class, -1, -1, sbAlread.toString(), paramterList.toArray(), null).size();
				map.put("alread", countAlread);
			}
			if (t == 3)
			{
				StringBuffer sbRefused = new StringBuffer();
				sbRefused.append(wherejpql);
				sbRefused.append("  and fdStatus=3 ");
				int countRefused = declareService.find(Declare.class, -1, -1, sbRefused.toString(), paramterList.toArray(), null).size();
				map.put("refused", countRefused);
			}
		}

		return map;
	}

	@Override
	public PageView<Declare> queryOrderByStatus(Integer userId, Integer fdStatus, String currentpage) throws OrderException
	{
		int pageSize = 6;
		int pageNow = 0;
		if (currentpage == null || currentpage.equals(""))
		{
			pageNow = 1;
		}
		else
		{
			try
			{
				pageNow = Integer.parseInt(currentpage);
			}
			catch (Exception e)
			{
				pageNow = 1;
			}
		}
		List<Object> queryParams=new ArrayList<Object>();
		StringBuffer sb=new StringBuffer("");
		if(userId>0)
		{
			int index=queryParams.size()+1;
			sb.append(" user.fdId=:p"+index).append(" ");
			queryParams.add(userId);
		}
		if(fdStatus>0)
		{
			int index=queryParams.size()+1;
			sb.append(" and fdStatus=:p"+index).append(" ");
			queryParams.add(fdStatus);
		}
		
		PageView<Declare> pageView = new PageView<Declare>(pageSize, pageNow);
		int firstindex = (pageView.getCurrentpage() - 1) * pageView.getMaxresult();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdId", "desc");
		QueryResult<Declare> qr = declareService.query(Declare.class, firstindex, pageView.getMaxresult(),sb.toString(),queryParams.toArray(), orderby);
		pageView.setQueryResult(qr);
		
		return pageView;
	}

	@Override
	public String updateOrderByStatus(String applyId,Integer fdStatus) throws OrderException
	{
		String rs="-1";
		rs =orderDao.updateOrderStatus(applyId,fdStatus);
		return rs;
	}
}
