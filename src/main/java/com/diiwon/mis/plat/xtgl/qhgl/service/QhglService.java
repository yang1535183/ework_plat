package com.diiwon.mis.plat.xtgl.qhgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diiwon.mis.plat.base.pojo.Admdivision;
import com.diiwon.mis.plat.base.pojo.TMisUser;
import com.diiwon.mis.plat.xtgl.qhgl.dao.QhglDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class QhglService {
	
	@Autowired
	private QhglDao qhglDao;

	/**
	 * 封装区划tree 数据
	 * 
	 * @return
	 */
	public String findQhTree(TMisUser user, String flag) {
		List<Admdivision> admList = this.qhglDao.queryQhlbTree(user);
		JSONArray array = new JSONArray();

		Admdivision adm = admList.get(0);
		JSONObject obj = new JSONObject();
		/**获取根节点*/
		obj.put("id", adm.getAdcode());
		obj.put("text", adm.getAdname());
		this.addChildren(admList, adm, obj, "open");
		array.add(obj);
		
		return array.toString();
	}
	
	/**
	 * 添加子节点
	 * @param admList
	 * @param adm
	 * @param obj
	 */
	private void addChildren(List<Admdivision> admList, Admdivision adm, JSONObject obj, String state){
		JSONArray subArray = new JSONArray();
		/**下级节点**/
		for (Admdivision admT : admList) {
			if (admT.getAdcode().length() > adm.getAdcode().length() &&
					admT.getAdcode().substring(0, adm.getAdcode().length()).equals(adm.getAdcode()) && 
					admT.getAdcode().length() == adm.getAdcode().length()+2) {
				JSONObject subObj = new JSONObject();
				subObj.put("id", admT.getAdcode());
				subObj.put("text", admT.getAdname());
				this.addChildren(admList, admT, subObj, "closed");
				
				subArray.add(subObj);
			}
		}
		if (subArray.size() > 0) {
			JSONObject attributes = new JSONObject();
			attributes.put("flag", 1);
			obj.put("attributes", attributes);
			obj.put("state", state);
			obj.put("children", subArray);
		}
	}
	
}
