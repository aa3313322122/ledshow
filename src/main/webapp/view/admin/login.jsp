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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/admin/style/login.css">

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
<body class="y_login_bg">
<form name="loginFormName" id="loginFormId" action="<%=request.getContextPath()%>/login/admin" method="post">
    <div class="login-container y_login-container animated fadeInDown">
        <div class="loginbox-title"><span class="y_login_logo"></span></div> 
        <div class="loginbox bg-white">                       
            <div class="loginbox-title">登录</div>
            <div class="loginbox-textbox">
                <input type="text" class="form-control" placeholder="请输入用户名" name="account" />               
            </div>
            <div class="loginbox-textbox loginbox-textbox1">
                <input type="password" class="form-control" placeholder="请输入密码"  name="password" id="passwordId"/>                
            </div>
            <div class="loginbox-textbox loginbox_vertCode clearfix hide">
                <input type="text" class="form-control left" placeholder="请输入验证码" /> 
                <div class="y_vertCode left mg_l_10" style="background-color: #eee"></div> 
                <a href="#" class="right" title="看不清？刷新"><i class="fa fa-refresh"></i></a>
            </div>
            <!-- 
            <div class="loginbox-forgot clearfix">
                <div class="y_remenberPwd left">
                    <input type="checkbox">
                    <span class="checkbox_lg_default left"></span>
                    <span class="left">记住我</span>
                </div>                
            </div>
            -->
            <div class="loginbox-submit">
                <input type="button" class="btn btn-primary btn-block" value="登录" id="logBtn">
            </div>            
        </div>
        <div class="logobox y_tips clearfix dis_none" id="msgId"><i class="left"></i><span class="left">请输入正确的用户名或密码</span></div>
        <div class="login_copyright">Copyright ©2016 puban Co.,Ltd<br/>普伴资本管理有限公司 版权所有 </div>
    </div>
</form>
<script src="<%=request.getContextPath()%>/view/admin/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/admin/script/placeHolder.js"></script>

<script type="text/javascript">
    //--列表中的复选框、单选框、图像域 --
    $(".checkbox_lg_default").toggle(function(){
        $(this).removeClass("checkbox_lg_default").addClass("checkbox_lg_checked");
    },function(){
        $(this).removeClass("checkbox_lg_checked").addClass("checkbox_lg_default");
    });
    
    //回车事件
    document.onkeydown=function(event)
    {   
    	var e = event || window.event || arguments.callee.caller.arguments[0];                                     
    	if(e && e.keyCode==13)
    	{ 
    		if(($("#passwordId").val()==null)||($("#passwordId").val()==''))
    		{
    			$("#msgId").removeClass("dis_none").addClass("dis_block");
    			setTimeout(hideMsg,2000);
    			("#passwordId").val("");
    			("#passwordId").focus();
    		}
    		else
    		{
    			login();
    		}
    	}       
    };
    
    $("#msgId").click(function(){
    	$("#msgId").addClass("dis_none").removeClass("dis_block");
    });
    function hideMsg(){
    	$("#msgId").addClass("dis_none").removeClass("dis_block");
    }
    $("#logBtn").click(function(){
    	
    	if(($("#passwordId").val()==null)||($("#passwordId").val()==''))
		{
    		$("#msgId").removeClass("dis_none").addClass("dis_block");
			setTimeout(hideMsg,2000);
			("#passwordId").val("");
			("#passwordId").focus();
		}
		else
		{
			login();
		}
    });
    function login()
    {
    	$.post("<%=request.getContextPath()%>/login/admin", $("#loginFormId").serialize(),function(data){
			if (data <0)
			{
				$("#msgId").removeClass("dis_none").addClass("dis_block");
				setTimeout(hideMsg,2000);
				("#passwordId").focus();
			} 
			else 
			{
				$("#loginFormId").attr("action","<%=request.getContextPath()%>/login/goIndex")
				$("#loginFormId").submit();
			}
		},"text");
    }
</script>
</body>
</html>
