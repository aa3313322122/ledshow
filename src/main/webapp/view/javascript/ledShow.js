(function(){


	$(function () {

		$('#fdValidateCode').change(function () {
			validCode();
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
	
}())