package com.puban.weixin.api.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil
{

	public static String sendHttp(String message, String url)
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse res = null;
		String context = null;
		try
		{

			HttpPost request = new HttpPost(url);
			// StringEntity formEntiry = new StringEntity(line);
			// request.setHeader("Accept", "text/plain");
			// request.setHeader("Content-Type", "gzip");
			request.setHeader("Accept-Charset", "utf-8");
			// request.setHeader("Transfer-Encoding", "chunked");

			ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
			byte[] ss = message.getBytes("UTF-8");

			ByteArrayEntity formEntiry = new ByteArrayEntity(ss);
			request.setEntity(formEntiry);
			res = httpClient.execute(request);
			HttpEntity entity = res.getEntity();
			context = EntityUtils.toString(entity);
			// System.out.println(context);
			// context = GzipUtil.uncompress(context);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			httpClient.getConnectionManager().shutdown();
		}
		return context;
	}

	public static String signResult(String sign)
	{
		String rs = "";
		try
		{
			rs = MD5Util.getMD5String(sign + PuBanWeiXinUtil.SIANKEY);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args)
	{
		String userId = loginPuDaiTong("szbd", "szbd");
		ApplyPo po = new ApplyPo();
		po.setFdType("0");
		//po.setFdBorrowMoney("1");
		po.setFdBorrowTime("1");
		po.setFdCardNo("1");
		po.setFdBorrowMobile("1");
		po.setFdName("1万元");
		po.setFdGuestName("1");
		po.setFdHouse("1");
		po.setFdHouseAddress("1");
		//po.setFdHousePrice("1");
		po.setFdChannel("1");
		po.setFdCreatorId(userId);
		String sign = "";
		try
		{
			sign = MD5Util.getMD5String(po.getFdName() + po.getFdGuestName() + PuBanWeiXinUtil.SIANKEY);
			po.setSign(sign);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		sendDeclareToPuDaiTong(po);
	}

	public static String sendDeclareToPuDaiTong(ApplyPo po)
	{
		String applyId = "0";
		String s = HttpClientUtil.sendHttp(JSONObject.toJSONString(po), PuBanWeiXinUtil.API_IP + PuBanWeiXinUtil.saveApplyToLoanApiWEB);
		JSONObject jsonObj = JSON.parseObject(s);
		String codestr = (String) jsonObj.get("code");
		if (codestr.equals("0000"))
		{
			JSONObject obj = jsonObj.getJSONObject("data");
			if (obj != null)
			{
				applyId = obj.getString("applyId");
			}
		}
		return applyId;
	}

	private static String loginPuDaiTong(String account, String password)
	{
		String userId = "";
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("userName", account);
		jSONObject.put("password", password);
		jSONObject.put("sign", PuBanWeiXinUtil.sign(account));

		String s = HttpClientUtil.sendHttp(jSONObject.toJSONString(), PuBanWeiXinUtil.API_IP + PuBanWeiXinUtil.loginToLoanApiWEB);
		JSONObject jsonObj = JSON.parseObject(s);
		String codestr = (String) jsonObj.get("code");
		if (codestr.equals("0000"))
		{
			JSONObject obj = jsonObj.getJSONObject("data");
			if (obj != null)
			{
				userId = obj.getString("fdUserId");
			}
		}
		return userId;
	}
}
