package com.khiya.cache;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class CacheObject {

	private final Object object;
	private final String name;
	private final LocalDateTime killDate;
	private final boolean isKillable;

	public CacheObject(String name, Object object) {
		this.name = name;
		this.object = object;
		this.killDate = null;
		this.isKillable = false;
	}

	public CacheObject(String name, Object object, long timeToCache, TimeUnit timeUnit) {
		this.name = name;
		this.object = object;
		this.killDate = getKillDate(timeToCache, timeUnit);
		this.isKillable = this.killDate != null ? true : false;
	}

	private LocalDateTime getKillDate(long timeToCache, TimeUnit timeUnit) {
		return LocalDateTime.now().plusSeconds(timeUnit.toSeconds(timeToCache));
	}

	public String getName() {
		return name;
	}

	public Object getObject() {
		return object;
	}

	public LocalDateTime getKillDate() {
		return killDate;
	}

	public boolean isKillable() {
		return isKillable;
	}

}
