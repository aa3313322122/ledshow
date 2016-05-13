package com.puban.weixin.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.puban.framework.core.controller.BaseController;
import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.declare.service.IOrderService;

@Controller
@RequestMapping(value = "/weixin/api")
public class PuBanWeiXinController extends BaseController
{
	@Resource(name = "orderService")
	private IOrderService orderService;

	@Resource(name = "declareService")
	private IDeclareService declareService;

	@ResponseBody
	@RequestMapping(value = "/orders/{applyId}/{fdStatus}")
	public String getOrders(@PathVariable(value="applyId") String applyId,@PathVariable(value="fdStatus") Integer fdStatus)
	{
		String rs = orderService.updateOrderByStatus(applyId,fdStatus);
		return JSON.toJSONString(rs);
	}
}
