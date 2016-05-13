<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.puban.weixin.declare.util.TokenProcessor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="textml; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>业务申请</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/common.css" type="text/css">
</head>

<body >
<% 
	TokenProcessor tokenProcessor=TokenProcessor.getInstance();
	String token=tokenProcessor.getToken(request);
%>
<div class="bg_color">
    <div class="declare_header clearfix">
		<div class="progress_1 left">
            <span class="left line_l"></span>
            <div class="left"><span class="circle1">1</span></div>
            <span class="left line_r"></span>
            <span class="texts">填写客户信息</span>
        </div>
        <div class="progress_1 progress_2  left">
            <span class="left line_l"></span>
            <div class="left"><span class="circle1">2</span></div>
            <span class="left line_r"></span>
            <span class="texts">上传客户资料</span>
        </div>
        <div class="progress_1 progress_3 prog_current1 left">
            <span class="left line_l"></span>
            <div class="left"><span class="circle1">3</span></div>
            <span class="left line_r"></span>
            <span class="texts">填写你的信息</span>
        </div>
	</div>
    <div class="main">
    	<form id="declareFormId3" name="declareFormName" method="post" action="">
        
        
        <div class="content">
        	<div class="title">填单人信息</div>
        	<ul>
        		<c:if test="${not empty channel}">
                <li>
                	<p><i>*</i><span>渠道编码：</span></p>
                    <input type="hidden"  name="channel.fdId"  value="${channel.fdId}"/>
                    <input type="text"  readonly="readonly"  value="${channel.fdCode}"/>
                </li>
                </c:if>
                
                <c:if test="${empty channel}">
                <li>
                	<p><i>*</i><span>渠道编码：</span></p>
                    <div class="select_button custom-select" style="width: 18rem">
                        <select name="channel.fdId"  id="channelSelectId">
                            <c:forEach var="obj"  items="${channels}" >
                    			<option  value="${obj.fdId}">${obj.fdCode}</option>
                    		</c:forEach>
                        </select>
                    </div>
                </li>
                </c:if>
            	<li>
                	<p><i>*</i><span>填单人手机号：</span></p> 
                    <input type="tel" id="fdDeclarerPhone" required name="fdDeclarerPhone" value="" placeholder="请填写您的手机号" class="input_phone">      
                </li> 
                <li>
                	<p><i>*</i><span>验证码：</span></p>
                	  
                    <input type="tel" name="fdValidateCode" required id="fdValidateCode" value="" placeholder="请填写验证码" class="left t_l writeCode">
                    <input type="button" id="btn" value="获取验证码" class="sendCode "/>                  
                </li>
            </ul>
           
        </div>
        <input type="hidden" name="fdCustomerName"  value="${declare.fdCustomerName }"  />
        <input type="hidden" name="fdIdentityCard" value="${declare.fdIdentityCard }"  />
        <input type="hidden" name="declareType"  value="${declare.declareType }"/>
        <input type="hidden" id="path1" name="fdIdentityCardPathP"  value="${declare.fdIdentityCardPathP }"  />
        <input type="hidden" id="path3" name="fdPropertyCardPath"  value="${declare.fdPropertyCardPath }"  />
        <input type="hidden" id="path4" name="fdPropertyCardPathS"  value="${declare.fdPropertyCardPathS }"  />
        <input type="hidden" id="path5" name="fdPropertyCardPathT"  value="${declare.fdPropertyCardPathT }"  />
        <input type="hidden" id="pathX" name="fdOtherFilePath"  value="${declare.fdOtherFilePath }"  />
        <input type="hidden" id="userId" value="${userId }" name="userId" />
        <input type="hidden" name="com.puban.token" value="<%=token%>"/>
        <input type="button" id="submitDeclare" value="提交申请" class="submitAply"/>
        
        </form> 
    </div>
</div>

<script src="<%=request.getContextPath()%>/view/javascript/jquery-1.8.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/messages_zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/declare.js" type="text/javascript"></script>

<!--提示信息-->
<div id="all" class="notice_bg hide"></div>
<div id="suc" class="notice_text hide"><img id="ok" src="<%=request.getContextPath()%>/view/img/right.png"><span id="subsuc">提交成功</span></div>
<div id="fal" class="notice_text hide"><img id="falimg" src="<%=request.getContextPath()%>/view/img/error.png"><span id="failed">身份证号码有误</span></div>
<!--提示信息-->
</body>
</html>
    