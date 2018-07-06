<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="infoFormtabs" class="easyui-tabs"
	style="width: 100%; height: 100%;">
	<div title="质监报表" data-options="iconCls:'icon-book_open'"
		style="padding: 20px; display: none;">
		<c:choose>
			<c:when test="${TBAEGCZJ.sjzt == 0 || TBAEGCZJ.sjzt == 3 || TBAEGCZJ.sjzt == 5}">
				<!-- 录入 -->
				<c:choose>
					<c:when test="${step == 'gclr' }">
						<c:choose>
							<c:when test="${sjzt == 3}">
								<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/gcxmcheck.jsp"%>
							</c:when>
							<c:otherwise>
								<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/gcxmModel.jsp"%>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/gcxmQuery.jsp"%>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<!-- 查看 -->
				<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/gcxmQuery.jsp"%>
			</c:otherwise>
		</c:choose>
		
		<div style="display: none;">
			<%@ include
				file="/WEB-INF/jsp/xmgl/gcgl/form/print/printZjboForm.jsp"%>
		</div>
	</div>
	<div title="附件" data-options="iconCls:'icon-book_link'"
		style="overflow: auto; padding: 20px; display: none;">
		<!-- 可上传 -->
		<c:choose>
			<c:when
				test="${TBAEGCZJ.sjzt == 0 || TBAEGCZJ.sjzt == 3 || TBAEGCZJ.sjzt == 5}">
				<!-- 录入 -->
				<c:choose>
					<c:when test="${step=='gclr' }">
						<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/fileUploadModel.jsp"%>
					</c:when>
					<c:otherwise>
						<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/fileUploadQuery.jsp"%>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${TBAEGCZJ.sjzt == 1}">
				<!-- 审查 -->
				<c:choose>
					<c:when test="${step=='gcsc' }">
						<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/fileUploadScsj.jsp"%>
					</c:when>
					<c:otherwise>
						<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/fileUploadQuery.jsp"%>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<!-- 查看 -->
				<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/fileUploadQuery.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${TBAEGCZJ.sjzt != 0}">
		<c:choose>
			<c:when test="${TBAEGCZJ.sjzt == 1}">
				<!-- 审查 -->
				<c:choose>
					<c:when test="${step=='gcsc' }">
						<div title="逐项审查"
							data-options="iconCls:'icon-application_form', selected:'true'"
							style="overflow: auto; padding: 20px; display: none;">
							<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/zxshModel.jsp"%>
						</div>
					</c:when>
					<c:otherwise>
						<div title="审查结果" data-options="iconCls:'icon-application_form'"
							style="overflow: auto; padding: 20px; display: none;">
							<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/zxshQuery.jsp"%>
						</div>
					</c:otherwise>
				</c:choose>

			</c:when>
			<c:otherwise>
				<!-- 查看审查结果-->
				<div title="审查结果" data-options="iconCls:'icon-application_form'"
					style="overflow: auto; padding: 20px; display: none;">
					<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/zxshQuery.jsp"%>
				</div>
			</c:otherwise>
		</c:choose>
		<!-- 查看流程-->
		<div title="流程" data-options="iconCls:'icon-reload'"
			style="padding: 20px; display: none;">
			<%@ include file="/WEB-INF/jsp/xmgl/gcgl/form/gcxmLcjl.jsp"%>
		</div>
	</c:if>
</div>

