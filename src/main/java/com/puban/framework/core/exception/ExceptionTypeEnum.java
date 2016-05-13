package com.puban.framework.core.exception;

public enum ExceptionTypeEnum
{
	VALUE_NULL(301, "301");

	private int code;

	private String message;

	private ExceptionTypeEnum(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}
