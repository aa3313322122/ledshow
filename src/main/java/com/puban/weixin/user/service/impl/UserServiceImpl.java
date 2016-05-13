package com.puban.weixin.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.user.dao.IUserDao;
import com.puban.weixin.user.exception.UserException;
import com.puban.weixin.user.model.User;
import com.puban.weixin.user.service.IUserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService
{
	@Resource(name = "userDao")
	private IUserDao userDao;

	@Override
	public User subscribeWeiXin(String openId) throws UserException
	{
		User user = this.findByOpenId(openId);
		if (user == null)
		{
			user = new User();
			user.setFdOpenId(openId);
			user.setFdSubcribe(true);
			user.setFdStatus("1");
			user.setFdCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			userDao.save(user);
		}
		else
		{
			user.setFdSubcribe(true);
			userDao.update(user);
		}

		return user;
	}

	@Override
	public User findByOpenId(String openId) throws UserException
	{
		User user = null;
		List<User> userList = userDao.find(User.class, -1, -1, " fdOpenId=:p1", new String[] { openId });
		if (userList != null && userList.size() > 0)
		{
			user = userList.get(0);
		}
		return user;
	}

	@Override
	public User unSubscribeWeiXin(String openId) throws UserException
	{
		User user = this.findByOpenId(openId);
		if (user != null)
		{
			user.setFdSubcribe(false);
			userDao.update(user);
		}

		return user;
	}

	@Override
	public Channel findUserChannel(String openId) throws UserException
	{
		Channel channel = null;
		User user = this.findByOpenId(openId);
		if (user != null)
		{
			if (user.getChannel() != null)
			{
				channel = user.getChannel();
			}
		}
		return channel;
	}

	@Override
	public String findOpenIdByUserId(Integer userId) throws UserException
	{
		User user = (User) userDao.get(User.class, userId);
		if (user != null)
		{
			return user.getFdOpenId();
		}
		else
		{
			return null;
		}
	}

}
