<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<style>
/*提示信息-放在common.css中后，魅族表现异常*/
.notice_bg{ background-color:#000; opacity:0.5; position:fixed; top:0; left:0; width:100%; height:100%; z-index:999}
.notice_text{ background-color:#fff; border-radius:2rem; width:80%; overflow:hidden; height:10rem; line-height:10rem; position:fixed; left:50%; top:50%; margin-left:-40%; margin-top:-5rem; text-align:center; z-index:1000}
.notice_text img{ vertical-align:middle; width:2.8rem; margin-top:-0.4rem}
.notice_text span{ font-size:1.7rem; display:inline-block; color:#555; padding-left:1rem;}
/*下拉选择*/
.select_button { float: right; border: none; }
/* --------------  */
/*  custom-select  */
/* --------------  */
.custom-select { position: relative; margin-top: 1rem; margin-right: 1.3rem; }
.custom-select select { width:100%;  margin:0; background:none; border: 1px solid transparent; outline: none;                     
 /* Prefixed box-sizing rules necessary for older browsers */
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  /* Remove select styling */
  appearance: none;
  -webkit-appearance: none;
  /* Font size must the 16px or larger to prevent iOS page zoom on focus */
  /* General select styles: change as needed */
  font-family: helvetica, sans-serif;
  font-size: 1.5rem;
  color: #444;
  padding: .3em 2em .3em .5em;
  line-height:1.3;
  direction: rtl;
}
.custom-select::after {
  content: "";
  position: absolute;
  width: 0px;
  height: 0px;
  top: 50%;
  right: 8px;
  margin-top:-4px;
  border:8px solid #929497;
  border-width: 8px 8px 8px;
  border-color: #929497 transparent transparent transparent;
  z-index: 2;
  pointer-events:none;                      
}
/* Hover style */
.custom-select:hover {
  border:none;
}
/* Focus style */
.custom-select select:focus {
  outline:none;
  box-shadow: none;
  background-color:transparent;
  color: #222;
  border:none;
}
/* Set options to normal weight */
.custom-select option {
  font-weight:normal;
}
x:-o-prefocus, .custom-select::after {
  display:none;
}    
@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {  
  .custom-select select::-ms-expand {
    display: none;
  }
  .custom-select select:focus::-ms-value {
    background: transparent;
    color: #222;
  }
}  
@-moz-document url-prefix() { 
  .custom-select {
    overflow: hidden;
  }
  .custom-select select {
    width: 120%;
    width: -moz-calc(100% + 3em);
    width: calc(100% + em);
  }
  
}
.custom-select select:-moz-focusring {
  color: transparent;
  text-shadow: 0 0 0 #000;
}
</style>
</head>

<body >
<div class="bg_color">
    <div class="header"> *为必填项，请确保信息准确无误后再提交申请<i class="arrow_down arrow_white"></i></div>
    <div class="main">
    	<form id="declareFormId" name="declareFormName" method="post" action="<%=request.getContextPath()%>/declare/add">
        <div class="content">
        	<div class="title">客户信息</div>
        	
        	<ul>
            	<li>
                	<p><i>*</i><span>客户名称：</span></p>
                    <input type="text" name="fdCustomerName"  value="" required placeholder="请填写" class="customer_name">
                </li>
                <li>
                	<p><i>*</i><span>身份证号：</span></p>
                    <input type="text" name="fdIdentityCard" value="" required placeholder="请填写">
                </li>
                <li class="mt_20 bd_btp_c8">
                	<p><i>*</i><span>借款人手机号：</span></p>
                    <input type="tel" id="fdBorrowerPhone" name="fdBorrowerPhone" value="" required placeholder="请填写">
                </li>
                <li>
                	<p><span>借款金额(万元)：</span></p>
                    <input type="tel" name="fdBorrowAmount" value="" placeholder="请填写">
                </li>
                <li class="bd_btm_c8">
                	<p><span>借款期限(月)：</span></p>
                    <input type="tel" name="fdBorrowTerm" value="" placeholder="请填写">
                </li>
            </ul>
        </div>
        <div class="content">
        	<div class="title">抵押信息</div>
        	<ul>
            	<li class="mt_20 bd_btp_c8">
                	<p><i>*</i><span>抵押房产地址：</span></p>
                    <input type="text" name="fdMortgageAddress"  value="" required placeholder="请填写" class="house_adds">
                </li>
                <li>
                	<p><i>*</i><span>抵押房产建筑面积(㎡)：</span></p>
                    <input type="tel" name="fdMortgageAcreage" value="" required placeholder="请填写">
                </li>
                <li>
                	<p><i>*</i><span>登记价格(元)：</span></p>
                    <input type="tel" name="fdMortgagePrice"  value="" required id="test" placeholder="请填写">
                </li>
                <c:if test="${not empty channel}">
                <li>
                	<p><i>*</i><span>渠道名称：</span></p>
                    <input type="hidden"  name="channel.fdId"  value="${channel.fdId}"/>
                    <input type="text"  readonly="readonly"  value="${channel.fdName}"/>
                </li>
                </c:if>
                
                <c:if test="${empty channel}">
                <li>
                	<p><i>*</i><span>渠道名称：</span></p>
                    <div class="select_button custom-select" style="width: 25.3rem">
                        <select name="channel.fdId"  id="channelSelectId">
                            <c:forEach var="obj"  items="${channels}" >
                    			<option  value="${obj.fdId}">${obj.fdName}</option>
                    		</c:forEach>
                        </select>
                    </div>
                </li>
                </c:if>
            </ul>
        </div>
        <div class="content">
        	<div class="title">填单人信息</div>
        	<ul>
            	<li class="mt_20 bd_btp_c8">
                	<div class="clearfix"><p><i>*</i><span>填单人手机号：</span></p> </div>  
                    <input type="tel" id="fdDeclarerPhone" required name="fdDeclarerPhone" value="" placeholder="请输入您的手机号" class="left t_l mt_07 input_phone">      
                    <input type="button" id="btn" value="发送验证码" class="sendCode mt_0"/>
                    
                </li>
                <li>
                	<p><i>*</i><span>验证码：</span></p>
                    <input type="tel" name="fdValidateCode" required id="fdValidateCode" value="" placeholder="请填写您接收到的验证码" class="writeCode">                    
                </li>
            </ul>
           
        </div>
        <input type="hidden" id="userId" value="${userId }" name="userId">
        <input type="button" id="subDeclare" value="提交申请" class="submitAply" />
        </form> 
    </div>
</div>
<input type="hidden" id="resultMsg" value="${msg }">

<script src="<%=request.getContextPath()%>/view/javascript/jquery-1.8.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/messages_zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/jquery.ddslick.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/declare.js" type="text/javascript"></script>

<!--提示信息-->
<div id="all" class="notice_bg hide"></div>
<div id="suc" class="notice_text hide"><img id="ok" src="<%=request.getContextPath()%>/view/img/right.png"><span id="subsuc">提交成功</span></div>
<div id="fal" class="notice_text hide"><img id="falimg" src="<%=request.getContextPath()%>/view/img/error.png"><span id="failed">身份证号码有误</span></div>
<!--提示信息-->
</body>
</html>
    