package com.puban.weixin.lading.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.qrcode.WeiXinQRCode;

public class QRCodeUtil
{
	/**
	 * 
	 * @param accessToken
	 * @param expireSeconds
	 * @param sceneId
	 * @return WeiXinQRCode
	 */
	public static WeiXinQRCode createTemporaryQRCode(String accessToken, int expireSeconds, int sceneId)
	{
		WeiXinQRCode weixinQRCode = null;
		String requestUrl = WeiXinApiUtil.QR_CODE_API.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"expire_seconds\":%d,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";
		String requestMethod = "POST";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, String.format(jsonMsg, expireSeconds, sceneId));
		if (null != jsonObject)
		{
			try
			{
				weixinQRCode = new WeiXinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject.getIntValue("expire_seconds"));
//				System.err.println("tiket:" + weixinQRCode.getTicket() + " expire_seconds:" + weixinQRCode.getExpireSeconds());
			}
			catch (Exception e)
			{
				weixinQRCode = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("errcode:" + errorCode + " errorMsg:" + errorMsg);
			}
		}
		return weixinQRCode;
	}

	/**
	 * 
	 * @param accessToken
	 * @param sceneId
	 * @return ticket
	 */
	public static String createPermanentQRCode(String accessToken, int sceneId)
	{
		String ticket = null;
		String requestUrl = WeiXinApiUtil.QR_CODE_API.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";
		String requestMethod = "POST";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, String.format(jsonMsg, sceneId));
		if (null != jsonObject)
		{
			try
			{
				ticket = jsonObject.getString("ticket");
//				System.err.println("ticket:" + ticket);
			}
			catch (Exception e)
			{
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("errorCode:" + errorCode + " errorMsg:" + errorMsg);
			}
		}
		return ticket;
	}

	/**
	 * 
	 * @param ticket
	 * @param savePath
	 * @return
	 */
	public static String getQRCode(String ticket, String savePath)
	{
		String filePath = null;

		String requestUrl = WeiXinApiUtil.QR_CODE_IMG.replace("TICKET", UrlEncodeUtil.urlEncodeUTF8(ticket));

		URL url = null;
		HttpsURLConnection conn = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;

		try
		{
			url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/"))
			{
				savePath += "/";
			}
			filePath = savePath + ticket + ".jpg";

			bis = new BufferedInputStream(conn.getInputStream());
			fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
			{
				fos.write(buf, 0, size);
			}

//			System.err.println("filePath=" + filePath);
		}
		catch (Exception e)
		{
			filePath = null;
			System.out.println(e);
		}
		finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch (IOException e)
				{
					fos = null;
					e.printStackTrace();
				}
			}
			if (bis != null)
			{
				try
				{
					bis.close();
				}
				catch (IOException e)
				{
					bis = null;
					e.printStackTrace();
				}
			}
			if (conn != null)
			{
				conn.disconnect();
			}
		}
		return filePath;
	}

}
