<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 合同上传 -->
<table id="PPHTSCLISTTABLE"></table>

<div id="PPTOOLBARHTSCLISTTABLE">
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
				<td>
					<input id="gcmc" class="easyui-textbox" style="width: 190px; height:28px;">
				</td>
				<td>&nbsp;&nbsp;</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryhtsc();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-flag_red'" onclick="ReportHtsbGcbj()">上报</a>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
	$(function() {
		$('#PPHTSCLISTTABLE').datagrid({
			fit: true,
			striped: true,
			remoteSort: false,
			singleSelect: true,
			url: '<c:url value="/xmgl/htba/htsc/jsonPagehtbaList.do"/>',
			queryParams: {
				cjsj: $('#PPTOOLBARHTSCLISTTABLE #cjsj').val()
			},
			columns: [[
				{field:'ck',checkbox:true},
				{field:'zcbm',title:'注册码',width:120,halign:'center', align:'left'},
				{field:'gcmc',title:'工程名称',width:180,halign:'center', align:'left'},
				{field:'gcdd',title:'工程地点',width:260,halign:'center',align:'left'},
				{field:'kgsj',title:'开工日期',width:80,halign:'center',align:'right'},
				{field:'xmlx',title:'项目类型',width:80,halign:'center',align:'left', formatter:function(value,row,index) {
					return gclxcode(value);
				}},
				{field:'FJ',title:'工程附件',width:80,align:'center',formatter:function(value,row,index) {
					return '<a target="_blank" href="<c:url value="/xmgl/gcgl/form/fjpage.do"/>?id='+row.id+'&gcmc='+row.gcmc+'" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'">查看附件</a>';
				}},
				{field:'SCHT',title:'合同',width:80,align:'center',formatter:function(value,row,index) {
					return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="htsclist();">点击上传</a>';
				}}
			]],
			toolbar:'#PPTOOLBARHTSCLISTTABLE',
	       	pageSize: 20,
			pagination: true,
			rownumbers: true,
			rowStyler: function(index, row){
				if(index%2!=0) {
					return 'background-color:#EEE;';
				}
			},
			view: detailview,
			detailFormatter:function(index,row){
	            return '<div style="padding:2px;position:relative;"><table class="ddv"></table></div>';
	        },
	        onExpandRow: function(index, row){
	            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	            ddv.datagrid({
	        		url: '<c:url value="/xmgl/gcgl/gcsc/htShow.do"/>',
	        		queryParams: {
	        			pzmc: 'HTLOADH',
	        			gcid: row.id
	    			},
	                fitColumns: true,
	                singleSelect: true,
	                loadMsg: '正在加载...',
	                height: 'auto',
	                columns: [[
	                    {title:'合同类型', field: 'dmname', halign:'center', width:100 },
	                    {title:'审查意见', field: 'scyj', halign:'center', width:150 },
	                    {title:'上传人员', field: 'username', halign:'center', width:150 },
	                    {title:'上传时间', field: 'scsj', halign:'center', align:'right', width:150 },
						{title:'查看合同',field:'CKHT',width:80,align:'center',formatter:function(value,row,index) {
							return '<a target="_blank" href="javascript:void(0);" class="easyui-linkbutton" onclick="alert(\'功能未实现\');">点击查看</a>';
						}}
	                ]],
	                onResize:function(){
	                    $('#PPHTSCLISTTABLE').datagrid('fixDetailRowHeight',index);
	                },
	                onLoadSuccess:function(){
	                    setTimeout(function(){
	                        $('#PPHTSCLISTTABLE').datagrid('fixDetailRowHeight',index);
	                    },0);
	                }
	            });
	            $('#PPHTSCLISTTABLE').datagrid('fixDetailRowHeight',index);
	        }
	    });
	})
	
	function htsclist() {
		var _html = '<div id="fildHtsbImpData" class="easyui-dialog" closed="true">';
		_html +='	<div style="padding:15px;">';
		_html +='		<form id="frmHtsbImpData" method="post" style="margin:0;padding:0;">';
		_html +='		<table cellpadding="0" cellspacing="0" class="formTable">';
		_html +='			<tr>';
		_html +='				<th><font>*</font>选择文件类型：</th>';
		_html +='				<td style="width:168px">';
		_html +='					<select id="xmlx" name="xmlx" style="width: 120px; height:21px;">';
		_html +='						<option value="ZHT">总合同</option>';
		_html +='						<option value="FHT">分合同</option>';
		_html +='						<option value="JLHT">监理合同</option>';
		_html +='						<option value="SBHT">设备合同</option>';
		_html +='					</select>';
		_html +='				</td>';
		_html +='				<td style="width:168px;">';
		_html +='					<input  id="wjmc" name="wjmc" type="file" style="width:160px" />';
		_html +='				</td>';
		_html +='				<td align="left">';
		_html +='					<input type="button" id="btnImpData" onclick="uploadcheck()" value="上传" />';
		_html +='				</td>';
		_html +='			</tr>';
		_html +='		</table>';
		_html +='		</form>';
		_html +='	</div>';
		_html +='</div>';
		$('#fildHtsbImpData').remove();
		
		$('body').append(_html);
		$('#frmHtsbImpData').resetForm();
		$('#fildHtsbImpData').dialog({
			title: '合同上传：',
			width: 610,
			height: 100,
			modal: true,
			closable: true,
			draggable: true
		}).dialog('open');
	}
	
	function uploadcheck() {
		var wjmc = $('#fildHtsbImpData #wjmc').val();
		if(!wjmc) {
			$.messager.alert('警告', '请选择需要上传数据！！！', 'warning');
			return;
		}
		var id = $('#PPHTSCLISTTABLE').datagrid('getSelected').id;
		var xmlx = $('#frmHtsbImpData #xmlx').val();
		$.ajax({
			url : '<c:url value="/xmgl/htba/htsc/uploadcheck.do"/>',
			type: 'POST',
			data: {
				pzmc: 'HTLOADH',
				xmlx: xmlx,
				gcid: id
			},
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
				$.messager.progress('close');
			},
			success: function(result) {
				$.messager.progress('close');
				if(!result.success) {
					$.messager.confirm('确认操作：',result.data,function(r){    
					    if (r){    
					    	btnUpload('HTLOADH', xmlx, id);
					    }    
					}); 
				}
				else {
					btnUpload('HTLOADH', xmlx, id);
				}
			}
		});
	}
	
	function btnUpload(pzmc, xmlx, gcid) {
		$.ajaxFileUpload({
			url: '<c:url value="/file/batchUpLoad/uploadH5.do"/>',
			type: 'POST',
			data: {
				pzmc: pzmc,
				xmlx: xmlx,
				gcid: gcid
			},
			secureuri: false, //是否需要安全协议，一般设置为false
			fileElementId: 'wjmc', //文件上传域的ID
			dataType: 'json', //返回值类型一般设置为json
			handleError: function(s, xhr, status, e) {
				show_('温馨提示：', "引用错误！！！", 'show');
			},
			success: function (data, status) {//服务器成功响应处理函数
				show_('温馨提示：', data.data, 'show');
				
			},
			error: function(s, xhr, status, e) {
				show_('温馨提示：', "引用错误！！！", 'show');
			}
		});
	}
	
	function ReportHtsbGcbj() {
		var row = $('#PPHTSCLISTTABLE').datagrid('getSelected');
		if(!row) {
			$.messager.alert('警告', '请选择要上报的项目！！！', 'warning');
			return;
		}
		$.messager.confirm('消息', '确定上报【'+row.gcmc+'】数据？', function(r) {
			if (r) {
				$.ajax({
					url: '<c:url value="/xmgl/gcgl/gcsc/ReportHtsbGcbj.do"/>',
					type: 'post',
					data: {
						gcid: row.id
					},
					beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress({
							title:'提示',
							msg:'正在执行数据操作，请稍待...'
						});
					},
					error: function(XmlHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
					},
					success: function(result) {
						$.messager.progress('close');
						if(!result.success) {
							$.messager.alert('提示', result.data, 'info');
						}
						else {
							$('#PPHTSCLISTTABLE').datagrid('clearSelections').datagrid('reload');
							ZENG.msgbox.show(result.data, 4, 1500);
						}
					}
				});
			}
		});
		
	}
	
	function queryhtsc() {
		var queryParams_ = $('#PPHTSCLISTTABLE').datagrid('options').queryParams;
		queryParams_.cjsj = $('#PPTOOLBARHTSCLISTTABLE #cjsj').val();
		queryParams_.gcmc = $('#PPTOOLBARHTSCLISTTABLE #gcmc').textbox('getValue');
		
		$('#PPHTSCLISTTABLE').datagrid('options').queryParams = queryParams_;
		$('#PPHTSCLISTTABLE').datagrid('clearSelections').datagrid('reload');
	}
</script>