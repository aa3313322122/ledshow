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
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/jQuery-File-Upload/css/jquery.fileupload.css" type="text/css">
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
        <div class="progress_1 progress_2 prog_current1 left">
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
    	<form id="declareFormId2" name="declareFormName" method="post" action="<%=request.getContextPath()%>/weixin/declare3">
        
        <div class="content" id="contentId">
            <!-- 
                uploadDefault_pic ： 默认显示状态 ；
                uploading_pic     ： 上传中 ；
                uploaded_pic      ： 上传完成 ；

                * 仅在“上传中”状态时，upload_cover覆盖层显示。
                * 问题：从默认状态变为上传中时，提示文字的变化。
             -->
            <div id="upload_pic_1" class="upload_pic uploadDefault_pic">
                <!-- <span class="upload_tips">点击上传身份证照片(必传)</span> -->
                <div class="upload_cover"></div>

                <span class="fileinput-button">
                    <span>点击上传身份证照片(必传)</span>
                    <input id="file_upload" type="file" required name="file" class="input_fileUpload">
                </span>
                <div id="progress1" class="upload_process" style="width:0"></div>

                <div id="pic_view_div" class="pic_view_div">
                     <img src="" id="pic_view" required class="pic_view">  
                </div>
                <a href="javascript:;" class="deleteThumbnail"></a>
            </div>

            <div id="upload_pic_2" class="upload_pic uploadDefault_pic">
                <div class="upload_cover"></div> 
                <span class="fileinput-button">
                    <span>点击上传房产证照片(1)(必传)</span>
                    <input id="file_upload2" type="file" required name="file" class="input_fileUpload">
                </span>
                <div class="upload_process" id="progress2" style="width:0"></div>
                <div id="pic_view_div2" class="pic_view_div">
                     <img src="" id="pic_view2" class="pic_view">
                </div>
                <a href="javascript:;" class="deleteThumbnail"></a>
            </div>
            
            <div id="upload_pic_3" class="upload_pic uploadDefault_pic">
                <div class="upload_cover"></div> 
                <span class="fileinput-button">
                    <span>点击上传房产证照片(2)</span>
                    <input id="file_upload3" type="file" name="file" class="input_fileUpload">
                </span>
                <div class="upload_process" id="progress3" style="width:0"></div>
                <div id="pic_view_div3" class="pic_view_div">
                     <img src="" id="pic_view3" class="pic_view">
                </div>
                <a href="javascript:;" class="deleteThumbnail"></a>
            </div>
            <div id="upload_pic_4" class="upload_pic uploadDefault_pic">
                <div class="upload_cover"></div> 
                <span class="fileinput-button">
                    <span>点击上传房产证照片(3)</span>
                    <input id="file_upload4" type="file" name="file" class="input_fileUpload">
                </span>
                <div class="upload_process" id="progress4" style="width:0"></div>
                <div id="pic_view_div4" class="pic_view_div">
                     <img src="" id="pic_view4" class="pic_view">
                </div>
                <a href="javascript:;" class="deleteThumbnail"></a>
            </div>
            <input type="button" value="上传更多" onclick="addFile()" class="view_moreBtn">
        </div>
        
        
        <input type="hidden" name="fdCustomerName"  value="${declare.fdCustomerName }" required />
        <input type="hidden" name="fdIdentityCard" value="${declare.fdIdentityCard }" required />
        <input type="hidden" name="declareType"  value="${declare.declareType }" required/>
        <input type="hidden" id="fdIdentityCardPathP" name="fdIdentityCardPathP"  value=""  />
        <input type="hidden" id="fdPropertyCardPath" name="fdPropertyCardPath"  value=""  />
        <input type="hidden" id="fdPropertyCardPathS" name="fdPropertyCardPathS"  value=""  />
        <input type="hidden" id="fdPropertyCardPathT" name="fdPropertyCardPathT"  value=""  />
        <input type="hidden" id="fdOtherFilePath" name="fdOtherFilePath"  value=""  />
        <input type="hidden" id="userId" value="${userId }" name="userId" required />
        <input type="hidden" name="com.puban.token" value="<%=token%>"/>
        <input type="submit" id="lastDeclare" value="下一步" class="submitAply"/>
        
        </form> 
    </div>
