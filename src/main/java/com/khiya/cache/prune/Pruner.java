package com.khiya.cache.prune;

public interface Pruner {

	public void startTask(long delay, long period);

	public void prune();

}
