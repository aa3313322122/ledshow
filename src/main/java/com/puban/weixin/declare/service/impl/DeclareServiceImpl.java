package com.puban.weixin.declare.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.api.util.ApplyPo;
import com.puban.weixin.api.util.HttpClientUtil;
import com.puban.weixin.api.util.MD5Util;
import com.puban.weixin.api.util.PuBanWeiXinUtil;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.channel.service.IChannelService;
import com.puban.weixin.declare.dao.IDeclareDao;
import com.puban.weixin.declare.exception.DeclareException;
import com.puban.weixin.declare.model.Declare;
import com.puban.weixin.declare.service.IDeclareService;
import com.puban.weixin.declare.util.TokenProcessor;
import com.puban.weixin.sms.service.ISmsService;
import com.puban.weixin.sms.util.RandomCodeUtil;
import com.puban.weixin.user.model.User;
import com.puban.weixin.user.model.UserSmsValid;
import com.puban.weixin.user.service.IUserService;
import com.puban.weixin.user.service.IUserSmsValidService;

public class DeclareServiceImpl extends BaseServiceImpl<Declare> implements IDeclareService
{
	@Resource(name = "declareDao")
	private IDeclareDao declareDao;

	@Resource(name = "userService")
	private IUserService userService;

	@Resource(name = "smsService")
	private ISmsService smsService;

	@Resource(name = "channelService")
	private IChannelService channelService;

	@Resource(name = "userSmsValidService")
	private IUserSmsValidService userSmsValidService;

