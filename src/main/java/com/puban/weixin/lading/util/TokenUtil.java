package com.puban.weixin.lading.util;

import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.token.Token;

public class TokenUtil
{
	public static Token getToken(String appid, String appsecret)
	{
		Token token = null;
		String requestUrl = WeiXinApiUtil.TOKEN_URL_API.replace("APPID", appid).replace("APPSCRET", appsecret);
		String requestMethod = "GET";
		String outputStr = null;
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, outputStr);

		if (null != jsonObject)
		{
			String accessToken = jsonObject.getString("access_token");
			int expiresIn = jsonObject.getIntValue("expires_in");
			try
			{
				token = new Token();
				token.setAccessToken(accessToken);
				token.setExpiresIn(expiresIn);
			}
			catch (Exception e)
			{
				token = null;
				e.printStackTrace();
			}
		}

		return token;
	}
}
