<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 流程记录jsp -->

<div id="gcxmlcjllist" class="step-body" id="myStep">
	<div class="step-header" style="width: 80%; display: block;">
		<c:choose>
			<c:when test="${sjzt == 1}">
				<div class="step-bar">
					<div class="step-bar-active" style="width: 40%;"></div>
				</div>
				<ul>
					<li class="step-active" style="width: 25%;"><span>1</span>
						<p>提报</p></li>
					<li class="step-active" style="width: 25%;"><span>2</span>
						<p>待审查</p></li>
					<li style="width: 25%;"><span>3</span>
						<p>待审批</p></li>
					<li style="width: 25%;"><span>4</span>
						<p>完成</p></li>
				</ul>
			</c:when>
			<c:when test="${sjzt == 2}">
				<div class="step-bar">
					<div class="step-bar-active" style="width: 60%;"></div>
				</div>
				<ul>
					<li class="step-active" style="width: 25%;"><span>1</span>
						<p>提报</p></li>
					<li class="step-active" style="width: 25%;"><span>2</span>
						<p>待审查</p></li>
					<li class="step-active" style="width: 25%;"><span>3</span>
						<p>待审批</p></li>
					<li style="width: 25%;"><span>4</span>
						<p>完成</p></li>
				</ul>
			</c:when>
			<c:when test="${sjzt == 4}">
				<div class="step-bar">
					<div class="step-bar-active" style="width: 100%;"></div>
				</div>
				<ul>
					<li class="step-active" style="width: 25%;"><span>1</span>
						<p>提报</p></li>
					<li class="step-active" style="width: 25%;"><span>2</span>
						<p>待审查</p></li>
					<li class="step-active" style="width: 25%;"><span>3</span>
						<p>待审批</p></li>
					<li class="step-active" style="width: 25%;"><span>4</span>
						<p>完成</p></li>
				</ul>
			</c:when>

			<c:otherwise>
				<div class="step-bar">
					<div class="step-bar-active" style="width: 0%;"></div>
				</div>
				<ul>
					<li style="width: 25%;"><span>1</span>
						<p>提报</p></li>
					<li style="width: 25%;"><span>2</span>
						<p>待审查</p></li>
					<li style="width: 25%;"><span>3</span>
						<p>待审批</p></li>
					<li style="width: 25%;"><span>4</span>
						<p>完成</p></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="step-content">
		<div class="step-list" style="display: block;">
			<div class="page-title">
				<h3 class="page-title-left">流程说明</h3>
				<h3 class="page-title-right">注：</h3>
			</div>
			<c:if test="${sjzt == 4}">
				<div class="step-list" style="display: block;">
					<div class="apply-finish">
						<div class="apply-finish-header">
							<img src="ework-res/scripts/lcgl/images/success.png">
							<div class="apply-finish-msg">恭喜您，报监成功！</div>
						</div>
						<div class="apply-finish-footer">
							<p>
								尊敬的企业用户，您已报监成功，注册码为：<span id="proNo">${zcbm }</span>
							</p>
						</div>
					</div>
				</div>
			</c:if>
			
			<div class="intro-flow">
				<c:forEach var="item" items="${ywLCList }" varStatus="s">
					<c:choose>
						<c:when test="${item.action=='1'}">
							<div class="intro-list">
								<div class="intro-list-left">工程信息提交</div>
								<div class="intro-list-right">
									<span>${item.pxh }</span>
									<div id="lcDiv1" class="intro-list-content">
										企业：${item.username}
										<p>${item.time }&nbsp;->&nbsp;${item.memo }</p>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${item.action=='2'}">
							<div class="intro-list">
								<div class="intro-list-left">审查员审查各项工程信息</div>
								<div class="intro-list-right">
									<span>${item.pxh }</span>
									<div id="lcDiv2" class="intro-list-content">
										审查人：${item.username}
										<p>${item.time }&nbsp;->&nbsp;${item.memo }</p>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${item.action=='3'}">
							<div class="intro-list intro-list-active">
								<div class="intro-list-left">审查打回</div>
								<div class="intro-list-right">
									<span>${item.pxh }</span>
									<div id="lcDiv3" class="intro-list-content">
										审查人：${item.username}
										<p>${item.time }&nbsp;->&nbsp;${item.memo }</p>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${item.action=='4'}">
							<div class="intro-list">
								<div class="intro-list-left">生成企业唯一注册码</div>
								<div class="intro-list-right">
									<span>${item.pxh }</span>
									<div id="lcDiv4" class="intro-list-content">
										审批人：${item.username}
										<p>${item.time }&nbsp;->&nbsp;${item.memo }</p>
										您的工程报监信息已成功通过！
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${item.action=='5'}">
							<div class="intro-list intro-list-active">
								<div class="intro-list-left">审批打回</div>
								<div class="intro-list-right">
									<span>${item.pxh }</span>
									<div id="lcDiv5" class="intro-list-content">
										审批人：${item.username}
										<p>${item.time }&nbsp;->&nbsp;${item.memo }</p>
									</div>
									</br>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</div>