package com.puban.weixin.lading.menu;

import com.puban.weixin.lading.menu.manager.MenuRootManager;
import com.puban.weixin.lading.token.Token;
import com.puban.weixin.lading.util.MenuUtil;
import com.puban.weixin.lading.util.Oauth2TokenUtil;
import com.puban.weixin.lading.util.TokenUtil;
import com.puban.weixin.lading.util.WeiXinApiUtil;

public class MenuManager extends MenuRootManager
{

	@Override
	public Menu getMenu()
	{
		ViewButton btn31=new ViewButton();
		btn31.setName(WeiXinApiUtil.getValue("menu.declare"));
		btn31.setType("view");
		btn31.setUrl(Oauth2TokenUtil.getOauth2TargetUserHref(WeiXinApiUtil.APPID, WeiXinApiUtil.getValue("domain.site")+WeiXinApiUtil.getValue("oath.redirectUri.declare"), "snsapi_userinfo"));
		
		
		ViewButton btn32=new ViewButton();
		btn32.setName(WeiXinApiUtil.getValue("menu.my.order"));
		btn32.setType("view");
		btn32.setUrl(Oauth2TokenUtil.getOauth2TargetUserHref(WeiXinApiUtil.APPID, WeiXinApiUtil.getValue("domain.site")+WeiXinApiUtil.getValue("oath.redirectUri.order"), "snsapi_userinfo"));
		
		ViewButton btn33=new ViewButton();
		btn33.setName(WeiXinApiUtil.getValue("menu.about.us"));
		btn33.setType("view");
		btn33.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MTA3OTUxNw==&mid=502617319&idx=1&sn=538f9f29e8578df2507f0f5345253d50&scene=0&previewkey=n7tT4fHmXJKSWv8HBLdYSMNS9bJajjJKzz%2F0By7ITJA%3D#wechat_redirect");
		
		
//		ClickButton btn32=new ClickButton();
//		btn32.setName("team");
//		btn32.setType("click");
//		btn32.setKey("team");
		
//		ClickButton btn33=new ClickButton();
//		btn33.setName("temporaryQRCode");
//		btn33.setType("click");
//		btn33.setKey("temporaryQRCode");
//		
//		ClickButton btn34=new ClickButton();
//		btn34.setName("permanentQRCode");
//		btn34.setType("click");
//		btn34.setKey("permanentQRCode");
		
//		ComplexButton mainBtn3=new ComplexButton();
//		mainBtn3.setName("view");
//		mainBtn3.setSub_button(new Button[]{btn31,btn32});
		
		Menu menu=new Menu();
		menu.setButton(new Button[]{btn31,btn32,btn33});
		
		return menu;
	}
	public static void main(String[] args)
	{
		String appid=WeiXinApiUtil.APPID;
		String appsecret=WeiXinApiUtil.APPSCRET;
		Token token=TokenUtil.getToken(appid, appsecret);
		
		if(null!=token)
		{
			boolean result=MenuUtil.createMenu(new MenuManager().getMenu(), token.getAccessToken());
			if(result)
			{
				System.err.println(result);
			}
			else
			{
				System.err.println(result);
			}
		}
		
		if(null!=token)
		{
			String result=MenuUtil.getMenu(token.getAccessToken());
			System.err.println(result);
		}
		
//		if(null!=token)
//		{
//			boolean result=MenuUtil.deleteMenu(token.getAccessToken());
//			if(result)
//			{
//				System.err.println(result);
//			}
//			else
//			{
//				System.err.println(result);
//			}
//		}
	}
}