	/**
	 * 申报
	 * 
	 * @param declare
	 * @param userId
	 * @return
	 */
	public String addDeclare(Declare declare, String userId, HttpServletRequest request)
	{
		if(!validField(declare))
		{
			return "-2";
		}

		User user = (User) userService.get(User.class, Integer.parseInt(userId));
		String openId = user.getFdOpenId();

		UserSmsValid usv = userSmsValidService.findByOpenIdAndPhone(openId, declare.getFdDeclarerPhone());
		String lastCode = null;
		if (null != usv && null != usv.getFdLastCode())
		{
			lastCode = usv.getFdLastCode();
			if (null != lastCode && declare.getFdValidateCode().equals(usv.getFdLastCode()))
			{
				Channel channel = null;
				if (declare.getChannel() != null)
				{
					channel = (Channel) channelService.get(Channel.class, declare.getChannel().getFdId());
					declare.setChannel(channel);
				}
				if (user != null)
				{
					if (channel != null)
					{
						user.setChannel(channel);
					}
					declare.setUser(user);
				}
				if (!validReSubmit(request))
				{
					return "-1";
				}
				declare.setFdCreateTime(dateToString(new Date()));
				declare.setFdStatus(1);
				userService.update(user);
				declareDao.save(declare);
				try
				{
					/** 同步普贷通申报单状态. fg：是否进行同步操作 **/
					boolean fg=false;
					if(fg)
					{
						String applyId = this.queryFromPuTaiTong(declare);
						if (applyId != null && !applyId.equals("0"))
						{
							declare.setApplyId(applyId);
							declare.setFdStatus(1);
							declareDao.update(declare);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return "0";
			}
		}

		return "3";
	}

	/**
	 * 校验渠道,1正常,0停用，若无关联渠道或停用则返回false
	 */
	public boolean validChannel(String userId, Integer channelId) throws DeclareException
	{
		if (null != userId && !"".equals(userId))
		{
			User user = userService.findByOpenId(userService.findOpenIdByUserId(Integer.parseInt(userId)));
			if (null != user)
			{
				if(null != user.getChannel())
				{
					if (null != user.getChannel().getFdStauts() && "1".equals(user.getChannel().getFdStauts()))
					{
						return true;
					}
				}
				else
				{
					Channel channel = null;
					channel = (Channel) channelService.get(Channel.class, channelId);
					if (null != channel.getFdStauts() && "1".equals(channel.getFdStauts()))
					{
						return true;
					}
						
				}
				
			}
			if ("".equals(user.getChannel()))
			{
				
			}
		}
		
		return false;
	}

	/**
	 * 校验用户状态，1有效返回true，0无效返回false
	 */
	public boolean validUserStatus(String userId) throws DeclareException
	{
		User user = userService.findByOpenId(userService.findOpenIdByUserId(Integer.parseInt(userId)));
		if (user != null && user.getFdStatus() != null && "1".equals(user.getFdStatus()))
		{
			return true;
		}
		return false;
	}

	/**
	 * 重放攻击拦截 false拦截，true继续。
	 */
	@Override
	public boolean validReSubmit(HttpServletRequest request) throws DeclareException
	{
		TokenProcessor tokenProcessor = TokenProcessor.getInstance();
		if (tokenProcessor.isTokenValid(request))
		{
			return true;
		}
		else
		{
			tokenProcessor.saveToken(request);
			return false;
		}
	}

	/**
	 * 校验验证码
	 */
	@Override
	public boolean validCode(String userId, String phone, String fdValidateCode) throws DeclareException
	{
		Map<String, String> rst = new HashMap<String, String>();
		if (null != fdValidateCode && !"".equals(fdValidateCode))
		{
			String lastTime = dateToString(new Timestamp(System.currentTimeMillis()));
			String openId = userService.findOpenIdByUserId(Integer.parseInt(userId));
			UserSmsValid usv = userSmsValidService.findByOpenIdAndPhone(openId, phone);
			if (null != usv)
			{
				rst = userSmsValidService.validInputUserSmsCode(openId, phone, fdValidateCode, lastTime);
				if (null != rst && rst.containsKey("code200"))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 发送验证码
	 */
	@Override
	public String sendValidCode(HttpServletRequest request, String fdDeclarerPhone, String userId) throws DeclareException
	{
		Map<String, String> rst = new HashMap<String, String>();
		if (null != userId && !"".equals(userId))
		{
			if (null != fdDeclarerPhone && !"".equals(fdDeclarerPhone))
			{
				String code = RandomCodeUtil.createRandom(true, 4);
				if (null != code && !"".equals(code))
				{
					String openId = userService.findOpenIdByUserId(Integer.parseInt(userId));
					Long time = System.currentTimeMillis();
					Timestamp expTime = new Timestamp(time + 60 * 1000);
					String lastTime = dateToString(new Timestamp(time));
					if (null != rst && !rst.containsKey("code202"))
					{

						UserSmsValid usv = userSmsValidService.findByOpenIdAndPhone(openId, fdDeclarerPhone);
						if (null == usv)
						{
							usv = new UserSmsValid();
							usv.setFdOpenId(openId);
							usv.setFdPhone(fdDeclarerPhone);
						}
						usv.setFdLastTime(lastTime);
						usv.setFdLastCode(code);
						usv.setFdExpiredTime(dateToString(expTime));
						userSmsValidService.saveOrUpdate(usv);
						String result = smsService.sendSms(fdDeclarerPhone, code);
						if (!result.startsWith("-"))
						{
							return "success";
						}
					}

				}
			}
		}

		String msg = null;
		for (Entry<String, String> entry : rst.entrySet())
		{
			msg = entry.getValue();
		}
		return msg;
	}

	private static boolean validField(Declare declare)
	{
		if (null == declare.getChannel())
		{
			return false;
		}
		if (null == declare.getFdCustomerName() || "".equals(declare.getFdCustomerName()))
		{
			return false;
		}
		if (null == declare.getFdDeclarerPhone() || "".equals(declare.getFdDeclarerPhone()))
		{
			return false;
		}
		if (null == declare.getFdIdentityCard() || "".equals(declare.getFdIdentityCard()))
		{
			return false;
		}
		/*if (null == declare.getFdBorrowAmount() || "".equals(declare.getFdBorrowAmount()))
		{
			return false;
		}
		if (null == declare.getFdBorrowerPhone() || "".equals(declare.getFdBorrowAmount()))
		{
			return false;
		}
		if (null == declare.getFdBorrowTerm() || "".equals(declare.getFdBorrowTerm()))
		{
			return false;
		}
		if (null == declare.getFdMortgageAcreage() || "".equals(declare.getFdMortgageAcreage()))
		{
			return false;
		}
		if (null == declare.getFdMortgageAddress() || "".equals(declare.getFdMortgageAddress()))
		{
			return false;
		}
		if (null == declare.getFdMortgagePrice() || "".equals(declare.getFdMortgagePrice()))
		{
			return false;
		}*/
		if (null == declare.getFdValidateCode() || "".equals(declare.getFdValidateCode()))
		{
			return false;
		}
		if (null == declare.getFdIdentityCardPathP() || "".equals(declare.getFdIdentityCardPathP()))
		{
			return false;
		}
		if (null == declare.getFdPropertyCardPath() || "".equals(declare.getFdPropertyCardPath()))
		{
			return false;
		}
		return true;
	}

	private static String dateToString(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	@Override
	public String queryFromPuTaiTong(Declare declare) throws DeclareException
	{
		String result = "0";
		if (declare == null)
		{
			return "0";
		}
		String channelCode = declare.getChannel().getFdCode();

		if (declare.getChannel() == null)
		{
			return "0";
		}
		if (declare.getChannel().getFdCode() == null || "".equals(declare.getChannel().getFdCode()))
		{
			return "0";
		}

		String account = "";
		String password = "";
		if (channelCode.toUpperCase().startsWith(PuBanWeiXinUtil.CITY_BEIJING))
		{
			account = PuBanWeiXinUtil.LOGIN_ACCOUNT_BEIJING;
			password = PuBanWeiXinUtil.LOGIN_PASSWORD_BEIJING;
		}
		else if (channelCode.toUpperCase().startsWith(PuBanWeiXinUtil.CITY_SHANGHAI))
		{
			account = PuBanWeiXinUtil.LOGIN_ACCOUNT_SHANGHAI;
			password = PuBanWeiXinUtil.LOGIN_PASSWORD_SHANGHAI;
		}
		else
		{
			account = PuBanWeiXinUtil.LOGIN_ACCOUNT_SHENZHEN;
			password = PuBanWeiXinUtil.LOGIN_PASSWORD_SHENZHEN;
		}

		ApplyPo po = new ApplyPo();
		String fdCreatorId = this.loginPuDaiTong(account, password);
		if (fdCreatorId != null && !"".equals(fdCreatorId))
		{
			po.setFdType("0");
			po.setFdBorrowMoney(declare.getFdBorrowAmount());
			po.setFdBorrowTime(declare.getFdBorrowTerm());
			po.setFdCardNo(declare.getFdIdentityCard());
			po.setFdBorrowMobile(declare.getFdDeclarerPhone());
			if (declare.getFdBorrowAmount() != null)
			{
				po.setFdName(declare.getFdCustomerName() + " " + declare.getFdBorrowAmount() + "万元");
			}
			po.setFdGuestName(declare.getFdCustomerName());
			po.setFdHouse(declare.getFdMortgageAddress());
			po.setFdHouseAddress(declare.getFdMortgageAcreage());
			po.setFdHousePrice(declare.getFdMortgagePrice());
			String fdChannel = declare.getChannel().getFdName() + "-" + declare.getChannel().getFdCode();
			po.setFdChannel(fdChannel);
			po.setFdCreatorId(fdCreatorId);

			String sign = "";
			try
			{
				sign = MD5Util.getMD5String(po.getFdName() + po.getFdGuestName() + PuBanWeiXinUtil.SIANKEY);
				po.setSign(sign);
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}

			if (po != null)
			{
				result = this.sendDeclareToPuDaiTong(po);
			}
		}
		else
		{
			result = "0";
		}

		return result;
	}

	private String sendDeclareToPuDaiTong(ApplyPo po)
	{
		String applyId = "0";
		String s = HttpClientUtil.sendHttp(JSONObject.toJSONString(po), PuBanWeiXinUtil.API_IP + PuBanWeiXinUtil.saveApplyToLoanApiWEB);
		JSONObject jsonObj = JSON.parseObject(s);
		String codestr = (String) jsonObj.get("code");
		if (codestr.equals("0000"))
		{
			JSONObject obj = jsonObj.getJSONObject("data");
			if (obj != null)
			{
				applyId = obj.getString("applyId");
			}
		}
		return applyId;
	}

	private String loginPuDaiTong(String account, String password)
	{
		String userId = "";
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("userName", account);
		jSONObject.put("password", password);
		jSONObject.put("sign", PuBanWeiXinUtil.sign(account));
		String s = HttpClientUtil.sendHttp(jSONObject.toJSONString(), PuBanWeiXinUtil.API_IP + PuBanWeiXinUtil.loginToLoanApiWEB);
		JSONObject jsonObj = JSON.parseObject(s);
		String codestr = (String) jsonObj.get("code");
		if (codestr.equals("0000"))
		{
			JSONObject obj = jsonObj.getJSONObject("data");
			if (obj != null)
			{
				userId = obj.getString("fdUserId");
			}
		}
		return userId;
	}
}
