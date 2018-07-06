<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="easyui-layout" fit="true" style="width: 100%">
	<div region="west" split="true" border="true" title='角色列表' style="width:60%;overflow: auto">
		<table id="tblListRole" border="false"></table>
	</div>
	<div region="center" border="true" title='人员列表' style="width:38%;overflow: auto">
		<table id="TBL_7BD26D76E25A4C4C9513096061B43E81" border="false"></table>
	</div>
</div>

<!-- //弹出对话框 -->
<div style="display:none;">
	<div id="dlgRole" class="easyui-dialog" closed="true" style="width: 546px;">
		<div style="padding:10px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<form id="frmRole" method="post" style="margin:0;padding:0">
						<table cellpadding="0" cellspacing="0" class="formTable">
							<tr>
								<th width="60px">角色代码：</th>
								<td>
									<input type="text" name="roleDisp" style="width:160px;" disabled="disabled" value="自动生成" />
									<input type="text" name="roleCode" readonly="readonly" style="width:160px;display:none;" />
									<input type="hidden" name="authCodes" />
									<input type="hidden" name="roleType" value="CUSTEM" />
									<input type="hidden" name="roleEndow" value="0" />
								</td>
							</tr>
							<tr>
								<th><font>*</font>角色名称：</th>
								<td><input type="text" id="roleName" name="roleName" style="width:160px;" class="easyui-validatebox" required="true" validType="unnormal" /></td>
							</tr>
							<tr style="display:none;">
								<th>角色级次：</th>
								<td>
									<select name="roleLevel" style="width:166px;">
									<option value="0">最高级</option>
									<option value="1">1-级</option>
									<option value="2">2-级</option>
									<option value="3">3-级</option>
									</select>
								</td>
							</tr>
						</table>
						</form>
					</td>
					<td>
						<div id="authTreeRole" style="width:240px;height:200px;margin:0 0 0 10px;padding:5px;border:1px solid #cccccc;overflow:auto;"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
var authTreeRole;
$(function(){
	// 构造datagrid
	$('#tblListRole').datagrid({
		fit:true,
		nowrap: false,
		striped: true,
		url:'<c:url value="/xtgl/jsgl/jsonTmisjsPageList.do"/>',
		frozenColumns:[[
            {field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'roleCode',title:'角色代码',width:160},
			{field:'roleName',title:'角色名称',width:160},
			{field:'roleType',title:'角色类型',width:100,formatter:function(value, rowData){
				if ('SYSTEM' == value.toUpperCase()) {
					return '内置角色';
				}
				else if ('CUSTOM' == value.toUpperCase()) {
					return '定制角色';
				}
				else {
					return value;
				}
			}},
			{field:'roleEndow',title:'可授予',width:100,formatter:function(value, rowData){
				if ('1' == value) {
					return '可授予';
				}
				else if ('0' == value) {
					return '不可授予';
				}
				else {
					return '未知状态';
				}
			}}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				newItemRole();
			}
		}
		/* ,{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				dropItemRole($('#tblListRole').datagrid('getSelected'));
			}
		} */
		,{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				editItemRole($('#tblListRole').datagrid('getSelected'));
			}
		}],
		onClickRow:function(rowIndex, rowData) {
			$('#tblListRole').datagrid('clearSelections').datagrid('selectRow', rowIndex);
			loadRybyjs(rowData.roleCode);
		},
		onDblClickRow:function(rowIndex, rowData) {
			editItemRole(rowData);
		},
		pageSize: 20,
		pageList: [10,20,30,40,50]
	});

	$('#TBL_7BD26D76E25A4C4C9513096061B43E81').datagrid({
		fit: true,
		nowrap: false,
		striped: true,
		remoteSort: false,
		idField: 'personuuid',
		url: '<c:url value="/xtgl/jsgl/jsonRyByjs.do"/>?page=1&rows=1000',
		queryParams: {role_code: ''},
		columns: [[
			{field:'username',title:'人员名称',width:150}
		]],
		rownumbers: true
	});

	// 执行构建权限树
	drawTreeRole()
});

