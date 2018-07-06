package com.diiwon.mis.plat.base.cache.imp;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 算法根据数据的历史访问频率来淘汰数据，其原理是如果数据过去被访问次数越多，将来被访问的几概率相对比较高。LFU的每个数据块都有一个引用计数，所有数据块按照引用计数排序，具有相同引用计数的数据块则按照时间排序。
 具体算法如下：
 1. 新加入数据插入到队列尾部（因为引用计数为1）；
 2. 队列中的数据被访问后，引用计数增加，队列重新排序；
 3. 当需要淘汰数据时，将已经排序的列表最后的数据块删除；
 
 * @author Sunshine
 *
 * @param <K>
 * @param <V>
 */
public class LFUCache<K,V> extends AbstractCacheMap<K, V> {

	public LFUCache(int cacheSize, long defaultExpire) {
		super(cacheSize, defaultExpire);
		
		cacheMap = new HashMap<K, CacheObject<K,V>>(cacheSize+1);
	}

	/**
	 * 实现删除过期对象 和 删除访问次数最少的对象 
	 * 
	 */
	@Override
	protected int eliminateCache() {
		Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
		int count  = 0 ;
		long minAccessCount = Long.MAX_VALUE  ;
		while(iterator.hasNext()){
			CacheObject<K, V> cacheObject = iterator.next();
			
			if(cacheObject.isExpired() ){
				iterator.remove(); 
				count++ ;
				continue ;
			}else{
				minAccessCount  = Math.min(cacheObject.accessCount , minAccessCount)  ;
			}
		}
		
		if(count > 0 ) return count ;
		
		if(minAccessCount != Long.MAX_VALUE ){
			iterator = cacheMap.values().iterator();
			
			while(iterator.hasNext()){
				CacheObject<K, V> cacheObject = iterator.next();
				cacheObject.accessCount  -=  minAccessCount ;
				
				if(cacheObject.accessCount <= 0 ){
					iterator.remove();
					count++ ;
				}
			}
		}
		
		return count;
	}

}
