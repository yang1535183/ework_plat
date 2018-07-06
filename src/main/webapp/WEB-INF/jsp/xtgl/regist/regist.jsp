<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>企业用户注册</title>
<link rel="stylesheet" href="<c:url value='/ework-res/regist/css/style.css'/>" type="text/css">
<style type="text/css">
#tips {
	float: left;
	margin: 2px 0 0 71px;
}

#pi {
	float: left;
	margin: -13px 0 0 75px;
}

#tips span {
	float: left;
	width: 50px;
	height: 20px;
	color: white;
	background: green;
	margin-right: 2px;
	line-height: 20px;
	text-align: center;
}

 #container {
     width: 500px;
     height: 270px;
 }

.amap-logo {
    display: none !important;
}

.amap-copyright {
    display: none !important;
}
</style>
</head>

<body>
	<header>
		<div class="w12 header">
			<a class="db logo fl"> <img src="ework-res/regist/img/logo1.jpg"
				width="327" height="94" alt="" />
			</a>
			<div class="fr logofr">
				<a href="#" class="blk">返回首页</a>| <a href="#" class="blk">客服中心</a> <br>
				如注册遇到问题请拨打： <strong style="font-size: 14px;">0551-6511-1652</strong>
			</div>
		</div>
	</header>
	<div class="head_border"></div>
	<section class="w12 login">
		<em class="fr">已有账号，请&nbsp;&nbsp; 
			<a class="db logbtn fr" href="<c:url value='/login.do'/>">前往登录</a>
		</em>
	</section>
	<!--页面1-->
	<section id="step1" class="main w12">
		<div class="title">
			<a class="title1 db fl">企业用户</a>
		</div>
		<div class="fr tit2">
			<span class="arr"></span>
		</div>
		<div class="w12 step"></div>
		<!--进度条-->
		<form id="step1_frm" method="post">
			<!--表单开始-->
			<div class="inputsec">
				<ul>
					<li>
						<label class="fl mr2">手机号码：</label> 
						<input id="cellphone" name="cellphone" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请输入11位手机号码" onblur="checkPhone();"> <!-- 修改1 添加提示信息容器 -->
						<span id='phoneMsg' style='color: red;'></span>
					</li>
					<li>
						<label class="fl mr2">验证码：</label> 
						<span class="fl yzm">
							<input id="identify" name="identify" type="text" class="fl txt-y"
							style="ime-mode: disabled" placeholder="不区分大小写" />
							<a class="fl mr2 mt2"> 
								<img id="im" src="file/verification.do" width="83" height="27" />
							</a>
							<a id="huan" class="fl chg">换一张</a>
						</span>
						<div id='identifyMsg' class="msg-error"></div>
					</li>
					<li>
						<label class="fl mr2">校验码：</label>
						<span class="fl yzm2">
							<input type="text" class="fl txt-j" id="code" name="code" placeholder="请输入校验码"
								onClick="if(value==defaultValue){value='';this.style.color='#333'}" />
						</span> 
						<a class="db fl chg send">发送至手机</a>
						<span id='codeMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl">&nbsp;</label>
						<p style="line-height: 28px;">
							<input id="agree" type="checkbox" name="agree" value="1">我已阅读并同意
							<a style="text-decoration: underline;">《工程协议》</a>
							<span id='agreeMsg' style='color: red;'> </span></li>
						</p>

					<li class="clr">
						<label class="db fl">&nbsp;</label> 
						<a id="nextBtn" class="db fl next" data-type='nextStep' style="width: 100px;">下一步</a></li>
				</ul>
		</div>
		</form>
	</section>

	<!--页面2-->
	<section id="step2" class="main w19" style="display: none;">
		<div class="title">
			<a class="title1 db fl">企业用户</a>
		</div>
		<div class="fr tit2">
			<span class="arr"></span>
		</div>
		<div class="w12 step two"></div>
		<form id="step2_frm" method="post">
			<div class="inputsec">
				<ul>
					<li>
						<label class="fl mm">输入密码：</label> <input id="loginpass" type="password" name="loginpass" style="ime-mode: disabled"
						class="txt-m fl " placeholder="字母、数字或者英文符号，最短6位"> 
						<span id='passMsg' style='color: red;'> </span><br />
						<div id="tips">
							<span></span><span></span><span></span>
						</div>
					</li>
					<li>
						<label class="fl mm">再次确认：</label> 
						<input id="loginpass2" type="password" name="loginpass2" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写相同密码"> 
						<span id='pass2Msg' style='color: red;'> </span>
					</li>
					<li class="clr">
						<label class="db fl">&nbsp;</label> 
						<a id="nextBtn2" class="db fl next" data-type='nextStep' href="#jia"
						style="width: 100px;">下一步</a>
					</li>
				</ul>
			</div>
		</form>
	</section>

	<!--页面3-->
	<section id="step3" class="main w98" style="display: none;">
		<div class="title">
			<a class="title1 db fl">企业用户</a>
		</div>
		<div class="fr tit2">
			<span class="arr"></span>
		</div>
		<div class="w12 step three"></div>
		<form id="step3_frm" method="post">
			<div class="inputsec">
				<ul>
					<li>
						<label class="fl mr2">企业名称：</label>
						<input id="username" name="username" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写完整企业名称"> 
						<span id='qiMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">机构编码：</label>
						<input id="idCard" name="idCard" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写正确机构代码"> 
						<span id='sfMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">法定代表：</label>
						<input id="people" name="people" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写代表人名字"> 
						<span id='poMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">联系电话：</label>
						<input id="lxdh" name="lxdh" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写联系电话"> 
						<span id='lxMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">注册地址：</label>
						<input id="address" name="address" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写完整注册地址输入关键字" onfocus="mapShow()"> 
						<span id='addMsg' style='color: red;'> </span>
						<p id="myPageTop" style="display:none">
						<span onclick="mapClose()" style="cursor:pointer">（点击收起地图）</span>
						</p>
					</li>
					<div id="container" tabindex="0" style="display: none;"></div>
					<li>
						<label class="fl mr2">单位性质：</label>
						<input id="dwxz" name="dwxz" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写单位性质"> 
						<span id='dwxzMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">电子邮箱：</label>
						<input id="dzyx" name="dzyx" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写电子邮箱"> 
						<span id='dzMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">注册资金：</label>
						<input id="money" name="money" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写注册资金金额"> 
						<span id='moMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">成立日期：</label>
						<input id="clDate" name="clDate" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写成立日期"> 
						<span id='clMsg' style='color: red;'> </span>
					</li>
					<li>
						<label class="fl mr2">经营范围：</label>
						<input id="jyfw" name="jyfw" type="text" style="ime-mode: disabled"
						class="txt-m fl " placeholder="请填写经营范围"> 
						<span id='jyfwMsg' style='color: red;'> </span>
					</li>
					<li class="clr">
						<label class="db fl">&nbsp;</label>
						<a id="nextBtn3" class="db fl next" data-type='nextStep' href="#ling"
						style="width: 100px;">下一步</a>
					</li>
				</ul>
			</div>
		</form>
	</section>

	<!--页面4-->
	<section id="step4" class="main w12" style="display: none;">
		<div class="title">
			<a class="title1 db fl">企业用户</a>
		</div>
		<div class="fr tit2">
			<span class="arr"></span>
		</div>
		<div class="w12 step four"></div>
		<div class="success">
			<ul>
				<li class="suc1">恭喜您注册成功</li>
				<li>注册手机号码： <em class="mr3"><span id="erphone"></span></em>注册名： <em><span id="erusername"></span></em>
				</li>
				<li class="suc2">
					<a class="db mr4 fl" target="_blank" href="<c:url value='/login.do'/>">立即登录</a>
					<a class="db fl" href="<c:url value='/regist.do'/>">返回注册</a>
				</li>
			</ul>
		</div>
	</section>
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.6&key=dfba932b7c6f823f8d8ed1e00dd7ea4d"></script>
<script src="http://webapi.amap.com/ui/1.0/main.js"></script>
<script type="text/javascript" src="<c:url value='/ework-res/jquery/1.8.0/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ework-res/regist/js/registMap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ework-res/regist/js/registValidate.js'/>"></script>
<script type="text/javascript">
var _platCity = '${bcmger.platAdname}';
	function checkajaxidentify() {
		var code=$('#identify').val();
		var falgIdentify=false;
		$.ajax({
			url: '<c:url value="/checkVerificode.do" />',
			type: 'POST',
			data: 'code='+ code,
			async: false,
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
			},
			success: function(result) {
				if(!result.success) {
					$('#identifyMsg').text("输入验证码错误。");
					$('#identifyMsg').css('color', 'red');
				}
				else {
					$('#identifyMsg').text("√");
					$('#identifyMsg').css('color', 'green');
					falgIdentify=true;
				}
			}
		});
		return falgIdentify;
	}
	
	/***企业注册，判断用户是否已经存在 **/
	function cheakRegist(arge, msgid, index) {
		var cheakRegistFlag = false;
		$.ajax({
			url: '<c:url value="/cheakRegist.do" />',
			type: 'POST',
			data: arge,
			async: false,
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
			},
			success: function(result) {
				if(result.success) {
					cheakRegistFlag = true;
					$('#'+msgid).text("√");
					$('#'+msgid).css('color', 'green');
					if(index) {
						nextStep(index);
					}
				}
				else {
					$('#'+msgid).text(result.data);
					$('#'+msgid).css('color', 'red');
				}
			}
		});
		return cheakRegistFlag;
	}
</script>
</body>
</html>