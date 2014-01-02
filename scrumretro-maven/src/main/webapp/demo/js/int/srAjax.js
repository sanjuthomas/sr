//https://developer.mozilla.org/en-US/docs/AJAX/Getting_Started
function srAjax() {
	var httpRequest = getHttpRequest();
	var type = 'POST';
	var async = false;
	var responseHandler;
	var ajaxCall = function() {
		httpRequest.onreadystatechange = stateChangeHandler;
		httpRequest.open(type, url,async);
		httpRequest.send('userName=' + encodeURIComponent(userName));
	};

	function stateChangeHandler() {
		if (httpRequest.readyState === 4) {
			if (httpRequest.status === 200) {
				var response = JSON.parse(httpRequest.responseText);
				alert(response.computedString);
			} else {
				alert('There was a problem with the request.');
			}
		}
	}

	
	
	function getHttpRequest() {
		if (window.XMLHttpRequest) { // Mozilla, Safari, ...
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE
			try {
				return new ActiveXObject("Msxml2.XMLHTTP.6.0");
			} catch (e) {
			}
			try {
				return new ActiveXObject("Msxml2.XMLHTTP.3.0");
			} catch (e) {
			}
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
		throw new Error("This browser does not support XMLHttpRequest.");
	}
}
