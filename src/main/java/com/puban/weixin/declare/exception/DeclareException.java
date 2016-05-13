package com.puban.weixin.declare.exception;

import com.puban.framework.core.exception.BaseException;
import com.puban.framework.core.exception.ExceptionTypeEnum;

public class DeclareException extends BaseException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeclareException(ExceptionTypeEnum type)
	{
		super(type);
	}
	

}
