<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${bcmger.platTitle}</title>
	<%@ include file="/WEB-INF/jsp/inc/inc_portal.jsp"%>
</head>
<body id="defaulteasyuilayout" class="easyui-layout">
<div data-options="region:'north',border:false" style="background: #A8D7E9;height: 70px;overflow: hidden;" class="frame_head">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" style="vertical-align: text-bottom; text-align: center;">
			<img src="ework-res/images/logo/${bcmger.platAdcode}.png" style="height: 70px;width: 100%"/>
		</td>
		<td align="right" nowrap>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr style="height: 70px;" align="right">
					<td style="vertical-align: bottom;" colspan="2">
						<div style="background: url(ework-res/images/top_bg.jpg) no-repeat right center; float: right;">
							<div style="float: left; line-height: 25px; margin-left: 70px;">
								<span style="color: #386780">当前用户:</span> 
								<span style="color: #FFFFFF">${user.username }</span>&nbsp;&nbsp;&nbsp;&nbsp; 
							</div>
							<div style="float: left; margin-left: 18px;">
								<div style="right: 0px; bottom: 0px;">
									<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-cog" style="color: #FFFFFF">控制面板</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-key_delete" style="color: #FFFFFF">注销</a>
								</div>
								<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
									<div onclick="userIfmtion();">
										<span class="icon-ck">&nbsp;</span>
										<span class="nav">版本信息</span>
									</div>
									<div class="menu-sep"></div>
									<div onclick="ywUpdatePwd();">
										<span class="icon-shape_square_edit">&nbsp;</span>
										<span class="nav">修改密码</span>
									</div>
								</div>
								<div id="layout_north_zxMenu" style="width: 100px; display: none;">
									<div class="menu-sep"></div>
									<div onclick="logout();">
											<span class="icon-house_go">&nbsp;</span>
											<span class="nav">退出系统</span>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<div data-options="region:'west',split:true,title:'导航菜单', collapsible:false" style="width:242px;">
	<div class="easyui-accordion" data-options="animate:true,fit:true" style="border: none;">
		<c:forEach items="${AUTHORITYS}" var="module">
	<!-- 主菜单 -->
		<c:if test="${module.authType == 'MENU' && module.flag != '2'}">	
			<div title="${module.displayName }" icon="icon-sys" style="padding: 3px;border: none;">
				<ul>
				<li><div class="menu-sep"></div></li>
					<c:forEach items="${AUTHORITYS}" var="submenu">
						 <!-- 子菜单  -->
						<c:if test="${(submenu.authType == 'URL') && (submenu.upAuthCode == module.authCode) && (module.flag != '2')}">
							<li>
								<div align="center">
									<a style="margin:0;padding:0;display:block" href="javascript:void(0);" rel="<c:url value='${submenu.directUrl }'/>" tb="${submenu.tabIcon }" >
										<span class="${submenu.icon }" style="width:32px;height:32px;margin-bottom:3px">&nbsp;</span></br>
										<span class="nav">${submenu.displayName }</span>
									</a>
								</div>
								<div class="menu-sep"></div>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</c:forEach>
	</div>
</div>
<div id="ywDialogUpdatePwd"></div>
<div id="ywDialogUpdatePwdButton" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="updatePwd();">确认修改</a>
</div> 
<%@ include file="/WEB-INF/jsp/center.jsp"%>
<script type="text/javascript">
	function ywUpdatePwd() {
		$('#ywDialogUpdatePwd').dialog({    
		    title: '修改密码',    
		    width: 450,    
		    height: 260,    
		    closed: false,    
		    cache: false,    
		    modal: true,
		    top:'20%',
		    buttons:'#ywDialogUpdatePwdButton',
		    href:'<c:url value="/xtgl/rygl/updatePwdPage.do"/>'
		}); 
	}
	var t=1;//倒计时时间
	
	function reLoadLogout(){
		if(t==0){
	          location.href = scriptArgsPath+'logout.do';
		}
	    t--;
	}
	
	function updatePwd() {
		var data = $('#updatePwdForm').serialize();
		$.ajax({
			url: "<c:url value='/xtgl/rygl/updatePwd.do'/>",
			type: 'POST',
			data: data,
			beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
				$.messager.progress({
					title: '提示',
					msg: '正在执行数据操作，请稍待...'
				});
			},
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
			    $.messager.progress('close');
			},
			success: function(result) {
			    $.messager.progress('close');
				if(!result.success) {
					show_err_msg(result.data);
				}
				else {
					$('#ywDialogUpdatePwd').dialog('close');
					ZENG.msgbox.show(result.data, 4, 1500);
					setInterval("reLoadLogout()", 1000); //启动1秒定时
				}
			}
		});
	}
</script>
</body>
</html>

