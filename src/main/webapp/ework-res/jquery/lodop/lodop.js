/****
 * 
 * @param flag  1:预览， 2：打印
 * @param frmid 表单ID
 * @returns
 */
function loadPrint(flag, frmid) {
	try {
		var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		if ((LODOP != null) && (typeof(LODOP.VERSION) != "undefined")) {
			createformpage(frmid);//打印方法
			if (flag == 1) {
				LODOP.PREVIEW();
			}
			else if (flag == 2) {
				LODOP.PRINT();
			}
		}
		else {//如果没安装提示用户需要安装插件
			$.messager.show({
				title:'警告',
				msg:'系统检测未安装打印控件！！！',
				timeout:5000,
				showType:'slide'
			});
			document.getElementById("PPPRINTDISPLAY").style.display="block";
			return;
		}
	}
	catch(err){
		return;
	}
}

//表单打印
function createformpage(frmid){
	LODOP = getLodop();
	LODOP.SET_PRINT_STYLE("FontSize", 16);
	LODOP.SET_PRINT_STYLE("Bold", 1);
	LODOP.ADD_PRINT_HTM(70, 5, "98%", "100%", document.getElementById(frmid).innerHTML);
}

//图片打印
function createimgpage(id){
	var width_ = 20;
	LODOP = getLodop();
	LODOP.SET_PRINT_STYLE("FontSize", 16);
	$("#"+id).each(function(){
		LODOP.ADD_PRINT_IMAGE(width_,20,'95%',345,"<img border='0' style='margin-left: 8%;' src='"+rootPath+"/extend/downLoad/doDownloadFile.do?fileid="+ this.id +"' width='90%' height='335'/><br/>");
		width_ = width_+345;
	});
}
