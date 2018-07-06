<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<head>
	<title>查看：${gcmc }&nbsp;附件</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">  
	<%@ include file="/WEB-INF/jsp/inc/inc_staticImg.jsp"%>
</head>
<body>
	<div class="content">
    	<c:choose>
			<c:when test="${!empty fjList}">
				<c:forEach items="${fjList}" var="item">
					<%-- title="${item.filename }" --%>
					<a class="elem" href="<c:url value='/file/download/queryImg.do'/>?fileid=${item.id }" 
						data-lcl-author="：${user }" 
						data-lcl-txt="${item.filename }" 
						data-lcl-thumb="<c:url value='/file/download/queryImg.do'/>?fileid=${item.id }">
						<div style="font-size:16px;text-align:center;" >
							${gcmc }：${item.filename }
						</div>
						<span style="background-image: url(<c:url value='/file/download/queryImg.do'/>?fileid=${item.id })"></span>
					</a>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<!-- <h3>暂无附件</h3> -->
				<%@ include file="/WEB-INF/jsp/xmgl/staticPage/imgShow/noFile.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>
	<script type="text/javascript">
		$(document).ready(function(e) {
			lc_lightbox('.elem', {
				wrap_class: 'lcl_fade_oc',
				gallery : true,	
				thumb_attr: 'data-lcl-thumb', 
				skin: 'minimal',
				radius: 0,
				padding	: 0,
				border_w: 0,
			});	
		});
	</script>
</body>
</html>