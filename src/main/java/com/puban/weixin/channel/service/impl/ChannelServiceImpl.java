package com.puban.weixin.channel.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.puban.framework.core.exception.CommonStateEnum;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.channel.dao.IChannelDao;
import com.puban.weixin.channel.exception.ChannelException;
import com.puban.weixin.channel.model.Channel;
import com.puban.weixin.channel.service.IChannelService;

public class ChannelServiceImpl extends BaseServiceImpl<Channel> implements IChannelService
{

	@Resource(name = "channelDao")
	private IChannelDao channelDao;

	@Override
	public Channel findByCode(String code) throws ChannelException
	{
		Channel channel = null;
		List<Channel> channelList = channelDao.find(Channel.class, 0, 1, " fdCode=:p1 and fdStatus='1' ", new String[] { code});
		if (channelList != null && channelList.size() > 0)
		{
			channel = channelList.get(0);
		}

		return channel;
	}

	@Override
	public Channel saveChannel(String code, String name) throws ChannelException
	{
		Channel channel = this.findByCode(code);
		if (channel != null)
		{
			throw new ChannelException(CommonStateEnum.CHANNEL_CODE_ERROR);
		}

		channel = new Channel();
		channel.setFdCode(code);
		channel.setFdName(name);
		channel.setFdStauts("1");
		channel.setFdCreateDate(new SimpleDateFormat("yyyy-MM:dd HH:mm:ss").format(new Date()));
		this.channelDao.save(channel);

		return channel;
	}

	@Override
	public List<Channel> importChannel(String filePath) throws ChannelException
	{
		List<Channel> channelList = new ArrayList<Channel>();
		try
		{
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists())
			{
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null)
				{
					String[] targets = lineTxt.split("=");
					Channel channel = this.findByCode(targets[0]);
					if (channel == null)
					{
						if (targets[1] != null && !targets[1].equals(""))
						{
							Channel newChannel = this.saveChannel(targets[0], targets[1]);
							channelList.add(newChannel);
						}
					}
					else
					{
						channel.setFdCode(targets[0]);
						channel.setFdName(targets[1]);
						this.channelDao.update(channel);
						channelList.add(channel);
					}
				}
				read.close();
			}
			else
			{

				System.out.println("找不到指定的文件");

			}
		}
		catch (Exception e)
		{
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return channelList;
	}
}
