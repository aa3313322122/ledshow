<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    	<i class="arrow_down arrow_grey hide"></i>
    	<span class="left" onclick="javascript:goMyOrder(0);">总订单: ${map.allOrder}</span>
        <span class="left untreated" onclick="javascript:goMyOrder(1);">未处理: <i>${map.untreated}</i></span>
        <span class="left processed" onclick="javascript:goMyOrder(2);">已处理: ${map.alread}</span>
        <%--<span class="left denied"    onclick="javascript:goMyOrder(3);">已拒绝:${map.refused}</span>--%>
    </div>
    
    <div class="main" id="mainId">
    	<form action="<%=request.getContextPath()%>/weixin/queryOrder"  name="pageFormName"  id="pageFormId"  method="post">
    	<div class="search clearfix">
        	<img src="<%=request.getContextPath()%>/view/img/mirror.png" class="left" id="queryBtn"/>
            <input type="search" name="customerName" id="customerNameId"  value="${customerName}"   placeholder="客户名称" class="left">
       		<input type="hidden" name="totalPage"  id="totalPageId"  value="${pageView.totalpage}"/>
    		<input type="hidden" name="userId"  id="userId"  value="${userId}"/> 
    		<input type="hidden" name="pageNow"  id="pageNowId"  value="${pageView.currentpage}"/>
    		<input type="hidden" name="fdStatus" value="0"/>
        </div>
        </form>
        
        <c:forEach var="entity" items="${pageView.records}">
        <div class="list">        	
        	<ul>
                <li>客户名称：${entity.fdCustomerName}</li>
                <c:if test="${entity.fdStatus==2}">
                    <li>最大可贷金额：${entity.fdAmountLoanable}<span> 万元</span></li>
                    <c:if test="${(not empty entity.fdAppointmentContact) or (not empty entity.fdAppointmentInformation)}">
                    <li>预约信息：<c:if test="${not empty entity.fdAppointmentContact}">${entity.fdAppointmentContact},</c:if><c:if test="${not empty entity.fdAppointmentInformation}">${entity.fdAppointmentInformation}</c:if></li>
               		</c:if>
                </c:if>
                <li>订单状态：<span 
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> class="untreated" </c:if>
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> class="processed" </c:if> >
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> 未处理</c:if>
                <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> 已处理</c:if>
                </span></li>
                <li class="bd_btm_0">订单时间：${entity.fdCreateTime}</li>
                 <%--
                <li>借款金额：${entity.fdBorrowAmount}</li>
                --%>
                <%-- 
                <li>借款期限：${entity.fdBorrowTerm}</li>
                --%>
                
            </ul>
        </div>
        </c:forEach>
         <div id="loadMoreDiv">
        		<c:if test="${pageView.totalpage>1}">
              	    <a href="javascript:void(0);" class="list_more" onclick="loadNext(<c:if test="${pageView.currentpage+1>=pageView.totalpage}">${pageView.totalpage}</c:if><c:if test="${pageView.currentpage+1<pageView.totalpage}">${pageView.currentpage+1}</c:if>);" id="loadmore">加载更多</a>
        		</c:if>
        		<c:if test="${pageView.totalpage<=1 or pageView.currentpage==pageView.totalpage }">
              	    <a href="javascript:void(0);" class="list_more" id="notMoreDataId">没有更多数据</a>
        		</c:if>
         </div>   
    </div>
</div>
<script src="<%=request.getContextPath()%>/view/javascript/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/javascript/order.js"></script>
<script>

document.onkeydown=function(event)
{            
	var e = event || window.event || arguments.callee.caller.arguments[0];                                     
	if(e && e.keyCode==13)
	{ 
		var form=document.getElementById("pageFormId");
	    form.pageNow.value=1;
		form.submit();    
	}       
}; 

function goMyOrder(status)
{
	var form=document.getElementById("pageFormId");
    form.fdStatus.value=status;
    form.customerName.value="";
    form.pageNow.value=1;
	form.submit();
}

$("#queryBtn").click(function(){
	var form=document.getElementById("pageFormId");
    form.pageNow.value=1;
	form.submit();
});
function loadNext(nextpages)
{
	var nextpage=parseInt(nextpages);
	var form=document.getElementById("pageFormId");
    form.pageNow.value=parseInt(nextpage);
    var userid=form.userId.value;
    var customername=form.customerName.value;

    $.post('appendList',
    {
        pageNow:form.pageNow.value,
        userId:userid,
        customerName:form.customerName.value
    },function(data){
        var model = data['pageView'];
        var map = data['map'];
        var dataList = model.records;

        var lastDiv=$("#mainId").find("div.list:last");
        var newDataDiv="";
        for(var i=0;i<dataList.length;i++)
        {
            newDataDiv+="<div class='list'>";
            newDataDiv+="<ul>";
            newDataDiv+="<li>客户名称："+dataList[i].fdCustomerName+"</li>";
            if(dataList[i].fdStatus == 2)
            {
                newDataDiv += "<li>最大可贷金额：" + dataList[i].fdAmountLoanable + "<span> 万元</span></li>";
                newDataDiv += "<li>预约信息：" + dataList[i].fdAppointmentContact + ","+  dataList[i].fdAppointmentInformation  +"</li>";
            }
            newDataDiv+="<li>订单状态:";
            if(dataList[i].fdStatus==1)
            {
                newDataDiv+="<span class='untreated'>  未处理</span>";
            }
            if(dataList[i].fdStatus==2)
            {
            	newDataDiv+="<span class='processed'>  已处理</span>";
            }
            if(dataList[i].fdStatus==3)
            {
            	newDataDiv+="<span class='denied'>  已拒绝</span>";
            }
            newDataDiv+="</li>";
            newDataDiv+="<li class='bd_btm_0'>订单时间："+dataList[i].fdCreateTime+"</li>";
            newDataDiv+="</ul>";
            newDataDiv+="</div>";
        }
        
    	lastDiv.append(newDataDiv);
		
    	var more="";
    	if(model.totalpage>1)
    	{
    		if(model.currentpage==model.totalpage)
    		{
    			more+='<a href="javascript:void(0);" class="list_more" id="notMoreDataId">没有更多数据</a>';
    		}
    		if(model.currentpage<model.totalpage)
    		{
    			more+='<a href="javascript:void(0);" class="list_more" onclick="loadNext('+(model.currentpage+1)+');" id="loadmore">加载更多</a>';
    		}
    	}
    	else
    	{
    		more+='<a href="javascript:void(0);" class="list_more" id="notMoreDataId">没有更多数据</a>';
    	}
    	$("#loadMoreDiv").html(more);
	});
}
</script>
</body>
</html>
    