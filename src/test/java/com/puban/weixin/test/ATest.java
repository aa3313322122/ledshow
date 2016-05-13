package com.puban.weixin.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.weixin.test.service.ITestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class ATest
{
	@Resource(name = "testService")
	private ITestService testService;

	@Test
	public void save()
	{
//		com.puban.weixin.test.model.Test obj = new com.puban.weixin.test.model.Test();
//		obj.setFdName("wang-save");
//		boolean fg = testService.save(obj);
//		System.out.println(fg);
	}

	@Test
	public void saveMyTest()
	{
//		com.puban.weixin.test.model.Test obj = new com.puban.weixin.test.model.Test();
//		obj.setFdName("wang-mytest");
//		testService.saveMyTest(obj);
	}

	@Test
	public void loadTest()
	{
//		com.puban.weixin.test.model.Test obj = new com.puban.weixin.test.model.Test();
//		com.puban.weixin.test.model.Test t = (com.puban.weixin.test.model.Test) testService.load(obj, 1);
//		if(t!=null)
//		{
//			System.err.println(t.getFdId());
//		}
	}

	@Test
	public void getTest()
	{
//		com.puban.weixin.test.model.Test obj = new com.puban.weixin.test.model.Test();
//		com.puban.weixin.test.model.Test t = (com.puban.weixin.test.model.Test) testService.get(obj, 1);
//		if(t!=null)
//		{
//			System.err.println(t.getFdId());
//		}
	}

	@Test
	public void removeAllTest()
	{
//		Integer[] ids = new Integer[4];
//		ids[0] = 3;
//		ids[1] = 4;
//		ids[2] = 5;
//		ids[3] = 6;
//		com.puban.weixin.test.model.Test obj = new com.puban.weixin.test.model.Test();
//		boolean s = testService.removeAll(obj, ids);
//		System.err.println(s);
	}

	@Test
	public void queryTest()
	{
//		com.puban.weixin.test.model.Test obj = new com.puban.weixin.test.model.Test();
//		List<?> list = testService.find(obj);
//		for (int t = 0; t < list.size(); t++)
//		{
//			com.puban.weixin.test.model.Test ts = (com.puban.weixin.test.model.Test) list.get(t);
//			System.err.println(ts.getFdId() + "------>" + ts.getFdName());
//		}
	}
	
	@Test
	public void queryPageTest()
	{
//		int pageSize = 10;
//		PageView<com.puban.weixin.test.model.Test> pageView = new PageView<com.puban.weixin.test.model.Test>(pageSize, 1);
//		int firstindex = (pageView.getCurrentpage() - 1) * pageView.getMaxresult();
//
//		/** order sql* */
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
//		orderby.put("fdId", "asc");
//
//		/** do service* */
//		QueryResult<com.puban.weixin.test.model.Test> qr = testService.query(com.puban.weixin.test.model.Test.class, firstindex, pageView.getMaxresult(), orderby);
//		pageView.setQueryResult(qr);
	}
	
	@Test
	public void queryByQBCTest()
	{
//		LinkedHashMap<String, String> paramsPropertyAndType=new LinkedHashMap<String, String>();
//		paramsPropertyAndType.put("fdId", "eq");
//		
//		Integer[] paramsValue=new Integer[1];
//		paramsValue[0]=90;
//		
//		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
//		orderby.put("fdId", "asc");
//		
//		QueryResult<com.puban.weixin.test.model.Test> qrlist = testService.queryByQBC(com.puban.weixin.test.model.Test.class, 0, 10, paramsPropertyAndType, paramsValue, orderby);
//		List<com.puban.weixin.test.model.Test> list=qrlist.getResultlist();
//		for (int t = 0; t < list.size(); t++)
//		{
//			com.puban.weixin.test.model.Test ts = (com.puban.weixin.test.model.Test) list.get(t);
//			System.err.println(ts.getFdId() + "------>" + ts.getFdName());
//		}
	}

}
