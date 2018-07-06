<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!-- 逐项审核 -->
<span>审核意见回车保存</span>
	<table id="ppzxshlisttable"></table>
<script>
$(function() {
	$('#ppzxshlisttable').datagrid({
		fit: true,
		nowrap:false,
		striped: true,
		remoteSort: false,
		singleSelect: true,
		url: '<c:url value="/xmgl/gcgl/gcsc/zxshJsonList.do"/>',
		queryParams: {
			id: '${gcid}',
			xmlx: '${xmlx}'
		},
		columns: [[
			/* {field:'ck',checkbox:true}, */
			{field:'id',title:'序号',width:30,halign:'center',align:'left',formatter:function(value, row, index){
				return index+1;
			}},
			{field:'yjml',title:'所属类型',width:90,halign:'center',align:'left'},
			{field:'scmc',title:'审查名称',width:180,halign:'center',align:'left'},
			{field:'scsj',title:'审查数据',width:180,halign:'center',align:'left', formatter:function(value, row, index) {
				return zxscData(row);
			}},
			{field:'SC',title:'审查',width:120,halign:'center',align:'center', formatter:function(value, row, index) {
				if(row.sccz == '1') {
					return '通过';
				}
				else {
					return '<span style="background-color:red">不通过</span>';
				}
			}},
			{field:'scyj',title:'审查意见',width:180,halign:'center',align:'right'}
		]],
		onLoadSuccess:function() {
			$(this).datagrid("autoMergeCells", ['yjml']);
		}
	});
})


/**选择动作**/
function saveOnchange(_e) {
	var id = _e.id;
	var value = _e.value;
	
	var tgid = id+"tg";
	var btgid = id+"btg";
	
	if(value == 0) {//不通过
		$("#"+btgid).css({"color":"red"});
		$("#"+tgid).css({"color":""});}
	else//通过
		{$("#"+btgid).css({"color":""});
		$("#"+tgid).css({"color":"red"});}
	
	$.ajax({
		type: 'post',
		url: '<c:url value="/xmgl/gcgl/gcsc/zxscSave.do"/>',
		data: 'id='+id+'&vl='+value,
		error: function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.progress('close');
			ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
		},
		success: function(result) {
			if(!result.success) {
				ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
			}
		}
	});
}
</script>