<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="textml; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>我的订单</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/common.css" type="text/css">
</head>

<body>
<div class="bg_color">
	<div class="header statistics clearfix">
    	<i class="arrow_down arrow_grey"></i>
    	<span class="left">总订单:${map.allOrder}</span>
        <span class="left untreated">未处理:<i>${map.untreated}</i></span>
        <span class="left processed">已处理:${map.alread}</span>
        <span class="left denied">已拒绝:${map.refused}</span>
    </div>
    
    <div class="main" id="mainId">
    	<form action="<%=request.getContextPath()%>/weixin/queryOrder"  name="pageFormName"  id="pageFormId"  method="post">
    	<div class="search clearfix">
        	<img src="<%=request.getContextPath()%>/view/img/mirror.png" class="left" id="queryBtn"/>
            <input type="search" name="customerName" id="customerNameId"  value="${customerName}"   placeholder="客户名称" class="left">
       		<input type="hidden" name="pageNow"  id="pageNowId"  value="${pageView.currentpage}"/>
       		<input type="hidden" name="totalPage"  id="totalPageId"  value="${pageView.totalpage}"/>
    		<input type="hidden" name="userId"  id="userId"  value="${userId}"/> 
        </div>
        </form>
        
        <c:forEach var="entity" items="${pageView.records}">
        <div class="list">        	
        	<ul>
            	<li>客户名称：${entity.fdCustomerName}</li>
                <li>借款金额：${entity.fdBorrowAmount}</li>
                <li>借款期限：${entity.fdBorrowTerm}</li>
                <li class="bd_btm_0">订单状态：<span 
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> class="untreated" </c:if>
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> class="processed" </c:if>
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==3}"> class="denied" </c:if> >  
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> 未处理</c:if>
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> 已处理</c:if>
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==3}"> 已拒绝</c:if>
                </span></li>
            </ul>
        </div>
        </c:forEach>
         <div id="loadMoreDiv">
        		<c:if test="${pageView.totalpage>1}">
              	    <a href="javascript:void(0);" class="list_more" onclick="loadNext('<c:if test="${pageView.currentpage+1>=pageView.totalpage}">${pageView.totalpage}</c:if><c:if test="${pageView.currentpage+1<pageView.totalpage}">${pageView.currentpage+1}</c:if>');" id="loadmore">加载更多</a>
        		</c:if>
        		<c:if test="${pageView.totalpage<=1 or pageView.currentpage==pageView.totalpage }">
              	    <a href="#" class="list_more" id="notMoreDataId">没有更多数据</a>
        		</c:if>
         </div>   
    </div>
</div>
<script src="<%=request.getContextPath()%>/view/javascript/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/javascript/order.js"></script>
<script>
$("#queryBtn").click(function(){
	var form=document.getElementById("pageFormId");
    form.pageNow.value=1;
	form.submit();
});
</script>
</body>
</html>
    