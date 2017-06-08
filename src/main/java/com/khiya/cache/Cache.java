package com.khiya.cache;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

	private static final Map<String, CacheObject> cacheContainer = new ConcurrentHashMap<String, CacheObject>(7);

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
	public static void store(String name, Object object) {
		CacheObject cacheObject = new CacheObject(name, object);
		cacheContainer.put(name, cacheObject);
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
	public static <T extends Object> T get(String name, Class<T> type) {
		return type.cast(cacheContainer.get(name).getObject());
	}

	/**
	 * Removes the cached object from the cache
	 * 
	 *  @param name
	 *  	the name of the object to remove
	 *  
	 *  @return boolean
	 *  	If the cached exists in the cache and was removed
	 * */
	public static boolean remove(String name) {
		return cacheContainer.remove(name) != null;
	}

	/**
	 * Iterates over the cache and removes each cached object if they are both killable 
	 * and their kill date is before LocalDateTime.now()
	 * 
	 * */
	public static void prune() {
		LocalDateTime now = LocalDateTime.now();
		cacheContainer.entrySet().removeIf(entry -> entry.getValue().isKillable() && entry.getValue().getKillDate().isBefore(now));
	}

}
