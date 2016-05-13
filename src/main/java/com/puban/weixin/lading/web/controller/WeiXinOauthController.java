package com.puban.weixin.lading.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puban.weixin.lading.token.oauth.SNSUserInfo;
import com.puban.weixin.lading.token.oauth.WeiXinOauth2Token;
import com.puban.weixin.lading.util.Oauth2TokenUtil;
import com.puban.weixin.lading.util.WeiXinApiUtil;

@Controller
@RequestMapping(value="/weixin")
public class WeiXinOauthController
{

	@RequestMapping(value="/oauth",method=RequestMethod.GET)
	public String oauth(HttpServletRequest request, HttpServletResponse response,Model map)throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		SNSUserInfo snsUserInfo=null;
		/** code **/
		String code = request.getParameter("code");
		if (null != code && !code.equals("authdeny"))
		{
			/**accessToken **/
			WeiXinOauth2Token weiXinOauth2Token = Oauth2TokenUtil.getOauth2AccessToken(WeiXinApiUtil.APPID, WeiXinApiUtil.APPSCRET, code);

			/**ҳaccessToken**/
			snsUserInfo = Oauth2TokenUtil.getSNSUserInfo(weiXinOauth2Token.getAccessToken(), weiXinOauth2Token.getOpenId());
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		map.addAttribute("snsUserInfo", snsUserInfo);
		
		return "/index";
	}
}
