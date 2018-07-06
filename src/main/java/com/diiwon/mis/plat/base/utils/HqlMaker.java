package com.diiwon.mis.plat.base.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 组装hql语句
 * 
 * @author sunshine
 * 
 */
public class HqlMaker {
	/**
	 * 组装like hql
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuHqlLike(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append(" like '%").append(param.trim()).append("%' ");
		}
		return sb.toString();
	}

	/**
	 * 组装 like 'xxxx%'
	 * 
	 * @param column 列名
	 * @param param 查询值
	 * @return hql
	 */
	public static String popuHqlRLike(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append(" like '").append(param.trim()).append("%' ");
		}
		return sb.toString();
	}

	/**
	 * 组装equals hql eg:col>'param'
	 * 
	 * @param column列名
	 * @param param参数
	 * @return
	 */
	public static String popuHqlGt(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append(">'").append(param.trim()).append("' ");
		}
		return sb.toString();
	}
	public static String popuHqlGtNum(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append(">").append(param.trim()).append(" ");
		}
		return sb.toString();
	}

	/**
	 * 组装equals hql eg:col>='param'
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuHqlGteq(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append(">='").append(param.trim()).append("' ");
		}
		return sb.toString();
	}
	public static String popuHqlGteqNum(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append(">=").append(param.trim()).append(" ");
		}
		return sb.toString();
	}

	/**
	 * 组装equals hql eg:col<='param'
	 * @param column
	 * @param param
	 * @return String
	 * @throws
	 */
	public static String popuHqlGtov(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append("<='").append(param.trim()).append("' ");
		}
		return sb.toString();
	}
	
	/**
	 * 组装equals hql eg:col<'param'
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuHqlGtxy(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append("<'").append(param.trim()).append("' ");
		}
		return sb.toString();
	}
	
	/**
	 * 组装equals hql eg:col='param'
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuHqlEq(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append("='").append(param.trim()).append("' ");
		}
		return sb.toString();
	}
	/**
	 * 组装equals hql eg:col!='param'
	 * @param column
	 * @param param
	 * @return
	 */
	public static String popuHqlNe(String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(param)) {
			sb.append(" and ").append(column).append("!='").append(param.trim()).append("' ");
		}
		return sb.toString();
	}

	/**
	 * 查询的开始时间,按日期日期格式查询
	 * 
	 * @param queryCondition
	 * @return
	 */
	public static String popuBeginDateHql(String coloumn, String queryCondition) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(queryCondition)) {
			sb.append(" and ").append(coloumn).append(" >=to_date('").append(queryCondition + " 00:00:00','YYYY-MM-DD HH24:MI:SS') ");
		}
		return sb.toString();
	}

	/**
	 * 查询的开始时间,按字符串日期格式查询
	 * 
	 * @param coloumn
	 * @param queryCondition
	 * @return
	 */
	public static String popuBeginStringDateHql(String coloumn, String queryCondition) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(queryCondition)) {
			sb.append(" and ").append(coloumn).append(" >='").append(DateUtils.formatDS(queryCondition) + "000000' ");
		}
		return sb.toString();
	}

	/**
	 * 查询的结束时间,按日期日期格式查询
	 * 
	 * @param queryCondition
	 * @return
	 */
	public static String popuEndDateHql(String coloumn, String queryCondition) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(queryCondition)) {
			sb.append(" and ").append(coloumn).append(" <=to_date('").append(queryCondition + " 23:59:59','YYYY-MM-DD HH24:MI:SS') ");
		}
		return sb.toString();
	}

	/**
	 * 查询的结束时间,按字符串日期格式查询
	 * 
	 * @param coloumn
	 * @param queryCondition
	 * @return
	 */
	public static String popuEndStringDateHql(String coloumn,
			String queryCondition) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(queryCondition)) {
			sb.append(" and ").append(coloumn).append(" <='").append(DateUtils.formatDS(queryCondition) + "235959' ");
		}
		return sb.toString();
	}

	/**
	 * 组装equals hql
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuHqlEq(String column, Integer param) {
		StringBuilder sb = new StringBuilder();
		if (param != null) {
			sb.append(" and ").append(column).append("='").append(param).append("' ");
		}
		return sb.toString();
	}

	/**
	 * 组装equals hql
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuHqlEq(String column, Long param) {
		StringBuilder sb = new StringBuilder();
		if (param != null) {
			sb.append(" and ").append(column).append("='").append(param).append("' ");
		}
		return sb.toString();
	}

	/**
	 * 组装equals sql
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuSqlEq(String tableName, String column, String param) {
		StringBuilder sb = new StringBuilder();
		if (param != null && !"".equals(param)) {
			if (tableName != null && !"".equals(tableName)) {
				sb.append(" and ").append(tableName).append(".").append(column).append("='").append(param.trim()).append("' ");

			}
			else {
				sb.append(" and ").append(column).append("='").append(param.trim()).append("' ");
			}
		}
		return sb.toString();
	}

	/**
	 * 组装equals sql
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuSqlEq(String tableName, String column,
			Integer param) {
		StringBuilder sb = new StringBuilder();
		if (param != null && !"".equals(param)) {
			if (tableName != null && !"".equals(tableName)) {
				sb.append(" and ").append(tableName).append(".").append(column).append("=").append(param).append(" ");
			}
			else {
				sb.append(" and ").append(column).append("=").append(param).append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * 组装equals sql
	 * 
	 * @param column 列名
	 * @param param 参数
	 * @return
	 */
	public static String popuSqlEq(String tableName, String column, Long param) {
		StringBuilder sb = new StringBuilder();
		if (param != null && !"".equals(param)) {
			if (tableName != null && !"".equals(tableName)) {
				sb.append(" and ").append(tableName).append(".").append(column).append("=").append(param).append(" ");
			}
			else {
				sb.append(" and ").append(column).append("=").append(param).append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * 带通配符判断的SQL 组装
	 * 
	 * @param column
	 * @param param
	 * @return 
	 *         ~------------------------------------------------------------------
	 *         ------- 修改使用替换方法获得SQL 模糊匹配符号
	 * 
	 */
	public static String pSqlLike(String tableName, String column, String param) {
		if (column != null && !"".equals(column) && param != null && !"".equals(param)) {
			String sql = "";
			if (tableName != null && !"".equals(tableName)) {
				sql += " and " + tableName + "." + column;
			}
			else {
				sql += " and " + column;
			}
			sql += "='" + param + "' ";
			return sql;

		}
		return "";
	}

	/**
	 * 查询的开始时间
	 * 
	 * @param queryCondition
	 * @return
	 */
	public static String popuBeginDateSql(String tableName, String coloumn, String queryCondition) {
		StringBuilder sb = new StringBuilder();

		if (queryCondition != null && queryCondition.trim().length() != 0) {
			if (tableName != null && !"".equals(tableName)) {
				sb
				.append(" and ")
				.append(tableName)
				.append(".")
				.append(coloumn)
				.append(" >=to_date('")
				.append(queryCondition + " 00:00:00','YYYY-MM-DD HH24:MI:SS') ");
			}
			else {
				sb
				.append(" and ")
				.append(coloumn)
				.append(" >=to_date('")
				.append(queryCondition + " 00:00:00','YYYY-MM-DD HH24:MI:SS') ");
			}
		}
		return sb.toString();
	}

	/**
	 * 查询的结束时间
	 * 
	 * @param queryCondition
	 * @return
	 */
	public static String popuEndDateSql(String tableName, String coloumn, String queryCondition) {
		StringBuilder sb = new StringBuilder();
		if (queryCondition != null && queryCondition.trim().length() != 0) {
			if (tableName != null && !"".equals(tableName)) {
				sb
				.append(" and ")
				.append(tableName)
				.append(".")
				.append(coloumn)
				.append(" <=to_date('")
				.append(queryCondition + " 23:59:59','YYYY-MM-DD HH24:MI:SS') ");
			}
			else {
				sb
				.append(" and ")
				.append(coloumn)
				.append(" <=to_date('")
				.append(queryCondition + " 23:59:59','YYYY-MM-DD HH24:MI:SS') ");
			}
		}
		return sb.toString();
	}

}
