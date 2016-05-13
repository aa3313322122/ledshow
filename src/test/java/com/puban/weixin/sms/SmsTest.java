package com.puban.weixin.sms;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.sms.service.ISmsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class SmsTest
{
	@Resource(name="smsService")
	private ISmsService smsService;
	
	@Test
	public void sendSmsTest()
	{
//		smsService.sendSms("13723709596", "60505");	
	}
}
