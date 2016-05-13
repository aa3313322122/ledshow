package com.puban.weixin.declare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.puban.framework.core.controller.BaseController;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.channel.service.IChannelService;
import com.puban.weixin.declare.model.Declare;
import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.declare.util.FileUpload;
import com.puban.weixin.lading.token.oauth.SNSUserInfo;
import com.puban.weixin.lading.util.UserUtil;
import com.puban.weixin.user.model.User;
import com.puban.weixin.user.service.IUserService;

@Controller
@RequestMapping(value = "/weixin")
public class DeclareController extends BaseController
{
	@Resource(name = "declareService")
	private IDeclareService declareService;

	@Resource(name = "channelService")
	private IChannelService channelService;

	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = "/declare", method = RequestMethod.GET)
	public String declare( @RequestParam(value = "code") String code, Model model)
	{
		SNSUserInfo weiXinUser = UserUtil.getSNSUserInfoByCode(code);
		String openId = weiXinUser.getOpenId();
		User user = userService.findByOpenId(openId);
		if (user != null)
		{
			if(weiXinUser.getNickname()!=null&&!"".equals(weiXinUser.getNickname()))
			{
				user.setFdNickName(weiXinUser.getNickname());
				userService.update(user);
			}
			
			model.addAttribute("userId", user.getFdId());
			
			Channel channel=user.getChannel();
			if(channel!=null)
			{
				model.addAttribute("channel", channel);
			}
			else
			{
				List<Channel> channelList = channelService.find(Channel.class);
				model.addAttribute("channels", channelList);
			}
		}
		return "declare";
	}
	
	/*
	 * 跳转到第二步页面
	 */
	@RequestMapping(value = "/declare2", method = RequestMethod.POST)
	public String declare2(Declare declare, String userId, Model model,HttpServletRequest request)
	{
		model.addAttribute("userId", userId);
		return "declare_2";
	}
	
	/*
	 * 跳转到第三步页面
	 */
	@RequestMapping(value = "/declare3", method = RequestMethod.POST)
	public String declare3(Declare declare,String[] tmpItem, String userId, Model model,HttpServletRequest request)
	{
		if(null != tmpItem && tmpItem.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for(String s : tmpItem)
			{
				if(null != s && !"".equals(s))
				{
					sb.append(s + ";");
				}
			}
			if(0 != sb.length())
			{
				declare.setFdOtherFilePath(sb.toString());
			}
		}
		model.addAttribute("userId", userId);
		User user = null;
		user = (User) userService.get(User.class, Integer.parseInt(userId));
		Channel channel = user.getChannel();
		if(channel!=null)
		{
			model.addAttribute("channel", channel);
		}
		else
		{
			List<Channel> channelList = channelService.find(Channel.class);
			model.addAttribute("channels", channelList);
		}
		return "declare_3";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces =  {"application/json;charset=UTF-8"})
	@ResponseBody
	public String upload(String inputName,@RequestParam MultipartFile file,Declare declare, String userId, Model model,HttpServletRequest request)
	{
		Map<String, String> result = new HashMap<String, String>();
		if(null == (result = FileUpload.upload(file,request, inputName)))
		{
			return null;
		}
		return JSONObject.toJSONString(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendValidateCode", method = RequestMethod.POST)
	public String sendValidCode(HttpServletRequest request, String fdDeclarerPhone, String userId)
	{
		return declareService.sendValidCode(request, fdDeclarerPhone, userId);
	}

	@ResponseBody
	@RequestMapping(value = "/validcode", method = RequestMethod.POST)
	public String valid(HttpServletRequest request, String fdValidateCode, String fdDeclarerPhone, String userId)
	{

		return (true == declareService.validCode(userId, fdDeclarerPhone, fdValidateCode)) ? "1" : "0";
	}
	
	@RequestMapping(value = "/declare/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Declare declare, String userId, Model model,HttpServletRequest request)
	{
		if (null == userId || "".equals(userId))
		{
			return "3";
		}
		if (!declareService.validUserStatus(userId))
		{
			return "1";
		}
		if (!declareService.validChannel(userId, declare.getChannel().getFdId()))
		{
			return "2";
		}
		return declareService.addDeclare(declare, userId, request);
	}

	@RequestMapping(value = "/testDeclare", method = RequestMethod.GET)
	public String testDeclare(Model model)
	{
		String openId = "oSeHWwScNOW-4mqC7etjPAo1Lxmg";
		User user = userService.findByOpenId(openId);
		if (user != null)
		{
			model.addAttribute("userId", user.getFdId());
			
			Channel channel=user.getChannel();
			if(channel!=null)
			{
				model.addAttribute("channel", channel);
			}
			else
			{
				List<Channel> channelList = channelService.find(Channel.class);
				model.addAttribute("channels", channelList);
			}
		}
		return "declare";
	}
}
