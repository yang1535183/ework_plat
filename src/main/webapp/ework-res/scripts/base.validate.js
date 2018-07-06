//对电子邮件的验证
function checkEmail(email) {
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!myreg.test(email)) {
		document.getElementById('div_email').innerHTML="无效E_mail!";
		return false;
	} else {
		return true;
	}
}

//手机号码验证
function checkPhone(phone) {
	var myreg =/^(13|14|15|18)\d{9}$/i; //	/(^0{0,1}1[3|5|8][0-9]{9}$)/;  

	if (!myreg.test(phone)) {
		document.getElementById('div_lxfs').innerHTML="无效手机号码";
		return false;
	} else {
		return true;
	}
}

//身份证验证
function isIdCardNo(num){  
	num = num.toUpperCase();
	//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。  
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
		document.getElementById('div_sfzh').innerHTML="无效证件!";
		return false;
	}
	//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	//下面分别分析出生日期和校验位
	var len, re;
	len = num.length;
	if (len == 15){
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = num.match(re);
		//检查生日日期是否正确
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
	if (!bGoodDay){
		document.getElementById('div_sfzh').innerHTML="无效证件!";
		return false;
	}
	else {
		//将15位身份证转成18位
		//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
        var nTemp = 0, i;  
        num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
        for(i = 0; i < 17; i ++) {
        	nTemp += num.substr(i, 1) * arrInt[i];
        }
	        num += arrCh[nTemp % 11];  
	        return num;  
		}  
	}
	if (len == 18) {
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = num.match(re);
		//检查生日日期是否正确
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay){
			alert(dtmBirth.getYear());
			alert(arrSplit[2]);
			document.getElementById('div_sfzh').innerHTML="无效证件!";
			return false;
		}
		else {
			//检验18位身份证的校验码是否正确。
			//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
			var nTemp = 0, i;
			for(i = 0; i < 17; i ++){
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum != num.substr(17, 1)) {
				document.getElementById('div_sfzh').innerHTML="无效证件!";
				return false;
			}
			return num;
		}
	}
	return false;
}
