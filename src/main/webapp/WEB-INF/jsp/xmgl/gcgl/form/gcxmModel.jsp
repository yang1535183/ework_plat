<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h2 align="center">合肥市建设工程质量安全监督报表</h2>
<div id="PPPRINTDISPLAY" style="display:none;font-size:16px;color:red" >
	<p>快去安装打印控件！<a href="#" onclick="wait()">点击我下载</a>
	<a href="#" onclick="geng()">点击我刷新</a></p>
</div>
<form id="ppgxaddandmmodelform" method="post">
	<div style="height: 96%; width: 990px; overflow: auto">
		<table border="1" style="border-collapse: collapse; bordercolor: black">
			<tr>
				<td width="148" rowspan="8"><div align="center">工程概况</div></td>
				<td width="136">
					<div align="center">工程名称</div>
				</td>
				<td width="158">
					<input id="gcmc" type="text" name="gcmc" value="${TBAEGCZJ.gcmc}"/></td>
				<td width="113"><div align="center">工程地点</div></td>
				<td colspan="4">
					<input type="text" id="qu" name="qu" value="${TBAEGCZJ.qu}" style="width: 132px;"/>区&nbsp;&nbsp;
					<input type="text" id="lu" name="lu" style="width: 132px;" value="${TBAEGCZJ.lu}" />路&nbsp;&nbsp;
					<input type="text" id="hao" name="hao" style="width: 132px;" value="${TBAEGCZJ.hao}"/>号
				</td>
			</tr>
			<tr>
				<td><div align="center">投资类别</div></td>
				<td colspan="4">
					<div align="center">
					<input type="radio" id="tzlb" name="tzlb" value="1" 
							<c:if test="${TBAEGCZJ.tzlb=='1'}">checked="checked"</c:if>/>1.外资 
							<input type="radio" id="tzlb" name="tzlb" value="2" 
							<c:if test="${TBAEGCZJ.tzlb=='2'}">checked="checked"</c:if>/>2.合资
							<input type="radio" id="tzlb" name="tzlb" value="3" 
							<c:if test="${TBAEGCZJ.tzlb=='3'}">checked="checked"</c:if>/>3.国有 
							<input type="radio" id="tzlb" name="tzlb" value="4" 
							<c:if test="${TBAEGCZJ.tzlb=='4'}">checked="checked"</c:if>/>4.集体
							<input type="radio" id="tzlb" name="tzlb" value="5"
							<c:if test="${TBAEGCZJ.tzlb=='5'}">checked="checked"</c:if>/> 5.民营
					</div>
				</td>
				<td width="101"> <div align="center">预算造价</div></td>
				<td width="123"><input id="yszj" type="text" name="yszj" style="width: 116px;" value="${TBAEGCZJ.yszj}"/>万元</td>
			</tr>
			<tr>
				<td><div align="center">建筑面积</div></td>
				<td><input id="jzmj" type="text" name="jzmj" style="width: 120px;" value="${TBAEGCZJ.jzmj}"/>M²</td>
				<td align="center">结构类型/层次</td>
				<td colspan="2"><input id="jgcc" type="text" name="jgcc" value="${TBAEGCZJ.jgcc}"/></td>
				<td><div align="center">每平方米造价</div></td>
				<td><input id="pmzj" type="text" name="pmzj" style="width: 116px;" value="${TBAEGCZJ.pmzj}"/>元</td>
			</tr>
			<tr>
				<td height="28"><div align="center">基础类型</div></td>
				<td><input id="jclx" type="text" name="jclx" value="${TBAEGCZJ.jclx}"/></td>
				<td align="center">工程类别</td>
				<td colspan="4">
					<div align="center">
						<input id="gclb" type="radio" name="gclb" value="1"
							<c:if test="${TBAEGCZJ.gclb=='1'}">checked="checked"</c:if> />1.住宅 
							<input id="gclb" type="radio" name="gclb" value="2"
							<c:if test="${TBAEGCZJ.gclb=='2'}">checked="checked"</c:if> />2.公建 
							<input id="gclb" type="radio" name="gclb" value="3" 
							<c:if test="${TBAEGCZJ.gclb=='3'}">checked="checked"</c:if>/>3.厂房 
							<input id="gclb" type="radio" name="gclb" value="4" 
							<c:if test="${TBAEGCZJ.gclb=='4'}">checked="checked"</c:if>/>4.其他
					</div>
				</td>
			</tr>
			<tr>
				<td>建筑节能执行标准</td>
				<td colspan="6">
					<div align="center">
						<input id="jnbz" type="radio" name="jnbz" value="1" 
							<c:if test="${TBAEGCZJ.jnbz=='1'}">checked="checked"</c:if>/>50% 
							<input id="jnbz" type="radio" name="jnbz" value="2" 
							<c:if test="${TBAEGCZJ.jnbz=='2'}">checked="checked"</c:if>/>65% 
							<input id="jnbz" type="radio" name="jnbz" value="3" 
							<c:if test="${TBAEGCZJ.jnbz=='3'}">checked="checked"</c:if>/>其他
					</div>
				</td>
			</tr>
			<tr>
				<td><div align="center">绿色建筑</div></td>
				<td colspan="2"><input id="lsjz" type="text" name="lsjz" value="${TBAEGCZJ.lsjz}"/>M²</td>
				<td colspan="4">
					<div align="center">
					<input id="xj" type="radio" name="xj" value="1"
							<c:if test="${TBAEGCZJ.xj=='1'}">checked="checked" </c:if>/>一星
							<input id="xj" type="radio" name="xj" value="2" 
							<c:if test="${TBAEGCZJ.xj=='2'}">checked="checked"</c:if>/>二星 
							<input id="xj" type="radio" name="xj" value="3" 
							<c:if test="${TBAEGCZJ.xj=='3'}">checked="checked"</c:if>/>三星
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><div align="center">可再生能源建筑应用/太阳能光热</div></td>
				<td>
					<input id="tyngr" type="text" name="tyngr" style="width: 80px;" value="${TBAEGCZJ.tyngr}"/>M²
				</td>
				<td width="135">
					<div align="center">太阳能光伏</div>
				</td>
				<td width="101">
					<input id="tyngf" type="text" name="tyngf" style="width: 70px;" value="${TBAEGCZJ.tyngf}"/>kw
				</td>
				<td><div align="center">地源热汞</div></td>
				<td>
					<input id="dyrb" type="text" name="dyrb" style="width: 115px;" value="${TBAEGCZJ.dyrb}"/>M²
				</td>
			</tr>
			<tr>
				<td><div align="center">计划开工日期</div></td>
				<td colspan="2">
					<input id="kgsj" type="text" name="kgsj" class="easyui-datebox" style="width: 100px;" value="${TBAEGCZJ.kgsj}" editable="false"/></td>
				<td>
					<div align="center">计划竣工日期</div>
				</td>
				<td colspan="3">
					<input id="jgsj" type="text" name="jgsj" class="easyui-datebox" style="width: 100px;" value="${TBAEGCZJ.jgsj}" editable="false"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">危险性较大工程</div>
				</td>
				<td colspan="3">
					<p>
						<input id="sjc" type="checkbox" name="sjc" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.sjc=='1'}">checked</c:if> />深基坑
						<input id="gdmb" type="checkbox" name="gdmb" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.gdmb=='1'}">checked</c:if>/>高大模板 
						<input id="gkzy" type="checkbox" name="gkzy" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.gkzy=='1'}">checked</c:if>/>30M以上高空作业<br />
						<input id="pj" type="checkbox" name="pj" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.pj=='1'}">checked</c:if>/>爬架 
						<input id="rhdt" type="checkbox" name="rhdt" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.rhdt=='1'}">checked</c:if>/>人货电梯 
						<input id="td" type="checkbox" name="td" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.td=='1'}">checked</c:if>/>塔吊 
						<input id="wxqt" type="checkbox" name="wxqt" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.wxqt=='1'}">checked</c:if>/>其他
					</p>
				</td>
				<td><div align="center">周边环境</div></td>
				<td colspan="3">
					<p>
						<input id="ljgyx" type="checkbox" name="ljgyx"  value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.ljgyx=='1'}">checked="checked"</c:if>/>临近高压线<br />
						<input id="ljjmq" type="checkbox" name="ljjmq" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.ljjmq=='1'}">checked="checked"</c:if> />临近居民区,商业区
						<input id="zbqt" type="checkbox" name="zbqt" value="1"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.zbqt=='1'}">checked="checked"</c:if>/>其他
						</p>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">建设单位</div></td>
				<td><div align="center">单位名称</div></td>
				<td colspan="2"><input id="jsdwmc" type="text" name="jsdwmc" value="${TBAEGCZJ.tBaeGczjdw.jsdwmc}"/></td>
				<td><div align="center">资质等级及证书</div></td>
				<td colspan="3"><input id="jsdjzs" type="text" name="jsdjzs" value="${TBAEGCZJ.tBaeGczjdw.jsdjzs}"/></td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td colspan="2"><input id="jsxmfzr" type="text" name="jsxmfzr" value="${TBAEGCZJ.tBaeGczjdw.jsxmfzr}"/></td>
				<td><div align="center">联系电话</div></td>
				<td colspan="3"><input id="jslxdh" type="text" name="jslxdh" value="${TBAEGCZJ.tBaeGczjdw.jslxdh}"/></td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">勘察单位</div></td>
				<td><div align="center">单位名称</div></td>
				<td colspan="2"><input id="kcdwmc" type="text" name="kcdwmc" value="${TBAEGCZJ.tBaeGczjdw.kcdwmc}"/></td>
				<td><div align="center">资质等级及证书</div></td>
				<td colspan="3"><input id="kcdjzs" type="text" name="kcdjzs" value="${TBAEGCZJ.tBaeGczjdw.kcdjzs}"/></td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td><input id="kcxmfzr" type="text" name="kcxmfzr" value="${TBAEGCZJ.tBaeGczjdw.kcxmfzr}"/></td>
				<td align="center">执业资格及证书</td>
				<td colspan="2"><input id="kczgzs" type="text" name="kczgzs" value="${TBAEGCZJ.tBaeGczjdw.kczgzs}"/></td>
				<td><div align="center">联系电话</div></td>
				<td><input id="kclxdh" type="text" name="kclxdh" value="${TBAEGCZJ.tBaeGczjdw.kclxdh}"/></td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">设计单位</div></td>
				<td><div align="center">单位名称</div></td>
				<td colspan="2"><input id="sjdwmc" type="text" name="sjdwmc" value="${TBAEGCZJ.tBaeGczjdw.sjdwmc}"/></td>
				<td><div align="center">资质等级及证书</div></td>
				<td colspan="3"><input id="sjdjzs" type="text" name="sjdjzs" value="${TBAEGCZJ.tBaeGczjdw.sjdjzs}"/></td>
			</tr>
			<tr>
				<td><div align="center">项目负责人</div></td>
				<td><input id="sjxmfrz" type="text" name="sjxmfrz" value="${TBAEGCZJ.tBaeGczjdw.sjxmfrz}"/></td>
				<td align="center">执业资格及证书</td>
				<td colspan="2"><input id="sjzgzs" type="text" name="sjzgzs" value="${TBAEGCZJ.tBaeGczjdw.sjzgzs}"/></td>
				<td><div align="center">联系电话</div></td>
				<td><input id="sjlxdh" type="text" name="sjlxdh" value="${TBAEGCZJ.tBaeGczjdw.sjlxdh}"/></td>
			</tr>
			<tr>
				<td rowspan="2"><div align="center">监理单位</div></td>
				<td><div align="center">单位名称</div></td>
				<td colspan="2"><input id="jldwmc" type="text" name="jldwmc" value="${TBAEGCZJ.tBaeGczjdw.jldwmc}"/></td>
				<td><div align="center">资质等级及证书</div></td>
				<td colspan="3"><input id="jldjzs" type="text" name="jldjzs" value="${TBAEGCZJ.tBaeGczjdw.jldjzs}"/></td>
			</tr>
			<tr>
				<td><div align="center">项目总监</div></td>
				<td><input id="jlxmfzr" type="text" name="jlxmfzr" value="${TBAEGCZJ.tBaeGczjdw.jlxmfzr}"/></td>
				<td align="center">执业资格及证书</td>
				<td colspan="2"><input id="jlzgzs" type="text" name="jlzgzs" value="${TBAEGCZJ.tBaeGczjdw.jlzgzs}"/></td>
				<td><div align="center">联系电话</div></td>
				<td><input id="jllxdh" type="text" name="jllxdh" value="${TBAEGCZJ.tBaeGczjdw.jllxdh}"/></td>
			</tr>
			<tr>
				<td rowspan="5"><div align="center">施工单位</div></td>
				<td rowspan="2"><div align="center">单位名称</div></td>
				<td colspan="2" rowspan="2"><input id="sgdwmc" type="text" name="sgdwmc" value="${TBAEGCZJ.tBaeGczjdw.sgdwmc}"/></td>
				<td><div align="center">资质等级及证书</div></td>
				<td colspan="3"><input id="sgdjzs" type="text" name="sgdjzs" value="${TBAEGCZJ.tBaeGczjdw.sgdjzs}"/></td>
			</tr>
			<tr>
				<td><div align="center">安全生产许可证</div></td>
				<td colspan="3"><input id="sgaqxkz" type="text" name="sgaqxkz" value="${TBAEGCZJ.tBaeGczjdw.sgaqxkz}"/></td>
			</tr>
			<tr>
				<td><div align="center">项目经理</div></td>
				<td colspan="2"><input id="sgxmjl" type="text" name="sgxmjl" value="${TBAEGCZJ.tBaeGczjdw.sgxmjl}"/></td>
				<td><div align="center">岗位证书</div></td>
				<td colspan="3"><input id="sggwzs" type="text" name="sggwzs" value="${TBAEGCZJ.tBaeGczjdw.sggwzs}"/></td>
			</tr>
			<tr>
				<td><div align="center">安全考核证号</div></td>
				<td colspan="2"><input id="sgkhzh" type="text" name="sgkhzh" value="${TBAEGCZJ.tBaeGczjdw.sgkhzh}"/></td>
				<td><div align="center">联系电话</div></td>
				<td colspan="3"><input id="sglxdh" type="text" name="sglxdh" value="${TBAEGCZJ.tBaeGczjdw.sglxdh}"/></td>
			</tr>
			<tr>
				<td height="101" colspan="7">
					<p align="left">
							工地现场专职安全员:
						<input id="sgxcaqy" type="text" name="sgxcaqy" class="xczzaqy1" value="${SGXCAQY0}"/>
							&nbsp;&nbsp;安全考核证号:
						<input id="sgaqyzs" type="text" name="sgaqyzs" class="aqkhzh1" value="${SGAQYZS0}"/>（1万M2以内至少一人）<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现场专职安全员:
						<input id="sgxcaqy" type="text" name="sgxcaqy" class="xczzaqy1" value="${SGXCAQY1}"/>
							&nbsp;&nbsp;安全考核证号:
						<input id="sgaqyzs" type="text" name="sgaqyzs" class="aqkhzh1" value="${SGAQYZS1}"/>（1-5万M²以内至少二人）<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现场专职安全员:
						<input id="sgxcaqy" type="text" name="sgxcaqy" class="xczzaqy1" value="${SGXCAQY2}"/>
							&nbsp;&nbsp;安全考核证号:
						<input id="sgaqyzs" type="text" name="sgaqyzs" class="aqkhzh1" value="${SGAQYZS2}"/>（5万M²以内至少三人）
					</p>
				</td>
			</tr>
			<tr>
				<td><div align="center">施工图审查单位</div></td>
				<td colspan="7"><input id="sgtscdw" type="text" name="sgtscdw" style="width: 200px" value="${TBAEGCZJ.tBaeGczjdw.sgtscdw}"/></td>
			</tr>
			<tr>
				<td><div align="center">工伤保险征收部门</div></td>
				<td colspan="2">
					<input id="gsbxbm" type="text" name="gsbxbm" value="${TBAEGCZJ.tBaeGczjdw.gsbxbm}"/></td>
				<td>
					<div align="center">社保保单号</div>
				</td>
				<td colspan="2"><input id="sbbdh" type="text" name="sbbdh" value="${TBAEGCZJ.tBaeGczjdw.sbbdh}"/></td>
				<td><div align="center">保费金额</div></td>
				<td><input id="bfje" type="text" name="bfje" style="width: 120px" value="${TBAEGCZJ.tBaeGczjdw.bfje}"/>元</td>
			</tr>
			<tr>
				<td colspan="4"><div align="center">建设单位承诺</div></td>
				<td colspan="4"><div align="center">审查意见</div></td>
			</tr>
			<tr>
				<td height="131" colspan="4">
					<div align="left">
						&nbsp;&nbsp;&nbsp;1、对本表所填内容真实性负责；<br />
						&nbsp;&nbsp;&nbsp;2、不具备安全生产条件不施工，不对设计、施工、监理单位提出违反法律、<br />
						法规和强制性标准的要求，不压缩合同约定的工期；<br />
						&nbsp;&nbsp;&nbsp;3、认真落实质量通病防治导则、节能保温等工作要求；<br />
						&nbsp;&nbsp;&nbsp;4、不肢解分包工程，对违法分包承担相应责任；<br />
						&nbsp;&nbsp;&nbsp;建设单位代表：
						<input id="jsdwdb" type="text" name="jsdwdb" style="width: 280px;" value="${TBAEGCZJ.tBaeGczjdw.jsdwdb}"/>（单位公章）
					</div>
				</td>
				<td colspan="4"><div align="left" style="margin: 0px 20px">
						符合 &nbsp;&nbsp;&nbsp;
								<input id="scyj" type="radio" name="scyj" value="1" readonly="true"
								<c:if test="${TBAEGCZJ.tBaeGczjdw.scyj=='1'}">checked="checked"</c:if>/> 
								&nbsp;&nbsp;&nbsp;不符合 &nbsp;&nbsp;&nbsp;
								<input id="scyj" type="radio" name="scyj" value="0" readonly="true"
								<c:if test="${TBAEGCZJ.tBaeGczjdw.scyj=='0'}">checked="checked"</c:if>/><br />
							<div>审查人:<input id="scr" type="text" name="scr" style="width: 280px;" value="${TBAEGCZJ.tBaeGczjdw.scr}" readonly="true"/></div>
							</p><p><br /></p>
					</div>
					<div style="margin-right: 57px" align="right">
						日&nbsp;&nbsp;期:&nbsp;
						<input id="scrq" type="text" name="scrq" class="easyui-datebox" style="width: 100px;" align="right" value="${TBAEGCZJ.tBaeGczjdw.scrq}" />
					</div>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td><div align="center">监督机构</div></td>
				<td colspan="3">
					<input id="jdjg" type="text" name="jdjg" value="${TBAEGCZJ.tBaeGczjdw.jdjg}"/></td>
				<td rowspan="3"><div align="center">报监意见</div></td>
				<td colspan="3" rowspan="3">
					<div align="left">
						<input id="bjyj" type="text" name="bjyj" style="width: 320px; height: 30px;align:right" value="${TBAEGCZJ.tBaeGczjdw.bjyj}" readonly="true"/>
					</div>
					<div align="right" style="margin-top: 10px">
						日&nbsp;&nbsp;期:&nbsp;
						<input id="bjrq" type="text" name="bjrq" class="easyui-datebox" style="width: 100px;" value="${TBAEGCZJ.tBaeGczjdw.bjrq}"/>
						（公&nbsp;&nbsp;章）
					</div>
				</td>
			</tr>
			<tr>
				<td><div align="center">联系电话</div></td>
				<td colspan="3"><input id="jdlxdh" type="text" name="jdlxdh" value="${TBAEGCZJ.tBaeGczjdw.jdlxdh}"/></td>
			</tr>
			<tr>
				<td height="27"><div align="center">监督注册号</div></td>
				<td colspan="3"><input id="jdzch" type="text" name="jdzch" value="${TBAEGCZJ.tBaeGczjdw.jdzch}"/></td>
			</tr>
			
		</table>
		<div style="text-align: center">
			<input name="id" id="id" type="hidden" value="${TBAEGCZJ.id }"/>
			<input name="bz" id="bz" type="hidden" />
			<input name="cjr" id="cjr" type="hidden" />
			<input name="cjsj" id="cjsj" type="hidden" />
			<input name="xmlx" id="xmlx" type="hidden" value="${TBAEGCZJ.xmlx}"/>
			<input name="sjzt" id="sjzt" type="hidden" value="${TBAEGCZJ.sjzt}"/>
			<div>注：1、本表一式五份，由建设单位填写，监督报监办理完毕后，返还建设单位一份；</div>
			<div style="margin-left: 48px">
				2、相关单位应填写全称，工程名称一施工许可证名称为准，一个施工许可证上有多个</div>
			<div>单位工程的应将各单体工程详细情况填写在《单体工程一览表》，并由建设单位盖章。</div>
		</div>
	</div>
</form>
<script>
//保存数据
function saveGcxmModel(_diagid, _grid) {
	var sjzt = $('#ppgxaddandmmodelform #sjzt').val();
	if(sjzt == 2 || sjzt == 4) {
		$.messager.alert('警告', '系统拒绝访问：当前状态错误！！！！！！', 'warning');
		return;
	}
	
	var sjztQuery=$('#sjztday0621').val();
	if(sjztQuery == 2 || sjztQuery == 4) {
		$.messager.alert('警告', '系统拒绝访问：当前状态错误！！！！！！', 'warning');
		return;
	}
	
	var data = $('#ppgxaddandmmodelform').serialize();
	$.ajax({
		url: "<c:url value='/xmgl/gcgl/saveXmbj.do'/>",
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

function wait(){
	alert('功能未完成！');
}

function geng(){
	window.location.reload();
}

$(function(){
	$("#scrq").datebox({    
	    disabled: true    
	});
	$("#bjrq").datebox({    
	    disabled: true    
	});
})
</script>