package com.puban.framework.core.exception;

import com.alibaba.fastjson.JSONObject;

public enum CommonStateEnum
{
	
	OK(200, 1, "成功"),
	
	ACCOUNT_PASS_ERROR(103, 1, "用户账号号或密码错误"),

	NO_THIS_ACCOUNT(101, 1, "用户账号不存在"),

	PASSWORD_ERROR(102, 1, "密码错误"), 

	BAD_VALI_CODE(103, 1, "验证码错误"),
	
	CHANNEL_CODE_ERROR(104, 1, "数据已存在！"),
	
	BAD_DATE_VALID_CODE(105, 1, "时间格式错误！");
	
	private int state;

	private int stateCode;

	private String message;

	private CommonStateEnum(int state, int stateCode, String message)
	{
		this.state = state;
		this.stateCode = stateCode;
		this.message = message;
	}

	public int getState()
	{
		return state;
	}

	public int getStateCode()
	{
		return stateCode;
	}

	public final String getMessage()
	{
		return message;
	}

	public final static String getMessage(int state, int stateCode)
	{
		for (int i = 0; i < CommonStateEnum.values().length; i++)
		{
			if (CommonStateEnum.values()[i].getState() == state && CommonStateEnum.values()[i].getStateCode() == stateCode)
			{
				return CommonStateEnum.values()[i].getMessage();
			}
		}
		return null;
	}

	public final static CommonStateEnum getCommonStateEnum(int state, int stateCode)
	{
		for (int i = 0; i < CommonStateEnum.values().length; i++)
		{
			if (CommonStateEnum.values()[i].getState() == state && CommonStateEnum.values()[i].getStateCode() == stateCode)
			{
				return CommonStateEnum.values()[i];
			}
		}
		return null;
	}

	public String getJSONString()
	{
		JSONObject obj = new JSONObject();
		obj.put(FieldEnum.CODE.getField(), this.getState());
		obj.put(FieldEnum.STATE_CODE.getField(), this.getStateCode());
		obj.put(FieldEnum.MESSAGE.getField(), this.getMessage());
		return obj.toString();
	}

	public static enum FieldEnum
	{

		CODE("code"), STATE_CODE("stateCode"), MESSAGE("message");

		private String field;

		private FieldEnum(String field)
		{
			this.field = field;
		}

		public String getField()
		{
			return this.field;
		}
	}
	
	
}