(function(){

	$(function(){ 
		var winH = $(window).height(); 
		var i = 1; 
		$(window).scroll(function (){ 
		  var pageH = $(document.body).height(); 
		  var scrollT = $(window).scrollTop(); 
		  var aa = (pageH-winH-scrollT)/winH; 
		  if(aa<0.02)
		  { 
			  //loadNext();
		  } 
		}); 
	}); 
	
	function loadNext(nextpage)
	{
		var form=document.getElementById("pageFormId");
	    form.pageNow.value=nextpage;
	    var userid=form.userId.value;
	    var customername=document.getElementById("customerNameId").value;

	    $.post('appendList',
	    {
	        pageNow:nextpage,
	        userId:userid,
	        customername:customername
	    },function(data){
	        var model = data['pageView'];
	        var dataList = model.records;

	        var lastDiv=$("#mainId").find("div.list:last");
	        var newDataDiv="";
	        for(var i=0;i<dataList.length;i++)
	        {
	            newDataDiv+="<div class='list'>";
	            newDataDiv+="<ul>";
	            newDataDiv+="<li>客户名称："+dataList[i].fdCustomerName+"</li>";
	            newDataDiv+="<li>借款金额："+dataList[i].fdBorrowAmount+"</li>";
	            newDataDiv+="<li>借款期限："+dataList[i].fdBorrowTerm+"</li>";
	            newDataDiv+="<li class='bd_btm_0'>订单状态:";
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
	            newDataDiv+="</li></ul>";
	            newDataDiv+="</div>";
	        }
	        
	    	lastDiv.append(newDataDiv);
			
	    	
			if(model.totalpage<=1 || model.currentpage==model.totalpage)
			{
		        $("#loadmore").hide();
		        $("#notMoreDataId").hide();
		        var loadmores = $("#loadMoreDiv");
		        var noMore = "<a href='#' class='list_more' id='notMoreDataId'>没有更多数据</a>";
		        loadmores.append(noMore);
		    }
		});
	}

}())