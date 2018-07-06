<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 市政 -->
<table id="ywSZSCTableDataGrid"></table>
<div id="ywSZSCToolBarDataGrid">
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
						<option value="">---请选择---</option>
    					<option value="1">待审查</option>
    					<option value="2">待审批</option>   
    					<option value="3">审查退回</option>
    					<option value="5">审批退回</option>   
    					<option value="4">注册码</option>
					</select>
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchXmbjsz();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton"  data-options="iconCls:'icon-book_open'" onclick="selectBootomButtonSZSC();">审查</a>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="ywBootomButton0608sz" style="text-align: center;height: 28px;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="zxscSaveSctj('ywSZSCTableDataGrid', 'ywGCDetail','GCSZ')">提交</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
		id='printGcbjlist' onclick="loadPrint(1,'ljlprintaddinfoaqjdbb'); ">打印预览</a>
</div>
<div id="ywBootomButton0608sz2" style="text-align: center;height: 28px;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
		id='printGcbjlist' onclick="loadPrint(1,'ljlprintaddinfoaqjdbb'); ">打印预览</a>
</div>

<script type="text/javascript">
	$(function() {
		$('#ywSZSCTableDataGrid').datagrid({
			fit: true,
			striped: true,
			remoteSort: false,
			singleSelect: true,
			url: '<c:url value="/xmgl/gcgl/gclr/jsonPageList.do"/>',
			queryParams: {
				xmlx: 'GCSZ',
				dmco: 'SCYTG'
			},
			columns: [[
				{field:'ck',checkbox:true},
				{field:'sjzt',title:'状态',width:80,halign:'center', formatter:function(value, row, index) {
					return sjztData(value,row.zcbm);
				}},
				{field:'gcmc',title:'工程名称',width:320,halign:'center', formatter:function(value, row, index) {
					return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="selectBootomButtonSZSC(\''+row.id+'\',\''+row.gcmc+'\',\''+row.sjzt+'\');">'+value+'</a>';
				}},
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
			toolbar:'#ywSZSCToolBarDataGrid',
	       	pageSize: 20,
			pagination: true,
			rownumbers: true,
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
	function searchXmbjsz() {
		var queryParams_ = $('#ywSZSCTableDataGrid').datagrid('options').queryParams;
		queryParams_.kgsj = $('#ywSZSCToolBarDataGrid #kgsj').val();
		queryParams_.sjzt = $('#ywSZSCToolBarDataGrid #sjzt').textbox('getValue');
		queryParams_.gcmc = $('#ywSZSCToolBarDataGrid #gcmc').textbox('getValue');
		$('#ywSZSCTableDataGrid').datagrid('options').queryParams = queryParams_;
		$('#ywSZSCTableDataGrid').datagrid('clearSelections').datagrid('reload');
	}
	
	function selectBootomButtonSZSC(_id, _gcmc, sjzt){
		if(_id) {
			if(sjzt == '1') {
				queryGcck('ywSZSCTableDataGrid', 'ywBootomButton0608sz', _id, _gcmc);
			}
			else {
				queryGcck('ywSZSCTableDataGrid', 'ywBootomButton0608sz2', _id, _gcmc);
			}
		}
		else{
			var row=$('#ywSZSCTableDataGrid').datagrid('getSelected');
			if (!row) {
				$.messager.alert('警告', '请选择要审查的工程项目！！！', 'warning');
				return;
			}
			if (row.sjzt=='1') {
				queryGcck('ywSZSCTableDataGrid', 'ywBootomButton0608sz');
			}else{
				queryGcck('ywSZSCTableDataGrid', 'ywBootomButton0608sz2');
			}
		}
		
	
	}
	
</script>