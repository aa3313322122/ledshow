package com.puban.weixin.api.util;

import java.io.UnsupportedEncodingException;

public class PuBanWeiXinUtil
{
	/**北京渠道**/
	public  static  final  String CITY_BEIJING="BJ";
	
	/**北京渠道报单登录名**/
	public  static  final  String LOGIN_ACCOUNT_BEIJING="bjbd";
	
	/**北京渠道报单密码**/
	public  static  final  String LOGIN_PASSWORD_BEIJING="bjbd";
	
	/**深圳渠道**/
	public  static  final  String CITY_SHENZHEN="SZ";
	
	/**深圳渠道报单登录名**/
	public  static  final  String LOGIN_ACCOUNT_SHENZHEN="szbd";
	
	/**深圳渠道报单密码**/
	public  static  final  String LOGIN_PASSWORD_SHENZHEN="szbd";
	
	/**上海渠道**/
	public  static  final  String CITY_SHANGHAI="SH";
	
	/**上海渠道报单登录名**/
	public  static  final  String LOGIN_ACCOUNT_SHANGHAI="shbd";
	
	/**上海渠道报单密码**/
	public  static  final  String LOGIN_PASSWORD_SHANGHAI="shbd";
	
	/** 加密的key **/
	public static final String SIANKEY = "puban";
	
	/**登录接口相关**/
	public static final String API_IP="http://www.szpbzb.com/loan";
	
	public static final String loginToLoanApiWEB="/puban/api/puban_api/pubanLoginAction.do?method=login";
	
	/**保存申报单至普贷通系统**/
	public static final String saveApplyToLoanApiWEB="/puban/api/puban_api/pubanApplyAction.do?method=save";
	
	/**加密签名函数**/
	public  static String sign(String s)
	{
		String rs="";
		try
		{
			rs = MD5Util.getMD5String(s+PuBanWeiXinUtil.SIANKEY);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
}
