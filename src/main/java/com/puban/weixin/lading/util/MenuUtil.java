package com.puban.weixin.lading.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.menu.Menu;

public class MenuUtil
{
	/**
	 * @param menu
	 * @param accessToken
	 * @return 
	 */
	public static boolean createMenu(Menu menu, String accessToken)
	{
		boolean result = false;
		String requestMethod = "POST";
		String requestUrl = WeiXinApiUtil.MENU_CREATE_API.replace("ACCESS_TOKEN", accessToken);
		String jsonMenu = JSON.toJSONString(menu);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, jsonMenu);
		if (null != jsonObject)
		{
			int errorCode = jsonObject.getIntValue("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode)
			{
				result = true;
			}
			else
			{
				result = false;
				System.err.println(errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * @param accessToken
	 * @return
	 */
	public static String getMenu(String accessToken)
	{
		String result=null;
		String requestUrl=WeiXinApiUtil.MENU_GET_API.replace("ACCESS_TOKEN", accessToken);
		String requestMethod="GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if(null!=jsonObject)
		{
			result=jsonObject.toJSONString();
		}
		return result;
	}
	
	/**
	 * @param accessToken
	 * @return 
	 */
	public static boolean deleteMenu(String accessToken)
	{
		boolean result=false;
		String requestUrl=WeiXinApiUtil.MENU_DELETE_API.replace("ACCESS_TOKEN", accessToken);
		String requestMethod="GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if(null!=jsonObject)
		{
			int errorCode=jsonObject.getIntValue("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if(0==errorCode)
			{
				result=true;
			}
			else
			{
				result=false;
				System.err.println(errorMsg);
			}
		}
		return result;
	}
}
