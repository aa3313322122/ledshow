package com.puban.weixin.admin.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.admin.model.Statistics;
import com.puban.weixin.admin.service.IStatisticsService;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.channel.service.IChannelService;
import com.puban.weixin.declare.model.Declare;

public class StatisticsServiceImpl extends BaseServiceImpl<Statistics> implements IStatisticsService
{
	@Resource(name = "channelService")
	private IChannelService channelService;
	
	public List<Statistics> findStatisticsByChannel()
	{
		List<Statistics> statistics = new ArrayList<Statistics>();
		List<Channel> channels = new ArrayList<Channel>();
		channels = channelService.find(Channel.class);
		for(Channel c : channels)
		{
			if(null != c.getFdCode() && !"".equals(c.getFdCode()))
			{
				Statistics s = new Statistics();
				s.setChannelCode(c.getFdCode());
				statistics.add(s);
			}
		}
		combineCountList(statistics, this.findCountByChannel(), "setDealCount");
		combineCountList(statistics, this.findDeclareCountByChannel(), "setDeclareCount");
		combineAmountList(statistics, this.findAmountByChannel(), "setDealAmount");
		combineAmountList(statistics, this.findDeclareAmountByChannel(), "setDeclareAmount");
		return statistics;
	}
	
	public List<Statistics> findStatisticsByCompany(String channelPrefix)
	{
		List<Statistics> statistics = new ArrayList<Statistics>();
		List<Channel> channels = new ArrayList<Channel>();
		channels = channelService.find(Channel.class);
		for(Channel c : channels)
		{
			if(c.getFdCode().startsWith(channelPrefix))
			{
				Statistics s = new Statistics();
				s.setChannelCode(c.getFdCode());
				statistics.add(s);
			}
		}
		combineCountList(statistics, this.findCountByCompany(channelPrefix), "setDealCount");
		combineCountList(statistics, this.findDeclareCountByCompany(channelPrefix), "setDeclareCount");
		combineAmountList(statistics, this.findAmountByCompany(channelPrefix), "setDealAmount");
		combineAmountList(statistics, this.findDeclareAmountByCompany(channelPrefix), "setDeclareAmount");
		return statistics;
	}
	
	/**
	 * 按分公司统计成单数
	 * @param 分公司标识，渠道前缀
	 * @return List<String, Long>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCountByCompany(String channelPrefix)
	{
		if(null == channelPrefix || "".equals(channelPrefix))
		{
			return null;
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		detachedCriteria.add(Restrictions.like("c.fdCode", channelPrefix + "%"))
			.add(Restrictions.isNotNull("fdFillingAmount"));
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.rowCount());
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按分公司统计成单金额
	 * @param 分公司标识，渠道前缀
	 * @return List<String, Double>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAmountByCompany(String channelPrefix)
	{
		if(null == channelPrefix || "".equals(channelPrefix))
		{
			return null;
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		detachedCriteria.add(Restrictions.like("c.fdCode", channelPrefix + "%"));
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.sum("fdFillingAmount"));
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按分公司统计报单金额
	 * @param 分公司标识，渠道前缀
	 * @return List<String, Double>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findDeclareAmountByCompany(String channelPrefix)
	{
		if(null == channelPrefix || "".equals(channelPrefix))
		{
			return null;
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		detachedCriteria.add(Restrictions.like("c.fdCode", channelPrefix + "%"));
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.sum("fdAmountLoanable"));
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按分公司统计报单数
	 * @param 分公司标识，渠道前缀
	 * @return List<String, Long>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findDeclareCountByCompany(String channelPrefix)
	{
		if(null == channelPrefix || "".equals(channelPrefix))
		{
			return null;
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		detachedCriteria.add(Restrictions.like("c.fdCode", channelPrefix + "%"));
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.rowCount());
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按渠道统计报单数
	 * @return List<String, Long>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findDeclareCountByChannel()
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		proList.add(Projections.groupProperty("c.fdCode"));
		proList.add(Projections.rowCount());
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按渠道统计报单金额
	 * @return List<String, Double>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findDeclareAmountByChannel()
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.sum("fdAmountLoanable"));
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按渠道统计成单金额
	 * @return List<String, Double>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAmountByChannel()
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.sum("fdFillingAmount"));
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 按渠道统计成单数
	 * @return List<String, Long>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCountByChannel()
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Declare.class);
		ProjectionList proList = Projections.projectionList();
		detachedCriteria.createAlias("channel", "c");
		detachedCriteria.add(Restrictions.isNotNull("fdFillingAmount"));
		proList.add(Projections.groupProperty("c.fdCode"))
				.add(Projections.rowCount());
		detachedCriteria.setProjection(proList);
		return (List<Object[]>) this.findByCriteria(detachedCriteria);
	}
	
	private List<Statistics> combineCountList(List<Statistics> statistics, List<Object[]> list
			, String methodName)
	{
		for(Statistics s : statistics)
		{
			for(Object[] arrO : list)
			{
				if(arrO.length > 0)
				{
				
					String channelCode = (String)arrO[0];
					if(s.getChannelCode().equals(channelCode))
					{
						try
						{
							Method m = s.getClass().getDeclaredMethod(methodName, Long.class);
							m.invoke(s, (Long)arrO[1]);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		return statistics;
	}
	
	private List<Statistics> combineAmountList(List<Statistics> statistics, List<Object[]> list
			, String methodName)
	{
		for(Statistics s : statistics)
		{
			for(Object[] arrO : list)
			{
				if(arrO.length > 0)
				{
				
					String channelCode = (String)arrO[0];
					if(s.getChannelCode().equals(channelCode))
					{
						try
						{
							Method m = s.getClass().getDeclaredMethod(methodName, Double.class);
							m.invoke(s, (Double)arrO[1]);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		return statistics;
	}
	
}
