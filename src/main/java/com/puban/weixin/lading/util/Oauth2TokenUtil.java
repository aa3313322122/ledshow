package com.puban.weixin.lading.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.token.oauth.SNSUserInfo;
import com.puban.weixin.lading.token.oauth.WeiXinOauth2Token;

public class Oauth2TokenUtil
{
	/**
	 * 
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return WeiXinOauth2Token
	 */
	public static WeiXinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code)
	{
		WeiXinOauth2Token wat = null;
		String requestUrl = WeiXinApiUtil.OAUTH2_TOKEN_API.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		String requestMethod = "GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if (null != jsonObject)
		{
			try
			{
				wat = new WeiXinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			}
			catch (Exception e)
			{
				wat = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("(" + errorCode + ") " + errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 
	 * @param appId
	 * @param refreshToken
	 * @return WeiXinOauth2Token
	 */
	public static WeiXinOauth2Token refreshOauth2AccessToken(String appId, String refreshToken)
	{
		WeiXinOauth2Token wat = null;
		String requestUrl = WeiXinApiUtil.OAUTH2_REFRESH_TOKEN_API.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		String requestMethod = "GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if (null != jsonObject)
		{
			try
			{
				wat = new WeiXinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			}
			catch (Exception e)
			{
				wat = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("(" + errorCode + ") " + errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 
	 * @param accessToken
	 * @param openId
	 * @return SNSUserInfo
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId)
	{
		SNSUserInfo snsUserInfo = null;
		String requestUrl = WeiXinApiUtil.OAUTH2_USER_INFO_API.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		String requestMethod = "GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if (null != jsonObject)
		{
			try
			{
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getIntValue("sex"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				snsUserInfo.setPrivilegeList(JSON.parseArray(jsonObject.getJSONArray("privilege").toJSONString(), String.class));
			}
			catch (Exception e)
			{
				snsUserInfo = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("(" + errorCode + ") " + errorMsg);
			}
		}
		return snsUserInfo;
	}
	
	public static String getOauth2TargetUserHref(String appId,String redirectUri,String scope)
	{
		
		String result=WeiXinApiUtil.OAUTH2_CODE_API.replace("APPID", appId);
		
		if(redirectUri!=null&&!redirectUri.equals(""))
		{
			String enCodeRedirectUri=UrlEncodeUtil.urlEncodeUTF8(redirectUri);
			result=result.replace("REDIRECT_URI",enCodeRedirectUri);
		}
		if(scope==null||scope.equals(""))
		{
			scope="snsapi_userinfo";
		}
		result=result.replace("SCOPE", scope);
		
		return result;
	}
}
