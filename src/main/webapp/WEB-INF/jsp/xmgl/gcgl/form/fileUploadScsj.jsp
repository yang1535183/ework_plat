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
						var _a = '<input onchange="saveOnchange(this, \'IMPDATA\', \'fileuploadanddownloadtable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="1" /><span id="'+row.id+'btg">通过</span>';
						var _b = '<input onchange="saveOnchange(this, \'IMPDATA\', \'fileuploadanddownloadtable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="0" /><span id="'+row.id+'btg">不通过</span>';
						if(row.sccz==1) 
							{_a = '<input onchange="saveOnchange(this, \'IMPDATA\', \'fileuploadanddownloadtable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="1" checked="checked"/><span id="'+row.id+'tg">通过</span>';}
						else 
							{_b = '<input onchange="saveOnchange(this, \'IMPDATA\', \'fileuploadanddownloadtable\');" type="radio" id="'+row.id+'" name="'+row.id+'" value="0" checked="checked"/><span id="'+row.id+'btg" style="color:red">不通过</span>';}
						return _a + '&nbsp;|' + _b;
					}
				}},
				{field:'scyj',title:'审查意见',width:180,halign:'center',align:'right', editor:{type: 'validatebox'}},
				{field:'CZ',title:'操作',width:130,align:'center',formatter:function(value, row, index) {
					var _a = '<a href="javascript:void(0);" onclick="downLoadFj(\''+row.id+'\');"><span class="icon-hourglass_add">&nbsp;</span>[下载]</a>';
					if(!row.id) {
						return '无附件';
					}
					else {
						return _a;
					}
				}}
			]],
			onLoadSuccess: function(data) {
				var dc = $(this).data('datagrid').dc;
	            var header2Row = dc.header2.find('tr.datagrid-header-row');
	            dc.body2.find('table').append(header2Row.clone().css({"visibility":"hidden"}));
	            
	            editIndex = undefined;
				$('#fileuploadanddownloadtable').datagrid('clearSelections');
	        },
	        onDblClickCell: function(rowIndex, field, value) {
	        	editRows(rowIndex, field, 'fileuploadanddownloadtable');
			},
			onAfterEdit: function(rowIndex, rowData, changes) {
				if(rowData.id) {
					var args = {tname:'IMPDATA', id: rowData.id};
					saveOnAfterEdit(changes, 'fileuploadanddownloadtable', args);
				}
				else {
					show_('系统拒绝请求：', '暂不支持无上传文件中填写审核意见！！！', 'show');
				}
			}
		});
		
	});
	
	function editRows(index, field, tblid){
		if (endEditing(tblid)){
			$('#'+tblid).datagrid('selectRow', index).datagrid('editCell', {
	            index: index,
	            field: field,
	            tblid: tblid,
	            editcol: 2
	        });
			
			editIndex = index;
		} else {
			setTimeout(function(){
				$('#'+tblid).datagrid('selectRow', editIndex);
			},0);
		}
	}
	
	var editIndex = undefined;
	function endEditing(tblid){
		if (editIndex == undefined){return true;}
		if ($('#'+tblid).datagrid('validateRow', editIndex)){
			$('#'+tblid).datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	
</script>