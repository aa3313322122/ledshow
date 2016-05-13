package com.puban.weixin.user.exception;

import com.puban.framework.core.exception.BaseException;
import com.puban.framework.core.exception.CommonStateEnum;

public class UserSmsValidException extends BaseException
{

	private static final long serialVersionUID = 1L;
	
	public UserSmsValidException(CommonStateEnum type)
	{
		super(type);
	}
}
