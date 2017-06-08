package com.khiya.cache;

import java.util.concurrent.TimeUnit;

public interface Cache {

	/**
	 * Iterates over the cache and removes each cached object if they are both
	 * killable and their kill date is before LocalDateTime.now()
	 * 
	 */
	void prune();

	/**
	 * Removes the cached object from the cache
	 * 
	 * @param name
	 *            the name of the object to remove
	 * 
	 * @return boolean If the cached exists in the cache and was removed
	 */
	boolean remove(String name);

	/**
	 * Uses the passed in class type to get the appropriate cache to find the
	 * object and to cast the object to the required return type.
	 * 
	 * @param name
	 *            The name of the object that was used when it was stored
	 * @param type
	 *            The runtime type of the object
	 */
	<T extends Object> T get(String name, Class<T> type);

	/**
	 * If the RunTime type associated with the object exists in the cache, the
	 * object is added. Else, a new cache for the type is created and added to
	 * the and the object is added
	 * 
	 * @param name
	 *            The name associated with the object. Used for retrieval
	 * @param object
	 *            The object to be cached
	 */
	void store(String name, Object object);

	/**
	 * 
	 * */
	boolean setPruningTimer(long delay, TimeUnit delayUnit, long period, TimeUnit periodUnit);

}
