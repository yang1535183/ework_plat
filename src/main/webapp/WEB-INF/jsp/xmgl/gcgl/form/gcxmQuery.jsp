<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div style="margin-left:5px"  id="ljlprintaddinfoaqqueryjdbb">
	<table style="width:900px" >
		<tr>
			<td align="center"><h2>合肥市房建工程质量安全监督报表</h2></td>
		</tr>
	</table>
	<form method="post" >
		<input name="sjzt" id="sjztday0621" type="hidden" value="${TBAEGCZJ.sjzt}"/>
		<div style="height: 86%; width: 980px; overflow: auto;" >
			<table  style="border:1px solid black; border-collapse: collapse; bordercolor: black;font-size:13px">
				<tr>
					<td style="border:1px solid black" width="126" rowspan="8"><div align="center">工程概况</div></td>
					<td style="border:1px solid black" width="143">
						<div align="center">工程名称</div>
					</td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.gcmc}</td>
					<td style="border:1px solid black" width="154"><div align="center">工程地点</div></td>
					<td style="border:1px solid black" colspan="3">${TBAEGCZJ.qu}区${TBAEGCZJ.lu}路${TBAEGCZJ.hao}号</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">投资类别</div></td>
					<td style="border:1px solid black"colspan="4">
						<div align="center">
							<input type="radio" id="tzlb" name="tzlb" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tzlb=='1'}">checked="checked"</c:if>/>1.外资 
							<input type="radio" id="tzlb" name="tzlb" value="2" disabled="true"
							<c:if test="${TBAEGCZJ.tzlb=='2'}">checked="checked"</c:if>/>2.合资
							<input type="radio" id="tzlb" name="tzlb" value="3" disabled="true"
							<c:if test="${TBAEGCZJ.tzlb=='3'}">checked="checked"</c:if>/>3.国有 
							<input type="radio" id="tzlb" name="tzlb" value="4" disabled="true"
							<c:if test="${TBAEGCZJ.tzlb=='4'}">checked="checked"</c:if>/>4.集体
							<input type="radio" id="tzlb" name="tzlb" value="5"disabled="true"
							<c:if test="${TBAEGCZJ.tzlb=='5'}">checked="checked"</c:if>/> 5.民营
						</div>
					</td>
					<td style="border:1px solid black" width="94"> <div align="center">预算造价</div></td>
					<td style="border:1px solid black" width="120">&nbsp;${TBAEGCZJ.yszj}万元</td>
			  </tr>
			  <tr>
					<td style="border:1px solid black"><div align="center">建筑面积</div></td>
					<td width="207" style="border:1px solid black">&nbsp;${TBAEGCZJ.jzmj}M²</td>
					<td width="150" align="center" style="border:1px solid black">结构类型/层次</td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.jgcc}</td>
					<td style="border:1px solid black"><div align="center">每平方米造价</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.pmzj}元</td>
			 </tr>
			 <tr>
					<td style="border:1px solid black" height="28"><div align="center">基础类型</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.jclx}</td>
					<td style="border:1px solid black" align="center">工程类别</td>
					<td style="border:1px solid black" colspan="4">
						<div align="center">
							<input id="gclb" type="radio" name="gclb" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.gclb=='1'}">checked="checked"</c:if> />1.住宅 
							<input id="gclb" type="radio" name="gclb" value="2" disabled="true"
							<c:if test="${TBAEGCZJ.gclb=='2'}">checked="checked"</c:if> />2.公建 
							<input id="gclb" type="radio" name="gclb" value="3"  disabled="true"
							<c:if test="${TBAEGCZJ.gclb=='3'}">checked="checked"</c:if>/>3.厂房 
							<input id="gclb" type="radio" name="gclb" value="4" disabled="true"
							<c:if test="${TBAEGCZJ.gclb=='4'}">checked="checked"</c:if>/>4.其他
						</div>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black"> <div align="center">建筑节能执行标准</div></td>
					<td style="border:1px solid black" colspan="6">
						<div align="center">
							<input id="jnbz" type="radio" name="jnbz" value="1"  disabled="true"
							<c:if test="${TBAEGCZJ.jnbz=='1'}">checked="checked"</c:if>/>50% 
							<input id="jnbz" type="radio" name="jnbz" value="2"  disabled="true"
							<c:if test="${TBAEGCZJ.jnbz=='2'}">checked="checked"</c:if>/>65% 
							<input id="jnbz" type="radio" name="jnbz" value="3"  disabled="true"
							<c:if test="${TBAEGCZJ.jnbz=='3'}">checked="checked"</c:if>/>其他
						</div>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">绿色建筑</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.lsjz}M²</td>
					<td style="border:1px solid black" colspan="4">
						<div align="center">
							<input id="xj" type="radio" name="xj" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.xj=='1'}">checked="checked" </c:if>/>一星
							<input id="xj" type="radio" name="xj" value="2"  disabled="true"
							<c:if test="${TBAEGCZJ.xj=='2'}">checked="checked"</c:if>/>二星 
							<input id="xj" type="radio" name="xj" value="3"  disabled="true"
							<c:if test="${TBAEGCZJ.xj=='3'}">checked="checked"</c:if>/>三星
						</div>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black" colspan="2"><div align="center">可再生能源建筑应用/太阳能光热</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tyngr}M²</td>
					<td style="border:1px solid black" width="154">
						<div align="center">太阳能光伏</div>
					</td>
					<td style="border:1px solid black" width="135">
						&nbsp;${TBAEGCZJ.tyngf}kw
					</td>
					<td style="border:1px solid black"><div align="center">地源热汞</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.dyrb}M²</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">计划开工日期</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.kgsj}</td>
					<td style="border:1px solid black">
						<div align="center">计划竣工日期</div>
					</td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.jgsj}</td>
				</tr>
				<tr>
					<td style="border:1px solid black">
						<div align="center">
						  危险性较</br>大工程
						</div>
					</td>
					<td style="border:1px solid black" colspan="3">
						<p>
							<input id="sjc" type="checkbox" name="sjc" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.sjc=='1'}">checked</c:if> />深基坑
							<input id="gdmb" type="checkbox" name="gdmb" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.gdmb=='1'}">checked</c:if>/>高大模板 
							<input id="gkzy" type="checkbox" name="gkzy" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.gkzy=='1'}">checked</c:if>/>30M以上高空作业<br />
							<input id="pj" type="checkbox" name="pj" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.pj=='1'}">checked</c:if>/>爬架 
							<input id="rhdt" type="checkbox" name="rhdt" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.rhdt=='1'}">checked</c:if>/>人货电梯 
							<input id="td" type="checkbox" name="td" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.td=='1'}">checked</c:if>/>塔吊 
							<input id="wxqt" type="checkbox" name="wxqt" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.wxqt=='1'}">checked</c:if>/>其他
						</p>
					</td>
					<td style="border:1px solid black"><div align="center">周边环境</div></td>
					<td style="border:1px solid black" colspan="3">
						<p>
							<input id="ljgyx" type="checkbox" name="ljgyx" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.ljgyx=='1'}">checked="checked"</c:if>/>临近高压线<br />
							<input id="ljjmq" type="checkbox" name="ljjmq" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.ljjmq=='1'}">checked="checked"</c:if> />临近居民区,商业区
							<input id="zbqt" type="checkbox" name="zbqt" value="1" disabled="true"
							<c:if test="${TBAEGCZJ.tBaeGczjsx.zbqt=='1'}">checked="checked"</c:if>/>其他
						</p>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black" rowspan="2"><div align="center">建设单位</div></td>
					<td style="border:1px solid black"><div align="center">单位名称</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.jsdwmc}</td>
					<td style="border:1px solid black"><div align="center">资质等级及证书</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.jsdjzs}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">项目负责人</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.jsxmfzr}</td>
					<td style="border:1px solid black"><div align="center">联系电话</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.jslxdh}</td>
				</tr>
				<tr>
					<td style="border:1px solid black" rowspan="2"><div align="center">勘察单位</div></td>
					<td style="border:1px solid black"><div align="center">单位名称</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.kcdwmc}</td>
					<td style="border:1px solid black"><div align="center">资质等级及证书</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.kcdjzs}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">项目负责人</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.kcxmfzr}</td>
					<td style="border:1px solid black" align="center">执业资格及证书</td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.kczgzs}</td>
					<td style="border:1px solid black"><div align="center">联系电话</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.kclxdh}</td>
				</tr>
				<tr>
					<td style="border:1px solid black" rowspan="2"><div align="center">设计单位</div></td>
					<td style="border:1px solid black"><div align="center">单位名称</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.sjdwmc}</td>
					<td style="border:1px solid black"><div align="center">资质等级及证书</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.sjdjzs}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">项目负责人</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.sjxmfrz}</td>
					<td style="border:1px solid black" align="center">执业资格及证书</td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.sjzgzs}</td>
					<td style="border:1px solid black"><div align="center">联系电话</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.sjlxdh}</td>
				</tr>
				<tr>
					<td style="border:1px solid black" rowspan="2"><div align="center">监理单位</div></td>
					<td style="border:1px solid black"><div align="center">单位名称</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.jldwmc}</td>
					<td style="border:1px solid black"><div align="center">资质等级及证书</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.jldjzs}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">项目总监</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.jlxmfzr}</td>
					<td style="border:1px solid black" align="center">执业资格及证书</td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.jlzgzs}</td>
					<td style="border:1px solid black"><div align="center">联系电话</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.jllxdh}</td>
				</tr>
				<tr>
					<td style="border:1px solid black" rowspan="5"><div align="center">施工单位</div></td>
					<td style="border:1px solid black" rowspan="2"><div align="center">单位名称</div></td>
					<td style="border:1px solid black" colspan="2" rowspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.sgdwmc}</td>
					<td style="border:1px solid black"><div align="center">资质等级及证书</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.sgdjzs}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">安全生产许可证</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.sgaqxkz}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">项目经理</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.sgxmjl}</td>
					<td style="border:1px solid black"><div align="center">岗位证书</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.sggwzs}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">安全考核证号</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.sgkhzh}</td>
					<td style="border:1px solid black"><div align="center">联系电话</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.sglxdh}</td>
				</tr>
				<tr>
					<td style="border:1px solid black" height="81" colspan="7">
						<p align="left" >
							<table style="font-size:13px">
								<tr>
									<td style="width: 210px;">工地现场专职安全员：&nbsp;&nbsp;${SGXCAQY0}</td>
									<td style="width: 210px;">安全考核证号：&nbsp;&nbsp;${SGAQYZS0}</td>
									<td style="width: 210px;">（1万M2以内至少一人）</td>
								</tr>
								<tr>
									<td style="padding-left: 26px;">现场专职安全员：&nbsp;&nbsp;${SGXCAQY1}</td>
									<td>安全考核证号：&nbsp;&nbsp;${SGAQYZS1}</td>
									<td>（1-5万M²以内至少二人）</td>
								</tr>
								
								<tr>
									<td style="padding-left: 26px;">现场专职安全员：&nbsp;&nbsp;${SGXCAQY2}</td>
									<td>安全考核证号：&nbsp;&nbsp;${SGAQYZS2}</td>
									<td>（5万M²以内至少三人）</td>
								</tr>
							</table>
						</p>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">施工图审</br>查单位</div></td>
					<td style="border:1px solid black" colspan="7">&nbsp;${TBAEGCZJ.tBaeGczjdw.sgtscdw}</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">工伤保险</br>征收部门</div></td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.gsbxbm}</td>
					<td style="border:1px solid black">
						<div align="center">社保保单号</div>
					</td>
					<td style="border:1px solid black" colspan="2">&nbsp;${TBAEGCZJ.tBaeGczjdw.sbbdh}</td>
					<td style="border:1px solid black"><div align="center">保费金额</div></td>
					<td style="border:1px solid black">&nbsp;${TBAEGCZJ.tBaeGczjdw.bfje}元</td>
				</tr>
				<tr>
					<td style="border:1px solid black" colspan="4"><div align="center">建设单位承诺</div></td>
					<td style="border:1px solid black" colspan="4"><div align="center">审查意见</div></td>
				</tr>
				<tr>
					<td style="border:1px solid black" height="131" colspan="4">
						<div align="left">
							&nbsp;&nbsp;&nbsp;1、对本表所填内容真实性负责；<br />
							&nbsp;&nbsp;&nbsp;2、不具备安全生产条件不施工，不对设计、施工、监理单位提出<br />违反法律、
							法规和强制性标准的要求，不压缩合同约定的工期；<br />
							&nbsp;&nbsp;&nbsp;3、认真落实质量通病防治导则、节能保温等工作要求；<br />
							&nbsp;&nbsp;&nbsp;4、不肢解分包工程，对违法分包承担相应责任；<br />
							&nbsp;&nbsp;&nbsp;建设单位代表：${TBAEGCZJ.tBaeGczjdw.jsdwdb}
							（ 单位公章）
						</div>
					</td>
					<td style="border:1px solid black" colspan="4"><div align="left" style="margin: 0px 20px">
							<p>
								符合 &nbsp;&nbsp;&nbsp;
								<input id="scyj" type="radio" name="scyj" value="1" readonly="true"
								<c:if test="${TBAEGCZJ.tBaeGczjdw.scyj=='1'}">checked="checked"</c:if>/> 
								&nbsp;&nbsp;&nbsp;不符合 &nbsp;&nbsp;&nbsp;
								<input id="scyj" type="radio" name="scyj" value="0" readonly="true"
								<c:if test="${TBAEGCZJ.tBaeGczjdw.scyj=='0'}">checked="checked"</c:if>/><br />
							<div>审查人:
							${TBAEGCZJ.tBaeGczjdw.scr}
							</div>
							</p>
							<p><br /></p>
						</div>
						<div style="margin-right: 57px" align="right">
						日&nbsp;&nbsp;期:&nbsp;${TBAEGCZJ.tBaeGczjdw.scrq}&nbsp;</div>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">监督机构</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;${TBAEGCZJ.tBaeGczjdw.jdjg}</td>
					<td style="border:1px solid black" rowspan="3"><div align="center">报监意见</div></td>
					<td style="border:1px solid black" colspan="3" rowspan="3">
						<div align="left">${TBAEGCZJ.tBaeGczjdw.bjyj}</div>
							<div align="right" style="margin-top:10px">
							日&nbsp;&nbsp;期:${TBAEGCZJ.tBaeGczjdw.bjrq}&nbsp;（公&nbsp;&nbsp;章）
					   </div>
					</td>
				</tr>
				<tr>
					<td style="border:1px solid black"><div align="center">联系电话</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;&nbsp;${TBAEGCZJ.tBaeGczjdw.jdlxdh}</td>
				</tr>
				<tr>
					<td style="border:1px solid black" height="27"><div align="center">监督注册号</div></td>
					<td style="border:1px solid black" colspan="3">&nbsp;&nbsp;${TBAEGCZJ.tBaeGczjdw.jdzch}
					</td>
				</tr>
			</table>
			<table style="width: 760px; ">
				<tr>
					<td	colspan="9">
						<div style="text-align: center;font-size:14px" >
							<br/>
							注：1、本表一式五份，由建设单位填写，监督报监办理完毕后，返还建设单位一份；<br/>
							<span style="width: 50px;">&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							 2、相关单位应填写全称，工程名称一施工许可证名称为准，一个施工许可证上有多个<br/>
							 单位工程的应将各单体工程详细情况填写在《单体工程一览表》，并由建设单位盖章。
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>