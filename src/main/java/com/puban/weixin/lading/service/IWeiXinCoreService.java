package com.puban.weixin.lading.service;

import javax.servlet.http.HttpServletRequest;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.lading.model.WeiXinCore;

public interface IWeiXinCoreService extends IBaseService<WeiXinCore>
{
	public  String processRequest(HttpServletRequest request);
}
