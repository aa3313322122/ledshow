package com.puban.weixin.test.service;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.test.model.Test;

public interface ITestService extends IBaseService<Test>
{
	public void saveMyTest(Test t);
}
