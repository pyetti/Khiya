package com.khiya.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.khiya.cache.container.CacheContainer;

public class IntegrationTest {

	@Test
	public void testCache() {
		RandoObject rando = new RandoObject("RandoThing", 1);

		CacheContainer.cacheObject("rando", rando);
		RandoObject returnedToMe = CacheContainer.getObject("rando", RandoObject.class);
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());

		RandoObject rando2 = new RandoObject("RandoThing2", 2);

		CacheContainer.cacheObject("rando2", rando2);
		RandoObject returnedToMe2 = CacheContainer.getObject("rando2", RandoObject.class);
		assertNotNull(returnedToMe2);
		assertEquals("RandoThing2", returnedToMe2.getThing());
		assertEquals(2, returnedToMe2.getOtherThing());

		CacheContainer.cacheObject("rando", rando);
		returnedToMe = CacheContainer.getObject("rando", RandoObject.class);
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());

		CacheContainer.cacheObject("rando2", rando2);
		returnedToMe2 = CacheContainer.getObject("rando2", RandoObject.class);
		assertNotNull(returnedToMe2);
		assertEquals("RandoThing2", returnedToMe2.getThing());
		assertEquals(2, returnedToMe2.getOtherThing());
	}

}

class RandoObject {
	private String thing;
	private int otherThing;

	public RandoObject(String thing, int otherThing) {
		this.thing = thing;
		this.otherThing = otherThing;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public int getOtherThing() {
		return otherThing;
	}

	public void setOtherThing(int otherThing) {
		this.otherThing = otherThing;
	}

}