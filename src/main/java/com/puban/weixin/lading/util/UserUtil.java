package com.puban.weixin.lading.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.token.oauth.SNSUserInfo;
import com.puban.weixin.lading.token.oauth.WeiXinOauth2Token;
import com.puban.weixin.lading.user.WeiXinUserInfo;
import com.puban.weixin.lading.user.WeiXinUserList;

public class UserUtil
{
	/**
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WeiXinUserInfo getUserInfo(String accessToken, String openId)
	{
		WeiXinUserInfo weiXinUserInfo = null;
		String requestUrl = WeiXinApiUtil.USER_INFO_API.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		String requestMethod = "GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if (null != jsonObject)
		{
			try
			{
				weiXinUserInfo = new WeiXinUserInfo();
				weiXinUserInfo.setOpenId(jsonObject.getString("openid"));
				weiXinUserInfo.setSubscribe(jsonObject.getIntValue("subscribe"));
				weiXinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
				weiXinUserInfo.setNickname(jsonObject.getString("nickname"));
				weiXinUserInfo.setSex(jsonObject.getIntValue("sex"));
				weiXinUserInfo.setCountry(jsonObject.getString("country"));
				weiXinUserInfo.setProvince(jsonObject.getString("province"));
				weiXinUserInfo.setCity(jsonObject.getString("city"));
				weiXinUserInfo.setLanguage(jsonObject.getString("language"));
				weiXinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			}
			catch (Exception e)
			{
				if (0 == weiXinUserInfo.getSubscribe())
				{
//					System.err.println(weiXinUserInfo.getOpenId());
				}
				else
				{
					int errorCode = jsonObject.getIntValue("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					System.err.println("errorCode:" + errorCode + " errorMsg:" + errorMsg);
				}
			}
		}
		return weiXinUserInfo;
	}

	/****
	 * 
	 * @param accessToken
	 * @param nextOpneId
	 * @return WeiXinUserList
	 */
	public static WeiXinUserList getUserList(String accessToken, String nextOpneId)
	{
		WeiXinUserList weiXinUserList = null;
		if (null == nextOpneId)
		{
			nextOpneId = "";
		}
		String requestUrl = WeiXinApiUtil.USER_ATTENTION_API.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("NEXT_OPENID", nextOpneId);
		String requestMethod = "GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if (null != jsonObject)
		{
			try
			{
				weiXinUserList = new WeiXinUserList();
				weiXinUserList.setTotal(jsonObject.getIntValue("total"));
				weiXinUserList.setCount(jsonObject.getIntValue("count"));
				weiXinUserList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				List<String> openIdList = JSON.parseArray(dataObject.getJSONArray("openid").toJSONString(), String.class);
				weiXinUserList.setOpenIdList(openIdList);
			}
			catch (Exception e)
			{
				weiXinUserList = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("errcode:" + errorCode + " errmsg:" + errorMsg);
			}
		}
		return weiXinUserList;
	}

	public static SNSUserInfo getSNSUserInfoByCode(String code)
	{
		SNSUserInfo snsUserInfo = null;
		if (null != code && !code.equals("authdeny"))
		{
			/** accessToken **/
			WeiXinOauth2Token weiXinOauth2Token = Oauth2TokenUtil.getOauth2AccessToken(WeiXinApiUtil.APPID, WeiXinApiUtil.APPSCRET, code);

			/** accessToken **/
			if(weiXinOauth2Token.getAccessToken()==null||"".equals(weiXinOauth2Token.getAccessToken()))
			{
				WeiXinOauth2Token refreshWeiXinOauth2Token=Oauth2TokenUtil.refreshOauth2AccessToken(WeiXinApiUtil.APPID, weiXinOauth2Token.getRefreshToken());
				snsUserInfo = Oauth2TokenUtil.getSNSUserInfo(refreshWeiXinOauth2Token.getAccessToken(), refreshWeiXinOauth2Token.getOpenId());
			}
			else
			{
				snsUserInfo = Oauth2TokenUtil.getSNSUserInfo(weiXinOauth2Token.getAccessToken(), weiXinOauth2Token.getOpenId());
			}
		}

		return snsUserInfo;
	}

	public static void main(String[] args)
	{
		String accessToken = TokenUtil.getToken(WeiXinApiUtil.APPID, WeiXinApiUtil.APPSCRET).getAccessToken();

		WeiXinUserList weiXinUserList = UserUtil.getUserList(accessToken, "");
		System.err.println(weiXinUserList.getTotal());
		System.err.println(weiXinUserList.getCount());
		System.err.println(weiXinUserList.getOpenIdList().toString());
		System.err.println(weiXinUserList.getNextOpenId());

		List<String> openids = weiXinUserList.getOpenIdList();
		for (int t = 0; t < openids.size(); t++)
		{
			String openid = openids.get(t);
			WeiXinUserInfo weiXinUserInfo = UserUtil.getUserInfo(accessToken, openid);
			System.err.println(weiXinUserInfo.getNickname());
		}
	}
}
