package com.puban.weixin.lading.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.media.WeiXinMedia;

public class MediaUtil
{
	/**
	 * 
	 * @param accessToken
	 * @param type(image\voice\video\thumb)
	 * @param mediaFileUrl
	 * @return WeiXinMedia
	 */
	public static WeiXinMedia uploadMedia(String accessToken, String type, String mediaFileUrl)
	{
		WeiXinMedia weiXinMedia = null;
		String uploadMediaUrl = WeiXinApiUtil.MEDIA_UPLOAD_API.replace("ACCESS_TOKEN", accessToken);
		uploadMediaUrl = uploadMediaUrl.replace("TYPE", type);

		String boundary = "------------7da2e536604c8";

		try
		{
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			String contentType = meidaConn.getHeaderField("Content-Type");

			String fileExt = MediaUtil.getFileExt(contentType);

			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition:form-data;name=\"media\";filename=\"file1%s\"\r\n", fileExt).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
			{
				outputStream.write(buf, 0, size);
			}

			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null)
			{
				buffer.append(str);
			}

			bufferedReader.close();
			inputStreamReader.close();

			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
//			System.err.println(jsonObject);
			weiXinMedia = new WeiXinMedia();
			weiXinMedia.setType(jsonObject.getString("type"));

			if ("thumb".equals(type))
			{
				weiXinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			}
			else
			{
				weiXinMedia.setMediaId(jsonObject.getString("media_id"));
			}
			weiXinMedia.setCreatedAt(jsonObject.getString("created_at"));
		}
		catch (Exception e)
		{
			weiXinMedia = null;
			System.err.println(e.getMessage());
		}

		return weiXinMedia;
	}

	/**
	 * @param accessToken
	 * @param mediaId
	 * @param savePath
	 * @return
	 */
	public static String getMedia(String accessToken, String mediaId, String savePath)
	{
		String filePath = null;

		String requestUrl = WeiXinApiUtil.MEDIA_GET_API.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("MEDIA_ID", mediaId);

		try
		{
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/"))
			{
				savePath += "/";
			}
			
			String fileExt = MediaUtil.getFileExt(conn.getHeaderField("Content-Type"));
			
			filePath = savePath + mediaId + fileExt;
			
			BufferedInputStream bis=new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos=new FileOutputStream(new File(filePath));
			byte[] buf=new byte[8096];
			int size=0;
			while((size=bis.read(buf))!=-1)
			{
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();
			
			conn.disconnect();
//			System.err.println(filePath);
		}
		catch (Exception e)
		{
			filePath=null;
			System.err.println(e.getMessage());
		}

		return filePath;
	}

	/**
	 * 
	 * @param contentType
	 * @return
	 */
	public static String getFileExt(String contentType)
	{
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
		{
			fileExt = ".jpg";
		}
		else if ("audio/mpeg".equals(contentType))
		{
			fileExt = ".mp3";
		}
		else if ("audio/amr".equals(contentType))
		{
			fileExt = ".amr";
		}
		else if ("video/mp4".equals(contentType))
		{
			fileExt = ".mp4";
		}
		else if ("video/mpeg4".equals(contentType))
		{
			fileExt = ".mp4";
		}
		return fileExt;
	}
	
	public static void main(String[] args)
	{
		String accessToken = TokenUtil.getToken(WeiXinApiUtil.APPID, WeiXinApiUtil.APPSCRET).getAccessToken();
		String mediaFileUrl="http://www.zinyun.com/weixin/voice/test.mp3";
		WeiXinMedia weiXinMedia=MediaUtil.uploadMedia(accessToken, "voice", mediaFileUrl);
		System.err.println("mediaId:"+weiXinMedia.getMediaId());
		System.err.println("type:"+weiXinMedia.getType());
		System.err.println("createAt:"+weiXinMedia.getCreatedAt());
		MediaUtil.getMedia(accessToken, weiXinMedia.getMediaId(), "/usr/local/web/tomcat8/tomcat-80/webapps/weixin/voice");
		
	}
}
