<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="PPPRINTDISPLAY"
	style="display: none; font-size: 16px; color: red">
	<p>
		快去安装打印控件！<a href="#" onclick="wait()">点击我下载</a> <a href="#" onclick="geng()">点击我更新</a>
	</p>
</div>
<form id="cCFormAdd" method="post">
	<c:choose>
		<c:when test="${TBAECCZJ.ccgczt == ''}">
			<input type="hidden" id="ccgczt" name="ccgczt" value="${TBAECCZJ.ccgczt }">
		</c:when>
		<c:otherwise>
			<input type="hidden" id="ccgczt" name="ccgczt" value="0">
		</c:otherwise>
	</c:choose>
	<div style="height: 96%; width: 1000px; overflow: auto">
		<table width="983" border="1" cellspacing="1" cellpadding="1" style="border-collapse: collapse; bordercolor: black">
			<h2 align="center">合肥市庐阳区房屋拆除工程安全监督报监表</h2>
			<p>建设单位：（公章）</p>
			<tr>
				<td width="100" rowspan="4"><div align="center">工程概况</div></td>
				<td width="100"><div align="center">工程名称</div></td>
				<td colspan="3">${TBAECCZJ.gcmc }</td>
				<td width="100"><div align="center">工程地点</div></td>
				<td colspan="2">${TBAECCZJ.gcdd }</td>
			</tr>
			<tr>
				<td><div align="center">拆除面积</div></td>
				<td width="100">${TBAECCZJ.ccmj }</td>
				<td width="100"><div align="center">结构层次</div></td>
				<td width="100">${TBAECCZJ.jgcc }</td>
				<td colspan="2"><div align="center">危险性较大工程</div></td>
				<td width="171"><div align="center">
						<input id="wxxjd" type="radio" name="wxxjd" value="0" readonly="true"
						 <c:if test="${TBAECCZJ.wxxjd=='1'}">checked="checked"</c:if>
						 />是 
						 <input id="wxxjd" type="radio" name="wxxjd" value="1" readonly="true"
						 <c:if test="${TBAECCZJ.wxxjd=='0'}">checked="checked"</c:if>
						 />否
					</div></td>
			</tr>
			<tr>
				<td><div align="center">周边环境</div></td>
				<td colspan="3">
					<div align="center">
						<input id="ljgyx" type="checkbox" name="ljgyx" value="1" disabled="true"
						<c:if test="${TBAECCZJ.ljgyx=='1'}">checked="checked"</c:if>
						/>临近高压线 
						<input id="ljjmq" type="checkbox" name="ljjmq" value="1" disabled="true"
						<c:if test="${TBAECCZJ.ljjmq=='1'}">checked="checked"</c:if>
						/>临近居民区商业区
						 <input id="zbqt" type="checkbox" name="zbqt" value="1" disabled="true"
						 <c:if test="${TBAECCZJ.zbqt=='1'}">checked="checked"</c:if>
						 />其他
					</div>
				</td>
				<td colspan="2"><div align="center">拟采用的拆除方法</div></td>
				<td><div align="center">
						<input id="jx" type="checkbox" name="jx" value="1" disabled="true"
						<c:if test="${TBAECCZJ.jx=='1'}">checked="checked"</c:if>/>机械 
						
						<input id="bp" type="checkbox" name="bp" value="1" disabled="true"
						<c:if test="${TBAECCZJ.bp=='1'}">checked="checked"</c:if>/>爆破
						
					</div></td>
			</tr>
			<tr>
				<td><div align="center">计划开工日期</div></td>
				<td colspan="3">${TBAECCZJ.jhkgrq }</td>
				<td colspan="2"><div align="center">计划竣工日期</div></td>
				<td>${TBAECCZJ.jhjgrq }</td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">建设单位</div></td>
				<td><div align="center">单位名称</div></td>
				<td colspan="3">${TBAECCZJ.tBaeCczjdw.jsdwmc }</td>
				<td><div align="center">属地</div></td>
				<td colspan="2">${TBAECCZJ.tBaeCczjdw.jssd }</td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td colspan="3">${TBAECCZJ.tBaeCczjdw.jsxmfzr }</td>
				<td><div align="center">联系电话</div></td>
				<td colspan="2">${TBAECCZJ.tBaeCczjdw.jslxdh }</td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">监理单位</div></td>
				<td align="center">单位名称</td>
				<td colspan="3">${TBAECCZJ.tBaeCczjdw.jldwmc }</td>
				<td colspan="2"><div align="center">资质等级及证书</div></td>
				<td>${TBAECCZJ.tBaeCczjdw.jldjzs }</td>
			</tr>
			<tr>
				<td align="center">项目总监</td>
				<td>${TBAECCZJ.tBaeCczjdw.jlxmzj }</td>
				<td><div align="center">职业资格及证书</div></td>
				<td>${TBAECCZJ.tBaeCczjdw.jlzgzs }</td>
				<td width="100"><div align="center">联系电话</div></td>
				<td colspan="2">${TBAECCZJ.tBaeCczjdw.jllxdh }</td>
			</tr>
			<tr>
				<td rowspan="5"><div align="center">拆除单位</div></td>
				<td rowspan="2"><div align="center">单位名称</div></td>
				<td colspan="3" rowspan="2">${TBAECCZJ.tBaeCczjdw.ccdwmc }</td>
				<td colspan="2"><div align="center">资质等级或备案</div></td>
				<td>${TBAECCZJ.tBaeCczjdw.cczzdj }</td>
			</tr>
			<tr>
				<td height="25" colspan="2"><div align="center">安全生产许可证号</div></td>
				<td>${TBAECCZJ.tBaeCczjdw.ccaqxkzh }</td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td colspan="3">${TBAECCZJ.tBaeCczjdw.ccxmfrz }</td>
				<td><div align="center">岗位证书</div></td>
				<td colspan="2">${TBAECCZJ.tBaeCczjdw.ccgwzs }</td>
			</tr>
			<tr>
				<td><div align="center">安全考核号</div></td>
				<td colspan="3">${TBAECCZJ.tBaeCczjdw.ccaqkhzh }</td>
				<td><div align="center">联系电话</div></td>
				<td colspan="2">${TBAECCZJ.tBaeCczjdw.cclxdh }</td>
			</tr>
			<tr>
				<td height="57" colspan="7"><p>
						&nbsp;&nbsp;现场专职安全员：${xcaqyArr[0] }
						&nbsp;&nbsp;&nbsp;安全考核证号：${khzhArr[0] }
					</p>
					<p>
						&nbsp;&nbsp;现场专职安全员：${xcaqyArr[1] }
						&nbsp;&nbsp;&nbsp;安全考核证号：${khzhArr[1] }
					</p></td>
			</tr>
			<tr>
				<td height="95"><div align="center">报监资料</div></td>
				<td colspan="7"><p>
						<input id="ccdwzzzs" type="checkbox" name="ccdwzzzs" value="1" disabled="true"
						<c:if test="${TBAECCZJ.tBaeCczjdw.ccdwzzzs=='1'}">checked="checked"</c:if>
						/>拆除单位资质证书(或登记备案证书)及复印件
					</p>
					<p>
						<input id="fwccwtht" type="checkbox" name="fwccwtht" value="1" disabled="true"
						<c:if test="${TBAECCZJ.tBaeCczjdw.fwccwtht=='1'}">checked="checked"</c:if>
						/>房屋拆除委托合同
					</p>
					<p>
						<input id="bzjjlpz" type="checkbox" name="bzjjlpz" value="1" disabled="true"
						<c:if test="${TBAECCZJ.tBaeCczjdw.bzjjlpz=='1'}">checked="checked"</c:if>
						/>保证金缴纳凭证
					</p></td>
			</tr>
			<tr>
				<td colspan="5"><div align="center">审查依据</div></td>
				<td colspan="3"><div align="center">审查意见</div></td>
			</tr>
			<tr>
				<td height="130" colspan="5">&nbsp;1、《建设工程安全生产管理条例》（国务院第393号令<br />
					&nbsp;2、《关于印发&lt;关于进一步明确全市建筑拆除施工安全生产职责的意见&gt;的通知》（合安办[2009]41号）<br />
					&nbsp;3、《关于进一步较强庐阳区建设领域安全生产工程的通知》（庐安[2013]3号）<br />
					&nbsp;4、《关于进一步加强庐阳区拆除施工安全管理工作的通知》（庐住建[2014]1号）<br /></td>
				<td colspan="3"><p>
						&nbsp;符&nbsp;合&nbsp;&nbsp;
						<input id="scyj" type="radio" name="scyj" value="0" disabled="disabled"
						<c:if test="${TBAECCZJ.tBaeCczjdw.scyj=='0'}">checked="checked"</c:if>
						/>
					</p>
					<p>
						&nbsp;不符合
						<input id="scyj" type="radio" name="scyj" value="1" disabled="disabled"
						<c:if test="${TBAECCZJ.tBaeCczjdw.scyj=='1'}">checked="checked"</c:if>
						/>
					</p>
					<p>
						&nbsp;审查人：${TBAECCZJ.tBaeCczjdw.scr }
						
					</p>
					<p align="right" style="margin-right: 50px">
						日期：${TBAECCZJ.tBaeCczjdw.scrq }
					</p>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="2"><div align="center">监督机构</div></td>
				<td colspan="3"><div align="center">庐阳区建设工程质量安全监督站</div></td>
				<td rowspan="3"><div align="center">报监意见</div></td>
				<td colspan="2" rowspan="3">
					<div align="left">${TBAEGCZJ.tBaeGczjdw.bjyj}</div>
							<div align="right" style="margin-top:10px">
							日&nbsp;&nbsp;期:${TBAEGCZJ.tBaeGczjdw.bjrq}&nbsp;（公&nbsp;&nbsp;章）
					   </div>
				</td>
			</tr>
			<tr>
				<td height="4" colspan="2"><div align="center">联系电话</div></td>
				<td colspan="3"><div align="center">0551-65850756</div></td>
			</tr>
			<tr>
				<td height="8" colspan="2"><div align="center">监督注册号</div></td>
				<td colspan="3" align="center">
				${TBAECCZJ.tBaeCczjdw.jdzch }</td>
			</tr>
		</table>
		<input name="id" id="id" type="hidden" value="${TBAECCZJ.id }"/>
		<input name="bz" id="bz" type="hidden" />
		<input name="cjr" id="cjr" type="hidden" />
		<input name="cjsj" id="cjsj" type="hidden" />
		<input name="xmlx" id="xmlx" type="hidden" value="${TBAECCZJ.xmlx}"/>
		<input name="sjzt" id="sjzt" type="hidden" />
		<p align="center">本表一式四份，由建设单位填写，监督报监办理完毕后，返还建设单位一份，报区安监局备案一份。</p>
	</div>
