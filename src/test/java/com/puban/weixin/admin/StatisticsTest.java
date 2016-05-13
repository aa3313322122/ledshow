package com.puban.weixin.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.admin.model.Statistics;
import com.puban.weixin.admin.service.IStatisticsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class StatisticsTest
{
	@Resource(name = "statisticsService")
	private IStatisticsService statisticsService;
	
	@Test
	public void showStatisticsCompany()
	{
		String prefix = "GDSZ";
		List<Statistics> statistics = new ArrayList<Statistics>();
		statistics = statisticsService.findStatisticsByCompany(prefix);
		System.out.println(statistics);
	}
	
	@Test
	public void showStatisticsChannel()
	{
		List<Statistics> statistics = new ArrayList<Statistics>();
		statistics = statisticsService.findStatisticsByChannel();
		System.out.println(statistics);
	}
	
	@Test
	public void findCountByChannel()
	{
		List<?> list = statisticsService.findCountByChannel();
		System.out.println(list);
	}
	
	@Test
	public void findAmountByChannel()
	{
		List<?> list = statisticsService.findAmountByChannel();
		System.out.println(list);
	}
	
	@Test
	public void findDeclareAmountByChannel()
	{

		List<?> list = statisticsService.findDeclareAmountByChannel();
		System.out.println(list);
	}
	
	@Test
	public void findDeclareCountByChannel()
	{
		List<?> list = statisticsService.findDeclareCountByChannel();
		System.out.println(list);
	}
	
	@Test
	public void findCountByCompany()
	{
		String channelPrefix = "GDSZ";
		List<?> list = statisticsService.findCountByCompany(channelPrefix);
		System.out.println(list);
	}
	
	@Test
	public void findDeclareCountByCompany()
	{
		String channelPrefix = "GDSZ";
		List<?> list = statisticsService.findDeclareCountByCompany(channelPrefix);
		System.out.println(list);
	}
	
	@Test
	public void findDeclareAmountByCompany()
	{
		String channelPrefix = "GDSZ";
		List<?> list = statisticsService.findDeclareAmountByCompany(channelPrefix);
		System.out.println(list);
	}
	
	@Test
	public void findAmountByCompany()
	{
		String channelPrefix = "GDSZ";
		List<?> list = statisticsService.findAmountByCompany(channelPrefix);
		System.out.println(list);
	}

}
