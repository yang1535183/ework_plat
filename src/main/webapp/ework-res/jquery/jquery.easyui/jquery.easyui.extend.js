
/**
 * jquery.ajax方法的封装
 */
jQuery.extend({
	_ajax: function(options) {
		var defaults = {
			url: '#',
			data: '',
			callback: function() { //alert('回调函数的执行代码');
			}
		};
		
		var options = $.extend(defaults, options);
		$.ajax({
			url: options.url,
			type: 'POST',
			data: options.data,
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				if (XmlHttpRequest.status > 200) {
					$.messager.show({
						title: '异常',
						msg: '服务器繁忙或无法连接，请求通讯失败！',
						timeout: 5000,
						showType: 'show'
					});
				}
				if(XmlHttpRequest.status == 200) {
					$.messager.show({
						title: '异常',
						msg: XmlHttpRequest.responseText,
						timeout: 5000,
						showType: 'show'
					});
				}
			},
			success: function(result) {
				options.callback(result);
			}
		});
	}
});

/**
 * jquery.easyui.datagrid编辑行扩展my97日期控件
 * xsding@vip.qq.com	2012/3/12 AM10:58
 */
$.extend($.fn.datagrid.defaults.editors, {
	my97 : {
		init : function(container, options) {
			var input = $('<input class="Wdate" onclick="WdatePicker({dateFmt:\''+options.dateFmt+'\',readOnly:true});"  />').appendTo(container);
			return input;
		},
		getValue : function(target) {
			return $(target).val();
		},
		setValue : function(target, value) {
			$(target).val(value);
		},
		resize : function(target, width) {
			var input = $(target);
			if ($.boxModel == true) {
				input.width(width - (input.outerWidth() - input.width()));
			} else {
				input.width(width);
			}
		}
	}
});

/**
 * $('#id').validatebox('remove');
 * $('#id').validatebox('reduce');
 */
$.extend($.fn.validatebox.methods, {  
	remove: function(jq, newposition){  
		return jq.each(function(){  
			$(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
		});  
	},
	reduce: function(jq, newposition){  
		return jq.each(function(){  
		   var opt = $(this).data().validatebox.options;
		   $(this).addClass("validatebox-text").validatebox(opt);
		});  
	}	
}); 


/**
* 扩展easyui的validator插件rules
* xsding@vip.qq.com		2012/3/19 PM4:52
*/
$.extend($.fn.validatebox.defaults.rules, {
	minLength : { // 判断最小长度
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '最少输入 {0} 个字符。'
	},
	length : {
		validator : function(value, param) {
			var len = $.trim(value).length;
			return len >= param[0] && len <= param[1];
		},
		message : "输入内容长度必须介于{0}和{1}之间."
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
					.test(value);
		},
		message : '格式不正确,请使用下面格式:020-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|14|15|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确'
	},
	idcard : {// 验证身份证
		validator : function(value) {
			//return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
			return checkIDCard(value);
		},
		message : '身份证号码格式不正确'
	},
	intOrFloat : {// 验证整数或小数
		validator : function(value) {
			return /^\d+(\.\d+)?$/i.test(value);
		},
		message : '请输入数字，并确保格式正确'
	},
	currency : {// 验证货币(支持负数)
		validator : function(value) {
			return /^-?\d+(\.\d+)?$/i.test(value);
		},
		message : '货币格式不正确'
	},
	qq : {// 验证QQ,从10000开始
		validator : function(value) {
			return /^[1-9]\d{4,9}$/i.test(value);
		},
		message : 'QQ号码格式不正确'
	},
	integer : {// 验证整数
		validator : function(value) {
			return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入整数'
	},
	digital : {// 验证数字
		validator : function(value) {
			return /^[0-9]*$/i.test(value);
		},
		message : '请输入整数'
	},
	chinese : {// 验证中文
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/i.test(value);
		},
		message : '请输入中文'
	},
	english : {// 验证英语
		validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
		},
		message : '请输入英文'
	},
	adcode : {// 验证县区行政编码
		validator : function(value) {
			return /^[1-9]\d{5}$/i.test(value);
		},
		message : '县区行政编码不正确'
	},
	unnormal : {// 验证是否包含空格和非法字符
		validator : function(value) {
			return /.+/i.test(value);
		},
		message : '输入值不能为空和包含其他非法字符'
	},
	username : {// 验证用户名
		validator : function(value) {
			return /^[a-zA-Z][a-zA-Z0-9_]{1,15}$/i.test(value);
		},
		message : '用户名不合法（字母开头，允许2-16字节，允许字母数字下划线）'
	},
	faxno : {// 验证传真
		validator : function(value) {
			//return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
		return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
				.test(value);
	},
	message : '传真号码不正确'
	},
	zip : {// 验证邮政编码
		validator : function(value) {
			return /^[1-9]\d{5}$/i.test(value);
		},
		message : '邮政编码格式不正确'
	},
	ip : {// 验证IP地址
		validator : function(value) {
			return /d+.d+.d+.d+/i.test(value);
		},
		message : 'IP地址格式不正确'
	},
	name : {// 验证姓名，可以是中文或英文
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/i.test(value)
					| /^\w+[\w\s]+\w+$/i.test(value);
		},
		message : '请输入姓名'
	},
	carNo : {
		validator : function(value) {
			return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
		},
		message : '车牌号码无效（例：粤J12350）'
	},
	carenergin : {
		validator : function(value) {
			return /^[a-zA-Z0-9]{16}$/.test(value);
		},
		message : '发动机型号无效(例：FG6H012345654584)'
	},
	email : {
		validator : function(value) {
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
					.test(value);
		},
		message : '请输入有效的电子邮件账号(例：abc@126.com)'
	},
	msn : {
		validator : function(value) {
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
					.test(value);
		},
		message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
	},
	same : {
		validator : function(value, param) {
			if ($("#" + param[0]).val() != "" && value != "") {
				return $("#" + param[0]).val() == value;
			} else {
				return true;
			}
		},
		message : '两次输入的密码不一致！'
	},
	equals: {
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入的密码不一致！'
    }
});

