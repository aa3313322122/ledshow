(function(){


	$(function () {
		
		$('#btn').click(function () {
			var phone = $("#fdDeclarerPhone").val();
			if(phone.length!=11)
	        {
	        	showFail('请输入有效的手机号码！');
	            return false;
	        }
	        
	        var myreg = /^(((1[0-9][0-9]{1})|159|153)+\d{8})$/;
	        if(!myreg.test(phone))
	        {
	        	showFail('请输入有效的手机号码！');
	            return false;
	        }
			sendValidateCode();
			var count = 60;
			var countdown = setInterval(CountDown, 1000);

			function CountDown() {
				$("#btn").attr("disabled", true);
				$("#btn").val(count + "s");
				if (count == 0) {
					$("#btn").val("发送验证码").removeAttr("disabled");
					clearInterval(countdown);
				}
				count--;
			}
			
			
			
		})
		$('#fdValidateCode').change(function () {
			validCode();
		})
		$('#submitDeclare').click(function () {
			$("#submitDeclare").addClass("submitAplyDisable");
			if($("#declareFormId3").valid())
			{
				subForm();
			}
			else
			{
				showFail("有必填字段未输入");
			}
			
			$("#submitDeclare").removeClass("submitAplyDisable");
		})
		
		$('#fdDeclarerPhone').change(function () {
			var phone = $("#fdDeclarerPhone").val();
			if(!validatemobile(phone))
			{
				$("#fdDeclarerPhone").val("");
			}
		})
		$('#fdBorrowerPhone').change(function () {
			var phone = $("#fdBorrowerPhone").val();
			if(!validatemobile(phone))
			{
				$("#fdBorrowerPhone").val("");
			}
		})
		$("#suc").click(function(){
			$("#suc").addClass("hide");
			$("#all").addClass("hide");
			
		});

		$("#fal").click(function(){
			$("#fal").addClass("hide");
			$("#all").addClass("hide");
		});
		$("#declareFormId").validate({
			focusInvalid:false,
			onfocusout:false,
			errorPlacement: function(error, element) {  
				showFail("有必填字段未输入");
			}
	    });
		$("#declareFormId2").validate({
			submitHandler:function(form){
				var str = "";
	            for(var i = 4;i < len + 1;i++)
            	{
	            	if("" != $('#tmp' + i).val() && null != $('#tmp' + i).val())
            		{
	            		if(str == "")
            			{
	            			str = $('#tmp' + i).val();
            			}
	            		str = str + ";" + $('#tmp' + i).val();
            		}
            	}
	            $("#fdOtherFilePath").val(str);
	           form.submit();
	        },
	        errorPlacement: function(error, element) {  
				showFail("有图片未上传");
			}
		})
	});
	function sendValidateCode()
	{
		var fdDeclarerPhone = $("#fdDeclarerPhone").val();
		var userId = $("#userId").val();
		var data = "fdDeclarerPhone=" + fdDeclarerPhone + "&userId=" + userId;
		var url = "sendValidateCode";
		if(null != fdDeclarerPhone)
		{
			$.ajax({
				url:url,
				type:"post",
				async:true,
				data:data,
				dataType:"text",
				success:function(data)
				{

				}
			});
		}
	}
	
	function validCode()
	{
		var fdValidateCode = $("#fdValidateCode").val();
		var fdDeclarerPhone = $("#fdDeclarerPhone").val();
		var userId = $("#userId").val();
		var data = "fdDeclarerPhone=" + fdDeclarerPhone + "&userId=" + userId + "&fdValidateCode=" + fdValidateCode;
		var url = "validcode";
			$.ajax({
				url:url,
				type:"post",
				async:false,
				data:data,
				dataType:"text",
				success:function(data)
				{
					if(data == '0')
					{
						showFail("验证码输入错误");
						$("#fdValidateCode").val("");
					}
				}
			});
		
	}
	
	function subForm() 
	{
		$.post("declare/add", $("#declareFormId3").serialize(),function(data){
			if (data != 0)
			{
				if (data == '1') {
					showFail("申报失败, 用户状态受限");
				}
				else if (data == '2') {
					showFail("申报失败, 渠道受限");
				}
				else if(data == '-1') {
					showFail("申报失败, 请勿重复提交！");
				}
				else if(data == '-2') {
					showFail("申报失败, 有必填字段未输入！");
				}
				else {
					showFail("申报失败");
				}
			} 
			else 
			{
				showSuccess("申报成功！"); 
			}
			
		},"text");
	}
	
	function hideSuccess()
	{
		$("#suc").addClass("hide");
		$("#all").addClass("hide");
		setTimeout(closeWindow,300);
	}
	function closeWindow()
	{
		WeixinJSBridge.invoke('closeWindow',{},function(res){});
	}

	function hideFail()
	{
		$("#fal").addClass("hide");
		$("#all").addClass("hide");
	}
	
	function showSuccess(sucessMsg)
	{
		$("#all").removeClass("hide");
		$("#suc").removeClass("hide");
		$("#subsuc").html(sucessMsg); 
		
		setTimeout(hideSuccess,3000);
	
	}
	function showFail(errorMsg)
	{
		$("#all").removeClass("hide");
		$("#fal").removeClass("hide");
		$("#failed").html(errorMsg);
		
		setTimeout(hideFail,3000);
	}
	
	function validatemobile(phone)
	{
        if(phone.length!=11)
        {
        	showFail('请输入有效的手机号码！');
            return false;
        }
        
        var myreg = /^(((1[0-9][0-9]{1})|159|153)+\d{8})$/;
        if(!myreg.test(phone))
        {
        	showFail('请输入有效的手机号码！');
            return false;
        }
        return true;
	}
	
	//上传图片
	$(function() {
		var showHost = 'http://120.76.75.193/puban/';
		//var showHost = 'http://192.168.1.186:8080/puban.weixin/';
	    $('#file_upload').fileupload({
	        //上传的图片存放路径
	        url: 'upload?inputName=IdentityCardPathP',
	        dataType: 'json',
	        done: function (e, result) {
	            $('#upload_pic_1').removeClass('uploading_pic').addClass('uploaded_pic');
	            inputName = result.result.inputName;
	            var filePath = result.result.filePath;
	            var showPath = result.result.showPath;
	            $('#'+'fd'+inputName).val(filePath);
	            $('#pic_view').attr("src", showHost + showPath);
	        },
	        progress: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress1').css(
	                'width',
	                progress + '%'
	            ).text(progress + '%');
	        },
	        send: function (e, data) {
	            $('#upload_pic_1').removeClass('uploadDefault_pic').addClass('uploading_pic');
	        }
	    });
	    $('#upload_pic_1 .deleteThumbnail').on('click', function () {
	        $('#progress1').css(
	            'width', 0
	        ).text('');
	        $('#fdIdentityCardPathP').val("");
	        $('#upload_pic_1').removeClass('uploaded_pic').addClass('uploadDefault_pic');
	    });
	    
	    $('#file_upload2').fileupload({
	        //上传的图片存放路径
	        url: 'upload?inputName=PropertyCardPath',
	        dataType: 'json',
	        done: function (e, result) {
	            $('#upload_pic_2').removeClass('uploading_pic').addClass('uploaded_pic');
	            var inputName = result.result.inputName;
	            var filePath = result.result.filePath;
	            var showPath = result.result.showPath;
	            $('#'+'fd'+inputName).val(filePath);
	            $('#pic_view2').attr("src", showHost + showPath);
	        },
	        progress: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress2').css(
	                'width',
	                progress + '%'
	            ).text(progress + '%');
	        },
	        send: function (e, data) {
	            $('#upload_pic_2').removeClass('uploadDefault_pic').addClass('uploading_pic');
	        }
	    });
	    $('#upload_pic_2 .deleteThumbnail').on('click', function () {
	        $('#progress2').css(
	            'width', 0
	        ).text('');
	        $('#fdPropertyCardPath').val("");
	        $('#upload_pic_2').removeClass('uploaded_pic').addClass('uploadDefault_pic');
	    });
	    
	    $('#file_upload3').fileupload({
	        //上传的图片存放路径
	        url: 'upload?inputName=PropertyCardPathS',
	        dataType: 'json',
	        done: function (e, result) {
	            $('#upload_pic_3').removeClass('uploading_pic').addClass('uploaded_pic');
	            var inputName = result.result.inputName;
	            var filePath = result.result.filePath;
	            var showPath = result.result.showPath;
	            $('#'+'fd'+inputName).val(filePath);
	            $('#pic_view3').attr("src", showHost + showPath);
	        },
	        progress: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress3').css(
	                'width',
	                progress + '%'
	            ).text(progress + '%');
	        },
	        send: function (e, data) {
	            $('#upload_pic_3').removeClass('uploadDefault_pic').addClass('uploading_pic');
	        }
	    });
	    $('#upload_pic_3 .deleteThumbnail').on('click', function () {
	        $('#progress3').css(
	            'width', 0
	        ).text('');
	        $('#fdPropertyCardPathS').val("");
	        $('#upload_pic_3').removeClass('uploaded_pic').addClass('uploadDefault_pic');
	    });
	    
	    $('#file_upload4').fileupload({
	        //上传的图片存放路径
	        url: 'upload?inputName=PropertyCardPathT',
	        dataType: 'json',
	        done: function (e, result) {
	            $('#upload_pic_4').removeClass('uploading_pic').addClass('uploaded_pic');
	            var inputName = result.result.inputName;
	            var filePath = result.result.filePath;
	            var showPath = result.result.showPath;
	            $('#'+'fd'+inputName).val(filePath);
	            $('#pic_view4').attr("src", showHost + showPath);
	        },
	        progress: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress4').css(
	                'width',
	                progress + '%'
	            ).text(progress + '%');
	        },
	        send: function (e, data) {
	            $('#upload_pic_4').removeClass('uploadDefault_pic').addClass('uploading_pic');
	        }
	    });
	    $('#upload_pic_4 .deleteThumbnail').on('click', function () {
	        $('#progress5').css(
	            'width', 0
	        ).text('');
	        $('#fdPropertyCardPathT').val("");
	        $('#upload_pic_4').removeClass('uploaded_pic').addClass('uploadDefault_pic');
	    });
	    
	}); 
	
}())