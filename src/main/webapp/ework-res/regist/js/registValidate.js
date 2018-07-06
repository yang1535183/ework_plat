	/**手机判断*/
	function cheakPhoneOne(index) {
		var phone = $('#cellphone').val();
		var arge= {cellphone: phone};
		return cheakRegist(arge, 'phoneMsg', index);
	}
	
	/**
	 * 进行首页面的手机号验证判断
	 */
	function checkPhone() {
		var phone = document.getElementById("cellphone").value;
		var myreg = /^(13|14|15|18)\d{9}$/i; //	/(^0{0,1}1[3|5|8][0-9]{9}$)/;  
		var msg;
		if (phone == "" || phone.trim() == "") {
			msg = " 手机号不能为空! ";
			$('#phoneMsg').text(msg);
			$('#phoneMsg').css('color', 'red');
			return false;
		} else if (!myreg.test(phone)) {
			msg = " 无效手机号码!";
			$('#phoneMsg').text(msg)
			$('#phoneMsg').css('color', 'red');
			return false;
		}else if(myreg.test(phone)) {
			return cheakPhoneOne();
		}else {
			$('#phoneMsg').text('√')
			$('#phoneMsg').css('color', 'green');
			return true;
		}
	}
	
	function checkAgree() {
		var agree = document.getElementById("agree").checked;
		if (agree) {
			return true;
		}else {
			$('#agreeMsg').text("请先同意工程协议。");
			return false;
		}
	}
	
	$(function() {
		identify.addEventListener("keyup", function(event) {
			checkajaxidentify();
		});
		
		/* $("#cellphone").keyup(function() {
			checkPhone()
		}); */
		
		var aStr = [ "强度弱", "强度中", "强度高" ];
		function checkStrong(val) {
			var modes = 0;
			if (/\d/.test(val)) modes++; //数字
			if (/[a-z]/.test(val)) modes++; //小写
			if (/[A-Z]/.test(val)) modes++; //大写  
			if (/\W/.test(val)) modes++; //特殊字符
			if (val.length > 14) return 3;
			return modes;
		};
		
		$("#loginpass").keyup(
			function() {
				var val = $(this).val();
				var liu=document.getElementById("loginpass");
				if(null == val || '' == val){
					$("#passMsg").text('密码不能为空');
				}else if (liu.value.length<6) {
					msg = " 长度不足六位!";
					$('#passMsg').text(msg)
					return false;
				}
				else {
					$("#passMsg").text('');
					}
				var num = checkStrong(val);
				switch (num) {
				case 0: break;
				case 1:
					$("#tips span").css('background', 'gray')
							.text('').eq(num - 1).css('background','red').text(aStr[num - 1]);
					break;
				case 2:
					$("#tips span").css('background', 'green').text('')
							.eq(num - 1).css('background', 'red').text(aStr[num - 1]);
					break;
				case 3:
					$("#tips span").css('background', 'green').text('')
							.eq(num - 1).css('background', 'red').text(aStr[num - 1]);
					break;
				default: break; }
			})
		})

		/**
		 * 点击更换验证码--开始
		 */
		function RefreshImage() {
			var el = document.getElementById("im");
			el.src = el.src + '?';
		}
		im.onclick = RefreshImage
		huan.onclick = RefreshImage

		/**
		 * 进行首页面的验证码验证判断
		 */
		function checkIdentify() {
			var val = document.getElementById("identify").value;
			var msg;
			if (val == "" || val.trim() == "") {
				msg = " 验证码不能为空! ";
				$('#identifyMsg').text(msg);
				return false;
			} else {
				return checkajaxidentify();
			}
		}
		
		/**
		 * 进行首页面的校验码验证判断
		 */
		function checkCode(val) {
			var msg;
			if (val == "" || val.trim() == "") {
				msg = " 校验码不能为空! ";
				$('#codeMsg').text(msg);
				return false;
			} else if (!null) { // 此处 判断 校验码 是否正确
				msg = " 校验码输入有误! ";
				$('#codeMsg').text(msg);
				return false
			} else {
				msg = '√';
				$('#codeMsg').css('color', 'green');
				$('#codeMsg').text(msg)
				return true;
			}
		}

		/**
		 * 进行页面二的密码验证判断
		 */
		function checkPwd(pass, pass2) {
		/*	var liu=document.getElementById("loginpass");*/
			var msg;
			var myreg = /^.{6,}$/;
			 if (pass == "" || pass.trim() == "") {
				msg = " 密码不能为空! ";
				$('#passMsg').text(msg);
				return false;
			} else if (!myreg.test(pass)) {
				msg = " 格式错误!";
				$('#passMsg').text(msg)
				return false;
			} else if (pass2 == "" || pass2 != pass) {
				msg = " 两次密码输入不一致!";
				$('#pass2Msg').text(msg)
				return false;
			} else if (pass2 != "" && pass2 == pass) {
				var checkNum = /[0-9]/; 
				var flagNum = checkNum.test(pass2);//true,说明有数字
				var checkVar = /[a-z]/i; 
				var flagVar = checkVar.test(pass2);//true,说明有英文字母
				if (flagNum && flagVar) {
					msg = '√';
					$('#passMsg').css('color', 'green');
					$('#passMsg').text(msg)
					$('#pass2Msg').css('color', 'green');
					$('#pass2Msg').text(msg)
					return true;
				}else {
					msg = " 密码需由数字和字母组合";
					$('#pass2Msg').text(msg)
					return false;
				}
			}
		}
		
		function checkRate(nubmer){
		     var re =  /^[0-9a-zA-Z]*$/g;  //判断字符串是否为数字和字母组合     //判断正整数 /^[1-9]+[0-9]*]*$/  
		     if (!re.test(nubmer)){
		        return false;
		     }else{
		    	return true;
		     }
		}
		
		/**
		 * 进行页面三的企业信息验证判断
		 */
		function checkQy(qi,sfz,fddb,zczj,zcdd,clrq,fw) {
			var msg;
			if (qi == "" || qi.trim() == "") {
				msg = " 企业名称不能为空! ";
				$('#qiMsg').text(msg);
				return false;
			}else if (sfz == "" || sfz.trim() == "") {
				msg = " 身份证不能为空! ";
				$('#sfMsg').text(msg);
				return false;
			}else if (fddb == "" || fddb.trim() == "") {
				msg = " 法定代表不能为空! ";
				$('#poMsg').text(msg);
				return false;
			}else if (zczj == "" || zczj.trim() == "") {
				msg = " 注册资金不能为空! ";
				$('#moMsg').text(msg);
				return false;
			}else if (zcdd == "" || zcdd.trim() == "") {
				msg = " 注册地址不能为空! ";
				$('#addMsg').text(msg);
				return false;
			}else if (clrq == "" || clrq.trim() == "") {
				msg = " 成立日期不能为空! ";
				$('#clMsg').text(msg);
				return false;
			}else if (fw == "" || fw.trim() == "") {
				msg = " 经营范围不能为空! ";
				$('#jyfwMsg').text(msg);
				return false;
			}
			else {
				msg = '√';
				$('#qiMsg').css('color', 'green');
				$('#qiMsg').text(msg)
				$('#idCard').css('color', 'green');
				$('#sfMsg').text(msg)
				$('#people').css('color', 'green');
				$('#poMsg').text(msg)
				$('#money').css('color', 'green');
				$('#moMsg').text(msg)
				$('#address').css('color', 'green');
				$('#addMsg').text(msg)
				$('#clDate').css('color', 'green');
				$('#clMsg').text(msg)
				$('#jyfw').css('color', 'green');
				$('#jyfwMsg').text(msg)
				
				return true;
			}
		}

		/**
		 * 首次页面跳转进行判断验证
		 */
		nextBtn.addEventListener('click', toNext);
		function toNext() {
			/*	if (!checkPhone(phone)||!checkIdentify(identify)||!checkCode(code)) {*/
			var phone = document.getElementById("cellphone").value;//电话号码
			if (checkPhone() && checkIdentify() && checkAgree()) 
				nextStep(1);
		}

		/**
		 * 进入页面2进行判断验证
		 */

		nextBtn2.addEventListener('click', toNext2);
		function toNext2() {
			var pass = document.getElementById("loginpass").value;
			var pass2 = document.getElementById("loginpass2").value;
			if (checkPwd(pass, pass2))
				nextStep(2);
		}

		/**
		 * 进入页面3进行判断验证
		 */
		nextBtn3.addEventListener('click', toNext3)
		function toNext3() {
			var qi = document.getElementById("username").value;
			var sfz = document.getElementById("idCard").value;
			var fddb = document.getElementById("people").value;
			var zczj= document.getElementById("money").value;
			var zcdd = document.getElementById("address").value;
			var clrq = document.getElementById("clDate").value;
			var fw = document.getElementById("jyfw").value;
			if (checkQy(qi,sfz,fddb,zczj,zcdd,clrq,fw)) {
				var arge = {username: qi,idCard:sfz,people: fddb,money:zczj,address: zcdd,clDate:clrq,jyfw:fw};
				cheakRegist(arge, 'qiMsg', 3);
			}
		}
		
		/**
		 * 点击下一步跳转页面--开始
		 */
		var partAll = [ document.getElementById('step1'),
			document.getElementById('step2'),
			document.getElementById('step3'),
			document.getElementById('step4') ];

		function nextStep(i) {
			var phone = document.getElementById("cellphone").value;//电话号码
			var code = document.getElementById("code").value;//校验码
			var agree = document.getElementById("agree").checked;//工程协议
			var identify = document.getElementById("identify").value; //验证码
			var pass = document.getElementById('loginpass').value;//密码
			var name = document.getElementById("username").value;//企业名称
			var name = document.getElementById("idCard").value;//机构编码
			var name = document.getElementById("people").value;//法定代表
			var name = document.getElementById("lxdh").value;//联系电话
			var name = document.getElementById("address").value;//注册地址
			var name = document.getElementById("dwxz").value;//单位性质
			var name = document.getElementById("dzyx").value;//电子邮箱
			var name = document.getElementById("money").value;//注册资金
			var name = document.getElementById("clDate").value;//成立日期
			var name = document.getElementById("jyfw").value;//经营范围
			if(i != 3)
				nextPart(i);
			else
			$.post('<c:url value="/registerUser.do" />', {
				cellphone: phone, 
				code: code,
				agree: agree,
				identify: identify,
				loginpass: pass,
				username: name
			}, function(result){
				if(result.success) {
					$('#erphone').html(result.phone);
					$('#erusername').html(result.name);
					nextPart(i);
				}
			});
		}
		
		function nextPart(i) {
			partAll.forEach(function(item, index) {
				if (i === index) {
					item.style.display = 'block'

				} else {
					item.style.display = 'none'
				}
			})
		}
		
		