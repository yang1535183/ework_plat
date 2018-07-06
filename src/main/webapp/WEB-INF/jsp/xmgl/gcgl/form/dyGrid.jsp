<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<table id="ppdygridtable"></table>

<script>
$(function() {
	$('#ppdygridtable').datagrid({
		fit: true,
		striped: true,
		remoteSort: false,
		singleSelect: true,
		url: '<c:url value="/xmgl/gcgl/dygridJsondata.do"/>',
		singleSelect: false,
		frozenColumns:[[
			{field:'ck',checkbox:true}
 		]],
		columns: [[
			{field:'xh',title:'序号',width:60,halign:'center',align:'right'},
			{field:'gcmc',title:'工程名称',width:260,halign:'center',align:'right'},
			{field:'mj',title:'面积㎡',width:80,halign:'center',align:'right'},
			{field:'jglx',title:'结构类型/层次',width:120,halign:'center',align:'right'},
			{field:'jclx',title:'基础类型',width:160,halign:'center',align:'right'},
			{field:'gclb',title:'工程类别',width:120,halign:'center',align:'right'},
			{field:'jhsj',title:'计划开工/竣工时间',width:120,halign:'center',align:'right'},
			{field:'jdzch',title:'监督注册号',width:120,halign:'center',align:'right'}
		]]
	});
})
</script>