</div>

<script src="<%=request.getContextPath()%>/view/javascript/jquery-1.8.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/jQuery-File-Upload/js/vendor/jquery.ui.widget.min.js"></script>
<script src="<%=request.getContextPath()%>/view/jQuery-File-Upload/js/jquery.fileupload.min.js"></script>
<script src="<%=request.getContextPath()%>/view/jQuery-File-Upload/js/jquery.iframe-transport.min.js"></script>

<script src="<%=request.getContextPath()%>/view/javascript/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/messages_zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/view/javascript/declare.js" type="text/javascript"></script>
<script>
var len;
var otherPath;
$(document).ready(function(){
	len = $("#contentId div.upload_pic").size();
});
function addFile(){
	len = len + 1;
	  var str = '<div id="upload_pic_' + len + '" class="upload_pic uploadDefault_pic">' +          
          '<div class="upload_cover"></div> ' +
          '<input type="button" class="del_moreBtn" onclick="delThis(this)" id="delBtn' + len + '" value="删除" >' +
          '<span class="fileinput-button">' +
              /* '<span>点击上传</span>' + */
              '<input id="file_upload' + len + '" type="file" name="file" onclick="getId(this)" class="input_fileUpload">' +
              '<input id="tmp' + len + '" type="hidden" name="tmpItem" />' +
          '</span>' +
          '<div class="upload_process" id="progress' + len + '" style="width:0"></div>' +
          '<div id="pic_view_div' + len + '" class="pic_view_div">' +
               '<img id="pic_view' + len + '" class="pic_view">' +
          '</div>' +
          '<a href="javascript:;" class="deleteThumbnail"></a>' +
          
      '</div>';
	  document.getElementById('contentId').insertAdjacentHTML("beforeEnd",str)
  }
function delThis(object)
{
	 var strId=object.getAttribute("id");
	 var index = strId.substring(6,strId.length);
     var tmpId = document.getElementById('upload_pic_'+index);
     tmpId.parentNode.removeChild(tmpId);
	 
}
 function getId(object)
 {
	 var strId=object.getAttribute("id");
	 var index = strId.substring(11,strId.length);
	 var showHost = 'http://120.76.75.193/puban/';
	 $('#file_upload'+index).fileupload({
	        //上传的图片存放路径
	        url: 'upload',
	        dataType: 'json',
	        done: function (e, result) {
	            $('#upload_pic_' + index).removeClass('uploading_pic').addClass('uploaded_pic');
	            var inputName = result.result.inputName;
	            var filePath = result.result.filePath;
	            var showPath = result.result.showPath;
	            $('#tmp'+index).val(filePath);
	            $('#pic_view'+index).attr("src", showHost + showPath);
	        },
	        progress: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress'+index).css(
	                'width',
	                progress + '%'
	            ).text(progress + '%');
	        },
	        send: function (e, data) {
	            $('#upload_pic_'+index).removeClass('uploadDefault_pic').addClass('uploading_pic');
	        }
	    });
    $('#upload_pic_'+index+' .deleteThumbnail').on('click', function () {
        $('#progress'+index).css(
            'width', 0
        ).text('');
        $('#tmp'+index).val("");
        $('#upload_pic_'+index).removeClass('uploaded_pic').addClass('uploadDefault_pic');
    });
 }
 
</script>
<!--提示信息-->
<div id="all" class="notice_bg hide"></div>
<div id="suc" class="notice_text hide"><img id="ok" src="<%=request.getContextPath()%>/view/img/right.png"><span id="subsuc">提交成功</span></div>
<div id="fal" class="notice_text hide"><img id="falimg" src="<%=request.getContextPath()%>/view/img/error.png"><span id="failed">身份证号码有误</span></div>
<!--提示信息-->

</body>
</html>
    