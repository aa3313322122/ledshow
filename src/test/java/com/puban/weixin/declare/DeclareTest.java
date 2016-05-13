package com.puban.weixin.declare;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.declare.service.IDeclareService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class DeclareTest
{
	@Resource(name = "declareService")
	private IDeclareService declareService;
	
	@Test
	public void validChannel()
	{
//		System.out.println(declareService.validChannel("1234"));
	}
	
	@Test
	public void declareForm()
	{
		
//		User user = new User();
//		user.setFdPhone("1341853");
//		DeclareForm declareForm = new DeclareForm();
//		declareForm.setFdIdentityCard("360203");
//		declareForm.setUser(user);
//		declareService.declareForm(declareForm);
	}
	
	@Test
	public void addDeclare()
	{
//		Declare d = new Declare();
//		Channel c = new Channel();
//		d.setFdBorrowAmount("2141245125");
//		d.setFdBorrowerPhone("21341241");
//		d.setFdBorrowTerm("231241");
//		d.setFdCreateTime("214214124");
//		d.setFdDeclarerPhone("2342142154125");
//		c.setFdId(1);
//		d.setChannel(c);
//		String userId = "1";
//		declareService.addDeclare(d, userId);
//		System.out.println();
	}
}
