var scriptArgsPath = document.getElementById('js_frame').getAttribute('path');
$(document).ready(function() {
	//设置默认焦点
	$('#loginname').focus();
	
	//设置热键
	$('#loginname').bind('keydown', function(e) {
		if (e.keyCode == 13) {
			$('#loginpass').focus();
		}
	});
	
	//回车键促发事件
	$('#loginpass').bind('keydown', function(e) {
		if (e.keyCode == 13) {
			platlogin();
		}
	});
	
});

//重置 
function resetloginForm() {
	$('#loginname').val('');
	$('#loginpass').val('');
	$("#ErrorTip").html('');
}

