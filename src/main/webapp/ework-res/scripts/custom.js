function rowBtnSel(tblid, rowid) {
	$('#'+tblid).treegrid('select', rowid);
}

function selQhTree(dlgid, width, height, left, top, modal, flag) {
	$('#'+dlgid).dialog({
		title: '选择地区',
		modal: modal,
		closable: true,
		width: width,
		height: height,
		left: left,
		top: top
	}).dialog('open').dialog('refresh', scriptArgsPath+'xtgl/qhgl/qhtree.do?flag='+flag);
}

function validatebox(frmid, ysid) {
	$('#'+frmid+' #'+ysid).validatebox('enableValidation').validatebox('validate');
}

function datagridrowBtnSel(tblid, rowindex) {
	$('#'+tblid).datagrid('selectRow', rowindex);
}

/**
 * 创建对话框：在主框架页面中动态创建一个EasyUI对话框
 * @param dlgId
 * @return
 */
function crtViewDlg(dlgId) {
	$('#'+dlgId).remove();
	$('body').append('<div id="'+dlgId+'" class="easyui-dialog" style="overflow:auto;" closable="false" closed="true" modal="true"></div>');
}

function closedlg(dlgId) {
	$('#'+dlgId).dialog('close').remove();
}

function msgshow(title, msg) {
	$.messager.show({
		title: title,
		msg: msg,
		timeout:5000,
		showType:'slide'
	});
}