function loadRybyjs(roleCode) {
	var queryParams_ = $('#TBL_7BD26D76E25A4C4C9513096061B43E81').datagrid('options').queryParams;
	queryParams_.role_code = roleCode;
	$('#TBL_7BD26D76E25A4C4C9513096061B43E81').datagrid('options').queryParams = queryParams_;
	$('#TBL_7BD26D76E25A4C4C9513096061B43E81').datagrid('clearSelections').datagrid('load');
}

// 构建权限树
function drawTreeRole() {
	$.ajax({
		url: '<c:url value="/xtgl/qxgl/qxJsonAll.do"/>',
		type: 'POST',
		success: function(result) {
			if(result.success) {
				var rows = result.data;
				authTreeRole = new dTree('authTreeRole','<c:url value="/ework-res/dtree/2.05/images/explorer/"/>');
				authTreeRole.config.folderLinks = true;
				authTreeRole.config.useCookies = false;
				authTreeRole.config.check = true;
				authTreeRole.add('0','-1','权限',"javascript:void(0);");
				
				for (var i=0; i<rows.length; i++) {
					authTreeRole.add(rows[i].auth_code, rows[i].up_auth_code, rows[i].display_name,"javascript:javascript:void(0);");
				}
				document.getElementById('authTreeRole').innerHTML = authTreeRole;
				authTreeRole.openAll();
				
			}
		}
	});
}

//新增：初始化窗口
function newItemRole(){
	drawTreeRole();
	$('#frmRole').resetForm();
	$('#frmRole input[name="roleDisp"]').css('display','block');
	$('#frmRole input[name="roleCode"]').css('display','none');
	$('#dlgRole').dialog({
		title:'新增角色',
		modal:true,
		closable:false,
		draggable:false,
		toolbar:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				saveItemRole();
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				$('#dlgRole').dialog('close');
			}
		}]
	}).dialog('open');
}

// 修改：初始化窗口
function editItemRole(row) {
	if (!row) {
		$.messager.alert('消息', '请选择要修改的数据！', 'info');
	}
	else {
		var nodes = authTreeRole.getCheckedNodes();
		for (var i=0; i<nodes.length; i++) {
			if(authTreeRole.co(nodes[i])!=null){
				authTreeRole.co(nodes[i]).checked = false;
			}
		}
		
		$('#frmRole input[name="roleDisp"]').css('display','none');
		$('#frmRole input[name="roleCode"]').css('display','block');
		$('#frmRole').form('load', row);
		for (var i=0; i<row.authoritys.length; i++) {
			if(authTreeRole.co(row.authoritys[i].authCode)){
				authTreeRole.co(row.authoritys[i].authCode).checked = true;
			}
		}
	
		$('#dlgRole').dialog({
			title:'修改角色',
			modal:true,
			closable:false,
			draggable:false,
			toolbar:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					saveItemRole();
				}
			},{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){
					$('#dlgRole').dialog('close');
				}
			}]
		}).dialog('open');
	}
}

//保存
function saveItemRole() {
	if ($('#frmRole').form('validate')) {

		var nodes = authTreeRole.getCheckedNodes();
		var authCodes = [];
		
		for (var i=0; i<nodes.length; i++) {
			if ('0' != nodes[i]) {
				authCodes.push(nodes[i]);
			}
		}
		authCodes.join(',');
		$('#frmRole input[name="authCodes"]').val(authCodes);

		var data = $('#frmRole').serialize();
		$.ajax({
			url: "<c:url value='/xtgl/jsgl/doJsSave.do' />",
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
					drawTreeRole();
					$('#tblListRole').datagrid('reload');
					$('#dlgRole').dialog('close');
				}
			}
		});
	}
}

//删除
function dropItemRole(row) {
	if (!row) {
		$.messager.alert('消息', '请选择要删除的数据！', 'info');
	}
	else {
		$.messager.confirm('删除数据', '确定要删除所选择的数据？', function(r){
			if (r){
				$.ajax({
					url: "<c:url value='/xtgl/jsgl/doDropJs.do' />",
					type: 'POST',
					data: 'roleCode='+row.roleCode,
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
							$.messager.alert('消息', result.data, 'info');
						}
						else {
							ZENG.msgbox.show(result.data, 4, 1500);
							$('#tblListRole').datagrid('clearSelections');
							$('#tblListRole').datagrid('reload');
						}
					}
				});
			}
		});
	}
}
</script>
