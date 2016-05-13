package com.puban.weixin.channel;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.channel.service.IChannelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class ChannelTest
{
	@Resource(name = "channelService")
	private IChannelService channelService;

	@Test
	public void importChannelTest()
	{
//		String filePath = "D://a.txt";
//		List<Channel> channelList = channelService.importChannel(filePath);
//		for (Channel c : channelList)
//		{
//			if (c != null)
//			{
//				System.err.println(c.getFdCode());
//				System.err.println(c.getFdName());
//				System.err.println(c.getFdStauts());
//				System.err.println(c.getFdCreateDate());
//			}
//		}
	}
	
	@Test
	public void saveChannelTest()
	{
//		channelService.saveChannel("GDSZ003", "腾讯科技");
	}
}