function validForm(obj,type,flag){
	if(flag){
		if(type=='input'){
			if(!obj.validatebox('isValid'))
				flag = false;
		}else{
			if(!obj.combo('isValid'))
				flag = false;
		}
	}
	return flag;
}

/**
 * 验证身份证合法性
 * @param idcard
 * @return
 */
function checkIDCard(idcard){
	var Errors=new Array(
	"验证通过!",
	"身份证号码位数不对!",
	"身份证号码出生日期超出范围或含有非法字符!",
	"身份证号码校验错误!",
	"身份证地区非法!"
	);
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
	
	var idcard,Y,JYM;
	var S,M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	//地区检验
	if(area[parseInt(idcard.substr(0,2))]==null) 
		return false;//return Errors[4];
	//身份号码位数及格式检验
	switch(idcard.length)
	{
		case 15:
			if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 ))
			{
				//测试出生日期的合法性
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
			}
			else 
			{
				//测试出生日期的合法性
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;
			}
			if(ereg.test(idcard)) 
				return true;//return Errors[0];
			else
				return false;//return Errors[2];
		break;
		case 18:
			//18位身份号码检测
			//出生日期的合法性检查 
			//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 ))
			{
				//闰年出生日期的合法性正则表达式
				ereg=/^[1-9][0-9]{5}19|20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;
			} 
			else 
			{
				//平年出生日期的合法性正则表达式
				ereg=/^[1-9][0-9]{5}19|20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;
			}
			if(ereg.test(idcard))
			{
				//测试出生日期的合法性
				//计算校验位
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
				+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
				+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
				+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
				+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
				+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
				+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
				+ parseInt(idcard_array[7]) * 1 
				+ parseInt(idcard_array[8]) * 6
				+ parseInt(idcard_array[9]) * 3 ;
				Y = S % 11;
				M = "F";
				JYM = "10X98765432";
				M = JYM.substr(Y,1);//判断校验位
				if(M == idcard_array[17]) 
					return true;//return Errors[0]; //检测ID的校验位
				else 
					return false;//return Errors[3];
			}
			else 
				return false;//return Errors[2];
		break;
		default:
			return false;//return Errors[1];
		break;
	}
}

/**
 * 根据身份证号得出生日期
 */
function getBrithByCardId(val){
	var birthdayValue;
	if (15 == val.length){ //15位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7);
        if (parseInt(birthdayValue) < 10) {
            birthdayValue = '20' + birthdayValue;
        }
        else {
            birthdayValue = '19' + birthdayValue;
        }
        birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
			
	}
	if (18 == val.length) { //18位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11) + '-' + val.charAt(12) + val.charAt(13);
	}
	return birthdayValue;
}

