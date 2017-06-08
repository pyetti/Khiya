package com.khiya.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.khiya.cache.Cache;

public class CacheIT {

	@Test
	public void testCache() {
		RandoObject rando = new RandoObject("RandoThing", 1);

		Cache.store("rando", rando);
		RandoObject returnedToMe = Cache.get("rando", RandoObject.class);
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());

		RandoObject rando2 = new RandoObject("RandoThing2", 2);

		Cache.store("rando2", rando2);
		RandoObject returnedToMe2 = Cache.get("rando2", RandoObject.class);
		assertNotNull(returnedToMe2);
		assertEquals("RandoThing2", returnedToMe2.getThing());
		assertEquals(2, returnedToMe2.getOtherThing());

		Cache.store("rando", rando);
		returnedToMe = Cache.get("rando", RandoObject.class);
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());

		Cache.store("rando2", rando2);
		returnedToMe2 = Cache.get("rando2", RandoObject.class);
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