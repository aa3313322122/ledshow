package com.puban.weixin.lading.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.message.resp.model.Article;
import com.puban.weixin.lading.message.resp.model.Music;

public class CustomServiceUtil
{
	/**
	 * 
	 * @param openId
	 * @param content
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content)
	{
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/***
	 * 
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId)
	{
		String jsonMsg = "{\"tosuser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 
	 * @param openId
	 * @param mediaId
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId)
	{
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 
	 * @param openId
	 * @param mediaId
	 * @param thumbMediaId
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId)
	{
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, thumbMediaId);
	}

	/**
	 * 
	 * @param openId
	 * @param music
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music)
	{
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.toJSONString(music));
		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
		return jsonMsg;

	}

	/***
	 * 
	 * @param openId
	 * @param articleList
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId, List<Article> articleList)
	{
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.toJSONString(articleList).toString().replaceAll("\"", "\\\""));
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}

	/***
	 * 
	 * @param accessToken
	 * @param jsonMsg
	 * @return
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg)
	{
		boolean result = false;
		String requestUrl = WeiXinApiUtil.CUSTOM_SERVICE_API.replace("ACCESS_TOKEN", accessToken);
		String requestMethod = "POST";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, jsonMsg);
		if (null != jsonObject)
		{
			int errorCode = jsonObject.getIntValue("errcode");
//			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode)
			{
				result = true;
			}
			else
			{
				result = false;
//				System.err.println(errorMsg);
			}
		}

		return result;
	}
}
