//easyui 实现主页布局基础功能，及登录状态
var scriptArgsPath = document.getElementById('js_frame').getAttribute('path');
$.parser.onComplete = function(context) {
	$("#Loading").fadeOut("normal", function() {
		$(this).remove();
	});
};

function refreshDbsx() {
	$.ajax({
		type : "GET",
		url : scriptArgsPath+"/xmgl/gcgl/msgc/userQueryMsgc.do",
		success : function(data) {
			$('#ywDbsxList').html("");
			for (var i = 0; i < data.length; i++) {
				if('null' == data[i]) {
					$('#ywDbsxList').append("<li><a href='javascript:void(0);'><img src='ework-res/images/icon_flat/news_list_ico.png'/>"+data[i].memo+"</a><span>"+data[i].scsj.substr(0, 7)+"</span></li>");
				}
			}
		}
	});
}

$(function() {
	InitLeftMenu();
	tabClose();
	tabCloseEven();
	refreshDbsx();
	// 每3秒执行一次
	window.setInterval(refreshDbsx, 3000);
	// 执行一个会超过30秒以上的方法
	$('body').everyTime('3das', 'overall', chkSession, 0, true);
});

// 系统会话状态检查
function chkSession() {
	$.ajax({
		url : scriptArgsPath + 'checkoutSession.do',
		type : 'POST',
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.show({
				title : '异常',
				msg : '服务器繁忙或无法连接，请求通讯失败！',
				timeout : 3000,
				showType : 'slide'
			});
		},
		success : function(result) {
			if (!result.success) {
				$('body').stopTime('overall');
				$(document).stopTime('overall');
				$.messager.alert('会话校验', '系统会话状态已过期，需要重新登录！', 'warning',
						function() {
							location.href = scriptArgsPath + 'login.do';
						});
			}
		}
	});
}

// 注销并转到登录页面
function logout() {
	$.messager.confirm('退出系统', '确定要退出系统并返回到登录页面吗？', function(r) {
		if (r) {
			location.href = scriptArgsPath + 'logout.do';
		}
	});
}

function InitLeftMenu() {
	$('.easyui-accordion li a').click(function() {
		var tabTitle = $(this).children('.nav').text();

		var href = $(this).attr("rel");
		var tb = $(this).attr("tb");
		/*
		 * var menuid = $(this).attr("ref"); var icon = getIcon(menuid,icon);
		 */

		addTab(tabTitle, href, tb);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

// 打开菜单项(以选项卡方式)
function addTab(title, href, icon) {
	var tt = $('#tabs');

	if (tt.tabs('exists', title)) {
		tt.tabs('select', title);
	} else {
		var content;
		if ("我的工作台" == title) {
			location.reload();
		}
		if ("#" == href) {
			show_err_msg('功能尚未实现...');
		} else {
			tt.tabs('add', {
				title : title,
				closable : true,
				icon : icon,
				style : {
					padding : '5px'
				},
				href : href,
				loadingMessage : '加载中...'
			});
		}
	}
	tabClose();
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").html();
		$('#tabs').tabs('close', subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").html();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}

// 绑定右键菜单事件
function tabCloseEven() {
	/**
	 * 屏蔽浏览器右键事件、 添加自定义鼠标右键事件
	 */
	document.oncontextmenu = function() {
		return false;
	};

	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				href : url
			}
		})
	})
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	})
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).html();
			$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			$.messager.alert('提示', '后边没有啦~~!', 'info');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).html();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			$.messager.alert('提示', '到头了，前边没有啦~~!', 'info');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).html();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	})
}
