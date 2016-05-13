package com.puban.weixin.lading.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puban.weixin.lading.user.group.WeiXinGroup;

public class GroupUtil
{
	/***
	 * 
	 * @param accessToken
	 * @return
	 */
	public static List<WeiXinGroup> getGroups(String accessToken)
	{
		List<WeiXinGroup> weiXinGroupList = null;
		String requestUrl = WeiXinApiUtil.GROUP_QUERY_API.replace("ACCESS_TOKEN", accessToken);
		String requestMethod = "GET";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, null);
		if (null != jsonObject)
		{
			try
			{
				weiXinGroupList = JSON.parseArray(jsonObject.getJSONArray("groups").toJSONString(), WeiXinGroup.class);
			}
			catch (Exception e)
			{
				weiXinGroupList = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("errcode:" + errorCode + " errmsg:" + errorMsg);
			}
		}
		return weiXinGroupList;
	}

	/***
	 * 
	 * @param accessToken
	 * @param groupName
	 * @return
	 */
	public static WeiXinGroup createGroup(String accessToken, String groupName)
	{
		WeiXinGroup weiXinGroup = null;
		String requestUrl = WeiXinApiUtil.GROUP_CREATE_API.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"group\":{\"name\":\"%s\"}}";
		String requestMethod = "POST";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, String.format(jsonData, groupName));
		if (null != jsonObject)
		{
			try
			{
				weiXinGroup = new WeiXinGroup();
				weiXinGroup.setId(jsonObject.getJSONObject("group").getIntValue("id"));
				weiXinGroup.setName(jsonObject.getJSONObject("group").getString("name"));
			}
			catch (Exception e)
			{
				weiXinGroup = null;
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.err.println("errcode:" + errorCode + " errmsg:" + errorMsg);
			}
		}
		return weiXinGroup;
	}

	/***
	 * 
	 * @param accessToken
	 * @param groupId
	 * @param groupName
	 * @return true | false
	 */
	public static boolean updateGroup(String accessToken, int groupId, String groupName)
	{
		boolean result = false;

		String requestUrl = WeiXinApiUtil.GROUP_UPDATE_API.replace("ACCESS_TOKEN", accessToken);

		String jsonData = "{\"group\":{\"id\":%d,\"name\":\"%s\"}}";

		String requestMethod = "POST";

		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, requestMethod, String.format(jsonData, groupId, groupName));

		if (null != jsonObject)
		{
			int errorCode = jsonObject.getIntValue("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode)
			{
				result = true;
				System.err.println("errcode:" + errorCode + " errmsg:" + errorMsg);
			}
			else
			{
				result = false;
				System.err.println("errcode:" + errorCode + " errmsg:" + errorMsg);
			}
		}

		return result;
	}
	
	public static boolean updateMemberGroup(String accessToken,String openId,int groupId)
	{
		boolean result=false;
		String requestUrl=WeiXinApiUtil.GROUP_MEMBERS_MOVE_API.replace("ACCESS_TOKEN", accessToken);
		String jsonData="{\"openid\":\"%s\",\"to_groupid\":%d}";
		String requestMethod="POST";
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, requestMethod, String.format(jsonData, openId,groupId));
		if(null!=jsonObject)
		{
			int errorCode=jsonObject.getIntValue("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if(0==errorCode)
			{
				result=true;
				System.err.println("errcode:"+errorCode+" errmsg:"+errorMsg);
			}
			else
			{
				result=false;
				System.err.println("errcode:"+errorCode+" errmsg:"+errorMsg);
			}
		}
		return result;
	}

	public static void main(String[] args)
	{
		String accessToken = TokenUtil.getToken(WeiXinApiUtil.APPID, WeiXinApiUtil.APPSCRET).getAccessToken();

//		WeiXinGroup group = GroupUtil.createGroup(accessToken, "groupName_1");
//		System.out.println(group.getId()+"--->"+group.getName()+"---->"+group.getCount());

		List<WeiXinGroup> weiXinGroupList = GroupUtil.getGroups(accessToken);
		for (WeiXinGroup weiXinGroup : weiXinGroupList)
		{
			if(weiXinGroup.getId()==100)
			{
				GroupUtil.updateGroup(accessToken, weiXinGroup.getId(), "groupName_2");
			}
//			System.err.println("id:" + weiXinGroup.getId() + "name:" + weiXinGroup.getName() + " count:" + weiXinGroup.getCount());
		}
	}
}
