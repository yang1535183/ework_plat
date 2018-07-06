<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:useBean id="now" class="java.util.Date" />
<table id="ywGetCCList0612"></table>
<div id="ywCCToolBar0612">
	<div data-options="region:'north'">
		<table>
			<tr>
				<td>日期：</td>
				<td><input id="kgsj" name="kgsj" type="text" class="Wdate round" readonly="readonly"
					value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy" />"
					onclick="WdatePicker({dateFmt:'yyyy'})" style="width: 90px; height: 24px;" /></td>
				<td>&nbsp;&nbsp;</td>
				<td>项目名称：</td>
				<td><input id="gcmc" class="easyui-textbox" style="width: 190px; height: 28px;"></td>
				<td>&nbsp;&nbsp;</td>
				<td>项目状态：</td>
				<td>
					<select id="sjzt" class="easyui-combobox" style="width: 130px; height:28px;" editable="false">
						<option value="">--请选择--</option>   
						<option value="0">未上报</option>   
    					<option value="1">待审查</option>
    					<option value="2">待审批</option>   
    					<option value="3">审查退回</option>
    					<option value="5">审批退回</option>   
    					<option value="4">注册码</option>   
					</select>
				</td>
				<td>&nbsp;&nbsp;</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchCcXm();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addCc('1')">录入</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="addCc('2');">修改</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="delChcGcxm('ywGetCCList0612');">删除</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-flag_red'" onclick="gcbjReportChcbj('ywGetCCList0612')">上报</a></td>
			</tr>
		</table>
	</div>
</div>
<div id="ywBootomButton06122" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveGcxmChc('ywCcDialog0612', 'ywGetCCList0612');">保存</a>
</div>
<div id="ywBootomButton0612" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveGcxmChc('ywCcDialog0612', 'ywGetCCList0612');">保存</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="loadPrint(1,'printChcFormcCForm');">打印预览</a>
</div>

<div id="ywBootomButton061222" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="loadPrint(1,'printChcFormcCForm');">打印预览</a>
</div>

<script type="text/javascript">
	$(function() {
		$('#ywGetCCList0612').datagrid({
			fit : true,
			striped : true,
			remoteSort : false,
			checkbox : true,
			rownumbers : true,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			url : '<c:url value="/xmgl/ccgl/cCListDataGrid.do"/>',
			queryParams : {
				xmlx: 'GCCHC',
				dmco: 'QYSB'
			},
			columns : [ [{
				field : 'sjzt',
				title : '状态',
				width : 80,
				halign : 'center',
				formatter : function(value, row, index) {
					return sjztData(value,row.zcbm);
				}
			}, {
				field : 'gcmc',
				title : '工程名称',
				width : 180,
				halign : 'center',
				formatter : function(value, row, index) {
					return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="addCc(2,\''+row.id+'\',\''+row.gcmc+'\',\''+row.sjzt+'\');">'+value+'</a>';
				}
			}, {
				field : 'gcdd',
				title : '工程地点',
				width : 180,
				halign : 'center'
			}, {
				field : 'ccmj',
				title : '拆除面积',
				width : 180,
				halign : 'center',
				align : 'right'
			}, {
				field : 'jgcc',
				title : '结构层次',
				width : 180,
				halign : 'center',
				align : 'right'
			}, {
				field : 'jhkgrq',
				title : '计划开工日期',
				width : 90,
				halign : 'center',
				align : 'right'
			}, {
				field : 'jhjgrq',
				title : '计划竣工日期',
				width : 90,
				halign : 'center',
				align : 'right'
			},{field:'FJ',title:'附件',width:80,align:'center',formatter:function(value,row,index) {
				return '<a target="_blank" onclick="clearOKgrid(\'#ywGetCCList0612\', \''+row.id+'\',\''+row.gcmc+'\')" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'">查看附件</a>';
			}}
			]],
			toolbar : '#ywCCToolBar0612',
			pageSize : 20,
			pagination : true,
			rownumbers : true,
			rowStyler: function(index,row){
				if(row.sjzt == '4') {
					return 'background-color:#01B468;color:#fff;';
				}
				else if (row.sjzt == '1' || row.sjzt == '2'){
					return 'background-color:#66CCCC;color:#fff;';
				}
				else if (row.sjzt == '3'||row.sjzt == '5'){
					return 'background-color:red;color:#fff;';
				}
			}
		});
	});

	//查询
	function searchCcXm() {
		var queryParams_ = $('#ywGetCCList0612').datagrid('options').queryParams;
		queryParams_.kgsj = $('#ywCCToolBar0612 #kgsj').val();
		queryParams_.gcmc = $('#ywCCToolBar0612 #gcmc').textbox('getValue');
		queryParams_.sjzt = $('#ywCCToolBar0612 #sjzt').textbox('getValue');
		$('#ywGetCCList0612').datagrid('options').queryParams = queryParams_;
		$('#ywGetCCList0612').datagrid('clearSelections').datagrid('reload');
	}

	//录入和修改工程信息
	function addCc(flag, _id, _gcmc,_sjzt) {
		var title, url_, btsid;
		if (flag == 1) {
			title = "录入：拆除报表";
			btsid='ywBootomButton06122';
			url_ = '<c:url value="/xmgl/ccgl/addCc.do"/>?step=gclr&xmlx=GCCHC';
		} else {
			var id;
			var gcmc;
			if(_id) {
				id = _id; gcmc = _gcmc;
				$('#ywGetCCList0612').datagrid('clearSelections');
				if (_sjzt==1 || _sjzt==2 || _sjzt==4) {
					btsid = "ywBootomButton061222";
				}else {
					btsid = "ywBootomButton0612";
				}
			}else{
				var row = $('#ywGetCCList0612').datagrid('getSelected');
				if (!row) {
					$.messager.alert('警告', '请选择要编辑的数据！', 'warning');
					return;
				}
				if (row.sjzt==1 || row.sjzt==2 || row.sjzt==4) {
					btsid = "ywBootomButton061222";
				}else {
					btsid = "ywBootomButton0612";
				}
				id = row.id;
				gcmc = row.gcmc;
			}
			url_ = '<c:url value="/xmgl/ccgl/addCc.do"/>?step=gclr&id=' + id;
			title = "编辑：" + gcmc;
		}
		
		$('#gcxmlcjllist').remove();
		$('#ppzxshlisttable').remove();
		$('#ppgxaddandmmodelform').remove();
		$('#ljlprintaddinfoaqjdbb').remove();
		$('#fileuploadanddownloaddiv').remove();
		$('#ywCcDialog0612').dialog('close').remove();
		$('body').append('<div id="ywCcDialog0612" class="easyui-dialog" style="overflow-x:hidden;padding:3px;"></div>');
		$('#ywCcDialog0612').dialog({
			title : title,
			modal : true,
			closable : true,
			draggable : true,
			width : 1070,
			height : '90%',
			top : '10%',
			buttons : '#'+btsid
		}).dialog('open').dialog('refresh', url_);
	}

</script>