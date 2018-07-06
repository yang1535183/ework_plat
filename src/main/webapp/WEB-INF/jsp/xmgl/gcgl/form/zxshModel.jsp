<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!-- 逐项审核 -->
<!-- <span>*注意：审核意见回车保存，也可通过操作中的保存进行保存！</span> -->
<div style="margin-top: 10px; "></div>
<table id="ppzxshlisttable"></table>

<script>
$(function() {
	$('#ppzxshlisttable').datagrid({
		fit: true,
		striped: false,
		nowrap:false,
		remoteSort: false,
		singleSelect: true,
		url: '<c:url value="/xmgl/gcgl/gcsc/zxshJsonList.do"/>',
		queryParams: {
			id: '${gcid}',
			xmlx: '${xmlx}'
		},
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'id',title:'序号',width:30,halign:'center',align:'left',formatter:function(value, row, index){
				return index+1;
			}},
			{field:'yjml',title:'所属类型',width:120,halign:'center',align:'left'},
 		]],
		columns: [[
			{field:'scmc',title:'审查名称',width:180,halign:'center',align:'left'},
			{field:'scsj',title:'审查数据',width:180,halign:'center',align:'left', formatter:function(value, row, index) {
				return zxscData(row);
			}},
			{field:'SC',title:'审查',width:120,halign:'center',align:'center', formatter:function(value, row, index) {
				var _a = '<input onchange="saveOnchange(this, \'ZXSPSJ\', \'ppzxshlisttable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="1" /><span id="'+row.id+'tg">通过</span>';
				var _b = '<input onchange="saveOnchange(this, \'ZXSPSJ\', \'ppzxshlisttable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="0" /><span id="'+row.id+'btg">不通过</span>';
				if(row.sccz==1) 
					{_a = '<input onchange="saveOnchange(this, \'ZXSPSJ\', \'ppzxshlisttable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="1" checked="checked"/><span id="'+row.id+'tg" >通过</span>';}
				else 
					{_b = '<input onchange="saveOnchange(this, \'ZXSPSJ\', \'ppzxshlisttable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="0" checked="checked"/><span id="'+row.id+'btg" style="color:red">不通过</span>';}
				return _a + '&nbsp;|' + _b;
			}},
			{field:'scyj',title:'审查意见',width:180,halign:'center',align:'right', editor:{type: 'validatebox'}}
		]],
		onLoadSuccess:function() {
			var dc = $(this).data('datagrid').dc;
            var header2Row = dc.header2.find('tr.datagrid-header-row');
            dc.body2.find('table').append(header2Row.clone().css({"visibility":"hidden"}));
            
            editIndex = undefined;
			$('#ppzxshlisttable').datagrid('clearSelections');
			$(this).datagrid("autoMergeCells", ['yjml']);
		},
        onDblClickCell: function(rowIndex, field, value) {
        	editRows(rowIndex, field, 'ppzxshlisttable');
		},
		onAfterEdit: function(rowIndex, rowData, changes) {
			var args = {tname:'ZXSPSJ', id: rowData.id};
			saveOnAfterEdit(changes, 'ppzxshlisttable', args);
		}
	});
})

</script>