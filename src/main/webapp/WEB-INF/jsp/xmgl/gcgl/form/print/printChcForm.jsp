<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<form id="printChcFormcCForm" method="post">
	<input type="hidden" id="id" name="id" value="${TBAEGCZJ.id }">
	<input type="hidden" id="gcid" name="gcid" value="${TBAEGCZJ.tBaeCczjdw.gcid }">
	<div style="height: 96%; width: 1000px; overflow: auto">
	<table style="width:740px"">
		<tr>
			<td align="center" style="font-size:20px"><B>合肥市庐阳区房屋拆除工程安全监督报监表</B></td>
		</tr>
		<tr><td>建设单位：（公章）</td></tr>
	</table>
		<table width="780"  style="border-collapse: collapse; bordercolor: black;font-size:13px;border:1px solid black;">
			<tr>
				<td style="border:1px solid black"  width="65" rowspan="4"><div align="center">工程概况</div></td>
				<td style="border:1px solid black" width="85"><div align="center">工程名称</div></td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.gcmc }</td>
				<td style="border:1px solid black" width="82"><div align="center">工程地点</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.gcdd }</td>
			</tr>
			<tr>
				<td style="border:1px solid black"><div align="center">拆除面积</div></td>
				<td style="border:1px solid black" width="60">${TBAECCZJ.ccmj }&nbsp;M²</td>
				<td style="border:1px solid black" width="82"><div align="center">结构层次</div></td>
				<td style="border:1px solid black" width="82">${TBAECCZJ.jgcc }</td>
				<td style="border:1px solid black" colspan="2"><div align="center">危险性较大工程</div></td>
				<td style="border:1px solid black" width="74" colspan="2"><div align="center">
						<input id="wxxjd" type="radio" name="wxxjd" value="0"
						 <c:if test="${TBAECCZJ.wxxjd=='1'}">checked="checked"</c:if>/>是 
						 
						 <input id="wxxjd" type="radio" name="wxxjd" value="1"
						 <c:if test="${TBAECCZJ.wxxjd=='0'}">checked="checked"</c:if>/>否 
						 
					</div></td>
			</tr>
			<tr>
				<td style="border:1px solid black"><div align="center">周边环境</div></td>
				<td colspan="3" style="border:1px solid black">
					<div align="center">
						<input id="ljgyx" type="checkbox" name="ljgyx" value="1"
						<c:if test="${TBAECCZJ.ljgyx=='1'}">checked="checked"</c:if>/>临近高压线 
						<input id="ljjmq" type="checkbox" name="ljjmq" value="1"
						<c:if test="${TBAECCZJ.ljjmq=='1'}">checked="checked"</c:if>/>临近居民区商业区</br>
						 <input id="zbqt" type="checkbox" name="zbqt" value="1" 
						 <c:if test="${TBAECCZJ.zbqt=='1'}">checked="checked"</c:if> />其他 
					</div>
				</td>
				<td style="border:1px solid black" colspan="2"><div align="center">拟采用的拆除方法</div></td>
				<td style="border:1px solid black"><div align="center">
					<input id="jx" type="checkbox" name="jx" value="1"
						<c:if test="${TBAECCZJ.jx=='1'}">checked="checked"</c:if>/>机械 
						</br>
						<input id="bp" type="checkbox" name="bp" value="1"
						<c:if test="${TBAECCZJ.bp=='1'}">checked="checked"</c:if>/>爆破 
						
					</div></td>
			</tr>
			<tr>
				<td style="border:1px solid black"><div align="center">计划开工日期</div></td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.jhkgrq }</td>
				<td style="border:1px solid black" ><div align="center">计划竣工日期</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.jhjgrq }</td>
			</tr>
			<tr>
				<td style="border:1px solid black" rowspan="2"><div align="center">建设单位</div></td>
				<td style="border:1px solid black"><div align="center">单位名称</div></td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.tBaeCczjdw.jsdwmc }</td>
				<td style="border:1px solid black"><div align="center">属地</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.jssd }</td>
			</tr>
			<tr>
				<td style="border:1px solid black"><div align="center">项目负责人</div></td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.tBaeCczjdw.jsxmfzr }</td>
				<td style="border:1px solid black"><div align="center">联系电话</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.jslxdh }</td>
			</tr>
			<tr>
				<td style="border:1px solid black" rowspan="2"><div align="center">监理单位</div></td>
				<td style="border:1px solid black" align="center">单位名称</td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.tBaeCczjdw.jldwmc }</td>
				<td style="border:1px solid black" ><div align="center">资质等级及证书</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.jldjzs }</td>
			</tr>
			<tr>
				<td style="border:1px solid black" align="center">项目总监</td>
				<td style="border:1px solid black">${TBAECCZJ.tBaeCczjdw.jlxmzj }</td>
				<td style="border:1px solid black"><div align="center">职业资格及证书</div></td>
				<td style="border:1px solid black">${TBAECCZJ.tBaeCczjdw.jlzgzs }</td>
				<td style="border:1px solid black" width="112"><div align="center">联系电话</div></td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.tBaeCczjdw.jllxdh }</td>
			</tr>
			<tr>
				<td style="border:1px solid black" rowspan="5"><div align="center">拆除单位</div></td>
				<td style="border:1px solid black" rowspan="2"><div align="center">单位名称</div></td>
				<td style="border:1px solid black" colspan="2" rowspan="2"> ${TBAECCZJ.tBaeCczjdw.ccdwmc} </td>
				<td style="border:1px solid black" ><div align="center">资质等级或备案</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.cczzdj }</td>
			</tr>
			<tr>
				<td style="border:1px solid black" height="26" ><div align="center">安全生产许可证号</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.ccaqxkzh }</td>
			</tr>
			<tr>
				<td style="border:1px solid black"><div align="center">项目负责人</div></td>
				<td style="border:1px solid black"  colspan="2">${TBAECCZJ.tBaeCczjdw.ccxmfrz }</td>
				<td style="border:1px solid black"><div align="center">岗位证书</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.ccgwzs }</td>
			</tr>
			<tr>
				<td style="border:1px solid black"><div align="center">安全考核号</div></td>
				<td style="border:1px solid black" colspan="2">${TBAECCZJ.tBaeCczjdw.ccaqkhzh}</td>
				<td style="border:1px solid black"><div align="center">联系电话</div></td>
				<td style="border:1px solid black" colspan="3">${TBAECCZJ.tBaeCczjdw.cclxdh }</td>
			</tr>
			<tr>
				<td style="border:1px solid black" height="57" colspan="7"><p>
						&nbsp;&nbsp;现场专职安全员：${xcaqyArr[0]}
						&nbsp;&nbsp;&nbsp;安全考核证号：${khzhArr[0]}
					</p>
					<p>
						&nbsp;&nbsp;现场专职安全员：${xcaqyArr[1]}
						&nbsp;&nbsp;&nbsp; 安全考核证号：${khzhArr[1]}
					</p></td>
			</tr>
			<tr>
				<td style="border:1px solid black" height="95"><div align="center">报监资料</div></td>
				<td style="border:1px solid black" colspan="7"><p>
						&nbsp;&nbsp;&nbsp;<input id="ccdwzzzs" type="checkbox" name="ccdwzzzs" value="1"
						<c:if test="${TBAECCZJ.tBaeCczjdw.ccdwzzzs=='1'}">checked="checked"</c:if>/>拆除单位资质证书(或登记备案证书)及复印件
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;<input id="fwccwtht" type="checkbox" name="fwccwtht" value="1"
						<c:if test="${TBAECCZJ.tBaeCczjdw.fwccwtht=='1'}">checked="checked"</c:if>/>房屋拆除委托合同
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;<input id="bzjjlpz" type="checkbox" name="bzjjlpz" value="1"
						<c:if test="${TBAECCZJ.tBaeCczjdw.bzjjlpz=='1'}">checked="checked"</c:if>/>保证金缴纳凭证
					</p> </td>
			</tr>
			<tr>
				<td style="border:1px solid black" colspan="4"><div align="center">审查依据</div></td>
				<td style="border:1px solid black" colspan="4"><div align="center">审查意见</div></td>
			</tr>
			<tr>
				<td style="border:1px solid black" height="130" colspan="4">&nbsp;1、《建设工程安全生产管理条例》（国务院第393号令<br />
					&nbsp;2、《关于印发&lt;关于进一步明确全市建筑拆除施工安全生产职责的意见&gt;的通知》<br />（合安办[2009]41号）<br />
					&nbsp;3、《关于进一步较强庐阳区建设领域安全生产工程的通知》（庐安[2013]3号）<br />
					&nbsp;4、《关于进一步加强庐阳区拆除施工安全管理工作的通知》（庐住建[2014]1号）<br /></td>
				<td style="border:1px solid black" colspan="4"><p>
						&nbsp;符&nbsp;合&nbsp;&nbsp;
						<input id="scyj" type="radio" name="scyj" value="0"
						<c:if test="${TBAECCZJ.tBaeCczjdw.bzjjlpz=='0'}">checked="checked"</c:if>
						/>
					</p>
					<p>
						&nbsp;不符合
						<input id="scyj" type="radio" name="scyj" value="1"
						<c:if test="${TBAECCZJ.tBaeCczjdw.scyj=='1'}">checked="checked"</c:if>
						/>
					</p>
					<p>
						&nbsp;审查人：<input id="scr" type="text" name="scr" value="${TBAEGCZJ.tBaeCczjdw.scr }"
						/>
					</p>
					<p align="right" style="margin-right: 50px">
						日期：${TBAECCZJ.tBaeCczjdw.scrq }
					</p> &nbsp;</td>
			</tr>
			<tr>
				<td style="border:1px solid black" height="1" colspan="2"><div align="center">监督机构</div></td>
				<td style="border:1px solid black" colspan="2"><div align="center">庐阳区建设工程质量安全监督站</div></td>
				<td style="border:1px solid black" rowspan="3"><div align="center">报监意见</div></td>
				<td style="border:1px solid black" colspan="3" rowspan="3">
					<div align="left">
					<input id="scr" type="text" name="scr" value="${TBAECCZJ.tBaeCczjdw.bjyj }"/>
					</div>
					<div align="left" style="margin-top: 10px">
						日期：${TBAECCZJ.tBaeCczjdw.bjrq }（公章）
					</div>&nbsp; 
				</td>
			</tr>
			<tr>
				<td style="border:1px solid black" height="4" colspan="2"><div align="center">联系电话</div></td>
				<td style="border:1px solid black" style="border:1px solid black" colspan="2"><div align="center">0551-65850756</div></td>
			</tr>
			<tr>
				<td style="border:1px solid black" style="border:1px solid black" height="8" colspan="2"><div align="center">监督注册号</div></td>
				<td style="border:1px solid black" colspan="2" align="center">${TBAECCZJ.tBaeCczjdw.jdzch }</td>
			</tr>
		</table>
			<div style=" width:780px; align:center">
				本表一式四份，由建设单位填写，监督报监办理完毕后，返还建设单位一份，报区安监局备案一份.
			</div>	
	</div>
</form>