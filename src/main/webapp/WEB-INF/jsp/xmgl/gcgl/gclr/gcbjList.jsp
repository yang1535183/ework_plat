<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 房建 -->
<table id="PPTABLE_9C2F2CB988B144769E"></table>
<div id="PPTOOLBAR9C2F2CB988B144769E">
	<div data-options="region:'north'">
		<table>
			<tr>
				<td>日期：</td>
				<td>
					<input id="cjsj" name="cjsj"  type="text" 
					class="Wdate round" readonly="readonly"
						value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy" />"
						onclick="WdatePicker({dateFmt:'yyyy'})" style="width: 90px; height:24px;" />
				</td>
				<td>&nbsp;&nbsp;</td>
				<td>项目名称：</td>
				<td>
					<input id="gcmc" class="easyui-textbox" style="width: 190px; height:28px;">
				</td>
					<td>&nbsp;&nbsp;</td>
				<td>项目状态：</td>
				<td>
					<select id="sjzt" class="easyui-combobox" style="width: 130px; height:28px;" editable="false">
						<option value="">--请选择--</option>   
						<option value="0">未上报</option>   
    					<option value="1">待审查</option>
    					<option value="2">待审批</option>   
    					<option value="3">审查退回</option>
    					<option value="5">审批退回</option>   
    					<option value="4">注册码</option>   
					</select>
				</td>
				<td>&nbsp;&nbsp;</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryXmbj();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addinfoXmbj('1')">录入</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="addinfoXmbj('2');">修改</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="delgcbjXmbj('PPTABLE_9C2F2CB988B144769E');">删除</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-flag_red'" onclick="gcbjReportGcbj('PPTABLE_9C2F2CB988B144769E')">上报</a>
				</td>
			</tr>
		</table>
	</div>
</div>

<div id="ppgcbjlistlodialoglinkbuttonone" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveGcxmModel('plpgcjlistdialog', 'PPTABLE_9C2F2CB988B144769E');">保存</a>
</div>

<div id="ppgcbjlistlodialoglinkbuttonone2" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
		id='printGcbjlist' onclick="loadPrint(1,'ljlprintaddinfoaqjdbb'); ">打印预览</a>
</div>

<div id="ppgcbjlistlodialoglinkbutton" style="text-align: center;">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveGcxmModel('plpgcjlistdialog', 'PPTABLE_9C2F2CB988B144769E');">保存</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-print'" 
		id='printGcbjlist' onclick="loadPrint(1,'ljlprintaddinfoaqjdbb'); ">打印预览</a>
</div>

<script>
$(function(){
	$('#PPTABLE_9C2F2CB988B144769E').datagrid({
		fit: true,
		striped: true,
		remoteSort: false,
		singleSelect: true,
		url: '<c:url value="/xmgl/gcgl/gclr/jsonPageList.do"/>',
		singleSelect: false,
		queryParams: {
			xmlx: 'GCFJ',
			dmco: 'QYSB'
		},
		columns: [[
			{field:'ck',checkbox:true},
			{field:'sjzt',title:'状态',width:80,halign:'center', formatter:function(value, row, index) {
				if (value == '注册号') {
					return row.zcbm;
				}else {
					return sjztData(value,row.zcbm);
				}
			}},
			{field:'gcmc',title:'工程名称',width:320,halign:'center', formatter:function(value, row, index) {
				return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'" onclick="addinfoXmbj(2,\''+row.id+'\',\''+row.gcmc+'\',\''+row.sjzt+'\');">'+value+'</a>';
			}},
			{field:'gcdd',title:'工程地点',width:180, formatter:function(value, row, index) {
				var str = '';
				if(row.qu)  {str = row.qu+'区';}
				if(row.lu)  {str = str + row.lu+'路';}
				if(row.hao) {str = str + row.hao+'号';}
				return str;
			}},
			{field:'kgsj',title:'开工日期',width:90,halign:'center',align:'right'},
			{field:'jgsj',title:'竣工日期',width:90,halign:'center',align:'right'},
			/* {field:'tzlb',title:'投资类别',width:80,formatter:function(value, row, index) {
				return tzlbData(value);
			}},
			{field:'yszj',title:'预算造价',width:80,halign:'center',align:'right'},
			{field:'jzmj',title:'建筑面积',width:80,halign:'center',align:'right'},
			{field:'jclx',title:'基础类型',width:100,halign:'center',align:'right'},
			{field:'jsdwmc',title:'建设单位',width:80,halign:'center',align:'right'},
			{field:'kcdwmc',title:'勘察单位',width:80,halign:'center',align:'right'},
			{field:'sjdwmc',title:'设计单位',width:80,halign:'center',align:'right'},
			{field:'jldwmc',title:'监理单位',width:80,halign:'center',align:'right'},
			{field:'sgdwmc',title:'施工单位',width:80,halign:'center',align:'right'},

			*/
			{field:'FJ',title:'附件',width:80,align:'center',formatter:function(value,row,index) {
				return '<a target="_blank" onclick="clearOKgrid(\'#PPTABLE_9C2F2CB988B144769E\', \''+row.id+'\',\''+row.gcmc+'\')" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'icon-print\'">查看附件</a>';
			}}
		]],
		toolbar:'#PPTOOLBAR9C2F2CB988B144769E',
       	pageSize: 20,
		pagination: true,
		rownumbers: true,
		rowStyler: function(index,row){
			if(row.sjzt == '4') {
				return 'background-color:#01B468;color:#fff;';
			}
			else if (row.sjzt == '1' || row.sjzt == '2'){
				return 'background-color:#66CCCC;color:#fff;';
			}
			else if (row.sjzt == '3'||row.sjzt == '5'){
				return 'background-color:red;color:#fff;';
			}
		}
    });
})

