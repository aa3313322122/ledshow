package com.puban.weixin.admin.service;

import java.util.List;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.admin.model.Statistics;

public interface IStatisticsService extends IBaseService<Statistics>
{
	public List<Statistics> findStatisticsByChannel();
	
	public List<Statistics> findStatisticsByCompany(String channelPrefix);
	
	public List<Object[]> findCountByCompany(String channelPrefix);
	
	public List<Object[]> findDeclareAmountByCompany(String channelPrefix);
	
	public List<Object[]> findAmountByCompany(String channelPrefix);
	
	public List<Object[]> findDeclareCountByCompany(String channelPrefix);
	
	public List<Object[]> findDeclareCountByChannel();
	
	public List<Object[]> findDeclareAmountByChannel();
	
	public List<Object[]> findAmountByChannel();
	
	public List<Object[]> findCountByChannel();
}
