<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ include file="/WEB-INF/jsp/inc/inc_login.jsp"%>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url: '<c:url value="/default.do"/>',
			type: 'POST',
			error: function(XmlHttpRequest, textStatus, errorThrown) {
				window.location.href="checklogin.do";
			},
			success: function(result) {
				if(!result.success) {
					window.location.href="checklogin.do";
				}
				else {
					window.location.href="default.do";
				}
			}
		});
	});
</script>