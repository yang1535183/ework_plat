<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="PPPRINTDISPLAY"
	style="display: none; font-size: 16px; color: red">
	<p>
		快去安装打印控件！<a href="#" onclick="wait()">点击我下载</a> <a href="#" onclick="geng()">点击我刷新</a>
	</p>
</div>
<form id="ppgxaddanchccheckform" method="post">
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
				<td colspan="3">
				<input id="gcmc" class="easyui-tooltip" position="top" type="text" name="gcmc" value="${TBAECCZJ.gcmc }" /></td>
				<td width="100"><div align="center">工程地点</div></td>
				<td colspan="2">
				<input id="gcdd" class="easyui-tooltip" position="top" type="text" name="gcdd" style="width: 170px;" value="${TBAECCZJ.gcdd }"/></td>
			</tr>
			<tr>
				<td><div align="center">拆除面积</div></td>
				<td width="80">
				<input id="ccmj" type="text" name="ccmj" class="easyui-tooltip" position="top"
					style="width: 80px;" value="${TBAECCZJ.ccmj }"/>M²</td>
				<td width="100"><div align="center">结构层次</div></td>
				<td width="100">
				<input id="jgcc" type="text" name="jgcc" class="easyui-tooltip" position="top"
					style="width: 97px;" value="${TBAECCZJ.jgcc }"/></td>
				<td colspan="2"><div align="center">危险性较大工程</div></td>
				<td width="171"><div align="center">
						<input id="wxxjd" type="radio" name="wxxjd" value="1"
						 <c:if test="${TBAECCZJ.wxxjd=='1'}">checked="checked"</c:if>/>是 
						 
						 <input id="wxxjd" type="radio" name="wxxjd" value="0"
						 <c:if test="${TBAECCZJ.wxxjd=='0'}">checked="checked"</c:if>/>否
						 
					</div></td>
			</tr>
			<tr>
				<td><div align="center">周边环境</div></td>
				<td colspan="3">
					<div align="center">
						<input id="ljgyx" type="checkbox" name="ljgyx" value="1"
						<c:if test="${TBAECCZJ.ljgyx=='1'}">checked="checked"</c:if>
						/>临近高压线 
						<input id="ljjmq" type="checkbox" name="ljjmq" value="1"
						<c:if test="${TBAECCZJ.ljjmq=='1'}">checked="checked"</c:if>
						/>临近居民区商业区
						 <input id="zbqt" type="checkbox" name="zbqt" value="1"
						 <c:if test="${TBAECCZJ.zbqt=='1'}">checked="checked"</c:if>
						 />其他
					</div>
				</td>
				<td colspan="2"><div align="center">拟采用的拆除方法</div></td>
				<td><div align="center">
						<input id="jx" type="checkbox" name="jx" value="1"
						<c:if test="${TBAECCZJ.jx=='1'}">checked="checked"</c:if>/>机械 
						
						<input id="bp" type="checkbox" name="bp" value="1"
						<c:if test="${TBAECCZJ.bp=='1'}">checked="checked"</c:if>/>爆破
						
					</div></td>
			</tr>
			<tr>
				<td><div align="center">计划开工日期</div></td>
				<td colspan="3">
					<input id="jhkgrq" type="text" name="jhkgrq" class="easyui-datebox" style="width: 120px;" value="${TBAECCZJ.jhkgrq }" editable="false"/>
					<span id="jhkgrqdivtooltip" name="jhkgrqdivtooltip" class="easyui-tooltip" position="top"></span>
				</td>
				<td colspan="2"><div align="center">计划竣工日期</div></td>
				<td>
					<input id="jhjgrq" type="text" name="jhjgrq" class="easyui-datebox" 
					style="width: 120px;" value="${TBAECCZJ.jhjgrq }" editable="false"/>
					<span id="jhjgrqdivtooltip" name="jhjgrqdivtooltip" class="easyui-tooltip" position="top"></span>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">建设单位</div></td>
				<td><div align="center">单位名称</div></td>
				<td colspan="3">
				<input id="jsdwmc" class="easyui-tooltip" position="top" type="text" name="jsdwmc" value="${TBAECCZJ.tBaeCczjdw.jsdwmc }"/></td>
				<td><div align="center">属地</div></td>
				<td colspan="2">
				<input id="jssd" class="easyui-tooltip" position="top" type="text" name="jssd" value="${TBAECCZJ.tBaeCczjdw.jssd }"/></td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td colspan="3">
				<input id="jsxmfzr" class="easyui-tooltip" position="top" type="text" name="jsxmfzr" value="${TBAECCZJ.tBaeCczjdw.jsxmfzr }"/></td>
				<td><div align="center">联系电话</div></td>
				<td colspan="2">
				<input id="jslxdh" class="easyui-tooltip" position="top" type="text" name="jslxdh" value="${TBAECCZJ.tBaeCczjdw.jslxdh }"/></td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">监理单位</div></td>
				<td align="center">单位名称</td>
				<td colspan="3">
				<input id="jldwmc" class="easyui-tooltip" position="top" type="text" name="jldwmc" value="${TBAECCZJ.tBaeCczjdw.jldwmc }"/></td>
				<td colspan="2"><div align="center">资质等级及证书</div></td>
				<td>
				<input id="jldjzs" class="easyui-tooltip" position="top" type="text" name="jldjzs" value="${TBAECCZJ.tBaeCczjdw.jldjzs }"/></td>
			</tr>
			<tr>
				<td align="center">项目总监</td>
				<td><input id="jlxmzj" type="text" name="jlxmzj" class="easyui-tooltip" position="top"
					style="width: 97px;" value="${TBAECCZJ.tBaeCczjdw.jlxmzj }"/></td>
				<td><div align="center">职业资格及证书</div></td>
				<td><input id="jlzgzs" type="text" name="jlzgzs" class="easyui-tooltip" position="top"
					style="width: 97px;" value="${TBAECCZJ.tBaeCczjdw.jlzgzs }"/></td>
				<td width="100"><div align="center">联系电话</div></td>
				<td colspan="2">
				<input id="jllxdh" class="easyui-tooltip" position="top" type="text" name="jllxdh" value="${TBAECCZJ.tBaeCczjdw.jllxdh }"/></td>
			</tr>
			<tr>
				<td rowspan="5"><div align="center">拆除单位</div></td>
				<td rowspan="2"><div align="center">单位名称</div></td>
				<td colspan="3" rowspan="2">
				<input id="ccdwmc" type="text" class="easyui-tooltip" position="top"
					name="ccdwmc" value="${TBAECCZJ.tBaeCczjdw.ccdwmc }"/></td>
				<td colspan="2"><div align="center">资质等级或备案</div></td>
				<td>
				<input id="cczzdj" class="easyui-tooltip" position="top" type="text" name="cczzdj" value="${TBAECCZJ.tBaeCczjdw.cczzdj }"/></td>
			</tr>
			<tr>
				<td height="25" colspan="2"><div align="center">安全生产许可证号</div></td>
				<td><input id="ccaqxkzh" class="easyui-tooltip" position="top" type="text" name="ccaqxkzh" value="${TBAECCZJ.tBaeCczjdw.ccaqxkzh }"/></td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td colspan="3">
				<input id="ccxmfrz" class="easyui-tooltip" position="top" type="text" name="ccxmfrz" value="${TBAECCZJ.tBaeCczjdw.ccxmfrz }"/></td>
				<td><div align="center">岗位证书</div></td>
				<td colspan="2">
				<input id="ccgwzs" class="easyui-tooltip" position="top" type="text" name="ccgwzs" value="${TBAECCZJ.tBaeCczjdw.ccgwzs }"/></td>
			</tr>
			<tr>
				<td><div align="center">安全考核号</div></td>
				<td colspan="3">
				<input id="ccaqkhzh" type="text" class="easyui-tooltip" position="top"
					name="ccaqkhzh" value="${TBAECCZJ.tBaeCczjdw.ccaqkhzh }"/></td>
				<td><div align="center">联系电话</div></td>
				<td colspan="2">
				<input id="cclxdh" class="easyui-tooltip" position="top" type="text" name="cclxdh" value="${TBAECCZJ.tBaeCczjdw.cclxdh }"/></td>
			</tr>
			<tr>
				<td height="57" colspan="7"><p>
						&nbsp;&nbsp;现场专职安全员：
						<input id="ccxcaqy" class="easyui-tooltip" position="top" type="text" name="ccxcaqy" value="${xcaqyArr[0] }"/>&nbsp;&nbsp;&nbsp;
						安全考核证号：<input id="aqkhzh" class="easyui-tooltip" position="top" type="text" name="aqkhzh" value="${khzhArr[0] }"/>
					</p>
					<p>
						&nbsp;&nbsp;现场专职安全员：<input id="ccxcaqy" class="easyui-tooltip" position="top" type="text" name="ccxcaqy" value="${xcaqyArr[1] }"/>&nbsp;&nbsp;&nbsp;
						安全考核证号：<input id="aqkhzh" class="easyui-tooltip" position="top" type="text" name="aqkhzh" value="${khzhArr[1] }"/>
					</p></td>
			</tr>
			<tr>
				<td height="95"><div align="center">报监资料</div></td>
				<td colspan="7"><p>
						<input id="ccdwzzzs" type="checkbox" name="ccdwzzzs" value="1"
						<c:if test="${TBAECCZJ.tBaeCczjdw.ccdwzzzs=='1'}">checked="checked"</c:if>
						/>拆除单位资质证书(或登记备案证书)及复印件
					</p>
					<p>
						<input id="fwccwtht" type="checkbox" name="fwccwtht" value="1"
						<c:if test="${TBAECCZJ.tBaeCczjdw.fwccwtht=='1'}">checked="checked"</c:if>
						/>房屋拆除委托合同
					</p>
					<p>
						<input id="bzjjlpz" type="checkbox" name="bzjjlpz" value="1"
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
						<input id="scyj" type="radio" name="scyj" value="0" readonly="true"
						<c:if test="${TBAECCZJ.tBaeCczjdw.scyj=='0'}">checked="checked"</c:if>
						/>
					</p>
					<p>
						&nbsp;不符合
						<input id="scyj" type="radio" name="scyj" value="1" readonly="true"
						<c:if test="${TBAECCZJ.tBaeCczjdw.scyj=='1'}">checked="checked"</c:if>
						/>
					</p>
					<p>
						&nbsp;审查人：<input id="scr" type="text" name="scr" value="${TBAECCZJ.tBaeCczjdw.scr }" readonly="true"
						/>
					</p>
					<p align="right" style="margin-right: 50px">
						日期：<input id="scrq" type="text" name="scrq" class="easyui-datebox"
							style="width: 100px;" value="${TBAECCZJ.tBaeCczjdw.scrq }" />
					</p>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="2"><div align="center">监督机构</div></td>
				<td colspan="3"><div align="center">庐阳区建设工程质量安全监督站</div></td>
				<td rowspan="3"><div align="center">报监意见</div></td>
				<td colspan="2" rowspan="3">
					<div align="left">
						<input id="bjyj" type="text" name="bjyj" readonly="true"
							style="width: 320px; height: 30px;align=" right"" value="${TBAECCZJ.tBaeCczjdw.bjyj }"/>
					</div>
					<div align="right" style="margin-top: 10px">
						日期：<input id="bjrq" type="text" name="bjrq" class="easyui-datebox"
							style="width: 100px;" value="${TBAECCZJ.tBaeCczjdw.bjrq }" />（公章）
					</div>&nbsp;
				</td>
			</tr>
			<tr>
				<td height="4" colspan="2"><div align="center">联系电话</div></td>
				<td colspan="3"><div align="center">0551-65850756</div></td>
			</tr>
			<tr>
				<td height="8" colspan="2"><div align="center">监督注册号</div></td>
				<td colspan="3" align="center">
				<input id="jdzch" class="easyui-tooltip" position="top" type="text" name="jdzch" value="${TBAECCZJ.tBaeCczjdw.jdzch }"/></td>
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
	var sjzt = $('#ppgxaddanchccheckform #sjzt').val();
	if(sjzt == 2 || sjzt == 4) {
		$.messager.alert('警告', '系统拒绝访问：当前状态错误！！！！！！', 'warning');
		return;
	}
	var data = $('#ppgxaddanchccheckform').serialize();
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
		window.location.reload();
	}
    
    $(function(){
		$("#scrq").datebox({
		    disabled: true    
		});
		$("#bjrq").datebox({    
		    disabled: true    
		});
		if('${sjzt}' == '3') {
			var chek = ${checkdxsc};
			for(var i=0; i<chek.length; i++) {
				if(chek[i].sclm+"divtooltip" == "jhkgrqdivtooltip") {
					document.getElementById("jhkgrqdivtooltip").innerHTML+='<a href="javascript:void(0);"><span class="icon-cross">&nbsp;</span></a>';
					$('#ppgxaddanchccheckform #jhkgrqdivtooltip').attr('title', "工程名称打回审查意见："+((chek[i].scyj==null)?'未填写。':chek[i].scyj));
				}
				else if(chek[i].sclm+"divtooltip" == "jhjgrqdivtooltip") {
					document.getElementById("jhjgrqdivtooltip").innerHTML+='<a href="javascript:void(0);"><span class="icon-cross">&nbsp;</span></a>';
					$('#ppgxaddanchccheckform #jhjgrqdivtooltip').attr('title', "工程名称打回审查意见："+((chek[i].scyj==null)?'未填写。':chek[i].scyj));
				}
				else {
					$('#ppgxaddanchccheckform #'+chek[i].sclm).css('backgroundColor', 'red');
					$('#ppgxaddanchccheckform #'+chek[i].sclm).attr('title', "工程名称打回审查意见："+((chek[i].scyj==null)?'未填写。':chek[i].scyj));
				}
			}
		}
	})
</script>