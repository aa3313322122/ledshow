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
<title>普伴资本房产抵押系统-登录</title>

<link rel="shortcut icon" href="<%=request.getContextPath()%>/view/admin/img/favicon.png" type="image/x-icon">

<link href="<%=request.getContextPath()%>/view/admin/style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/admin/style/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/view/admin/style/animate.min.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/admin/style/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/admin/style/index.css">

<!--让IE认得HTML5-->
<!-–[if lt IE 9]--><script src="<%=request.getContextPath()%>/view/admin/script/html5shiv.js "></script ><!--[endif]–- > 
<!--[if IE]>
<script>
(function(){if(!/*@cc_on!@*/0)return;var e = "header,footer,nav,article,section".split(','),i=e.length;while(i--){document.createElement(e[i])}})()
</script>
<![endif]-->

<!--让IE8-认得@media，上传服务器方可查看效果-->
<script src="<%=request.getContextPath()%>/view/admin/script/Respond-master/dest/respond.src.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/skins.min.js"></script>
</head>
<!--Head Ends-->
<!--Body-->
<body class="y_index_bg">
<div class="index_header clearfix ">
    <a href="###" class="left logo"></a>
    <div class="header_right right">
        <span>您好！<%=session.getAttribute("loginName")==null?"":(String)session.getAttribute("loginName")%></span>
        <a href="<%=request.getContextPath()%>/login/logout" class="exit">退出</a>
    </div>
</div>

