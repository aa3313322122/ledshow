package com.puban.weixin.user.service;

import java.util.Map;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.user.exception.UserSmsValidException;
import com.puban.weixin.user.model.UserSmsValid;

public interface IUserSmsValidService extends IBaseService<UserSmsValid>
{
	public UserSmsValid findByOpenIdAndPhone(String openId,String phone)throws UserSmsValidException;
	
	public  Map<String,String> validInputUserSmsCode(String openId,String phone,String lastInputCode,String lastInputTime)throws UserSmsValidException;
	
	public  Map<String,String> validSendUserSmsCode(String openId,String phone,String sendCode,String sendTime)throws UserSmsValidException;
}
