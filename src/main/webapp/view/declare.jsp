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
		<div class="progress_1 prog_current1 left">
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
        <div class="progress_1 progress_3  left">
            <span class="left line_l"></span>
            <div class="left"><span class="circle1">3</span></div>
            <span class="left line_r"></span>
            <span class="texts">填写你的信息</span>
        </div>
	</div>
    <div class="main">
    	<form id="declareFormId" name="declareFormName" method="post" action="<%=request.getContextPath()%>/weixin/declare2">
        <div class="content">
        	<div class="title">客户信息</div>        	
        	<ul>
            	<li>
                	<p><i>*</i><span>客户名称：</span></p>
                    <input type="text" name="fdCustomerName"  value="" required placeholder="请填写客户名称" class="customer_name">
                </li>
                <li>
                	<p><i>*</i><span>身份证号：</span></p>
                    <input type="text" name="fdIdentityCard" value="" required placeholder="请填写客户身份证号码">
                </li> 
                <li>
                	<p><i>*</i><span>业务类型：</span></p>
                    <div class="select_button custom-select" style="width: 18rem" >
                        	<select name="declareType"  id="declareTypeId">                            
                    		<option  value="1">宅生金之房抵贷</option>
                    		<option  value="2">宅生金之物业贷</option> 
                    		<option  value="3">宅生金之交易贷</option> 
                        </select>
                    </div>
                </li> 
            </ul>
        </div>
        
        <input type="hidden" id="userId" value="${userId }" name="userId" />
        <input type="hidden" name="com.puban.token" value="<%=token%>"/>
        <input type="submit" id="nextDeclare" value="下一步" class="submitAply"/>
                
        </form> 
    </div>
</div>

<script src="<%=request.getContextPath()%>/view/javascript/jquery-1.8.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/messages_zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/declare.js" type="text/javascript"></script>
<script type="text/javascript">
$("#declareTypeId").change(function(){
	 var declareTypeSelectValue=$(this).children("option:selected").val();
	 document.getElementById("declareTypeId").value=declareTypeSelectValue;
});
</script>

<!--提示信息-->
<div id="all" class="notice_bg hide"></div>
<div id="suc" class="notice_text hide"><img id="ok" src="<%=request.getContextPath()%>/view/img/right.png"><span id="subsuc">提交成功</span></div>
<div id="fal" class="notice_text hide"><img id="falimg" src="<%=request.getContextPath()%>/view/img/error.png"><span id="failed">身份证号码有误</span></div>
<!--提示信息-->
</body>
</html>
    