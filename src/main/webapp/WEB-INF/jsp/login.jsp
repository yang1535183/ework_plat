<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
    <title>${TITLE}</title>
    <%@ include file="/WEB-INF/jsp/inc/inc_login.jsp"%>
</head>
<body  id="ljllogin">
	<div class="f-login">
		<div class="f-login-header">
	    <div class="f-login-logo">
				<img src="<c:url value='/ework-res/login/img/loginLogo.png'/>"/>
		</div>
		</div>
		<div class="f-login-content">
			<div class="f-login-content-main">
                <div class="f-login-frame">
                    <div class="title">用户登录</div>
                    <div class="form">
                        <form id="loginForm" style="margin: 0;" method="post">
                            <input name="__RequestVerificationToken" type="hidden" value="" />
                            <div class="input-prepend">
                                <span class="add-on">账号</span>
                                <input type="text" class="input-small" id="loginname" name="loginname" />
                            </div>
                            <div class="input-prepend">
                                <span class="add-on">密码</span>
                                <input type="password" class="input-small" id="loginpass" name="loginpass" />
                            </div>
                        </form>
                        <div id="ErrorTip" class="tx_text"></div>
                        <div id="forget">
                        	<a href="#">忘记密码？</a> 	
                        	<a href="#" onclick="goRegist()">注册账号</a>
                        </div>
                        <div class="buttons">
							<table>
								<tr>
									<td>
										<a type="button" class="btn btn-success login" onclick="platlogin(this)">登&nbsp;&nbsp;录</a>
									</td>
									<td>
										<a type="button" class="btn btn-success login" onclick="resetloginForm(this);">重&nbsp;&nbsp;置</a>
									</td>
								</tr>
							</table>
                        </div>
                    </div>
                </div>
          </div>
        </div>
        <div class="f-login-footer">
            <div class="f-login-footer-main">
                <div class="copyright">
                   	技术支持：安徽迪万科技有限公司
                   	&nbsp;&nbsp;网站：http://www.diiwon.com<br/>
                    Copyright © 2016-2026 All Rights Reserved.
                </div>
            </div>
        </div>
    </div>
    
<script type="text/javascript">

	function goRegist(){
		window.location=scriptArgsPath+'regist.do';
	}
  	//登录
    function platlogin(flag) {
    	if($('#loginname').val() == '') {
    		show_err_msg('用户名不能为空！');
    		$('#loginname').focus();
    		return;
    	}
    	if($('#loginpass').val() == '') {
    		show_err_msg('密码不能为空！');
    		$('#loginpass').focus();
    		return;
    	}
    	else{
    		var data = $('#loginForm').serialize();
    		$.ajax({
    			url: '<c:url value="checkoutLogin.do"/>',
    			type: 'POST',
    			data: data,
    			error: function(XmlHttpRequest, textStatus, errorThrown) {
    				if (XmlHttpRequest.status > 200) {
    					show_err_msg('服务器繁忙或无法连接，请稍后再试！！');
    				}
    			},
    			success: function(result) {
    				if(!result.success) {
    					if(result.cherk) {
    						$.messager.confirm('确认', result.data, function(r){
    						    if (r){
    						    	okCheck(data);
    						    }
    						});
    						return;
    					}
    				}
    				else {
    					self.location = scriptArgsPath+'default.do';
    				}
    			}
    		});
    	}
    }
  	
  	function okCheck(data) {
  		$.ajax({
			url: '<c:url value="checkoutLogin.do"/>?flag=check',
			type: 'POST',
			data: data,
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				if (XmlHttpRequest.status > 200) {
					show_err_msg('服务器繁忙或无法连接，请稍后再试！！');
				}
			},
			success: function(result) {
				if(!result.success) {
					if(result.cherk) {
						$.messager.confirm('确认', result.data, function(r){
						    if (r){
						    	platlogin(true);
						    }
						});
						return;
					}
				}
				else {
					self.location = scriptArgsPath+'default.do';
				}
			}
		});
  	}
</script>
</body>

</html>
