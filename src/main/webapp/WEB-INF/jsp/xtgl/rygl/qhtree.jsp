<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(function(){
	$('#TREE_B361D9882A2F474288A9BCA4F226268E').tree({   
		checkbox: true,
		lines: true,
		data: ${QHLIST},
		cascadeCheck: false,
		onCheck: function(node, checked) {
			return;
		}
	});
});
</script>

<div class="easyui-layout" style="height: 100%;">
	<div region="center" style="border:0">
		<ul id="TREE_B361D9882A2F474288A9BCA4F226268E"></ul>
	</div>
</div>

