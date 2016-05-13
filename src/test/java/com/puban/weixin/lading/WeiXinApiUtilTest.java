package com.puban.weixin.lading;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class WeiXinApiUtilTest
{
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Test
	public void getValueTest()
	{
//		String rs=WeiXinApiUtil.TOKEN_URL_API;
//		System.err.println("TOKEN_URL_API is "+rs);
	}
	
	@Test
	public void subscribeWeiXinTest()
	{
//		String openId="oSeHWwScNOW-4mqC7etjPAo1Lxmg";
//		
//		userService.subscribeWeiXin(openId);
	}
	@Test
	public void unSubscribeWeiXin()
	{
//		String openId="oSeHWwScNOW-4mqC7etjPAo1Lxmg";
//		
//		userService.unSubscribeWeiXin(openId);
	}
	
}
