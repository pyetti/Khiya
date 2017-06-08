package com.khiya.cache.prune;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CachePrunerTest {

	@Test
	public void test_CachePrunerTimerNotStarted() {
		Pruner cachePruner = new CachePruner(0, 0);
		assertFalse(cachePruner.isRunning());
	}

	@Test
	public void test_CachePrunerTimerStartedNoTimeUnit() {
		Pruner cachePruner = new CachePruner(1000000, 1000000);
		assertTrue(cachePruner.isRunning());
	}

	@Test
	public void test_CachePrunerTimerStarted() {
		Pruner cachePruner = new CachePruner(1, TimeUnit.DAYS, 1, TimeUnit.HOURS);
		assertTrue(cachePruner.isRunning());
	}

}
