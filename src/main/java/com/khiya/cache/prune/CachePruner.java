package com.khiya.cache.prune;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.khiya.cache.Cache;

public class CachePruner extends TimerTask implements Pruner {
	
	private final Timer timer;
	private final CachePruningRunner cachePruningRunner;
	private final ExecutorService executor;
	
	public CachePruner(Timer timer) {
		this.timer = timer;
		this.cachePruningRunner = new CachePruningRunner();
		this.executor = Executors.newFixedThreadPool(1);
	}

	public void prune() {
		Cache.prune();
	}

	@Override
	public void startTask(long delay, long period) {
		this.timer.purge();
		this.timer.schedule(this, delay, period);
	}

	@Override
	public void run() {
		executor.execute(cachePruningRunner);
	}

	private class CachePruningRunner implements Runnable {

		public void run() {
			prune();
		}
		
	}

}
