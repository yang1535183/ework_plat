/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.base.pojo 
 * @author: Mr.Yang   
 * @date: 2018年6月11日 下午4:13:17 
 */
package com.diiwon.mis.plat.base.pojo;

/**    
* @ClassName: TMisLcjl.java
* @Description: 该类的功能描述 流程记录对应的bean
*
* @version: v1.0.0
* @author: Mr.Yang
* @date: 2018年6月11日 下午4:13:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月11日     Mr.Yang           v1.0.0               修改原因
*/
public class TMisLcjl {
	private int id;
	private String gcid;
	private String action;
	private String memo;
	private String user;
	private String time;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the gcid
	 */
	public String getGcid() {
		return gcid;
	}
	/**
	 * @param gcid the gcid to set
	 */
	public void setGcid(String gcid) {
		this.gcid = gcid;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TMisLcjl [id=" + id + ", gcid=" + gcid + ", action=" + action + ", memo=" + memo + ", user=" + user
				+ ", time=" + time + "]";
	}
}
