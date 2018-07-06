<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!-- //页面布局 -->
<div class="easyui-layout" fit="true" style="background:#ccc;">
	<div region="west" split="true" title="权限管理树" style="width:260px;padding:5px;">
		<div id="authTreeQx"></div>
	</div>
	<div region="center" title="权限管理">
		<div class="menuerl" style="padding:3px;background:#efefef;border-bottom:1px solid #ccc;">	
			<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="newItemQx()" iconCls="icon-add">新增</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="dropItemQx()" iconCls="icon-remove">删除</a> 
			<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="saveItemQx()" iconCls="icon-save">保存</a> 
		</div>
		<div style="padding:10px;">
			<form id="frmQx" method="post" style="margin:0;padding:0">
				<table class="formTableEasyui" cellpadding="0" cellspacing="0">
					<tr>
						<th style="width:70px;">权限代码：</th>
						<td>
							<input type="text" name="authDisp" class="textReadonly" style="width:300px;" disabled="disabled" value="自动生成" />
							<input type="text" name="authCode" class="textReadonly" style="width:300px;display:none;" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th><font>*</font>权限类型：</th>
						<td>
							<input id="authType" name="authType" class="easyui-combobox text" style="width:302px;" panelHeight="auto" required="true" validType="unnormal"/>
						</td>
					</tr>
					<tr>
						<th><font>*</font>显示名称：</th>
						<td><input type="text" id="displayName" name="displayName" class="easyui-validatebox text" style="width:300px;" required="true" validType="unnormal" /></td>
					</tr>
					<tr>
						<th>权限描述：</th>
						<td><textarea name="authDescribe" class="easyui-validatebox text" style="overflow:auto;width:300px;"></textarea></td>
					</tr>
					<tr>
						<th><font>*</font>跳转地址：</th>
						<td><input type="text" id="directUrl" name="directUrl" class="easyui-validatebox text" style="width:300px;" value="#" required="true" validType="unnormal"/></td>
					</tr>
					<tr>
						<th><font>*</font>排序：</th>
						<td><input type="text" id="sortOrder" name="sortOrder" class="easyui-validatebox text" style="width:300px;" value="100" /></td>
					</tr>
					<tr>
						<th><font>*</font>上一级权限：</th>
						<td>
							<input id="upAuthCode" name="upAuthCode" class="easyui-combobox text" style="width:302px;" required="true" validType="unnormal" />
						</td>
					</tr>
					<tr>
						<th><font>*</font>是否显示：</th>
						<td>
							<input class="easyui-combobox" name="status" id="status" required="true" validType="unnormal"/>
						</td>
					</tr>
					<tr>
						<th><font>*</font>图标：</th>
						<td>
							<input class="text" class="easyui-validatebox text" name="icon" id="icon" style="width:300px;"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
var authTreeQx;
var targetForm = '#frmQx';

$(function(){
	drawTreeQx();
	newItemQx();
	
	$('#authType').combobox({
		data: [{value: 'MENU', text: '菜单'},{value: 'URL', text: '链接'},{value: 'BUTTON', text: '按钮'}],
		onChange: function(newValue, oldValue) {
			qxTypeChange(newValue);
		}
	});
	
	$('#upAuthCode').combobox({data: [{value: '0', text: '无'}]});
	$('#status').combobox({
		data: [{id: '0', text: '显示', "selected":true},
				{id: '1', text: '隐藏'}],
	    valueField: 'id',    
	    textField: 'text',
	    panelHeight: 'auto',
	    editable: 'false'
    });
	
});

// 构建权限树
function drawTreeQx() {
	$.ajax({
		url: '<c:url value="/xtgl/qxgl/qxJsonAll.do"/>',
		type: 'POST',
		success: function(result) {
			if(result.success) {
				var rows = result.data;

				authTreeQx = new dTree('authTreeQx','<c:url value="/ework-res/dtree/2.05/images/explorer/"/>');
				authTreeQx.config.folderLinks = true;
				authTreeQx.config.useCookies = false;
				authTreeQx.add('0','-1','权限树',"javascript:void(0);");
				
				for (var i=0; i<rows.length; i++) {
					authTreeQx.add(rows[i].auth_code, rows[i].up_auth_code, rows[i].display_name,"javascript:editItemQx('"+rows[i].auth_code+"');");
				}
				document.getElementById('authTreeQx').innerHTML = authTreeQx;
				authTreeQx.openAll();
			}
		}
	});
}

