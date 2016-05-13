<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]--> 
<head>
<meta name="description" content="Dashboard" />
<meta content="text/html;charset=utf-8;text/x-component" http-equiv="Content-Type">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>普伴资本房产抵押系统-查看</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/admin/style/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/admin/style/index.css">
</head>
<body class="y_index_bg">
<div class="index_header clearfix ">
    <a href="###" class="left logo"></a>
    <div class="header_right right">
        <span>您好！<%=session.getAttribute("loginName")==null?"":(String)session.getAttribute("loginName")%></span>
        <a href="<%=request.getContextPath()%>/login/logout" class="exit">退出</a>
    </div>
</div>
<div class="index_content">
	<p class="breadCrumbs">您的位置：<a href="<%=request.getContextPath()%>/login/queryInfo">首页</a> > 查看</p>
	<div class="index_list">
	    <table width="100%">
	        <thead>
	            <tr>
	                <th width="10%" class="t_l view_name">客户名称</th>
	                <th width="11%" class="t_l">最大可贷金额</th>
	                <th width="16%" class="t_l">订单时间</th>
	                <th width="12%" class="t_l">预约联系人</th>
	                <th width="12%" class="t_l">预约联系方式</th>
	                <th>查看上传照片</th>	                
	            </tr>
	        </thead>
	        <tbody>
	            <div class="list">            	
	                <tr>
	                    <td class="t_l view_name">${declare.fdCustomerName}</td>
	                    <td class="t_l"><c:if test="${not empty declare.fdAmountLoanable}">${declare.fdAmountLoanable} <span> 万元</span></c:if></td>
	                    <td class="t_l">${declare.fdCreateTime}</td>
	                    <td class="t_l"><c:if test="${not empty declare.fdAppointmentContact}">${declare.fdAppointmentContact}</c:if></td>
	                    <td class="t_l"><c:if test="${not empty declare.fdAppointmentInformation}">${declare.fdAppointmentInformation}</c:if></td>
	                    <td>
	                    	<c:if test="${not empty declare.fdIdentityCardPathP}"><a href="http://weixin.szpbzb.com/puban/${declare.fdIdentityCardPathP}" target="_blank" class="btn_feedback btn_viewFeedback">身份证</a></c:if>
	                    	<c:if test="${not empty declare.fdPropertyCardPath}"><a href="http://weixin.szpbzb.com/puban/${declare.fdPropertyCardPath}" target="_blank" class="btn_feedback btn_viewFeedback">房产证(1)</a></c:if>
	                    	<c:if test="${not empty declare.fdPropertyCardPathS}"><a href="http://weixin.szpbzb.com/puban/${declare.fdPropertyCardPathS}" target="_blank" class="btn_feedback btn_viewFeedback">房产证(2)</a></c:if>
	                    	<c:if test="${not empty declare.fdPropertyCardPathT}"><a href="http://weixin.szpbzb.com/puban/${declare.fdPropertyCardPathT}" target="_blank" class="btn_feedback btn_viewFeedback">房产证(3)</a></c:if>
	                    	<c:if test="${not empty declare.fdOtherFilePath}"><a href="<%=request.getContextPath()%>/login/viewOther/${declare.fdId}" target="_blank" class="btn_feedback btn_viewFeedback">房产证其他照片</a></c:if>
	                    </td>	                    
	                </tr>        	
	         </div>
	        </tbody>
	    </table>
	</div>
</div>
</body>
</html>
