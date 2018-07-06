/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.spjk.controller 
 * @author: Mr.Yang   
 * @date: 2018年6月5日 下午1:54:44 
 */
package com.diiwon.mis.plat.spjk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**    
* @ClassName: SPJKController.java
* @Description: 现场视频监控
*
* @version: v1.0.0
* @author: Mr.Yang
* @date: 2018年6月5日 下午1:54:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月5日     Mr.Yang           v1.0.0               修改原因
*/

@Controller
public class SPJKController {
	@RequestMapping(value="spjk/xcsp.do",method=RequestMethod.GET)
	public String xCJk() {
		return "/spjk/xcsp";
	}
}
