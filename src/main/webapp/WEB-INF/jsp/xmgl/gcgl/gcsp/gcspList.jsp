<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date" />
<table id="ywGetSPList0609"></table>
<div id="ywToolBar0609">
	<div data-options="region:'north'">
		<table>
			<tr>
				<td>日期：</td>
				<td>
					<input id="cjsj" name="cjsj"  type="text" class="Wdate round" readonly="readonly"
						value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy" />"
						onclick="WdatePicker({dateFmt:'yyyy'})" style="width: 90px; height:24px;" />
				</td>
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
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryXmbj()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-book_open'" onclick="seeGCDetail()">查看</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="passSP('ywGetSPList0609', 'ywGCSPDetail', 'GCFJ', 'SPLDTG')">通过</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="passSP('ywGetSPList0609', 'ywGCSPDetail', 'GCFJ', 'SPLDBTG')">打回</a></td>
			</tr>
		</table>
	</div>
</div>
<div id="ywBootomButton0609" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="passSp('ywGetSPList0609', 'ywGCSPDetail', 'GCFJ', 'SPLDTG')">通过</a> 
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="passSp('ywGetSPList0609', 'ywGCSPDetail', 'GCFJ', 'SPLDBTG')">打回</a>
</div>

<script type="text/javascript">
	$(function() {
		$('#ywGetSPList0609').datagrid({
			fit: true,
			striped: true,
			remoteSort: false,
			singleSelect: true,
			url: '<c:url value="/xmgl/gcgl/gclr/jsonPageList.do"/>',
			queryParams: {
				xmlx: 'GCFJ',
				dmco: 'SPLDTG'
			},
			frozenColumns:[[
				{field:'ck',checkbox:true}
	 		]],
			columns: [[
				{field:'sjzt',title:'状态',width:80,halign:'center', formatter:function(value, row, index) {
					return sjztData(value,row.zcbm);
				}},
				{field:'gcmc',title:'工程名称',width:320,halign:'center'},
				{field:'gcdd',title:'工程地点',width:180, formatter:function(value, row, index) {
					var str = '';
					if(row.qu)  {str = row.qu+'区';}
					if(row.lu)  {str = str + row.lu+'路';}
					if(row.hao) {str = str + row.hao+'号';}
					return str;
				}},
				{field:'kgsj',title:'开工日期',width:80,halign:'center',align:'right'},
				{field:'jgsj',title:'竣工日期',width:80,halign:'center',align:'right'},
				{field:'FJ',title:'附件',width:80,align:'center',formatter:function(value,row,index) {
					return '<a target="_blank" href="<c:url value="/xmgl/gcgl/form/fjpage.do"/>?id='+row.id+'&gcmc='+row.gcmc+'" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'">查看附件</a>';
				}}
			]],
			toolbar:'#ywToolBar0609',
	       	pageSize: 20,
			pagination: true,
			rownumbers: true,
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
	function queryXmbj() {
		var queryParams_ = $('#ywGetSPList0609').datagrid('options').queryParams;
		queryParams_.kgsj = $('#ywToolBar0609 #kgsj').val();
		queryParams_.gcmc = $('#ywToolBar0609 #gcmc').textbox('getValue');
		queryParams_.sjzt = $('#ywToolBar0609 #sjzt').textbox('getValue');
		$('#ywGetSPList0609').datagrid('options').queryParams = queryParams_;
		$('#ywGetSPList0609').datagrid('clearSelections').datagrid('reload');
	}
	
	function seeGCDetail() {
		var row=$('#ywGetSPList0609').datagrid('getSelected');
		if (!row) {
			$.messager.alert('警告', '请选择要查看的工程项目！！！', 'warning');
			return;
		}
		
		$('#ywGCSPDetail').dialog('close').remove();
		$('body').append('<div id="ywGCSPDetail" class="easyui-dialog" style="overflow-x:hidden;padding:3px;"></div>');
		$('#ywGCSPDetail').dialog({
			title: '工程详情:'+row.gcmc,
			modal: true,
			closable: true,
			draggable: true,
			width: 1070,
			height:'90%',
			top:'10%',
			buttons:'#ywBootomButton0609'
		}).dialog('open').dialog('refresh', '<c:url value="/xmgl/gcgl/infoForm.do"/>?step=gcsp&id='+row.id);
	}
</script>