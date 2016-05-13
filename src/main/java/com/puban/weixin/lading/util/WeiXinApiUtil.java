package com.puban.weixin.lading.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WeiXinApiUtil
{
	/** token **/
	public final static String TOKEN = getValue("TOKEN");

	/** appid **/
	public final static String APPID = getValue("APPID");

	/** appscret **/
	public final static String APPSCRET = getValue("APPSCRET");

	/** (GET) **/
	public final static String TOKEN_URL_API = getValue("TOKEN_URL_API");;

	/** (POST) **/
	public final static String MENU_CREATE_API = getValue("MENU_CREATE_API");

	/** (GET) **/
	public final static String MENU_GET_API = getValue("MENU_GET_API");

	/** (GET) **/
	public final static String MENU_DELETE_API = getValue("MENU_DELETE_API");

	/** (POST) **/
	public final static String CUSTOM_SERVICE_API = getValue("CUSTOM_SERVICE_API");

	/** (GET) **/
	public final static String OAUTH2_CODE_API = getValue("OAUTH2_CODE_API");

	/** (GET) **/
	public final static String OAUTH2_TOKEN_API =  getValue("OAUTH2_TOKEN_API");

	/** (GET) **/
	public final static String OAUTH2_REFRESH_TOKEN_API =  getValue("OAUTH2_REFRESH_TOKEN_API");

	/** (GET) **/
	public final static String OAUTH2_USER_INFO_API = getValue("OAUTH2_USER_INFO_API");

	/** (POST) **/
	public final static String QR_CODE_API = getValue("QR_CODE_API");

	/**(GET)**/
	public final static String QR_CODE_IMG = getValue("QR_CODE_IMG");

	/** (GET) **/
	public final static String USER_INFO_API = getValue("USER_INFO_API");

	/** (GET) **/
	public final static String USER_ATTENTION_API = getValue("USER_ATTENTION_API");

	/** (GET) **/
	public final static String GROUP_QUERY_API = getValue("GROUP_QUERY_API");

	/** (POST) **/
	public final static String GROUP_CREATE_API = getValue("GROUP_CREATE_API");

	/** (POST) **/
	public final static String GROUP_UPDATE_API = getValue("GROUP_UPDATE_API");

	/** (POST) **/
	public final static String GROUP_MEMBERS_MOVE_API = getValue("GROUP_MEMBERS_MOVE_API");

	/** (POST/FORM) **/
	public final static String MEDIA_UPLOAD_API = getValue("MEDIA_UPLOAD_API");

	/** (GET) **/
	public final static String MEDIA_GET_API = getValue("MEDIA_GET_API");

	
	public static String getValue(String key)
	{
		Properties prop = new Properties(); 
    	try
		{
			prop.load(WeiXinApiUtil.class.getResourceAsStream("config.properties"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    	return prop.getProperty(key);
	}
}
