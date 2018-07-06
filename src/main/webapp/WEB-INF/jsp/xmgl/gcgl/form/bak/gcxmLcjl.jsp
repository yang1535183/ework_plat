<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 流程记录jsp -->

<div class="step-body" id="myStep">
	<div class="step-header" style="width: 80%; display: block;">
		<c:choose>
			<c:when test="${TBAEGCZJ.sjzt == 1}">
				<div class="step-bar">
					<div class="step-bar-active" style="width: 20%;"></div>
				</div>
				<ul>
					<li class="step-active" style="width: 20%;"><span>1</span>
						<p>提报</p></li>
					<li style="width: 20%;"><span>2</span>
						<p>待审查</p></li>
					<li style="width: 20%;"><span>3</span>
						<p>待审批</p></li>
					<li style="width: 20%;"><span>4</span>
						<p>注册号生成</p></li>
					<li style="width: 20%;"><span>5</span>
						<p>完成</p></li>
				</ul>
			</c:when>
			<c:when test="${TBAEGCZJ.sjzt == 2}">
				<div class="step-bar">
					<div class="step-bar-active" style="width: 40%;"></div>
				</div>
				<ul>
					<li class="step-active" style="width: 20%;"><span>1</span>
						<p>提报</p></li>
					<li class="step-active" style="width: 20%;"><span>2</span>
						<p>待审查</p></li>
					<li style="width: 20%;"><span>3</span>
						<p>待审批</p></li>
					<li style="width: 20%;"><span>4</span>
						<p>注册号生成</p></li>
					<li style="width: 20%;"><span>5</span>
						<p>完成</p></li>
				</ul>
			</c:when>
			<c:when test="${TBAEGCZJ.sjzt == 4}">
				<div class="step-bar">
					<div class="step-bar-active" style="width: 40%;"></div>
				</div>
				<ul>
					<li class="step-active" style="width: 20%;"><span>1</span>
						<p>提报</p></li>
					<li class="step-active" style="width: 20%;"><span>2</span>
						<p>待审查</p></li>
					<li class="step-active" style="width: 20%;"><span>3</span>
						<p>待审批</p></li>
					<li class="step-active" style="width: 20%;"><span>4</span>
						<p>注册号生成</p></li>
					<li style="width: 20%;"><span>5</span>
						<p>完成</p></li>
				</ul>
			</c:when>

			<c:otherwise>
				<div class="step-bar">
					<div class="step-bar-active" style="width: 0%;"></div>
				</div>
				<ul>
					<li style="width: 20%;"><span>1</span>
						<p>提报</p></li>
					<li style="width: 20%;"><span>2</span>
						<p>待审查</p></li>
					<li style="width: 20%;"><span>3</span>
						<p>待审批</p></li>
					<li style="width: 20%;"><span>4</span>
						<p>注册号生成</p></li>
					<li style="width: 20%;"><span>5</span>
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
			<div class="intro-flow">
				<div class="intro-list">
					<div class="intro-list-left">工程信息提交</div>
					<div class="intro-list-right">
						<span>1</span>
						<div id="lcDiv1" class="intro-list-content">企业方提交工程重点细节信息，上报到住建局</div>
					</div>
				</div>
				<div class="intro-list intro-list-active">
					<div class="intro-list-left">审查员审查各项工程信息</div>
					<div class="intro-list-right">
						<span>2</span>
						<div id="lcDiv2" class="intro-list-content">
							逐项审查工程内容信息是否合格或是否达到标准，若合格则通过且由审批领导继续审批，反之则打回由企业再次修改提交，审查员要填写审查意见。
						</div>
					</div>
				</div>
				<div class="intro-list">
					<div class="intro-list-left">审批领导审批各项工程信息</div>
					<div class="intro-list-right">
						<span>3</span>
						<div id="lcDiv3" class="intro-list-content">
							审批由审查员已通过的工程内容信息是否合格或是否达到标准，若合格则通过，反之则打回由企业再次修改提交，审批领导要填写审查意见。</div>
					</div>
				</div>
				<div class="intro-list">
					<div class="intro-list-left">生成企业唯一注册码</div>
					<div class="intro-list-right">
						<span>4</span>
						<div id="lcDiv4" class="intro-list-content">是企业工程的唯一不可替代标识</div>
					</div>
				</div>
				<div class="intro-list intro-list-last">
					<div class="intro-list-left">完成</div>
					<div class="intro-list-right">
						<span>5</span>
						<div id="lcDiv5" class="intro-list-content">您的工程报监信息已成功通过！</div>
						</br>
					</div>
				</div>

				<c:forEach var="item" items="${ywLCList }" varStatus="s">
					<c:choose>
						<c:when test="${item.action=='1'}">
							<script type="text/javascript">
								$("#lcDiv1").append("<p>${item.time } ${} ${item.memo }</p>");
							</script>
						</c:when>
						<c:when test="${item.action=='2'}">
							<script type="text/javascript">
								$("#lcDiv2").append("<p>${item.memo }</p>");
							</script>
						</c:when>
						<c:when test="${item.action=='3'}">
							<script type="text/javascript">
								$("#lcDiv3").append("<p>${item.memo }</p>");
							</script>
						</c:when>
						<c:when test="${item.action=='4'}">
							<script type="text/javascript">
								$("#lcDiv4").append("<p>${item.memo }</p>");
							</script>
						</c:when>
						<c:when test="${item.action=='5'}">
							<script type="text/javascript">
								$("#lcDiv5").append("<p>${item.memo }</p>");
							</script>
						</c:when>
					</c:choose>
				</c:forEach>

				<c:if test="">
				</c:if>
				<div class="step-list">
					<div class="apply-finish">
						<div class="apply-finish-header">
							<img src="ework-res/scripts/lcgl/images/success.png">
							<div class="apply-finish-msg">恭喜您，报监成功！</div>
						</div>
						<div class="apply-finish-footer">
							<p>
								尊敬的企业用户，您已报监成功，注册码为：<span id="proNo">LS23423432</span>。
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>