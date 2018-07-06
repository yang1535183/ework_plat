<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:useBean id="now" class="java.util.Date" />
<table id="ywGetCCSCList0612"></table>
<div id="ywCCSCToolBar0612">
	<div data-options="region:'north'">
		<table>
			<tr>
				<td>日期：</td>
				<td><input id="kgsj" name="kgsj" type="text"
					class="Wdate round" readonly="readonly"
					value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy" />"
					onclick="WdatePicker({dateFmt:'yyyy'})"
					style="width: 90px; height: 24px;" /></td>
				<td>&nbsp;&nbsp;</td>
				<td>项目名称：</td>
				<td><input id="gcmc" class="easyui-textbox"
					style="width: 190px; height: 28px;"></td>
				<td>&nbsp;&nbsp;</td>
				<td>项目状态：</td>
				<td>
					<select id="sjzt" class="easyui-combobox" style="width: 130px; height:28px;" editable="false">
						<option value="">--请选择--</option>   
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
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-book_open'" onclick="queryGcChc()">审查</a></td>
			</tr>
		</table>
	</div>
</div>
<div id="ywCCSCBootomButton0612" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-save'" onclick="zxscSaveSctj('ywGetCCSCList0612', 'ywCCSCDetail', 'GCCHC');">提交</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
		 onclick="loadPrint(1,'printChcFormcCForm'); ">打印预览</a>
</div>

<div id="ywCCSCBootomButton06122" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
	onclick="loadPrint(1,'printChcFormcCForm');">打印预览</a>
</div>

<script type="text/javascript">
	$(function() {
		$('#ywGetCCSCList0612').datagrid({
			fit : true,
			striped : true,
			checkbox : true,
			singleSelect: true,
			remoteSort : false,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			url : '<c:url value="/xmgl/ccgl/cCListDataGrid.do"/>',
			queryParams : {
				xmlx: 'GCCHC',
				dmco: 'SCYTG'
			},
			columns : [ [ {
				field : 'sjzt',
				title : '状态',
				width : 80,
				halign : 'center',
				formatter : function(value, row, index) {
					return sjztData(value,row.zcbm);
				}
			}, {field:'gcmc',title:'工程名称',width:320,halign:'center', formatter:function(value, row, index) {
				return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="queryGcChc(\''+row.id+'\',\''+row.gcmc+'\',\''+row.sjzt+'\');">'+value+'</a>';
			}}, {
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
				width : 180,
				halign : 'center',
				align : 'right'
			}, {
				field : 'jhjgrq',
				title : '计划竣工日期',
				width : 180,
				halign : 'center',
				align : 'right'
			},{field:'FJ',title:'附件',width:80,align:'center',formatter:function(value,row,index) {
				return '<a target="_blank" href="<c:url value="/xmgl/gcgl/form/fjpage.do"/>?id='+row.id+'&gcmc='+row.gcmc+'" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'">查看附件</a>';
			}}
			]],
			toolbar : '#ywCCSCToolBar0612',
			pageSize : 20,
			pagination : true,
			rownumbers : true,
			rowStyler: function(index,row){
				if(row.sjzt == '4') {
					return 'background-color:#01B468;color:#fff;';
				}
				else if (row.sjzt == '2'){
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
		var queryParams_ = $('#ywGetCCSCList0612').datagrid('options').queryParams;
		queryParams_.kgsj = $('#ywCCSCToolBar0612 #kgsj').val();
		queryParams_.gcmc = $('#ywCCSCToolBar0612 #gcmc').textbox('getValue');
		queryParams_.sjzt = $('#ywCCSCToolBar0612 #sjzt').textbox('getValue');
		$('#ywGetCCSCList0612').datagrid('options').queryParams = queryParams_;
		$('#ywGetCCSCList0612').datagrid('clearSelections').datagrid('reload');
	}
	
	//审查
	function queryGcChc(_id, _gcmc,sjzt) {
		var id, gcmc;
		var scButton='#ywCCSCBootomButton06122';
		if(_id) {
			id = _id;
			gcmc = _gcmc;
			if(sjzt=='1'){
				scButton='#ywCCSCBootomButton0612';
			}else{
				var scButton='#ywCCSCBootomButton06122';
			}
		}
		else {
			var row=$('#ywGetCCSCList0612').datagrid('getSelected');
			if (!row) {
				$.messager.alert('警告', '请选择工程项目！！！', 'warning');
				return;
			}
			id = row.id;
			gcmc = row.gcmc;
			if (row.sjzt==1) {
				scButton='#ywCCSCBootomButton0612';
			}
		}
		
		$('#gcxmlcjllist').remove();
		$('#ppzxshlisttable').remove();
		$('#ppgxaddandmmodelform').remove();
		$('#ljlprintaddinfoaqjdbb').remove();
		$('#fileuploadanddownloaddiv').remove();
		$('#ywCCSCDetail').dialog('close').remove();
		$('body').append('<div id="ywCCSCDetail" class="easyui-dialog" style="overflow-x:hidden;padding:3px;"></div>');
		$('#ywCCSCDetail').dialog({
			title: '工程详情：'+gcmc,
			modal: true,
			closable: true,
			draggable: true,
			width: 1070,
			height:'90%',
			top:'10%',
			buttons:scButton
		}).dialog('open').dialog('refresh', '<c:url value="/xmgl/ccgl/addCc.do"/>?step=gcsc&id='+id);
	}
</script>