<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
	$(function(){
		$('#frm_6612FA46271740098ACCF7B134476FDB #status').combobox({
		    data: statusData,
		    valueField: 'id',
		    textField: 'text',
		    editable: false,
		    panelHeight: 'auto',
		    onLoadSuccess:function(){
		    	if(${FLAG} == "1") {
		    		$('#frm_6612FA46271740098ACCF7B134476FDB #status').combobox('setValue','${USER.status }');
				}
		    	else {
		    	//	$('#frm_6612FA46271740098ACCF7B134476FDB #status').combobox('setText','启用');
		    		$('#frm_6612FA46271740098ACCF7B134476FDB #status').combobox('setValue','1');
				}
		    }

		});
		
		$('#frm_6612FA46271740098ACCF7B134476FDB #sex').combobox({
		    data: sexData,
		    valueField: 'id',
		    textField: 'text',
		    editable: false,
		    panelHeight: 'auto',
		    onLoadSuccess:function(){
				$('#frm_6612FA46271740098ACCF7B134476FDB #sex').combobox('setValue','${USER.sex }');
		    }
		});

		/* $('#frm_6612FA46271740098ACCF7B134476FDB #xmlx').combotree({
			fit: true,
			required:true,
		    multiple:true,
		    data: ${STRMAPZD},
			idField: 'dmcode',
			textField: 'dmname',
			editable: false,
		    onLoadSuccess:function(){
		    	$('#frm_6612FA46271740098ACCF7B134476FDB #xmlx').combotree('setValues','${USER.xmlx }');
	   		}
		}); */
		
		$('#frm_6612FA46271740098ACCF7B134476FDB #js').combogrid({
			fit: true,
			required:true,
			idField: 'roleCode',
			textField: 'roleName',
			url: '<c:url value="/xtgl/jsgl/jsonTmisjsPageList.do"/>?page=1&rows=1000',
			columns: [[
				{field:'roleName',title:'角色名称',width:170,halign:'center'},
				{field:'roleType',title:'角色类型',width:150,halign:'center',formatter:function(value, rowData, rowIndex){
					if (value == '1') {
						return '定制角色';
					}
					else {
						return '内置角色';
					}
				}}
			]],
			editable: false,
		    onLoadSuccess:function(){
				$('#frm_6612FA46271740098ACCF7B134476FDB #js').combogrid('setValue','${ROLE}');
	   		}
		});
	});
	
	function validatebox(id) {
		$('#frm_6612FA46271740098ACCF7B134476FDB #'+id).validatebox('enableValidation').validatebox('validate');
	}

	function selqh() {
		selQhTree('dlg_B2B576F0BEB34949AF7B12F69608BBDF', 300, 450, $(document).width()-400, $(document).height()-550, true, 2);
	}

	function saveqh() {
		var chknode = $('#TREE_B361D9882A2F474288A9BCA4F226268E').tree('getChecked');
		if (chknode.length > 1) {
			$.messager.alert('警告', '只能选择一个地区！', 'warning');
			return;
		}

		$('#frm_6612FA46271740098ACCF7B134476FDB #qhmc').val(chknode[0].text);
		$('#frm_6612FA46271740098ACCF7B134476FDB #qhdm').val(chknode[0].id);

		$('#dlg_B2B576F0BEB34949AF7B12F69608BBDF').dialog('close');
	}
</script>

