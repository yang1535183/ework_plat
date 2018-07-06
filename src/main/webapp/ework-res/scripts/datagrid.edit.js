function saveOnAfterEdit(changes, tblid, data) {
	var cges = json2str(changes).replace('}','').replace('{','').replace(':',',');
	var column = cges.split(",")[0].replace(/'/g,'');
	var val = cges.split(",")[1].replace(/'/g,'');
	
	$.ajax({
		url: scriptArgsPath + 'xmgl/datagrid/saveOnAfterEdit.do?column='+column+'&val='+val,
		type: 'POST',
		data: data,
		error: function(XmlHttpRequest, textStatus, errorThrown) {
			ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
		},
		success: function(result) {
			if(!result.success) {
				$.messager.alert('错误', result.data, 'error');
			}
			else {
			//	$('#'+tblid).datagrid('clearSelections').datagrid('reload');
				show_('温馨提示：', result.data, 'show');
			}
		}
	});
}

//查看附件跳转清除选中datagrid复选框
function clearOKgrid(_grid, id, gcmc) {
	console.log(_grid);
	$(_grid).datagrid('clearSelections');
	window.open(scriptArgsPath + '/xmgl/gcgl/form/fjpage.do?id='+id+'&gcmc='+gcmc);
}

//删除工程信息(刘)
function delgcbjXmbj(_grid) {
	var rows = $('#'+_grid).datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择要删除的数据！！！', 'warning');
		return;
	}
	var ids = [];
	
	var errornum=0;//记录错误数据个数
	var msg = "";//错误数据提示
	for(var i=0; i<rows.length; i++){
		if(rows[i].sjzt == '0') {
			ids.push(rows[i].id);
		}
		else {errornum ++;}
		if(errornum != 0) {msg = "当前已过滤"+errornum+"条无法删除。。。";}
	}
	if((rows.length - errornum)==0) {
		$.messager.alert('提示', "当前所选请求失败，无法删除更改状态后的数据！！！", 'info');
		return;
	}
	else {msg = msg + "可删除"+(rows.length - errornum)+"条数，";}
	
	$.messager.confirm('消息', msg+'确认删除所数据吗？', function(r){
		if (r){
			$.ajax({
				url: scriptArgsPath + '/xmgl/gcgl/delGong.do',
				type: 'post',
				data: 'id='+ids,
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
						$('#'+_grid).datagrid('clearSelections').datagrid('reload');
						ZENG.msgbox.show(result.data, 4, 1500);
					}
				}
			});
		}
	});
}

/**企业上报**/
function gcbjReportGcbj(_grid) {
	var rows = $('#'+_grid).datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择要上报数据！！！', 'warning');
		return;
	}
	var ids = [];
	var msg = "";//错误数据提示
	var errornum = 0;//记录错误数据
	for(var i=0; i<rows.length; i++) {
		if(rows[i].sjzt == '0' || 
				rows[i].sjzt == '3' || rows[i].sjzt == '5') {
			ids.push(rows[i].id);
		}
		else {errornum ++;}
		if(errornum != 0) {msg = "当前已过滤"+errornum+"条无法上报数据。。。";}
	}
	if((rows.length - errornum)==0) {
		$.messager.alert('提示', "当前选中，无可上报数据！！！", 'info');
		return;
	}
	else {msg = msg + "可上报"+(rows.length - errornum)+"条数，";}
	
	$.messager.confirm('消息', msg+'确认上报吗？上报后数据无法修改！！！', function(r){
		if (r){
			$.ajax({
				url: scriptArgsPath + '/xmgl/gcgl/reportGcbj.do',
				type: 'post',
				data: 'id='+ids+'&flag=1',
				beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
					$.messager.progress({
						title:'提示',
						msg:'排队等候中，请稍待...'
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
						$('#'+_grid).datagrid('clearSelections').datagrid('reload');
						ZENG.msgbox.show(result.data, 4, 1500);
					}
				}
			});
		}
	});
}

/**选择动作**/
function saveOnchange(_e, _dmcode, _grid) {
	var id = _e.id;
	var value = _e.value;
	if (value == "" || value==null) {
		value="";
	}
	
	var tgid = id+"tg";
	var btgid = id+"btg";
	
	if(value == 0) {//不通过
		$("#"+btgid).css({"color":"red"});
		$("#"+tgid).css({"color":""});
	}else{//通过
		$("#"+btgid).css({"color":""});
		$("#"+tgid).css({"color":""});
	}
	
	updateRadio(id, value, _dmcode, _grid);
}

