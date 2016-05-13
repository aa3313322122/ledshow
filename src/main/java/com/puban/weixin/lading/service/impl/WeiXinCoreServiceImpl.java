package com.puban.weixin.lading.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.weixin.lading.message.resp.TextMessage;
import com.puban.weixin.lading.model.WeiXinCore;
import com.puban.weixin.lading.service.IWeiXinCoreService;
import com.puban.weixin.lading.util.MessageUtil;
import com.puban.weixin.lading.util.Oauth2TokenUtil;
import com.puban.weixin.lading.util.WeiXinApiUtil;
import com.puban.weixin.user.service.IUserService;

public class WeiXinCoreServiceImpl extends BaseServiceImpl<WeiXinCore> implements IWeiXinCoreService
{
	@Resource(name="userService")
	private IUserService userService;
	
	
	@Override
	public  String processRequest(HttpServletRequest request)
	{
		String respXml = null;

		String respContent = "not sourpt message!";

		try
		{
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String FromUserName = requestMap.get("FromUserName");
			String ToUserName = requestMap.get("ToUserName");
			String MsgType = requestMap.get("MsgType");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(FromUserName);
			textMessage.setFromUserName(ToUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);

			if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT))
			{
				String Content=requestMap.get("Content");
				StringBuffer sb=new StringBuffer();
				sb.append("房抵贷业务类型详情，请回复1").append("\n");
				sb.append("物业贷业务类型详情，请回复2").append("\n");
				sb.append("交易贷业务类型详情，请回复3");
				
				if(Content!=null&&!Content.equals(""))
				{
					if(Content.trim().equals("1"))
					{
						respContent="";
						respContent="指向借款人发起的以房产为抵押物，用于借款人企业审查经营或个人消费的贷款。";
					}
					else if(Content.trim().equals("2"))
					{
						respContent="";
						respContent="指我司向公司或个人发放的贷款。以其拥有的商业物业作为抵押物，并以该物业的快速变现能力作为主要还款来源，借款人租金收入，经营收入及其他合法收入作为补充还款来源的一种贷款业务。";
					}
					else if(Content.trim().equals("3"))
					{
						respContent="";
						respContent="指借款人拟将其已抵押的房产抵押至新放款机构，需由我司现行发放借款偿还已抵押借款余额后解除设定于该房产的抵押，再将该房产抵押给新放款机构，新放款机构放款后借款人归还我司借款的业务。";
					}
					else
					{
						respContent="";
						respContent=sb.toString();
					}
				}
				else
				{
					respContent="";
					respContent=sb.toString();
				}
			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE))
			{
				respContent = "image";
			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE))
			{
				respContent = "voice";
				String mediaId = requestMap.get("MediaId");
				String format = requestMap.get("Format");
				String recognition = requestMap.get("Recognition");

				System.err.println("mediaId:" + mediaId);
				System.err.println("format:" + format);
				System.err.println("recognition:" + recognition);

			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO))
			{
				respContent = "video";
			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO))
			{
				respContent = "shortvideo";
			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION))
			{
				respContent = "location";
			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK))
			{
				respContent = "link";
			}
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
			{
				String EventType = requestMap.get("Event");
				if (EventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE))
				{
					StringBuffer sb=new StringBuffer(WeiXinApiUtil.getValue("subscribe.welcome"));
					sb.append("\n");
					sb.append("房抵贷业务类型详情，请回复1").append("\n");
					sb.append("物业贷业务类型详情，请回复2").append("\n");
					sb.append("交易贷业务类型详情，请回复3");
					respContent=sb.toString();
					userService.subscribeWeiXin(FromUserName);
				}
				else if (EventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE))
				{
					System.err.println("UNSUBSCRIBE openid is "+FromUserName);
					userService.unSubscribeWeiXin(FromUserName);
				}
				else if (EventType.equals(MessageUtil.EVENT_TYPE_CLICK))
				{
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("declare"))
					{
						StringBuffer sb = new StringBuffer();
						sb.append(WeiXinApiUtil.getValue("pleaseClickUrlToCeclare")).append(" ");
						sb.append(WeiXinApiUtil.getValue("domain.site")+"/puban/weixin/declare/openid/").append(FromUserName);
						respContent = sb.toString();
					}
					else if (eventKey.equals("myOrder"))
					{
						StringBuffer sb = new StringBuffer();
						sb.append(WeiXinApiUtil.getValue("pleaseClickUrlToMyOrder")).append(" ");
						sb.append(WeiXinApiUtil.getValue("domain.site")+"/puban/weixin/order/openid/").append(FromUserName);
						respContent = sb.toString();
					}
					else if(eventKey.equals("about"))
					{
						respContent=Oauth2TokenUtil.getOauth2TargetUserHref(WeiXinApiUtil.APPID, "http://weixin.puban.com/puban/weixin/oauth", "snsapi_userinfo");
					}
				}
			}

			textMessage.setContent(respContent);

			respXml = MessageUtil.messageToXml(textMessage);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return respXml;
	}

}
