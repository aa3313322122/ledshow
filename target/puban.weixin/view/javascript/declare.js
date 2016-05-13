(function(){


	$(function () {
		$('#btn').click(function () {
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
			sendValidateCode();
		})
		$('#fdValidateCode').change(function () {
			validCode();
		})
		$('#fdDeclarerPhone').change(function () {
			var phone = $("#fdDeclarerPhone").val();
			validatemobile(phone);
		})
		$('#fdBorrowerPhone').change(function () {
			var phone = $("#fdBorrowerPhone").val();
			validatemobile(phone);
		})
		
		$('#subDeclare').click(function () {
			if($("#declareFormId").valid()){
				
				subForm();
			 }
			else
			{
				showFail("有必填字段未输入");
			}
		})

		$("#suc").click(function(){
			$("#suc").addClass("hide");
			$("#all").addClass("hide");
			WeixinJSBridge.invoke('closeWindow',{},function(res){});
		});

		$("#fal").click(function(){
			$("#fal").addClass("hide");
			$("#all").addClass("hide");
		});
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
		$.post("declare/add", $("#declareFormId").serialize(),function(data){
	        
			
		});
	}
	
	function hideSuccess()
	{
		$("#suc").addClass("hide");
		$("#all").addClass("hide");
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
	        
	        var myreg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
	        if(!myreg.test(phone))
	        {
	        	showFail('请输入有效的手机号码！');
	            return false;
	        }
	    }
	
	}())