/**修改radio的值**/
function updateRadio(id, vl, tname, _grid) {
	$.ajax({
		type: 'post',
		url: scriptArgsPath + '/xmgl/gcgl/gcsc/zxscSave.do?id='+id+'&vl='+vl+'&tname='+tname,
		error: function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.progress('close');
			ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
		},
		success: function(result) {
			if(!result.success) {
				ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
			}
			else {
				$('#'+_grid).datagrid('clearSelections').datagrid('reload');
			}
		}
	});
	
}

/**审查**/
function queryGcck(_grid, _btsid, _id, _gcmc) {
	var id, gcmc;
	if(_id) {
		id = _id;
		gcmc = _gcmc;
	}
	else {
		var row=$('#'+_grid).datagrid('getSelected');
		if (!row) {
			$.messager.alert('警告', '请选择工程项目！！！', 'warning');
			return;
		}
		id = row.id;
		gcmc = row.gcmc;
	}
	
	$('#gcxmlcjllist').remove();
	$('#ppzxshlisttable').remove();
	$('#ppgxaddandmmodelform').remove();
	$('#ljlprintaddinfoaqjdbb').remove();
	$('#fileuploadanddownloaddiv').remove();
	$('#ywGCDetail').dialog('close').remove();
	$('body').append('<div id="ywGCDetail" class="easyui-dialog" style="overflow-x:hidden;padding:3px;"></div>');
	$('#ywGCDetail').dialog({
		title: '工程详情:'+gcmc,
		modal: true,
		closable: true,
		draggable: true,
		width: 1070,
		height:'90%',
		top:'10%',
		buttons:'#'+_btsid
	}).dialog('open').dialog('refresh', scriptArgsPath + '/xmgl/gcgl/infoForm.do?step=gcsc&id='+id);
}

/**审查提交**/
function zxscSaveSctj(_grid, _dlgid, dmco) {
	/**获取datagird数据，是否存在sjzt为0的，给出不同提示***/
	var row = $('#'+_grid).datagrid('getSelected');
	if (row.sjzt==2 || row.sjzt==3 || row.sjzt==4) {
		ZENG.msgbox.show('系统拒绝请求:不可操作该状态下的工程!',1);
		return;
	}
	$.ajax({
		url: scriptArgsPath + '/xmgl/gcgl/zxscSaveSctj.do',
		type: 'POST',
		data: 'id='+row.id+'&dmco='+dmco,
		beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.progress({
				title: '提示',
				msg: '正在执行数据操作，请稍待...'
			});
		},
		error: function(XmlHttpRequest, textStatus, errorThrown) {
			ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
		    $.messager.progress('close');
		},
		success: function(result) {
		    $.messager.progress('close');
			if(!result.success) {
				show_err_msg(result.data);
			}
			else {
				$('#'+_dlgid).dialog('close');
				$('#'+_grid).datagrid('clearSelections').datagrid('reload');
				ZENG.msgbox.show(result.data, 4, 1500);
			}
		}
	});
}

//删除
function delChcGcxm(_grid) {
	var rows = $('#'+_grid).datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择要删除的数据！！！', 'warning');
		return;
	}
	var ids = [];
	
	var errornum=0;//记录错误数据个数
	var msg = "";//错误数据提示
	for(var i=0; i<rows.length; i++){
		if(rows[i].sjzt == '0') {
			ids.push(rows[i].id);
		}
		else {errornum ++;}
		if(errornum != 0) {msg = "当前已过滤"+errornum+"条无法删除。。。";}
	}
	if((rows.length - errornum)==0) {
		$.messager.alert('提示', "当前所选请求失败，无法删除更改状态后的数据！！！", 'info');
		return;
	}
	else {msg = msg + "可删除"+(rows.length - errornum)+"条数，";}
	
	$.messager.confirm('消息', msg + '确认删除所数据吗？', function(r) {
		if (r) {
			$.ajax({
				url : scriptArgsPath + '/xmgl/ccgl/delCc.do',
				type : 'post',
				data : 'id=' + ids,
				beforeSend : function(XmlHttpRequest, textStatus,
						errorThrown) {
					$.messager.progress({
						title : '提示',
						msg : '正在执行数据操作，请稍待...'
					});
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
				},
				success : function(result) {
					$.messager.progress('close');
					if (!result.success) {
						$.messager.alert('提示', result.data, 'info');
					} else {
						$('#'+_grid).datagrid('clearSelections').datagrid('reload');
						ZENG.msgbox.show(result.data, 4, 1500);
					}
				}
			});
		}
	});
}

