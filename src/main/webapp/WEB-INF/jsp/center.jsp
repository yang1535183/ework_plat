<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="<c:url value='/ework-res/scripts/jomino.js'/>"></script>
<script type="text/javascript">
	var da = new Date();
	$(function(){
		showtime();
		$(".navIcon").hide();
		$(".navIcon").jomino();
		$(".navIcon_nobg").hide();
		$(".navIcon_nobg").jomino();
	});
	
	function customHeightSet(contentHeight){
		$(".page_content").height(contentHeight);
	}

	function showtime(){
		var hournow = da.getHours();
		if(hournow<8){
			$('#today1').html("早上");
		}
		else if(hournow>=8 && hournow<12){
			$('#today1').html("上午");
		}
		else if(hournow>=12 && hournow<=13){
			$('#today1').html("中午");
		}
		else if(hournow>=13 && hournow<=18){
			$('#today1').html("下午");
		}
		else if(hournow>18){
			$('#today1').html("晚上");
		}
		setTimeout("showtime()",10000);//每隔10秒执行一次
	}
</script>

<div data-options="region:'center'">
	<div id="tabs" class="easyui-tabs"  data-options="fit:'true',border:'false'">
		<div title="我的工作台" data-options="iconCls: 'icon-house'" style="maring:0px;padding:20px;overflow-x:hidden;background-image: url('<c:url value="/ework-res/images/welcome.jpg')"/>">
			<div class="welinfo">
				<span><img src="<c:url value='/ework-res/images/sun.png'/>" alt="天气" /></span>
				<b><dfn>${user.username }&nbsp;</dfn> <font id="today1"></font>好，欢迎使用${bcmger.platTitle }</b>
			</div>
			<div id="cc" class="easyui-layout" style="width: 100%;height: 90%">
			    <div style="height:180px; padding-left: 15px;" data-options="iconCls: 'icon-film',region:'north',title:'监控导航',split:true,collapsible:false">
			    	<table>
			    		<tr height="136">
							<td>
								<div class="navIcon" onclick="addTab('混泥土厂监控','<c:url value='/main/video.do'/>?flag=1','icon-film');">
									<div class="navIcon_left"><img src="<c:url value='/ework-res/images/icon_flat/mailfront.png'/>"/></div>
									<div class="navIcon_right">
										<div class="navIcon_right_title">混泥土厂远程视频监控</div>
										<div class="navIcon_right_con">
											混泥土厂远程视频<br />
											查看混泥土厂远程视频
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</td>
							<td>
								<div class="navIcon" onclick="addTab('施工现场监控','<c:url value='/main/video.do'/>?flag=2','icon-film');">
									<div class="navIcon_left"><img src="<c:url value='/ework-res/images/icon_flat/designbumb.png'/>"/></div>
									<div class="navIcon_right">
										<div class="navIcon_right_title">混泥土与施工现场监控</div>
										<div class="navIcon_right_con">
											施工现场远程视频<br />
											查看施工现场远程视频
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</td>
						</tr>
			    	</table>
			    </div>
			    <div style="width:48%"  data-options="iconCls: 'icon-book_edit', region:'west', title:'待办事项',split:true,collapsible:false" >
					 <div class="news_tzgg_content">
						<ul id="ywDbsxList">
							<c:forEach var="dbsx" items="${dbsx }">
						 		<li>
									<a href="javascript:void(0);">
										<img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>"/>${dbsx.memo }
									</a>
									<span>${dbsx.scsj }</span>
								</li>
					 		</c:forEach>
						</ul>
					</div>
			    </div>
			    <div style="padding:1px;" data-options="iconCls: 'icon-sound',region:'center', title:'通知公告 '">
			  		<div class="news_tzgg_content">
						<ul>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>"/>习近平向中阿合作论坛第七届部长届…</a><span>05-20</span></li>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>">习近平如何破除“为官不为” 治国</a><span>05-20</span></li>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>">国务院常务会议召开 李克强：促进…</a><span>05-20</span></li>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>">中央军委：未来5年军队关键作战能</a><span>05-20</span></li>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>">铁路新图5月15日正式实施</a><span>05-20</span></li>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>">为近十年最大范围调整朝鲜公布金动…</a><span>05-20</span></li>
							<li>
								<a href=""><img src="<c:url value='/ework-res/images/icon_flat/news_list_ico.png'/>">新华社评雷洋案：警方信息把事件引</a><span>05-20</span></li>
						</ul>
					</div>
			    </div>
			</div>
		</div>
	</div>
</div>

