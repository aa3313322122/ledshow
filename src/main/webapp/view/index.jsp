<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.puban.weixin.lading.token.oauth.SNSUserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OAuth2.0网页授权</title>
<meta name="viewport" content="width=device-width,user-scalable=0">
<style type="text/css">
	*{margin:0;padding:0}
	table{border:1px dashed #B9B9DD;font-size:12pt}
	td{border:1px dashed #B9B9DD;word-break:break-all;word-wrap:break-word;}
</style>
</head>
<body>
	<% 
		/**获取由OAuthServlet传入的参数**/
		SNSUserInfo user=(SNSUserInfo)request.getAttribute("snsUserInfo");
	    if(null!=user)
	    {
	%>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenId</td><td><%=user.getOpenId()%></td></tr>
		
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><%=user.getHeadImgUrl()%></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
	</table>
	<%  
		}
	    else
	    {
			out.print("未获取到用户信息");	
			
		}
	 %>
</body>
</html>