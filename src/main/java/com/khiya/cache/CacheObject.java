package com.khiya.cache;

public class CacheObject {

	private final Object object;
	private final String name;

	public CacheObject(String name, Object object) {
		this.name = name;
		this.object = object;
	}

	public String getName() {
		return name;
	}

	public Object getObject() {
		return object;
	}

}
