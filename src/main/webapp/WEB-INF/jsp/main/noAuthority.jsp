<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<style>
.sign{
	font-size:22px;
	font-family:kaiti;
}
</style>
	<div align="center">
		<img src="<c:url value='/ework-res/images/icon_flat/lost.jpg'/>"/>
	</div>
	<div align="center" class="sign">
		您好：您还未有权限!&nbsp;&nbsp;<span id="show">3</span>&nbsp;&nbsp;秒后跳转到登陆界面,如没跳转<a href="/htgl/checklogin.do">点击跳转</a>到登陆界面.
	</div>
<script>
var t=3;//倒计时时间
setInterval("reLoad()", 1000); //启动1秒定时

function reLoad(){
	if(t==0){
          location.href="checklogin.do";
	}
    document.getElementById('show').innerHTML= t;
    t--;
}
</script>