$.extend($.fn.datagrid.methods, {
    autoMergeCells : function (jq, fields) {
        return jq.each(function () {
            var target = $(this);
            if (!fields) {
                fields = target.datagrid("getColumnFields");
            }
            var rows = target.datagrid("getRows");
            var i = 0,
            j = 0,
            temp = {};
            for (i; i < rows.length; i++) {
                var row = rows[i];
                j = 0;
                for (j; j < fields.length; j++) {
                    var field = fields[j];
                    var tf = temp[field];
                    if (!tf) {
                        tf = temp[field] = {};
                        tf[row[field]] = [i];
                    } else {
                        var tfv = tf[row[field]];
                        if (tfv) {
                            tfv.push(i);
                        } else {
                            tfv = tf[row[field]] = [i];
                        }
                    }
                }
            }
            $.each(temp, function (field, colunm) {
                $.each(colunm, function () {
                    var group = this;
                    
                    if (group.length > 1) {
                        var before,
                        after,
                        megerIndex = group[0];
                        for (var i = 0; i < group.length; i++) {
                            before = group[i];
                            after = group[i + 1];
                            if (after && (after - before) == 1) {
                                continue;
                            }
                            var rowspan = before - megerIndex + 1;
                            if (rowspan > 1) {
                                target.datagrid('mergeCells', {
                                    index : megerIndex,
                                    field : field,
                                    rowspan : rowspan
                                });
                            }
                            if (after && (after - before) != 1) {
                                megerIndex = after;
                            }
                        }
                    }
                });
            });
        });
    }
});

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			var ed = $(this).datagrid('getEditor', {index: param.index, field: param.field});
            if (ed){
                if ($(ed.target).hasClass('textbox-f')){
                    $(ed.target).textbox('textbox').focus();
                } else {
                    $(ed.target).focus();
                }
            }
            
            var tblid = param.tblid;
            var editcol = param.editcol;
            var editors = $('#'+tblid).datagrid('getEditors', param.index);
            if (editors.length != 0) {
            	var amountEditor = editors[0];
            	//失去焦点关闭单元格编辑
            	amountEditor.target.on('blur', function (e) {
            		$('#'+tblid).datagrid('acceptChanges');
            	});
            	
    			//end
    			//begin回车事件处理,直接换行到指定单元格!也可以设定一个热键来响应数量单元格的数据!
            	/*amountEditor.target.on(
    				'keydown',
    				function(e) {
    					if (e.keyCode == 39 ) {//右光标
    						$('#'+tblid).datagrid('endEdit', param.index);
    						for(var i=0; i<fields.length; i++){
    							if (fields[i] == param.field&&fields[fields.length-1]!=param.field){
    								$('#'+tblid).datagrid('selectRow', param.index).datagrid('editCell', {index:param.index, field:fields[i+1], tblid:tblid, editcol:editcol});
    							}
    						}
    						if(fields[fields.length-1] == param.field){
    							var rows = $('#'+tblid).datagrid("getRows");
    							//判断最大的行号是否已经是最后一行
    							if(rows.length >  param.index + 1){
    								//编辑下一行
    								$('#'+tblid).datagrid('selectRow', param.index + 1).datagrid('editCell', {index:param.index + 1,field:fields[editcol], tblid:tblid, editcol:editcol});
    								$('#'+tblid).datagrid('endEdit',param.index);
    								editIndex = param.index+1;
    							}else{
    								editIndex = 0;
    							}
    						}
    						//返回到第一个单元格
    						var row = $('#'+tblid).datagrid('getSelected');
    						var rowIndex = $('#'+tblid).datagrid('getRowIndex', row);
    						var rows = $('#'+tblid).datagrid("getRows");
    						
    						if(rows.length-1==param.index&&fields[fields.length-1] == param.field){
    							$('#'+tblid).datagrid('selectRow',0).datagrid('editCell', {index:0 ,field:fields[0], tblid:tblid, editcol:editcol});
    						}
    					}
    					if (e.keyCode == 37) {//左光标
    					    $('#'+tblid).datagrid('endEdit', param.index);
    						var row = $('#'+tblid).datagrid('getSelected');
    						var rowIndex = $('#'+tblid).datagrid('getRowIndex', row);
    						var rows = $('#'+tblid).datagrid("getRows");
    						
    						for(var i=editcol; i<fields.length; i++){
    							if (fields[i] == param.field&&fields[0]!=param.field){
    								$('#'+tblid).datagrid('editCell', {index:param.index ,field:fields[i-1], tblid: tblid});
    							}
    						}
    						if(fields[0] == param.field){	
    		                    var rows = $('#'+tblid).datagrid("getRows");
    							//判断最大的行号是否已经是最后一行
    							if(0 < param.index){
    								//编辑下一行
    								$('#'+tblid).datagrid('selectRow', param.index - 1).datagrid('editCell', {index:param.index - 1,field:fields[fields.length-1], tblid: tblid});
    								$('#'+tblid).datagrid('endEdit',param.index);
    								editIndex = param.index-1;
    							}else{
    								editIndex = rows.length-1;
    							}
    						}
    						//返回到最后一个单元格
    						if(0==param.index&&fields[0] == param.field){
    							$('#'+tblid).datagrid('selectRow',rows.length-1).datagrid('editCell', {index:rows.length-1,field:fields[fields.length-1], tblid: tblid});
    							$('#'+tblid).datagrid('endEdit',param.index);
    						}
    					}
    					if (e.keyCode == 38) {//上光标
    						var selected = $('#'+tblid).datagrid('getSelected');
    						var index = $('#'+tblid).datagrid('getRowIndex', selected);
    	                    if (selected&&index!=0) {
    	                        $('#'+tblid).datagrid('selectRow', index - 1).datagrid('editCell', {index:param.index - 1,field:param.field, tblid: tblid});
    	                        $('#'+tblid).datagrid('endEdit',param.index);
    	                        editIndex  = param.index-1;
    	                    } else {
    	                        var rows = $('#'+tblid).datagrid('getRows');
    	                        $('#'+tblid).datagrid('selectRow', rows.length - 1).datagrid('editCell', {index:rows.length - 1,field:param.field, tblid: tblid});
    	                        $('#'+tblid).datagrid('endEdit',param.index);
    	                        editIndex = rows.length - 1;
    	                    }		
    					}
    					if(e.keyCode == 40){//下光标
    						var selected = $('#'+tblid).datagrid('getSelected');
    						var index = $('#'+tblid).datagrid('getRowIndex', selected);
    						var rows = $('#'+tblid).datagrid('getRows');
    	                    if (selected&&index!=rows.length-1) {
    	                        $('#'+tblid).datagrid('selectRow', index + 1).datagrid('editCell', {index:param.index + 1,field:param.field, tblid: tblid});
    	                        $('#'+tblid).datagrid('endEdit',param.index);
    	                        editIndex  = param.index+1;  
    	                    } else {
    	                        $('#'+tblid).datagrid('selectRow', rows.length-1 - index).datagrid('editCell', {index:rows.length -1- index,field:param.field, tblid: tblid});
    	                        $('#'+tblid).datagrid('endEdit',param.index);
    	                        editIndex =0;
    	                    }	
    					}
    					if(e.keyCode==13){//回车键
    						$('#'+tblid).datagrid('acceptChanges');
    					}
    				});*/
            }
			for(var i=0; i<fields.length; i++){
				var col = $('#'+tblid).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	},
    enableCellEditing: function(jq){
        return jq.each(function(){
            var dg = $(this);
            var opts = dg.datagrid('options');
            opts.oldOnDblClickCell = opts.onDblClickCell; //双击
            opts.onDblClickCell = function(index, field){
            	if (opts.editIndex != undefined){
                    if (dg.datagrid('validateRow', opts.editIndex)){
                        dg.datagrid('endEdit', opts.editIndex);
                        opts.editIndex = undefined;
                    } else {
                        return;
                    }
                }
                dg.datagrid('selectRow', index).datagrid('editCell', {
                    index: index,
                    field: field
                });
                opts.editIndex = index;
                opts.oldOnDblClickCell.call(this, index, field);
            };
            opts.oldOnClickCell = opts.onClickCell; //单击
            opts.onClickCell = function(index, field){
                if (opts.editIndex != undefined){
                    if (dg.datagrid('validateRow', opts.editIndex)){
                        dg.datagrid('endEdit', opts.editIndex);
                        opts.editIndex = undefined;
                    } else {
                        return;
                    }
                }
                /*dg.datagrid('selectRow', index).datagrid('editCell', {
                    index: index,
                    field: field
                });
                opts.editIndex = index;
                opts.oldOnClickCell.call(this, index, field);*/
            }
        });
    }
});