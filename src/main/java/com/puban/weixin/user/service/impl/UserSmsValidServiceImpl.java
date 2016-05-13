package com.puban.weixin.user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.puban.framework.core.exception.CommonStateEnum;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.user.dao.IUserSmsValidDao;
import com.puban.weixin.user.exception.UserSmsValidException;
import com.puban.weixin.user.model.UserSmsValid;
import com.puban.weixin.user.service.IUserSmsValidService;

public class UserSmsValidServiceImpl extends BaseServiceImpl<UserSmsValid> implements IUserSmsValidService
{
	@Resource(name = "userSmsValidDao")
	private IUserSmsValidDao userSmsValidDao;

	@Override
	public Map<String, String> validInputUserSmsCode(String openId, String phone, String lastInputCode, String lastInputTime) throws UserSmsValidException
	{
		Map<String, String> map = new HashMap<String, String>();

		if (phone == null || phone.trim().equals(""))
		{
			map.put("code001", "手机号码为空！");
			return map;
		}
		if (openId == null || openId.trim().equals(""))
		{
			map.put("code002", "用户不存在！");
			return map;
		}

		UserSmsValid userSmsValid = this.findByOpenIdAndPhone(openId, phone);

		if (lastInputCode == null || lastInputCode.trim().equals(""))
		{
			map.put("code003", "验证码为空！");
			return map;
		}
		if (userSmsValid != null && userSmsValid.getFdLastCode() != null && !userSmsValid.getFdLastCode().trim().equals(""))
		{
			if (userSmsValid.getFdLastCode().toLowerCase().equals(lastInputCode))
			{
				String fdExpiredTime=userSmsValid.getFdExpiredTime();
				if(fdExpiredTime!=null&&!fdExpiredTime.trim().equals(""))
				{
					if(checkUserSmsValidCodeExpired(lastInputTime,fdExpiredTime))
					{
						map.put("code200", "验证通过！");
						return map;
					}
					else
					{
						map.put("code201", "验证码失效！");
						return map;
					}
				}
			}
			else
			{
				map.put("code202", "验证码不匹配！");
			}
		}
		else
		{
			map.put("code005", "验证码为空！");
			return map;
		}

		return map;
	}

	@Override
	public UserSmsValid findByOpenIdAndPhone(String openId, String phone) throws UserSmsValidException
	{
		UserSmsValid userSmsValid = null;
		List<UserSmsValid> userSmsValidList = this.userSmsValidDao.find(UserSmsValid.class, 0, 1, " fdOpenId=:p1 and fdPhone=:p2", new String[] { openId, phone });
		if (userSmsValidList != null && userSmsValidList.size() > 0)
		{
			userSmsValid = userSmsValidList.get(0);
		}
		return userSmsValid;
	}

	private boolean checkUserSmsValidCodeExpired(String lastInputTime,String expiredTime)throws UserSmsValidException
	{
		boolean fg=false;
		try
		{
			Date expired=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expiredTime);
			Date lastInput=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastInputTime);
			if(lastInput.after(expired))
			{
				fg=false;
			}
			else
			{
				fg=true;
			}
		}
		catch (ParseException e)
		{
			fg=false;
			throw new UserSmsValidException(CommonStateEnum.BAD_DATE_VALID_CODE);
		}
		
		return fg;
	}

	@Override
	public Map<String, String> validSendUserSmsCode(String openId, String phone, String sendCode, String sendTime) throws UserSmsValidException
	{
		Map<String, String> map = new HashMap<String, String>();

		if (phone == null || phone.trim().equals(""))
		{
			map.put("code001", "手机号码为空！");
			return map;
		}
		if (openId == null || openId.trim().equals(""))
		{
			map.put("code002", "用户不存在！");
			return map;
		}

		UserSmsValid userSmsValid = this.findByOpenIdAndPhone(openId, phone);
		if(userSmsValid==null)
		{
			map.put("code200", "ok");
			return map;
		}
		else
		{
			if(checkUserSmsValidCodeExpired(sendTime,userSmsValid.getFdExpiredTime()))
			{
				map.put("code201", "验证码时间过期,请重新发送！");
			}
			else
			{
				map.put("code202", "验证码在有效时间内,请勿重复发送！");
			}
		}
		return map;
	}
}
