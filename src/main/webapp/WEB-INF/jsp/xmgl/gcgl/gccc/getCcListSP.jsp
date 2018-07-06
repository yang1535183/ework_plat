<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:useBean id="now" class="java.util.Date" />
<table id="ywGetCCSPList0619"></table>
<div id="ywCCSPToolBar0619">
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
    					<option value="2">待审批</option>   
    					<option value="5">审批退回</option>   
    					<option value="4">注册码</option>   
					</select>
				</td>
				<td>&nbsp;&nbsp;</td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryChcXmbj()">查询</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-book_open'" onclick="seeCCSPDetail()">查看</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="passSP('ywGetCCSPList0619', 'ywCCSPDetail', 'GCCHC', 'SPLDTG')">通过</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="passSP('ywGetCCSPList0619', 'ywCCSPDetail', 'GCCHC', 'SPLDBTG')">打回</a>
				<td>
			</tr>
		</table>
	</div>
</div>
<div id="ywCCSPBootomButton0612" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="passSP('ywGetCCSPList0619', 'ywCCSPDetail', 'GCCHC', 'SPLDTG')">通过</a> 
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="passSP('ywGetCCSPList0619', 'ywCCSPDetail', 'GCCHC', 'SPLDBTG')">打回</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="loadPrint(1,'printChcFormcCForm');">打印预览</a>
</div>

<div id="ywCCSPBootomButton06122" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="loadPrint(1,'printChcFormcCForm');">打印预览</a>
</div>

<script type="text/javascript">
	$(function() {
		$('#ywGetCCSPList0619').datagrid({
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
				dmco: 'SPLDTG'
			},
			columns : [ [ {
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
				halign : 'center', formatter:function(value, row, index) {
					return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="seeCCSPDetail(\''+row.id+'\',\''+row.gcmc+'\',\''+row.sjzt+'\');">'+value+'</a>';
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
			] ],
			toolbar : '#ywCCSPToolBar0619',
			pageSize : 20,
			pagination : true,
			rownumbers : true,
			rowStyler: function(index,row){
				if (row.sjzt == '4'){
					return 'background-color:#01B468;color:#fff;';
				}
				else if (row.sjzt == '3'||row.sjzt == '5'){
					return 'background-color:red;color:#fff;';
				}
			}
		});
	});

	//查询
	function queryChcXmbj() {
		var queryParams_ = $('#ywGetCCSPList0619').datagrid('options').queryParams;
		queryParams_.kgsj = $('#ywCCSPToolBar0619 #kgsj').val();
		queryParams_.gcmc = $('#ywCCSPToolBar0619 #gcmc').textbox('getValue');
		queryParams_.sjzt = $('#ywCCSPToolBar0619 #sjzt').textbox('getValue');
		$('#ywGetCCSPList0619').datagrid('options').queryParams = queryParams_;
		$('#ywGetCCSPList0619').datagrid('clearSelections').datagrid('reload');
	}
	
	//工程详情
	function seeCCSPDetail(_id, _gcmc,_sjzt) {
		
		var id, gcmc;
		var spButton = '#ywCCSPBootomButton06122';
		if(_id) {
			id = _id;
			gcmc = _gcmc;
			$('#ywGetCCSPList0619').datagrid('clearSelections');
			if (_sjzt==2) {
				/* $('#ywGetCCSPList0619').datagrid('clearSelections'); */
				spButton='#ywCCSPBootomButton0612';
			}
		}
		else {
			var row=$('#ywGetCCSPList0619').datagrid('getSelected');
			if (!row) {
				$.messager.alert('警告', '请选择工程项目！！！', 'warning');
				return;
			}
			id = row.id;
			gcmc = row.gcmc;
			if (row.sjzt==2) {
				spButton='#ywCCSPBootomButton0612';
			}
		}
		
		$('#ljlprintaddinfoaqjdbb').remove();
		$('#fileuploadanddownloaddiv').remove();
		$('#ywCCSPDetail').dialog('close').remove();
		$('body').append('<div id="ywCCSPDetail" class="easyui-dialog" style="overflow-x:hidden;padding:3px;"></div>');
		$('#ywCCSPDetail').dialog({
			title: '工程详情:'+gcmc,
			modal: true,
			closable: true,
			draggable: true,
			width: 1070,
			height:'90%',
			top:'10%',
			buttons:spButton
		}).dialog('open').dialog('refresh', '<c:url value="/xmgl/ccgl/addCc.do"/>?step=gcsp&id='+id);
	}
	
</script>