//拆除工程上报
function gcbjReportChcbj(_grid) {
	var rows = $('#'+_grid).datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择要上报数据！！！', 'warning');
		return;
	}
	var ids = [];
	var msg = "";//错误数据提示
	var errornum = 0;//记录错误数据
	for(var i=0; i<rows.length; i++) {
		if(rows[i].sjzt == '0' || 
				rows[i].sjzt == '3' || rows[i].sjzt == '5') {
			ids.push(rows[i].id);
		}
		else {errornum ++;}
		if(errornum != 0) {msg = "当前已过滤"+errornum+"条无法上报数据。。。";}
	}
	if((rows.length - errornum)==0) {
		$.messager.alert('提示', "当前选中，无可上报数据！！！", 'info');
		return;
	}
	else {msg = msg + "可上报"+(rows.length - errornum)+"条数，";}

	$.messager.confirm('消息', msg + '确认上报吗？上报后数据无法修改！！！', function(r) {
		if (r) {
			$.ajax({
				url : scriptArgsPath+'/xmgl/ccgl/ccgcSb.do',
				type : 'post',
				data : 'id=' + ids,
				beforeSend : function(XmlHttpRequest, textStatus,
						errorThrown) {
					$.messager.progress({
						title : '提示',
						msg : '正在执行数据操作，请稍待...'
					});
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
				},
				success : function(result) {
					$.messager.progress('close');
					if (!result.success) {
						$.messager.alert('提示', result.data, 'info');
					} else {
						$('#'+_grid).datagrid('clearSelections').datagrid('reload');
						ZENG.msgbox.show(result.data, 4, 1500);
					}
				}
			});
		}
	});
}

//动作
function passSP(grid, dilg, dmco, flag) {
	var rows = $('#'+grid).datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('警告', '请选择要上报数据！！！', 'warning');
		return;
	}
	var ids = [];
	var msg = "";//错误数据提示
	var errornum = 0;//记录错误数据
	for(var i=0; i<rows.length; i++) {
		if(rows[i].sjzt == '2') {
			ids.push(rows[i].id);
		}
		else {errornum ++;}
		if(errornum != 0) {msg = "当前已过滤"+errornum+"条重复审批数据。";}
	}
	if((rows.length - errornum)==0) {
		$.messager.alert('提示', "当前选中，无可审批数据！！！", 'info');
		return;
	}
	else {msg = msg + "可审批"+(rows.length - errornum)+"条数，";}
	
	if('SPLDBTG' == flag) {
		$.messager.prompt('温馨提示：', msg+'请输入意见', function(r){
			if (r){
				reportGcxm(grid, dilg, dmco, ids, flag, r);
			}
		});
	}
	else {
		$.messager.confirm('确认', msg+',请您确定操作！！！',function(r){    
		    if (r){    
		    	reportGcxm(grid, dilg, dmco, ids, flag);
		    }    
		}); 
	}
}

function reportGcxm(grid, dilg, dmco, id, flag, memo) {
	console.log(id);
	$.ajax({
		type: 'post',
		url: scriptArgsPath + '/xmgl/gcgl/reportGcxm.do',
		data: 'dmco='+dmco+'&id='+id+'&flag='+flag+'&memo='+memo,
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
				$('#'+grid).datagrid('clearSelections').datagrid('reload');
				ZENG.msgbox.show(result.data, 4, 1500);
			}
			$('#'+dilg).dialog('close').remove();
		}
	});
}

/**
 * 右下角弹出框
 * ***/
function show_(tit_, msg_, showType_) {
	$.messager.show({
		title: tit_,
		msg: msg_,
		timeout: 20000,
		showType: showType_
	});
}