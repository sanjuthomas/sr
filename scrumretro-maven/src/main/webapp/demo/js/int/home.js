$('.forgot').bind('click', function() {
	$('.box-login').hide();
	$('.box-forgot').show();
});
$('.register').bind('click', function() {
	$('.box-login').hide();
	$('.box-register').show();
});
$('.go-back').click(function() {
	$('.box-login').show();
	$('.box-forgot').hide();
	$('.box-register').hide();
});
$('#btn-login').click(function() {
	alert("Btn-Login clicked..");
	var url = 'sr/login';
	$.ajax({
		url  : 'sr/login',
		type : 'GET',
		data : $('#form-login').serialize(),
		success : function(data) {
			alert("In Success....");
//			alert(data.toJSONString());
			
//			alert(JSON.toString(data));
		},
		error : function(jqXHR, textStatus, errorThrown ) {
			alert("In error");
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}
	});
//	var srAjaxObj = new srAjax(url, ajaxSettings);
//	srAjaxObj.call();
});