<div class="index_content">
    <form action="<%=request.getContextPath()%>/login/queryInfo" name="pageFormName" id="pageFormId" method="post">
    	<div class="clearfix">
	        <div class="index_search clearfix left">
	            <input type="search" name="channelCode" id="channelCodeId" value="${channelCode}" placeholder="请输入渠道编码"
	                   class="left sch_input_search">
	            
	            <input type="hidden"  name="currentPage" id="currentPageId" value="${pageView.currentpage}"/>
	            <input type="hidden" name="pageSize" id="pageSizeId" value="${pageSize}"/>
	        </div>
	        <select  class="index_searchSelect left mg_l_10" name="status" id="selectStatusId">
	        	 <option value="-1"  <c:if test="${not empty status and status==-1}">selected="selected"</c:if> >全  部</option>
                 <option value="1"  <c:if test="${not empty status and status==1}">selected="selected"</c:if> >未处理</option>
                 <option value="2"  <c:if test="${not empty status and status==2}">selected="selected"</c:if> >已处理</option>
             </select>
             <input type="submit" class="left sch_input_btn mg_l_10" value="查  询">
        </div>
    </form>
    <div class="index_list">
        <table width="100%">
            <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th width="9%" class="t_l">渠道编号</th>
                    <th width="12%" class="t_l">提单人微信昵称</th>
                    <th width="12%" class="t_l">客户名称</th>
                    <th width="12%" class="t_l">业务类型</th>
                    <th width="12%" class="t_l">单据提交时间</th>
                    <c:if test="${not empty status and status==2}">
                    <th width="12%" class="t_l">最大可贷金额</th>
                    </c:if>
                    <th width="9%">单据状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <div class="list">
                	<c:forEach var="entity" varStatus="xh" items="${pageView.records}">
                    <tr>
                        <td>${entity.fdId}</td>
                        <td class="t_l">${entity.user.channel.fdCode}</td>
                        <td class="t_l"><c:if test="${not empty entity.user.fdNickName}">${entity.user.fdNickName}</c:if></td>
                        <td class="t_l">${entity.fdCustomerName}</td>
                        <td class="t_l">
                        	<c:if test="${empty entity.declareType or  entity.declareType==1}">宅生金之房抵贷</c:if>
                        	<c:if test="${not empty entity.declareType and  entity.declareType==2}">宅生金之物业贷</c:if>
                        	<c:if test="${not empty entity.declareType and  entity.declareType==3}">宅生金之交易贷</c:if>
                        </td>
                        <td class="t_l">${entity.fdCreateTime}</td>
                        <c:if test="${not empty status and status==2}">
                        <td class="t_l"><c:if test="${not empty entity.fdAmountLoanable}">${entity.fdAmountLoanable} <span> 万元</span></c:if></td>
                        </c:if>
                        <td class="bd_btm_0"><span
                                <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> class="untreated" </c:if>
                                <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> class="processed" </c:if> >
                        <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> 未处理</c:if>
                        <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> 已处理</c:if>
                        </span></td>
                        <td>
                           <a class="btn_feedback btn_viewFeedback" href="<%=request.getContextPath()%>/login/view/${entity.fdId}" target="_blank">查看信息</a>
                           <a class="btn_feedback <c:if test="${not empty entity.fdStatus and entity.fdStatus==2}"> forbidden</c:if>" href="#" <c:if test="${not empty entity.fdStatus and entity.fdStatus==1}"> onclick="javascript:guzhiBack(${entity.fdId}); </c:if>">估值反馈</a>
                           <a class="btn_feedback <c:if test="${not empty entity.fdFillingAmount}"> forbidden</c:if>" href="#" <c:if test="${entity.fdStatus==2 and empty entity.fdFillingAmount}"> onclick="javascript:amountBack(${entity.fdId}); </c:if>">成交金额</a>
                        </td>
                    </tr>
            	</c:forEach>
             </div>
            </tbody>
        </table>

    </div>
    <!-- start 翻页 -->
    <div class="DTTTFooter">
        <div class="DTTTFooter_left clearfix">
            <div class="dataTables_info left">共${pageView.totalrecord}条数据， 每页显示</div>
            <div class="dataTables_length left">
                <label>
                    <select class="form-control input-sm" aria-controls="searchable"  name="selectPageSize" aria-controls="searchable" id="selectPageSizeId">
                         <option value="10" <c:if test="${pageSize==10}">selected="selected"</c:if> >10 </option>
                         <option value="20" <c:if test="${pageSize==20}">selected="selected"</c:if> >20 </option>
                         <option value="30" <c:if test="${pageSize==30}">selected="selected"</c:if> >30 </option>
                    </select>
                </label>
            </div>
            <div class="dataTables_info left">条</div>
        </div>
        <div class="DTTTFooter_right">
            <div class="dataTables_paginate paging_bootstrap">
                <ul class="pagination">
                    <li class="index">
                        <a href="javascript:goToPage('1');">首页</a>
                    </li>
                    <li class="prev">
                        <a href="javascript:goToPage('<c:if test="${pageView.currentpage-1<1}">1</c:if><c:if test="${pageView.currentpage-1>0}">${pageView.currentpage-1}</c:if>');">上一页</a>
                    </li>
                    <c:forEach var="i" begin="1"  end="${pageView.totalpage}">
                     <li <c:if test="${pageView.currentpage==i}">class="active"</c:if> >
                        <a href="javascript:goToPage('${i}')">${i}</a>
                     </li> 
                    </c:forEach>
                    <li class="next">
                        <a href="javascript:goToPage('<c:if test="${pageView.currentpage+1>=pageView.totalpage}">${pageView.totalpage}</c:if><c:if test="${pageView.currentpage+1<pageView.totalpage}">${pageView.currentpage+1}</c:if>');">下一页</a>
                    </li>
                    <li class="next">
                        <a href="javascript:goToPage('${pageView.totalpage}');">尾页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- end 翻页 -->
</div>

<!-- 弹出层 -->
<form action="<%=request.getContextPath()%>/login/updateMoney" name="backForm" id="backFormId" method="post">
<div class="bg_cover dis_none" id="bgCoverId"></div>
<div class="pop_content dis_none" id="popContentId">
    <div class="title clearfix">
        <h3 class="left">估值反馈</h3>
        <a href="javascript:void(0);" id="closeId" class="right pop_close"></a>
    </div>
    <div class="pop_contents ">
    	<div class="clearfix">
	        <span><strong>*</strong> 最大可贷金额：</span>
	        <input type="input" name="fdAmountLoanable" id="fdAmountLoanable"/>
	        <span>万元</span>
        </div>
        <div class="clearfix mg_t_20">
	        <span><strong>*</strong> 预约信息：</span>
	        <input type="input" name="fdAppointmentContact" id="fdAppointmentContactId" placeholder="联系人" class="contantName"/>
	        <input type="input" name="fdAppointmentInformation" id="fdAppointmentInformationId" placeholder="联系电话" class="contantPhone"/>
        </div>
        
    </div>
    
    <input type="hidden" name="fdId" id="fdId" />
    <input type="button" class="index_btn_submit" value="提 交"  id="btn"/>
