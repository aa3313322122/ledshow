package com.puban.weixin.declare;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.declare.service.IOrderService;
import com.puban.weixin.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class OrderTest
{
	@Resource(name = "orderService")
	private IOrderService orderService;
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name = "declareService")
	private IDeclareService declareService;
	
	@Test
	public void saveOrderTest()
	{
//		for(int t=0;t<100;t++)
//		{
//			Declare declare=new Declare();
//			if(t%2==0)
//			{
//				declare.setUser(userService.findByOpenId("123456"));
//				declare.setFdCustomerName("wcp"+t);
//				declare.setFdStatus(1);
//			}
//			if(t%2>0)
//			{
//				declare.setUser(userService.findByOpenId("123456"));
//				declare.setFdCustomerName("wcp"+t);
//				declare.setFdStatus(2);
//			}
//			declare.setFdBorrowAmount("1000"+t);
//			declare.setFdBorrowerPhone("13723709596");
//			declare.setFdBorrowTerm("10"+t);
//			declareService.save(declare);
//		}
	}
}