</form>
<script>
//保存数据
function saveGcxmChc(_diagid, _grid) {
	var sjzt = $('#cCFormAdd #sjzt').val();
	if(sjzt == 2 || sjzt == 4) {
		$.messager.alert('警告', '系统拒绝访问：当前状态错误！！！！！！', 'warning');
		return;
	}
	var data = $('#cCFormAdd').serialize();
	$.ajax({
		url: "<c:url value='/xmgl/ccgl/saveCc.do'/>",
		type: 'POST',
		data: data,
		beforeSend: function(XmlHttpRequest, textStatus, errorThrown) {
			$.messager.progress({
				title: '提示',
				msg: '正在执行数据操作，请稍待...'
			});
		},
		error: function(XmlHttpRequest, textStatus, errorThrown) {
			ZENG.msgbox.show('服务器繁忙或无法连接，请求通讯失败！', 1);
		    $.messager.progress('close');
		},
		success: function(result) {
		    $.messager.progress('close');
			if(!result.success) {
				show_err_msg(result.data);
			}
			else {
				$('#'+_diagid).dialog('close');
				$('#'+_grid).datagrid('clearSelections').datagrid('reload');
				ZENG.msgbox.show(result.data, 4, 1500);
			}
		}
	});
	
}

	function wait() {
		alert('功能未完成！');
	}

	function geng() {
		alert('功能未完成！');
	}
</script>