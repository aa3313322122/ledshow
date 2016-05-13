package com.puban.weixin.channel.service;

import java.util.List;

import com.puban.framework.core.service.IBaseService;
import com.puban.weixin.channel.exception.ChannelException;
import com.puban.weixin.channel.model.Channel;

public interface IChannelService extends IBaseService<Channel>
{
	public Channel findByCode(String code)throws ChannelException;
	
	public Channel saveChannel(String code,String name)throws ChannelException;
	
	public List<Channel> importChannel(String filePath)throws ChannelException;
}
