package com.puban.weixin.test.service.impl;

import javax.annotation.Resource;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.test.dao.ITestDao;
import com.puban.weixin.test.model.Test;
import com.puban.weixin.test.service.ITestService;

public class TestServiceImpl extends BaseServiceImpl<Test> implements ITestService
{
	@Resource(name="testDao")
	private ITestDao testDao;
	
	public void saveMyTest(Test obj)
	{
		testDao.save(obj);
	}

}
