/**   
 * 功能描述：
 * @Package: com.diiwon.mis.plat.xmgl.ccgl.MyException 
 * @author: Mr.Yang   
 * @date: 2018年6月13日 下午5:41:11 
 */
package com.diiwon.mis.plat.xmgl.ccgl.MyException;

/**    
* @ClassName: MyException.java
* @Description: 自定义异常类，在事物未按预期执行时，抛出自定义异常
*
* @version: v1.0.0
* @author: Mr.Yang
* @date: 2018年6月13日 下午5:41:11 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月13日     Mr.Yang           v1.0.0               修改原因
*/
@SuppressWarnings("serial")
public class MyException extends Exception{
	//无参构造方法
    public MyException(){

        super();
    }

    //有参的构造方法
    public MyException(String message){
        super(message);

    }

    // 用指定的详细信息和原因构造一个新的异常
    public MyException(String message, Throwable cause){

        super(message,cause);
    }

    //用指定原因构造一个新的异常
     public MyException(Throwable cause) {

         super(cause);
     }
}
