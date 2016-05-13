package com.puban.weixin.user.service;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.user.exception.UserException;
import com.puban.weixin.user.model.User;

public interface IUserService extends IBaseService<User>
{

	public User subscribeWeiXin(String openId)throws UserException;

	public User findByOpenId(String openId)throws UserException;
	
	public String findOpenIdByUserId(Integer userId)throws UserException;

	public User unSubscribeWeiXin(String openId)throws UserException;

	public Channel findUserChannel(String openId)throws UserException;
}
