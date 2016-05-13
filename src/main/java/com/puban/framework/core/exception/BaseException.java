package com.puban.framework.core.exception;

import com.alibaba.fastjson.JSONObject;
import com.puban.framework.core.exception.CommonStateEnum.FieldEnum;

public class BaseException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private String message;

	private int code;

	private int stateCode;

	public BaseException(CommonStateEnum type)
	{
		super(type.getMessage());
		this.code = type.getState();
		this.stateCode = type.getStateCode();
		this.message = type.getMessage();
	}

	public BaseException(CommonStateEnum type, String message)
	{
		super(message);
		this.code = type.getState();
		this.message = message;
	}

	public BaseException(ExceptionTypeEnum type)
	{
		super(type.getMessage());
		this.code = type.getCode();
		this.message = type.getMessage();
	}

	public BaseException(ExceptionTypeEnum type, String message)
	{
		super(message);
		this.code = type.getCode();
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	public int getCode()
	{
		return code;
	}

	public int getStateCode()
	{
		return stateCode;
	}

	public String getJSONString()
	{
		JSONObject obj = new JSONObject();
		obj.put(FieldEnum.CODE.getField(), this.getCode());
		obj.put(FieldEnum.STATE_CODE.getField(), this.getStateCode());
		obj.put(FieldEnum.MESSAGE.getField(), this.getMessage());
		return obj.toString();
	}
}