</div>
</form>
<!-- end 弹出层 -->

<!-- 反填金额弹出层 -->
<form action="<%=request.getContextPath()%>/login/updateAmount" name="amountFormId" id="amountFormId" method="post" onsubmit="return amountSumbit()">
    <div class="bg_cover dis_none" id="bgCoverIds"></div>
    <div class="pop_content dis_none" id="popContentIds">
        <div class="title clearfix">
            <h3 class="left">成交金额</h3>
            <a href="javascript:void(0);" id="closeIds" class="right pop_close"></a>
        </div>
        <div class="pop_contents ">
            <div class="clearfix">
                <span><strong>*</strong> 成交金额：</span>
                <input type="input" name="fdFillingAmount" id="fdFillingAmount"/>
                <span>万元</span>
            </div>
        </div>

        <input type="hidden" name="id" id="id" />
        <input type="submit" class="index_btn_submit" value="提 交" id="btns" />
    </div>
</form>
<!-- 成交金额end 弹出层 -->

<script src="<%=request.getContextPath()%>/view/admin/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/placeHolder.js"></script>

<script type="text/javascript">

function guzhiBack(id)
{
    $("#fdId").val(id);
    $("#bgCoverId").removeClass("dis_none").addClass("dis_blcok");
    $("#popContentId").removeClass("dis_none").addClass("dis_blcok");
}
$("#closeId").click(function(){
    $("#bgCoverId").addClass("dis_none").removeClass("dis_blcok");
    $("#popContentId").addClass("dis_none").removeClass("dis_blcok");
});

function amountBack(id){
    $("#id").val(id);
    $("#bgCoverIds").removeClass("dis_none").addClass("dis_blcok");
    $("#popContentIds").removeClass("dis_none").addClass("dis_blcok");
}
$("#closeIds").click(function () {
    $("#bgCoverIds").addClass("dis_none").removeClass("dis_blcok");
    $("#popContentIds").addClass("dis_none").removeClass("dis_blcok");
});

    
$("#selectPageSizeId").change(function(){
	 var pageSizes=$(this).children("option:selected").val();
	 document.getElementById("selectPageSizeId").value=pageSizes;
	 document.getElementById("pageSizeId").value=pageSizes;
	 goToPage(1);
}); 

function goToPage(pageNo)
{
	var form=document.getElementById("pageFormId");
	form.currentPage.value=pageNo;
	document.getElementById("pageFormId").submit();
}
$("#btn").click(function(){
	var fdal=$("#fdAmountLoanable").val();
    var reg = /^[0-9]*$/i;
	var fdAppointmentContactValue=$("#fdAppointmentContactId").val();
	var fdAppointmentInformationValue=$("#fdAppointmentInformationId").val();
	 
	if(fdal==''||fdal==null)
	{
		alert("最大可贷金额不能为空！");
		$("#fdAmountLoanable").focus();
	}
    else if(reg.test(fdal) == false)
    {
        alert("最大可贷金额只能输入数字！");
        $("#fdAmountLoanable").focus();
    }
	else if(fdAppointmentContactValue==''||fdAppointmentContactValue==null)
	{
		alert("预约联系人不能为空！");
		$("#fdAppointmentContactId").focus();
	}
	else if(fdAppointmentInformationValue==''||fdAppointmentInformationValue==null)
	{
		alert("预约联系方式不能为空！");
		$("#fdAppointmentInformationId").focus();
	}
	else
	{
		$("#backFormId").submit();
	}
});

function amountSumbit(){
    var fdal = $("#fdFillingAmount").val();
    var reg = /^[0-9]*$/i;
    if (fdal == '' || fdal == null) {
        alert("成交金额不能为空！");
        return false;
    } else if (reg.test(fdal) == false) {
        alert("成交金额只能为数字！");
        return false;
    }
    else {
        return true;
    }
}




$("#selectStatusId").change(function(){
	 var statusValue=$(this).children("option:selected").val();
	 document.getElementById("selectStatusId").value=statusValue;
});
</script>
</body>
</html>
