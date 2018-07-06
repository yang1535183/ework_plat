<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 房建 -->
<table id="ywGetSCList0608"></table>
<div id="ywToolBar0608">
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
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchXmbj()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton"  data-options="iconCls:'icon-book_open'" onclick="queryGcck('ywGetSCList0608', 'ywBootomButton0608');">审查</a>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="ywBootomButton0608" style="text-align: center;height: 28px;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="zxscSaveSctj('ywGetSCList0608', 'ywGCDetail', 'GCFJ')">提交</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
		id='printGcbjlist' onclick="loadPrint(1,'ljlprintaddinfoaqjdbb'); ">打印预览</a>
</div>

<script type="text/javascript">
	$(function() {
		$('#ywGetSCList0608').datagrid({
			fit: true,
			striped: true,
			remoteSort: false,
			singleSelect: true,
			url: '<c:url value="/xmgl/gcgl/gclr/jsonPageList.do"/>',
			queryParams: {
				xmlx: 'GCFJ',
				dmco: 'SCYTG'
			},
			columns: [[
				{field:'ck',checkbox:true},
				{field:'sjzt',title:'状态',width:80,halign:'center', formatter:function(value, row, index) {
					return sjztData(value,row.zcbm);
				}},
				{field:'gcmc',title:'工程名称',width:320,halign:'center', formatter:function(value, row, index) {
					return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="queryGcckxm(3,\''+row.id+'\',\''+row.gcmc+'\');">'+value+'</a>';
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
			toolbar:'#ywToolBar0608',
	       	pageSize: 20,
			pagination: true,
			rownumbers: true,
			rowStyler: function(index,row){
				if (row.sjzt == '4'){
					return 'background-color:#66CCCC;color:#fff;';
				}else if (row.sjzt == '2'){
					return 'background-color:#66CCCC;color:#fff;';
				}
				else if (row.sjzt == '3'||row.sjzt == '5'){
					return 'background-color:red;color:#fff;';
				}
			}
		});
	});
	
	//查询
	function searchXmbj() {
		var queryParams_ = $('#ywGetSCList0608').datagrid('options').queryParams;
		queryParams_.kgsj = $('#ywToolBar0608 #kgsj').val();
		queryParams_.sjzt = $('#ywToolBar0608 #sjzt').textbox('getValue');	
		$('#ywGetSCList0608').datagrid('options').queryParams = queryParams_;
		$('#ywGetSCList0608').datagrid('clearSelections').datagrid('reload');
	}
</script>