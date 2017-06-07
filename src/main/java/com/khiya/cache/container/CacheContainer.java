package com.khiya.cache.container;

import com.khiya.cache.CacheObject;

public class CacheContainer {

	public static void cacheObject(String key, Object value) {
		CacheObject cacheObject = new CacheObject(value);
		
	}

	public static <T extends Object> T getObject(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
