package com.khiya.cache.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.khiya.cache.CacheObject;

public class CacheContainer {
	
	private static final Map<String, Map<String, CacheObject>> cacheContainer = new ConcurrentHashMap<String, Map<String, CacheObject>>(9);

	/**
	 * If the RunTime type associated with the object exists in the cache,
	 * the object is added.
	 * Else, a new cache for the type is created and added to the and the
	 * object is added
	 * 
	 * @param name
	 * 		The name associated with the object. Used for retrieval
	 * @param object
	 * 		The object to be cached
	 * */
	public static void cacheObject(String name, Object object) {
		CacheObject cacheObject = new CacheObject(name, object);
		if (cacheContainer.containsKey(object.getClass().getName())) {
			cacheContainer.get(object.getClass().getName()).put(name, cacheObject);
			return;
		}
		cacheContainer.put(object.getClass().getName(), new ConcurrentHashMap<String, CacheObject>(7));
		cacheContainer.get(object.getClass().getName()).put(name, cacheObject);
		
	}

	/**
	 * Uses the passed in class type to get the appropriate cache to find 
	 * the object and to cast the object to the required return type.
	 * 
	 * @param name
	 * 		The name of the object that was used when it was stored
	 * @param type
	 * 		The runtime type of the object
	 * */
	public static <T extends Object> T getObject(String name, Class<T> type) {
		Object cachedObject = cacheContainer.get(type.getName()).get(name).getObject();
		return type.cast(cachedObject);
	}

}
