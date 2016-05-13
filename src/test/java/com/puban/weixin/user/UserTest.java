package com.puban.weixin.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class UserTest
{

	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name = "declareService")
	private IDeclareService declareService;
	
	@Test
	public void saveUserTest()
	{
//		String fdCustomerName="wcp0";
//		List<Object> queryParams=new ArrayList<Object>();
//		StringBuffer sb=new StringBuffer();
//		
//		sb.append(" user.fdId=:p1  and fdCustomerName like :p2 ");
//		queryParams.add(1);
//		queryParams.add("%"+fdCustomerName+"%");
//		
//		int pageNow=1;
//		PageView<Declare> pageView = new PageView<Declare>(6, pageNow);
//		int firstindex = (pageView.getCurrentpage() - 1) * pageView.getMaxresult();
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//		orderby.put("fdId", "desc");
//		QueryResult<Declare> qr = declareService.query(Declare.class, firstindex, pageView.getMaxresult(),sb.toString(),queryParams.toArray(), orderby);
//		System.err.println(qr.getResultlist().size());
//		for(int t=0;t<qr.getResultlist().size();t++)
//		{
//			System.err.println(qr.getResultlist().get(t).getFdCustomerName());
//		}
		
	}
}
