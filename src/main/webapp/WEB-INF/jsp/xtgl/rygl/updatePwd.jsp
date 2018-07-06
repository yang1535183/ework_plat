<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div>
	<form id="updatePwdForm">
		请输入原始密码<input id="oldPWd" name="oldPwd" class="easyui-passwordbox" prompt="原始密码" iconWidth="28" style="width:100%;height:34px;padding:10px"> 
		请输入新密码<input id="newPwd" name="newPwd" class="easyui-validatebox easyui-passwordbox" prompt="新密码" iconWidth="28" style="width:100%;height:34px;padding:10px" data-options="required:true" />   
		请再次输入新密码<input id="rpwd" name="rpwd" class="easyui-validatebox easyui-passwordbox" prompt="再次输入新密码" iconWidth="28" style="width:100%;height:34px;padding:10px" required="required" validType="equals['#newPwd']" />  
	</form>
</div>