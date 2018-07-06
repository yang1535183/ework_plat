package com.diiwon.mis.plat.base.utils;

public class SysCode {
	
	/**上传资源-存储类型(数据库存储=0)*/
	public static final String SCZY_CCLX_SJKCC = "0";
	/**附件-存储类型[相对路径文件存储=1]*/
	public static final String FJ_CCLX_XDLJ_WJCC = "1";
	/**附件-存储类型[绝对路径文件存储=2]*/
	public static final String FJ_CCLX_JDLJ_WJCC = "2";
	
	/**附件-有效标识[未生效=0]*/
	public static final String FJ_YXBS_WSX = "0";
	/**附件-有效标识[已生效=1]*/
	public static final String FJ_YXBS_YSX = "1";
	
	public static final String PROVINCE_QHDM = "34";
	
	/**角色表-用户类型[0-内置]*/
	public static final String USER_TYPE_SYSTEM = "0";
	/**用户表-用户类型[1-定制]*/
	public static final String USER_TYPE_CUSTOM = "1";
	
	/**用户表-是否禁用[0-禁用]*/
	public static final String USER_ISDISABLED_NO = "0";
	/**用户表-是否禁用[1-正常]*/
	public static final String USER_ISDISABLED_YES = "1";
	
	/**用户表-是否删除[0-未删除]*/
	public static final String USER_ISDEL_NO = "0";
	/**用户表-是否删除[1-已删除]*/
	public static final String USER_ISDEL_YES = "1";

	/**权限表-权限类型[MENU-菜单]*/
	public static final String AUTHORITY_TYPE_MENU = "MENU";
	/**权限表-权限类型[URL-链接]*/
	public static final String AUTHORITY_TYPE_URL = "URL";
	/**权限表-通用***/
	public static final String AUTHORITY_TYPE_TY = "0";
	/**权限表-PC***/
	public static final String AUTHORITY_TYPE_PC = "1";
	/**权限表-APP***/
	public static final String AUTHORITY_TYPE_APP = "2";
	
	/**角色表-角色类型[0-内置]*/
	public static final String ROLE_TYPE_SYSTEM = "SYSTEM";
	/**角色表-角色类型[1-定制]*/
	public static final String ROLE_TYPE_CUSTOM = "CUSTOM";
	
	/**角色表-限制[0-不可授权]***/
	public static final String ROLE_TYPE_ENDOW = "0";
	/**角色表-限制[0-可授权]***/
	public static final String ROLE_TYPE_UNENDOW = "1";
	
	/**角色表-角色级次[0-最高级]*/
	public static final String ROLE_LEVEL_0 = "0";
	/**角色表-角色级次[1-级]*/
	public static final String ROLE_LEVEL_1 = "1";
	/**角色表-角色级次[2-级]*/
	public static final String ROLE_LEVEL_2 = "2";
	/**角色表-角色级次[3-级]*/
	public static final String ROLE_LEVEL_3 = "3";
	
	/**超级管理员账户*/
	public static final String USER_ADMIN[] = {"sadmin", "admin"};
	
	/**用户表-默认密码[DEFAULT=123456]*/
	public static final String USER_PWD_DEFAULT = "123456";
	/**用户表-默认密码[DEFAULT=123456](密文)*/
	public static final String USER_PWD_DEFAULT_ENCRYPT = "e10adc3949ba59abbe56e057f20f883e";
	
	/**上级节点-默认值[0]*/
	public static final String SJJD_DEFAULT = "0";
	
	/** 系统控制管理-启用系统数据  **/
	public static final String SYS_CTRL_START_NAME = "CTRL_START";
	/** 系统控制管理-启用系统数据(状态：0=未启用, 1=已启用)  **/
	public static final String SYS_CTRL_START_STATUS_FAIL = "0";
	/** 系统控制管理-启用系统数据(状态：0=未启用, 1=已启用)  **/
	public static final String SYS_CTRL_START_STATUS_SUCCESS = "1";
	
	/** 查询统计时间的配置名称 */
	public static final String PZ_HSTJ_TJSJ = "PZ_HSTJ_TJSJ";
	
	/** 系统去特殊字符处理。注意 在最后[/ /]有空格,此处做去空格处理 */
	public static final String SYS_REGEX = "[`~!@#$%^&*()+=|{}':;',//[/ /]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	
	/**	文件上传图片格式	后缀只允许jpeg|jpeg|jpg|jpg|gif|gif|bmp|bmp|png|png 格式^\\w+\\.(jpg|gif)$	**/
	public static final String SYS_IMG = "(jpeg|jpg|gif|bmp|png)";
	
	/** 货币符号(人民币) */
	public static final String HBFH_RMB = "￥";
	/** 货币符号(美元) */
	public static final String HBFH_MY = "$";
	
	/** MD5加密类型(32位长度) */
	public static final String MD5_LX_32 = "32";
	/** MD5加密类型(16位长度) */
	public static final String MD5_LX_16 = "16";

