package com.puban.weixin.channel.exception;

import com.puban.framework.core.exception.BaseException;
import com.puban.framework.core.exception.CommonStateEnum;

public class ChannelException extends BaseException
{

	private static final long serialVersionUID = 1L;

	public ChannelException(CommonStateEnum type)
	{
		super(type);
	}
}