//查询
	function queryXmbj() {
		var queryParams_ = $('#PPTABLE_9C2F2CB988B144769E').datagrid('options').queryParams;
		queryParams_.cjsj = $('#PPTOOLBAR9C2F2CB988B144769E #cjsj').val();
		queryParams_.gcmc = $('#PPTOOLBAR9C2F2CB988B144769E #gcmc').textbox('getValue');
		queryParams_.sjzt = $('#PPTOOLBAR9C2F2CB988B144769E #sjzt').textbox('getValue');
		$('#PPTABLE_9C2F2CB988B144769E').datagrid('options').queryParams = queryParams_;
		$('#PPTABLE_9C2F2CB988B144769E').datagrid('clearSelections').datagrid('reload');
	}
	
	//录入和修改工程信息
	function addinfoXmbj(flag, _id, _gcmc,_sjzt) {
		var title, _buttons;
		var url_ ='';
		
		if (flag == 1) {
			_buttons = "ppgcbjlistlodialoglinkbuttonone";
			title = "录入：房建 工程质量安全监督报表";
			url_ = '<c:url value="/xmgl/gcgl/infoForm.do"/>?step=gclr&xmlx=GCFJ';
		}
		else {
			var id;
			var gcmc;
			if(_id) {id = _id; gcmc = _gcmc;
				$('#PPTABLE_9C2F2CB988B144769E').datagrid('clearSelections');
				if (_sjzt==1 || _sjzt==2 || _sjzt==4) {
					_buttons = "ppgcbjlistlodialoglinkbuttonone2";
			}else {
				_buttons = "ppgcbjlistlodialoglinkbutton";
			}
			}
			else {
				var row = $('#PPTABLE_9C2F2CB988B144769E').datagrid('getSelected');
				if (!row) {
					$.messager.alert('警告', '请选择要编辑的数据！', 'warning');
					return;
				}
				if (row.sjzt==1 || row.sjzt==2 || row.sjzt==4) {
					_buttons = "ppgcbjlistlodialoglinkbuttonone2";
				}else {
					_buttons = "ppgcbjlistlodialoglinkbutton";
				}
				id = row.id;
				gcmc = row.gcmc;
			}
			
			url_ = '<c:url value="/xmgl/gcgl/infoForm.do"/>?step=gclr&id='+id;
			title = "编辑："+gcmc;
		}
		
		$('#gcxmlcjllist').remove();
		$('#ppzxshlisttable').remove();
		$('#ppgxaddandmmodelform').remove();
		$('#ljlprintaddinfoaqjdbb').remove();
		$('#fileuploadanddownloaddiv').remove();
		$('#plpgcjlistdialog').dialog('close').remove();
		$('body').append('<div id="plpgcjlistdialog" class="easyui-dialog" style="overflow-x:hidden;padding:3px;"></div>');
		$('#plpgcjlistdialog').dialog({
			title: title,
			modal: true,
			closable: true,
			draggable: true,
			width: 1070,
			height:'90%',
			top:'10%',
			buttons:'#'+_buttons
		}).dialog('open').dialog('refresh', url_);
	}
</script>