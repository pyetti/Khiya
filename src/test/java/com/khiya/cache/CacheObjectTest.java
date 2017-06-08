package com.khiya.cache;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.khiya.testutils.RandoObject;

public class CacheObjectTest {

	@Test
	public void test_CacheObjectNoKilldate() {
		RandoObject randoObject = new RandoObject("name", 1);
		CacheObject cacheObject = new CacheObject("rando", randoObject);
		assertFalse(cacheObject.isKillable());
		assertNull(cacheObject.getKillDate());
	}

	@Test
	public void test_CacheObjectWithKilldate() {
		RandoObject randoObject = new RandoObject("name", 1);
		CacheObject cacheObject = new CacheObject("rando", randoObject, 1, TimeUnit.DAYS);
		assertTrue(cacheObject.isKillable());
		assertNotNull(cacheObject.getKillDate());
		assertTrue(cacheObject.getKillDate().isAfter(LocalDateTime.now()));
	}

}
