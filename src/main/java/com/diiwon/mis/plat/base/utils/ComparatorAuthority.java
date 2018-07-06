package com.diiwon.mis.plat.base.utils;

import java.util.Comparator;

import com.diiwon.mis.plat.base.pojo.TMisQx;

/**
 * TMisAuthority的比较类，实现Comparator接口
 * @author sunshine
 *
 */
public class ComparatorAuthority implements Comparator<TMisQx> {

	public int compare(TMisQx A1, TMisQx A2) {
		return A1.getSortOrder().compareTo(A2.getSortOrder());
	}
}