// 新增：初始化窗口
function newItemQx() {
	$(targetForm).form('clear');
	$(targetForm + ' input[name="authDisp"]').css('display','block');
	$(targetForm + ' input[name="authCode"]').css('display','none');
	$(targetForm + ' input[name="authDisp"]').val('自动生成');
	$(targetForm + ' input[name="directUrl"]').val('#');
	$(targetForm + ' input[name="sortOrder"]').val('100');
}

// 保存
function saveItemQx() {
	if ($(targetForm).form('validate')) {
		var data = $(targetForm).serialize();
		$.ajax({
			url: "<c:url value='/xtgl/qxgl/doQxSave.do' />",
			type: 'POST',
			data: data,
			beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
				$.messager.progress({
					title:'提示',
					msg:'正在执行数据操作，请稍待...'
				});
			},
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				$.messager.show({
					title:'异常',
					msg:'服务器繁忙或无法连接，请求通讯失败！',
					timeout:3000,
					showType:'slide'
				});
				$.messager.progress('close');
			},
			success: function(result) {
				$.messager.progress('close');
				if(!result.success) {
					$.messager.alert('错误', result.data, 'error');
				}
				else {
					ZENG.msgbox.show(result.data, 4, 1500);
					drawTreeQx();
				}
			},
			complete: function (XMLHttpRequest, textStatus) {
			}
		});
	}
}

// 删除
function dropItemQx() {
	var authCode = $(targetForm + ' input[name="authCode"]').val();
	if (0 == authCode.length) {
		$.messager.alert('警告', '请选择要删除的数据！', 'warning');
	}
	else {
		$.messager.confirm('删除数据', '确定要删除所选择的数据？', function(r){
			if (r){
				$.ajax({
					url: "<c:url value='/xtgl/qxgl/doQxDrop.do' />",
					type: 'POST',
					data: 'authCode='+authCode,
					beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress({
							title:'提示',
							msg:'正在执行数据操作，请稍待...'
						});
					},
					error: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.show({
							title:'异常',
							msg:'服务器繁忙或无法连接，请求通讯失败！',
							timeout:3000,
							showType:'slide'
						});
						$.messager.progress('close');
					},
					success: function(result) {
						$.messager.progress('close');
						if(!result.success) {
							$.messager.alert('错误', result.data, 'error');
						}
						else {
							ZENG.msgbox.show(result.data, 4, 1500);
							drawTreeQx();
							newItemQx();
						}
					},
					complete: function (XMLHttpRequest, textStatus) {
					}
				});
			}
		});
	}
}

//修改或删除：初始化数据窗口
function editItemQx(authCode) {
	$.ajax({
		url: "<c:url value='/xtgl/qxgl/getQxJson.do' />",
		type: 'POST',
		data: 'authCode='+authCode,
		success: function(result) {
			if(result.success) {
				var authority = result.data;
				qxTypeChange(authority.authType, authority.upAuthCode);
				$(targetForm + ' input[name="authDisp"]').css('display','none');
				$(targetForm + ' input[name="authCode"]').css('display','block');
				$(targetForm).form('load', authority);
				//$('#upAuthCode').combobox('setValue', );
			}
		}
	});
}

//权限类型：值变化时的响应函数
function qxTypeChange(value, upAuthCode) {
	// 清除所有可选项
	$('#upAuthCode').combobox({data: []});
	
	if ('MENU' == value) {
		$('#upAuthCode').combobox({data: [{value: '0', text: '无'}]});
	}
	else if ('URL' == value) {
		$.post('<c:url value="/xtgl/qxgl/jsonQx.do"/>?flag=1',function(result){
			var data = [];
			// 添加最新的可选项
			for (var i=0; i<result.auth.length; i++) {
				if (null != upAuthCode && result.auth[i].authCode == upAuthCode) {
					data[i] = {value: result.auth[i].authCode, text: result.auth[i].displayName, selected: true};
				}
				else {
					data[i] = {value: result.auth[i].authCode, text: result.auth[i].displayName};
				}
			}
			$('#upAuthCode').combobox({data: data});
		});
	}
	else {
		$.post('<c:url value="/xtgl/qxgl/jsonQx.do"/>?flag=2',function(result){
			var data = [];
			// 添加最新的可选项
			for (var i=0; i<result.auth.length; i++) {
				if (null != upAuthCode && result.auth[i].authCode == upAuthCode) {
					data[i] = {value: result.auth[i].authCode, text: result.auth[i].displayName, selected: true};
				}
				else {
					data[i] = {value: result.auth[i].authCode, text: result.auth[i].displayName};
				}
			}
			$('#upAuthCode').combobox({data: data});
		});
	}
}
</script>