<form id="frm_6612FA46271740098ACCF7B134476FDB" method="post">
	<table class="formTableEasyuiShadow" style="width:100%;height: 90%" cellpadding="1" cellspacing="1">
		<tr>
			<th width="15%">
				<span style="color: #f00">*</span>登录名称：
			</th>
			<td width="35%">
			<c:choose >
				<c:when test="${FLAG != '1'}">
					<input id="loginname" name="loginname" class="easyui-validatebox textbox" maxlength="20" 
					value="${USER.loginname }" data-options="required:'true',validType:'unnormal',novalidate:true" 
					style="height:20px;width: 99%;" onblur="validatebox(this.id);"/>
				</c:when>
				<c:otherwise>
					<input id="loginname" name="loginname" class="easyui-textbox" maxlength="20" 
					value="${USER.loginname }" data-options="readonly:'true',validType:'unnormal',novalidate:true" 
					style="height:20px;width: 99%;" onblur="validatebox(this.id);"/>
				</c:otherwise>
			</c:choose>
				
				<input type="hidden" id="uuid" name="uuid" value="${USER.uuid }"/>
			</td>
			<th width="15%">
				<span style="color: #f00">*</span>真实姓名：
			</th>
			<td width="35%">
				<input id="username" name="username" class="easyui-validatebox textbox" maxlength="20" 
				value="${USER.username }" data-options="required:'true',validType:'unnormal',novalidate:true" 
				style="height:20px;width: 99%;" onblur="validatebox(this.id);"/>
			</td>
		</tr>
		<c:choose >
			<c:when test="${FLAG != '1'}">
				<tr>
					<th>
						<span style="color: #f00">*</span>登录密码：
					</th>
					<td>
						<input type="password" id="loginpass" name="loginpass" class="easyui-validatebox textbox" 
						style="height:20px;width: 99%;" value="123456" title="默认密码：123456" readonly="readonly"/>
					</td>
					<th>
						是否禁用：
					</th>
					<td colspan="3">
						<input id="status" name="status" class="easyui-combobox" style="height:23px;width: 40%;" />
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th>
						是否禁用：
					</th>
					<td colspan="5">
						<input id="status" name="status" class="easyui-combobox" style="height:23px;width: 40%;" />
					</td>
				</tr>
				<input type="hidden" id="loginpass" name="loginpass" value="${USER.loginpass }" />
				<input type="hidden" id="reloginpass" name="reloginpass" value="${USER.loginpass }"/>
			</c:otherwise>
		</c:choose>
		<tr>
			<th>
				性别：
			</th>
			<td>
				<input id="sex" name="sex" class="easyui-combobox" style="height:23px;width: 80%;" />
			</td>
			<th>
				QQ号码：
			</th>
			<td>
				<input id="qq" name="qq" class="easyui-validatebox textbox" maxlength="20" value="${USER.qq }" 
				data-options="validType:'qq',novalidate:true"  style="height:23px;width: 80%;" onblur="validatebox(this.id);"/>
			</td>
		</tr>
		<tr>
			<th>
				联系方式：
			</th>
			<td>
				<input id="cellphone" name="cellphone" class="easyui-validatebox easyui-textbox" maxlength="100" value="${USER.cellphone }" style="height:23px;width: 80%;" />
			</td>
			<th>
				常用邮箱：
			</th>
			<td>
				<input id="email" name="email" class="easyui-validatebox textbox" maxlength="20" data-options="required:'true', novalidate:true, validType:'email'" 
				value="${USER.email }" style="height:23px;width: 80%;" onblur="validatebox(this.id);"/>
			</td>
		</tr>
		<tr>
			<th>
				所属地区：
			</th>
			<td colspan="3">
				<input id="adname" name="adname" readonly="readonly" class="textbox" value="${ADNAME }"style="height:23px;width: 50%;" 
				data-options="required:'true',validType:'unnormal',novalidate:true" onblur="validatebox(this.id);" />
				
				<a style="float: right" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selqh();">选取</a>
				<input id="qhdm" name="qhdm" type="hidden" value="${ADCODE }"/>
			</td>
		</tr>
		<tr>
			<th>
				角色：
			</th>
			<td colspan="3">
				<input id="js" name="js" class="easyui-combogrid" style="height:20px;width: 80%;" />
			</td>
		</tr>
	</table>
</form>

<div style="display:none;">
	<div id="dlg_B2B576F0BEB34949AF7B12F69608BBDF" class="easyui-dialog" closed="true" toolbar="#dlg-toolbar"></div>
	
	<!--创建Toolbar-->
    <div id="dlg-toolbar">
        <table cellpadding="0" cellspacing="0" style="width: 100%">
            <tr>
                <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="saveqh();">选择</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="javascript:$('#dlg_B2B576F0BEB34949AF7B12F69608BBDF').dialog('close')">取消</a>
                </td>
            </tr>
        </table>
    </div>
</div>