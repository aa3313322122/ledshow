package com.puban.weixin.order;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.declare.service.IOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class OrderTest
{
	@Resource(name = "orderService")
	private IOrderService orderService;

	@Test
	public void importChannelTest()
	{
		// String filePath = "D://a.txt";
		// List<Channel> channelList = channelService.importChannel(filePath);
		// for (Channel c : channelList)
		// {
		// if (c != null)
		// {
		// System.err.println(c.getFdCode());
		// System.err.println(c.getFdName());
		// System.err.println(c.getFdStauts());
		// System.err.println(c.getFdCreateDate());
		// }
		// }
	}

	@Test
	public void saveChannelTest()
	{
//		QueryResult<Declare> list = orderService.queryIndexOrder("123456", "mukuro", "1");
//		System.out.println(list.getTotalrecord() + "**********");
	}
}
