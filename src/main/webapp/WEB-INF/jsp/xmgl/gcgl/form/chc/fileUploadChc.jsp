<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="filechcuploadanddownloaddiv" style="width: 100%; height:100%; overflow:hidden;">
	<div id="filechcuploadanddownload" style="width: 860px; height: 310px; overflow:hidden; margin-top: 40px; margin-left: 120px;">
		<table id=filechcuploadanddownloadtable></table>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$('#filechcuploadanddownloadtable').datagrid({
			fit: true,
			striped: true,
			remoteSort: false,
			singleSelect: true,
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
				{field:'dm_name',title:'附件名称',width:210,halign:'center',align:'left'},
				{field:'filename',title:'上传文件名',width:160,halign:'center',align:'right'},
				{field:'SC',title:'审查',width:120,halign:'center',align:'center', formatter:function(value, row, index) {
					if(!row.id) {
						return '无附件';
					}
					else {
						if(row.sccz==1) {
							return '通过';
						}
						else{
							return '<span style="background-color:red">'+"不通过"+'</span>';
						}
					}
				}},
				{field:'scyj',title:'审查意见',width:138,halign:'center',align:'right'},
				{field:'CZ',title:'操作',width:120,align:'center',formatter:function(value, row, index) {
					var _a = '<a href="javascript:void(0);" onclick="uploadChcFj(\''+row.id+'\',\''+row.dm_code+'\',\''+row.dm_name+'\',\''+row.gcid+'\');"><span class="icon-redo">&nbsp;</span>[上传]</a>'; 
					var _b = '<a href="<c:url value="/file/download/fileDownload.do"/>?fileid='+row.id+'" target="_blank"><span class="icon-hourglass_add">&nbsp;</span>[下载]</a>';
					return _a + _b;
				}}
			]]
		});
		
	});
	
	function uploadChcFj(id, dmcode, dm_name, _gcid) {
		if('null' != _gcid) {
			$.messager.confirm('消息', '系统检测'+dm_name+'，已上传，是否覆盖已上传数据！！！', function(r){
				if (r){
					fileChcUpload(id, dmcode, dm_name);
				}
			});
		}
		else {
			fileChcUpload(id, dmcode, dm_name);
		}
	}
	
	function fileChcUpload(_id, dmcode, dm_name) {
		var _html = '<div id="fildImpData" class="easyui-dialog" closed="true">';
		_html +='	<div style="padding:15px;">';
		_html +='		<form id="frmImpData" method="post" style="margin:0;padding:0">';
		_html +='		<table cellpadding="0" cellspacing="0" class="formTable">';
		_html +='			<tr>';
		_html +='				<th><font>*</font>选择文件：</th>';
		_html +='				<td style="width:340px">';
		_html +='					<input type="hidden" id="fjbm" name="fjbm" />';
		_html +='					<input type="text" id="wjmc" name="wjmc" style="width:250px" readonly="readonly" />';
		_html +='					<span id="spanImpData"></span>';
		_html +='				</td>';
		_html +='			</tr>';
		_html +='		</table>';
		_html +='		</form>';
		_html +='	</div>';
		_html +='</div>';
		$('#fildImpData').remove();
		$('body').append(_html);

		$('#frmImpData').resetForm();
		$('#fildImpData').dialog({
			title: dm_name+'-附件上传',
			width: 460,
			height: 100,
			modal: true,
			closable: true,
			draggable: true
		}).dialog('open');
		$("#spanImpData").empty().append('<input type="button" id="btnImpData" class="button_bg" value="上传" />');
		
		if('null' != _id) {
			var args = {pzmc:'UPLOADH5', id: _id, xmlx:dmcode, gcid:$('#cCFormAdd #id').val()};
		}
		else {
			var args = {pzmc:'UPLOADH5', xmlx:dmcode, gcid:$('#cCFormAdd #id').val()};
		}
		
		$("#btnImpData").each(function() {
			new AjaxUpload(this, {
				action: '<c:url value="/file/batchUpLoad/uploadH5.do"/>',
				name: 'frmImpData',
				data: args,
				onSubmit: function(file, ext) {
					$.messager.progress({
						title: '请稍候',
						msg: '文件上传中...'
					});
					this.disable();
				},
				onComplete: function(file, response) {
					$.messager.progress('close');
					var result = response.substring(response.lastIndexOf('{'), 
							response.indexOf('}')+1);
					
					result = eval('('+result+')');
					if (result.success) {
						$("#cCFormAdd #id").attr("value", result.gcid);
						fileQuery(result.gcid);
						$('#fildImpData').dialog('close').remove();
					}
					else {
						$.messager.alert('错误', result.data, 'error');
					}
					this.enable();
				}
			});
		});
	}
	
	function fileQuery() {
		var queryParams_ = $('#filechcuploadanddownloadtable').datagrid('options').queryParams;
		queryParams_.gcid = $('#cCFormAdd #id').val();
		$('#filechcuploadanddownloadtable').datagrid('options').queryParams = queryParams_;
		$('#filechcuploadanddownloadtable').datagrid('clearSelections').datagrid('reload');
	}
</script>