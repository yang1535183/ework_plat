<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>未上传附件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <link rel="stylesheet" type="text/css" href="404/error_all.css?t=201303212934"> -->
<%@ include file="/WEB-INF/jsp/inc/inc_noFile.jsp"%>
</head>
<body class="error-404">
<div id="doc_main">
	<section class="bd clearfix">
		<div class="module-error">
			<div class="error-main clearfix">
				<div class="label"></div>
				<div class="info">
					<h2 class="title">啊哦，你所访问的页面没有附件！</h2>
					<div class="reason">
						<p>可能的原因：</p>
						<p>1.工程未上传附件。</p>
						<p>2.没有权限查看附件功能。</p>
						<p>3.会话已过期。</p>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
</body>
</html>
