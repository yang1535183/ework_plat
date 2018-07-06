function zxscData(row) {
	 if(row.sclm=="xj" && $.trim(row.scsj) == '1') {
			return "一星";
		}
		else if(row.sclm=="xj" && $.trim(row.scsj) == '2') {
			return "二星";
		}
		else if(row.sclm=="xj" && $.trim(row.scsj) == '3') {
			return "三星";
		}
		else if(row.sclm=="tzlb" && $.trim(row.scsj) == '1') {
			return "外资";
		}
		else if(row.sclm=="tzlb" && $.trim(row.scsj) == '2') {
			return "合资";
		}
		else if(row.sclm=="tzlb" && $.trim(row.scsj) == '3') {
			return "国有";
		}
		else if(row.sclm=="tzlb" && $.trim(row.scsj) == '4') {
			return "集体";
		}
		else if(row.sclm=="tzlb" && $.trim(row.scsj) == '5') {
			return "民营";
		}
		else if(row.sclm=="gclb" && $.trim(row.scsj) == '1') {
			return "住宅";
		}
		else if(row.sclm=="gclb" && $.trim(row.scsj) == '2') {
			return "公建";
		}
		else if(row.sclm=="gclb" && $.trim(row.scsj) == '3') {
			return "厂房";
		}
		else if(row.sclm=="gclb" && $.trim(row.scsj) == '4') {
			return "其他";
		}
		else if(row.sclm=="jnbz" && $.trim(row.scsj) == '1') {
			return "50%";
		}
		else if(row.sclm=="jnbz" && $.trim(row.scsj) == '2') {
			return "65%";
		}
		else if(row.sclm=="jnbz" && $.trim(row.scsj) == '3') {
			return "其他";
		}
		else if(row.sclm=="wxxjd" && $.trim(row.scsj) == '0') {
			return "否";
		}
		else if(row.sclm=="sjc" && $.trim(row.scsj) == '1') {
			return "深基坑";
		}
		else if(row.sclm=="gdmb" && $.trim(row.scsj) == '1') {
			return "高大模板";
		}
		else if(row.sclm=="gkzy" && $.trim(row.scsj) == '1') {
			return "高空作业";
		}
		else if(row.sclm=="pj" && $.trim(row.scsj) == '1') {
			return "爬架";
		}
		else if(row.sclm=="rhdt" && $.trim(row.scsj) == '1') {
			return "人货电梯";
		}
		else if(row.sclm=="td" && $.trim(row.scsj) == '1') {
			return "塔吊";
		}
		else if(row.sclm=="wxqt" && $.trim(row.scsj) == '1') {
			return "其他";
		}
		else if(row.sclm=="ljgyx" && $.trim(row.scsj) == '1') {
			return "临近高压线";
		}
		else if(row.sclm=="ljjmq" && $.trim(row.scsj) == '1') {
			return "临近居民区";
		}
		else if(row.sclm=="zbqt" && $.trim(row.scsj) == '1') {
			return "其他";
		}
		else if(row.sclm=="ccdwzzzs" && $.trim(row.scsj) == '1') {
			return "是";
		}
		else if(row.sclm=="fwccwtht" && $.trim(row.scsj) == '1') {
			return "是";
		}
		else if(row.sclm=="bzjjlpz" && $.trim(row.scsj) == '1') {
			return "是";
		}
		else if(row.sclm=="wxxjd" && $.trim(row.scsj) == '1') {
			return "是";
		}
		else if(row.sclm=="wxxjd" && $.trim(row.scsj) == '0') {
			return "否";
		}
		else if(row.sclm=="jx" && $.trim(row.scsj) == '1') {
			return "机械";
		}else if(row.sclm=="bp" && $.trim(row.scsj) == '1') {
			return "爆破";
		}
		else if($.trim(row.scsj) == ',,') {
			return "";
		}else {return row.scsj;}
}

//工程类型
function gclxcode(value) {
	if('GCFJ' == value) {
		return '房建工程';
	}
	else if('GCZS' == value) {
		return '装饰工程';
	}
	else if('GCSZ' == value) {
		return '市政工程';
	}
	else if('GCCHC' == value) {
		return '拆除工程';
	}
}

//角色
function roleType(value) {
	if ('SYSTEM' == value.toUpperCase()) {
		return '内置角色';
	}
	else if ('CUSTEM' == value.toUpperCase()) {
		return '定制角色';
	}
	else {
		return value;
	}
}

//角色状态
function roleEndow(value) {
	if ('1' == value.toUpperCase()) {
		return '可授予';
	}
	else if ('2' == value.toUpperCase()) {
		return '不可授予';
	}
	else {
		return '未知状态';
	}
}

function sjztData(_v,zcbm) {
	if(_v == '0') {
		return '未上报';
	}
	else if(_v == '1') {
		return '待审查';
	}
	else if(_v == '2') {
		return '待审批';
	}
	else if(_v == '3') {
		return '审查退回';
	}
	else if(_v == '4') {
		return zcbm;
	}
	else if(_v == '5') {
		return '审批退回';
	}
	else {
		return "";
	}
}

/**
 * 投资类别
 * **/
function tzlbData(value) {
	if(value == '1') {
		return '外资'
	}
	else if(value == '2') {
		return '合资'
	}
	else if(value == '3') {
		return '国有'
	}
	else if(value == '4') {
		return '集体'
	}
	else if(value == '5') {
		return '民营'
	}
	else {
		return '未定义'
	}
}

/***性别**/
var sexData = [{
	"id": '',
	"text": '--请选择--'
},{
	"id": '0',
	"text": '男'
}, {
	"id": '1',
	"text": '女'
}, {
	"id": '-1',
	"text": '保密'
}];

var statusData = [{
	"id": '1',
	"text": '启用'
}, {
	"id": '0',
	"text": '禁用',
}];
