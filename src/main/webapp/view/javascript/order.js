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
			 
		  } 
		}); 
	}); 
	
}())