<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="fileuploadanddownloaddiv" style="width: 100%; height:100%; overflow:hidden; ">
<!-- <span>*注意：如企业没有上传附件，可在审查中点击【退回】即可退回当前项目！</span> -->
	<div id="fileuploadanddownload" style="width: 860px; height: 310px; overflow:hidden; margin-top: 10px; margin-left: 60px;">
		<table id="fileuploadanddownloadtable"></table>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$('#fileuploadanddownloadtable').datagrid({
			fit: true,
			striped: true,
			remoteSort: false,
			singleSelect: false,
			url: '<c:url value="/xmgl/gcgl/jsonUploadfile.do"/>',
			queryParams: {
				gcid: '${gcid}'
			},
			frozenColumns:[[
				{field:'ck',checkbox:true}
	 		]],
			columns: [[
				{field:'name',title:'序号',width:40,halign:'center',align:'right',formatter:function(value, row, index) {
					return index+1;
				}},
				{field:'dm_name',title:'附件名称',width:140,halign:'center',align:'left'},
				{field:'filename',title:'上传文件名',width:160,halign:'center',align:'right'},
				{field:'SC',title:'审查',width:120,halign:'center',align:'center', formatter:function(value, row, index) {
					if(!row.id) {
						return '无附件';
					}
					else {
						if(row.sccz==1) {return '通过';}
							else {return '不通过';}
					}
				}},
				{field:'scyj',title:'审查意见',width:180,halign:'center',align:'right'},
				{field:'CZ',title:'操作',width:130,align:'center',formatter:function(value, row, index) {
					var _a = '<a href="<c:url value="/file/download/fileDownload.do"/>?fileid='+row.id+'"><span class="icon-hourglass_add">&nbsp;</span>[下载]</a>';
					if(!row.id) {
						return '无附件';
					}
					else {
						return _a;
					}
				}}
			]]
		});
		
	});
	
</script>