package com.khiya.cache.prune;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.khiya.cache.CacheContainer;

public class CachePruner extends TimerTask implements Pruner {

	private final boolean isRunning;
	private final CachePruningRunner cachePruningRunner;
	private final ExecutorService executor;
	private final Timer timer = new Timer();

	public CachePruner(long delay, long period) {
		this.cachePruningRunner = new CachePruningRunner();
		this.executor = Executors.newFixedThreadPool(1);
		this.isRunning = startTask(delay, period);
	}

	public CachePruner(long delay, TimeUnit delayUnit, long period, TimeUnit periodUnit) {
		this.cachePruningRunner = new CachePruningRunner();
		this.executor = Executors.newFixedThreadPool(1);
		this.isRunning = startTask(delay, delayUnit, period, periodUnit);
	}

	public void prune() {
		CacheContainer.getInstance().prune();
	}

	private boolean startTask(long delay, long period) {
		if (period > 1000) {
			timer.cancel();
			this.timer.purge();
			this.timer.schedule(this, delay, period);
			return true;
		}
		return false;
	}

	private boolean startTask(long delay, TimeUnit delayUnit, long period, TimeUnit periodUnit) {
		this.timer.cancel();
		this.timer.purge();
		this.timer.schedule(this, delayUnit.toMillis(delay), periodUnit.toMillis(period));
		return true;
	}

	@Override
	public void run() {
		executor.execute(cachePruningRunner);
	}

	public Timer getTimer() {
		return timer;
	}

	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void finalize() {
		executor.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!executor.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("executor did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			executor.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	private class CachePruningRunner implements Runnable {

		public void run() {
			prune();
		}

	}

}