	/** 日期格式化-横线格式-长日期(yyyy-MM-dd HH:mm:ss) */
	public static final String RQGSH_HXGS_CRQ = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式化-横线格式-短日期(yyyy-MM-dd) */
	public static final String RQGSH_HXGS_DRQ = "yyyy-MM-dd";
	/** 日期格式化-斜线格式-长日期(yyyy/MM/dd HH:mm:ss) */
	public static final String RQGSH_XXGS_CRQ = "yyyy/MM/dd HH:mm:ss";
	/** 日期格式化-斜线格式-短日期(yyyy/MM/dd) */
	public static final String RQGSH_XXGS_DRQ = "yyyy-MM-dd";
	/** 日期格式化-无符号-长日期(yyyyMMddHHmmss) */
	public static final String RQGSH_WFH_CRQ = "yyyyMMddHHmmss";
	/** 日期格式化-无符号-短日期(yyyyMMdd) */
	public static final String RQGSH_WFH_DRQ = "yyyyMMdd";
	/** 日期格式化-有符号时间(HH:mm:ss) */
	public static final String RQGSH_YFHSJ = "HH:mm:ss";
	/** 日期格式化-无符号时间(HHmmss) */
	public static final String RQGSH_WFHSJ = "HHmmss";
	
	/***文件上传接口 类型*****/
	public static final String SYS_UPLOADH5 = "UPLOADH5";
	
	/**数据默认状态*/
	public static final String SJZT_DEFAULT = "0";
	/**数据提报【待审查】状态*/
	public static final String SJZT_DSC = "1";
	/**数据 审查 退回状态*/
	public static final String SJZT_SCTH = "3";
	/**数据  待审批  状态*/
	public static final String SJZT_DSP = "2";
	/**数据 审批退回  状态*/
	public static final String SJZT_SPTH = "5";
	/**数据 生成注册码 状态*/
	public static final String SJZT_SCZCM = "4";
	
	/***企业用户***/
	public static final String DBSX_QYYH = "QYYH";
	/***工程审查员***/
	public static final String DBSX_BJSC = "BJSC";
	/***工程审批员***/
	public static final String DBSX_BJSH = "BJSP";
	
	/**企业录入***/
	public static final String SYS_QYLR = "QYSB";
	/**企业已上报***/
	public static final String SYS_DSC = "SCYDSC";
	/**符合条件-审查通过***/
	public static final String SYS_SCYSCTG = "SCYTG";
	/**不符合条件-审查不通过***/
	public static final String SYS_SCYSCBTG = "SCYBTG";
	/**不符合条件-审批不通过***/
	public static final String SYS_SPLDBTG = "SPLDBTG";
	/**数据 生成注册码 【审批通过】状态*/
	public static final String SYS_SCZCM = "SPLDTG";
	/**上报默认意见***/
	public static final String DFNAME_QYLR = "符合条件-上报成功";
	public static final String DFNAME_SCYTG = "符合条件-审查通过";
	public static final String DFNAME_SCYBTG = "不符合条件-审查不通过";
	public static final String DFNAME_SPLDTG = "符合条件-审批通过";
	public static final String DFNAME_SPLDBTG = "不符合条件-审批不通过";
	
	/**四大工程项目系统类型**/
	public static final String SYS_GCFJ= "GCFJ";
	public static final String SYS_GCZS = "GCZS";
	public static final String SYS_GCSZ = "GCSZ";
	public static final String SYS_GCCHC = "GCCHC";
	
	/****数据编号***/
	public static final String BH_GCFJ = "";
	public static final String BH_GCZS = "ZS";
	public static final String BH_GCSZ = "SZ";
	public static final String BH_GCCHC= "CHC";
	
	/***合同申报**/
	public static final String SYS_HTQYSB = "HTQYSB";
	
	/**字典表 工程表格***/
	public static final String SYS_GCBG = "30";
	/**字典表 上传附件***/
	public static final String SYS_SCFJ = "60";
	/**字典表 合同类型***/
	public static final String SYS_HTLX = "80";
	
	/***工程表单-市政查看表单***/
	public static final String CKGCBD_SZ="gcxmQuery";
	/***工程表单-房建查看表单***/
	public static final String CKGCBD_FJ="gcxmQuery";
	/***工程表单-二次装饰查看表单***/
	public static final String CKGCBD_ZS="gcxmQuery";
	/***工程表单-拆除查看表单***/
	public static final String CKGCBD_CHC="chcForm";
	
	/***工程表单-市政修改表单***/
	public static final String XGGCBD_SZ="gcxmModel";
	/***工程表单-房建修改表单***/
	public static final String XGGCBD_FJ="gcxmModel";
	/***工程表单-二次装饰修改表单***/
	public static final String XGGCBD_ZS="gcxmModel";
	/***工程表单-拆除修改表单***/
	public static final String XGGCBD_CHC="chcForm";
	
	/***合同文件上传对应码**/
	public static final String UPLOAD_HTWJ = "HTLOADH";
	/***工程附件上传对应码**/
	public static final String UPLOAD_GCFJ = "UPLOADH5";
	
	/**注册码类型**/
	public static final String SYSROLECODE = "";
	
	/**下拉列表默认*****/
	public static final String SYS_DEFUL = "0";
	/**下拉列表框添加请选择*****/
	public static final String SYS_COMBO = "1";
}
