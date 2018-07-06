<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<table id="tbl_0B9557B832A144E696094AD23AB36FBA"></table>
<div style="display:none;">
	<div id="BTN_21B6F7D798A44366B56A56EA4B8D2A45" style="text-align: center;">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="doSaveUser();">保存</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="close_addUser();">取消</a>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid({
    	fit: true,
		nowrap: false,
		striped: true,
		url: '<c:url value="/xtgl/rygl/jsonTMisUserPageList.do"/>',
		singleSelect: true,
		idField: 'UUID',
		columns: [[
			{field:'loginname',title:'登录名称',width:170,halign:'center'},
			{field:'username',title:'真实姓名',width:150},
			{field:'cellphone',title:'联系方式',width:150},
			{field:'status',title:'启用',width:80,align:'center',formatter:function(value, rowData, rowIndex){
				if (value == '1') {
					return '<span class="icon-able">&nbsp;</span>';
				}
				else {
					return '<span class="icon-cancell">&nbsp;</span>';
				}
			}},
			{field:'SEX',title:'性别',width:80,align:'center',formatter:function(value, rowData, rowIndex){
				if (value == '0') {
					return '男';
				}
				else if (value == '1') {
					return '女';
				}
				else {
					return '保密';
				}
			}},
			{field:'role_name',title:'用户角色',width:120,halign:'center'},
			{field:'gl',title:'管理',width:100,align:'center',formatter:function(value, rowData, rowIndex){
				var del = '<a href="javascript:void(0);" onclick="deluser(\''+rowIndex+'\');"><span class="icon-sc">&nbsp;</span>'+'[删除]</a>';
				return del;
			}}
		]],
		toolbar:[{
			text: '用户录入',
			iconCls: 'icon-add',
			handler: function(){
				addUser(1);
			}
		},'-',{
			text: '用户编辑',
			iconCls: 'icon-edit',
			handler: function(){
				addUser(2);
			}
		},'-',{
			text: '重置密码',
			iconCls: 'icon-wand',
			handler: function(){
				resetpass();
			}
		}],
       	pageSize: 20,
		pagination: true,
		rownumbers: true
    });
});

	//增加
	function addUser(flag) {
		var title;
		var url_ = '<c:url value="/xtgl/rygl/operUser.do"/>';
		if (flag == 1) {
			title = "录入";
		}
		else {
			var row = $('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('getSelected');
			if (!row) {
				$.messager.alert('警告', '请选择要编辑的人员！', 'warning');
				return;
			}
			title = "编辑";
			url_ = url_ + '?ryid='+row.uuid+'&adname='+row.adname+'&adcode='+row.adcode;
		}
		
		$('#DLG_9C2F2CB988B144769E49D669FE6BF684').dialog('close').remove();
		$('body').append('<div id="DLG_9C2F2CB988B144769E49D669FE6BF684" class="easyui-dialog" style="overflow:hidden;padding:3px;"></div>');
		$('#DLG_9C2F2CB988B144769E49D669FE6BF684').dialog({
			title: title+'用户',
			modal: true,
			closable: true,
			draggable: true,
			width: 500,
			height: 300,
			buttons: '#BTN_21B6F7D798A44366B56A56EA4B8D2A45'
		}).dialog('open').dialog('refresh', url_);
	}

	//保存
	function doSaveUser(){
		if($('#frm_6612FA46271740098ACCF7B134476FDB').form('enableValidation').form('validate')){
			var data = $('#frm_6612FA46271740098ACCF7B134476FDB').serialize();
			$.ajax({
				url: "<c:url value='/xtgl/rygl/doSaveUser.do'/>",
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
					//	$.messager.alert('错误', result.data, 'error');
					}
					else {
						$('#DLG_9C2F2CB988B144769E49D669FE6BF684').dialog('close');
						$('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('clearSelections').datagrid('reload');
						ZENG.msgbox.show(result.data, 4, 1500);
					}
				}
			});
		}
	}

	//取消
	function close_addUser() {
		$('#DLG_9C2F2CB988B144769E49D669FE6BF684').dialog('close').remove();
//		$('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('clearSelections');
	}

	//删除
	function deluser(rowIndex){
		datagridrowBtnSel('tbl_0B9557B832A144E696094AD23AB36FBA', rowIndex);
		var row = $('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('getSelected');
		if (!row) {
			$.messager.alert('警告', '请选择要删除的人员！', 'warning');
			return;
		}
		$.messager.confirm('消息', '确认删除所选择的人员吗？', function(r){
			if (r){
				$.ajax({
					url: "<c:url value='/xtgl/rygl/delUser.do' />",
					type: 'post',
					data: 'ryid='+row.uuid,
					beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress({
							title:'提示',
							msg:'正在执行数据操作，请稍待...'
						});
					},
					error: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
					},
					success: function(result) {
						$.messager.progress('close');
						if(!result.success) {
							$.messager.alert('提示', result.data, 'info');
						}
						else {
							$('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('clearSelections').datagrid('reload');
							ZENG.msgbox.show(result.data, 4, 1500);
						}
					}
				});
			}
		});
	}

	//重置密码 
	function resetpass() {
		var row = $('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('getSelected');
		if (!row) {
			$.messager.alert('警告', '请选择要重置密码的人员！', 'warning');
			return;
		}
		$.messager.confirm('消息', '确认要重置所选择的人员密码吗？', function(r){
			if (r){
				$.ajax({
					url: "<c:url value='/xtgl/rygl/resetPass.do' />",
					type: 'post',
					data: 'ryid='+row.uuid,
					beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress({
							title:'提示',
							msg:'正在执行数据操作，请稍待...'
						});
					},
					error: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
					},
					success: function(result) {
						$.messager.progress('close');
						if(!result.success) {
							$.messager.alert('提示', result.data, 'info');
						}
						else {
							$('#tbl_0B9557B832A144E696094AD23AB36FBA').datagrid('clearSelections').datagrid('reload');
							ZENG.msgbox.show(result.data, 4, 1500);
						}
					}
				});
			}
		});
	}
</script>