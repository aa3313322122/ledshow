package com.puban.weixin.declare.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.puban.framework.core.controller.BaseController;
import com.puban.framework.core.page.PageView;
import com.puban.weixin.declare.model.Declare;
import com.puban.weixin.declare.service.IOrderService;
import com.puban.weixin.lading.token.oauth.SNSUserInfo;
import com.puban.weixin.lading.util.UserUtil;
import com.puban.weixin.user.model.User;
import com.puban.weixin.user.service.IUserService;

@Controller
@RequestMapping(value = "/weixin")
public class OrderController extends BaseController
{
	@Resource(name = "orderService")
	private IOrderService orderService;
	
	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(Model model, @RequestParam(value = "code") String code, String pageNow, String customerName, HttpServletRequest request, HttpServletResponse response)
	{
		SNSUserInfo weiXinUser= null;
		if(code!=null&&!"".equals(code))
		{
			weiXinUser=UserUtil.getSNSUserInfoByCode(code);
		}
		String openId = weiXinUser.getOpenId();
		User user=userService.findByOpenId(openId);

		/** 分页数据 **/
		PageView<Declare> pageView = orderService.queryIndexOrder(user.getFdId(), customerName, pageNow);
		model.addAttribute("pageView", pageView);

		/** 订单数量数据 **/
		Map<String, Integer> orderCountMap = orderService.findOrderCount(user.getFdId());
		model.addAttribute("map", orderCountMap);

		/** 查询参数 **/
		model.addAttribute("userId", user.getFdId());
		if (customerName != null && !customerName.equals(""))
		{
			model.addAttribute("customerName", customerName);
		}

		return "/order";
	}

	@RequestMapping(value = "/queryOrder")
	public String query(@RequestParam(value = "userId") String userId, String customerName,Integer fdStatus, Integer pageNow, Model model)
	{
		if(fdStatus>0)
		{
			/** 根据状态查询分页数据 **/
			PageView<Declare> pageView = orderService.queryOrderByStatus(Integer.valueOf(userId), fdStatus, String.valueOf(pageNow));
			model.addAttribute("pageView", pageView);
		}
		else
		{
			/** 分页数据 **/
			PageView<Declare> pageView = orderService.queryIndexOrder(Integer.valueOf(userId), customerName, String.valueOf(pageNow));
			model.addAttribute("pageView", pageView);
		}

		/** 订单数量数据 **/
		Map<String, Integer> orderCountMap = orderService.findOrderCount(Integer.valueOf(userId));
		model.addAttribute("map", orderCountMap);

		/** 查询参数 **/
		model.addAttribute("userId", userId);
		if (customerName != null && !customerName.equals(""))
		{
			model.addAttribute("customerName", customerName);
		}
		return "/order";
	}

	@RequestMapping(value = "/appendList", method = RequestMethod.POST)
	public void appendList(@RequestParam(value = "userId") String userId, Integer pageNow, String customerName, HttpServletResponse response, Model model)
	{
		/** 下一页分页数据 **/
		PageView<Declare> pageView = orderService.queryIndexOrder(Integer.valueOf(userId), customerName, String.valueOf(pageNow));
		model.addAttribute("pageView", pageView);

		/** 查询参数 **/
		model.addAttribute("userId", userId);
		if (customerName != null && !customerName.equals(""))
		{
			model.addAttribute("customerName", customerName);
		}
		
		ajaxJson(JSON.toJSONString(model), response);
	}

	
	@RequestMapping(value = "/testOrder", method = RequestMethod.GET)
	public String testOrder(Model model,String pageNow, String customerName, HttpServletRequest request, HttpServletResponse response)
	{
		String openId = "oSeHWwScNOW-4mqC7etjPAo1Lxmg";
		User user=userService.findByOpenId(openId);

		/** 分页数据 **/
		PageView<Declare> pageView = orderService.queryIndexOrder(user.getFdId(), customerName, pageNow);
		model.addAttribute("pageView", pageView);

		/** 订单数量数据 **/
		Map<String, Integer> orderCountMap = orderService.findOrderCount(user.getFdId());
		model.addAttribute("map", orderCountMap);

		/** 查询参数 **/
		model.addAttribute("userId", user.getFdId());
		if (customerName != null && !customerName.equals(""))
		{
			model.addAttribute("customerName", customerName);
		}

		return "/order";
	}
}
