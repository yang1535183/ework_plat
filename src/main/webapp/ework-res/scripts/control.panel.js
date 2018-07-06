//登录成功后控制面板模块；
function userIfmtion() {//个人信息
	var msgbbxx;
	//错误时：提示调用方法
	var msg = '"版本信息"';
	 $('.msg_bg').html('');
//	 clearTimeout(msgbbxx);
	 $('body').append('<div class="sub_err" style="position:absolute;top:60px;left:0;width:500px;z-index:999999;"></div>');
	 var errhtml = '<div  class="bac" style="padding:8px 0px;border:1px solid #ff0000;width:100%;margin:0 auto;background-color:#fff;color:#B90802;border:3px #ff0000 solid;text-align:center;font-size:16px;font-family:微软雅黑;">';
	 	errhtml += '<div><p>版权所有  &copy;2011-2021&nbsp;&nbsp;安徽迪万科技有限公司&nbsp;&nbsp;@Sunshine.Mr</p>'
	 	errhtml += '<p>版本： v1.1.0 Build110110 </p>'
	 	errhtml += '<a href="javascript:void(0);" style="font-family: 微软雅黑;font-size:14px;" onclick="msgbbxx();">关闭</a>' 
	 	errhtml += '</div>'
	 var errhtmlfoot='</div>';
	 $('.msg_bg').height($(document).height());
	 $('.sub_err').html(errhtml+errhtmlfoot);
	 var left=($(document).width()-500)/2;
	 $('.sub_err').css({'left':left+'px'});
	 var scroll_height=$(document).scrollTop();
	 $('.sub_err').animate({'top': scroll_height+220},600);
	 
};

function msgbbxx() {
	$('.msg_bg').remove();
	$('.sub_err').remove();
	 /*msgbbxx=setTimeout(function() {
		var scroll_height=$(document).scrollTop();
		$('.sub_err').animate({'top': scroll_height+80},600);
		setTimeout(function(){
			$('.msg_bg').remove();
			$('.sub_err').remove();
		},300);
	 }, 0);*/
}

//清除coolie
function closestyle() {
	$.ajax({
		url: scriptArgsPath + '/defaultStyle.do',
		type: 'POST',
		beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.progress({
				title:'提示',
				msg:'正在执行数据操作，请稍待...'
			});
		},
		error: function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.progress('close');
			$.messager.show({
				title:'异常',
				msg:'服务器繁忙或无法连接，请求通讯失败！',
				timeout:3000,
				showType:'slide'
			});
		},
		success: function(result) {
			$.messager.progress('close');
			if(!result.success) {
				$.messager.alert('错误', result.data, 'error');
			}
			else {
				ZENG.msgbox.show(result.data, 4, 1500);
			//	$('#dlgInfoDialog').dialog('close');
			}
		}
	});
}

//修改密码
function saveItem() {
	if ($('#frmDetail').form('enableValidation').form('validate')) {
		var data = $('#frmDetail').serialize();
		$.ajax({
			url: scriptArgsPath + '/xtgl/rygl/updatePsd.do',
			type: 'POST',
			data: data,
			beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
				$.messager.progress({
					title:'提示',
					msg:'正在执行数据操作，请稍待...'
				});
			},
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
				$.messager.show({
					title:'异常',
					msg:'服务器繁忙或无法连接，请求通讯失败！',
					timeout:3000,
					showType:'slide'
				});
			},
			success: function(result) {
				$.messager.progress('close');
				if(!result.success) {
					$.messager.alert('错误', result.data, 'error');
				}
				else {
					ZENG.msgbox.show(result.data, 4, 1500);
					$('#frmDetail').resetForm();
					$('#dlgInfo').dialog('close');
				}
			}
		});
	}
};



