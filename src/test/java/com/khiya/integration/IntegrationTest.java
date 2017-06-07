package com.khiya.integration;

import static org.junit.Assert.*;

import org.junit.Test;

import com.khiya.cache.container.CacheContainer;

public class IntegrationTest {

	@Test
	public void test() {
		RandoObject rando = new RandoObject("RandoThing", 1);

		CacheContainer.cacheObject("rando", rando);
		RandoObject returnedToMe = CacheContainer.getObject("rando");
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());
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