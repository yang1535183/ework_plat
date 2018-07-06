<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="cc" class="easyui-layout" style="width: 100%; height: 100%">
	<div style="width: 280px; padding-left: 10px;"
		data-options="iconCls: 'icon-image_link', region:'west', title:'视频导航', collapsible:true">
		<table>
			<tr height="136">
				<td><a href="spjk/xcsp.do" target="ifr">
						<div id="navIcona" class="navIcon" style="background-color: #2DA9FA;" onclick="queryVideo(this);">
							<div class="navIcon_left">
								<img src="<c:url value='/ework-res/images/icon_flat/p4.png'/>" />
							</div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">远程视频监控</div>
								<div class="navIcon_right_con">
									混泥土厂远程视频<br /> 查看混泥土厂远程视频
								</div>
							</div>
							<div class="clear"></div>
						</div>
				</a></td>
			</tr>
			<tr height="136">
				<td><a href="http://main.diiwon.com/dist/build/" target="ifr">
						<div id="navIconb" class="navIcon" onclick="queryVideo(this);">
							<div class="navIcon_left">
								<img src="<c:url value='/ework-res/images/icon_flat/p4.png'/>" />
							</div>
							<div class="navIcon_right">
								<div class="navIcon_right_title">混泥土厂数据监控</div>
								<div class="navIcon_right_con">
									混泥土厂远程视频<br /> 查看混泥土厂远程视频
								</div>
							</div>
							<div class="clear"></div>
						</div>
				</a></td>
			</tr>
			<tr height="136">
				<td>
					<div id="navIconc" class="navIcon" onclick="queryVideo(this);">
						<div class="navIcon_left">
							<img src="<c:url value='/ework-res/images/icon_flat/p4.png'/>" />
						</div>
						<div class="navIcon_right">
							<div class="navIcon_right_title">混泥土厂远程视频监控</div>
							<div class="navIcon_right_con">
								混泥土厂远程视频<br /> 查看混泥土厂远程视频
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</td>
			</tr>
			<tr height="136">
				<td>
					<div id="navIcond" class="navIcon" onclick="queryVideo(this);">
						<div class="navIcon_left">
							<img src="<c:url value='/ework-res/images/icon_flat/p4.png'/>" />
						</div>
						<div class="navIcon_right">
							<div class="navIcon_right_title">混泥土厂远程视频监控</div>
							<div class="navIcon_right_con">
								混泥土厂远程视频<br /> 查看混泥土厂远程视频
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding: 1px; text-align: centera"
		data-options="iconCls: 'icon-image',region:'center', split:true, title:'视频监控 '">
		<!-- 此处展示视频播放 -->
		<iframe name="ifr" src="spjk/xcsp.do" frameborder="no" border="0"
			marginwidth="0" marginheight="0" scrolling="yes"
			allowtransparency="yes" style="width: 100%; height: 100%;">
		</iframe>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$('#defaulteasyuilayout').layout('collapse','west');
	})
	
	function queryVideo(e_) {
		
		$("#navIcona").css("background-color","");
		$("#navIconb").css("background-color","");
		$("#navIconc").css("background-color","");
		$("#navIcond").css("background-color","");
		e_.style.backgroundColor = "#2DA9FA";
	}
	
</script>