package com.khiya.cache;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.khiya.cache.prune.CachePruner;

public class CacheContainer implements Cache {

	private static Cache cache;
	private CachePruner cachePruner;
	private static final Map<String, CacheObject> cacheContainer = new ConcurrentHashMap<String, CacheObject>(11);

	private CacheContainer() {
		if (this.cachePruner == null) {
			this.cachePruner = new CachePruner(0, 0);
		}
	}

	public static Cache getInstance() {
		if (cache == null) {
			cache = new CacheContainer();
		}
		return cache;
	}

	@Override
	public void store(String name, Object object) {
		CacheObject cacheObject = new CacheObject(name, object);
		cacheContainer.put(name, cacheObject);
	}

	@Override
	public <T extends Object> T get(String name, Class<T> type) {
		return type.cast(cacheContainer.get(name).getObject());
	}

	@Override
	public boolean remove(String name) {
		return cacheContainer.remove(name) != null;
	}

	@Override
	public void prune() {
		LocalDateTime now = LocalDateTime.now();
		cacheContainer.entrySet().removeIf(entry -> entry.getValue().isKillable() && entry.getValue().getKillDate().isBefore(now));
	}

	@Override
	public boolean setPruningTimer(long delay, TimeUnit delayUnit, long period, TimeUnit periodUnit) {
		this.cachePruner = new CachePruner(delay, period);
		return this.cachePruner.isRunning();
	}

}
