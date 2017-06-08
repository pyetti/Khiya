package com.khiya.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.khiya.cache.CacheContainer;
import com.khiya.testutils.RandoObject;

public class CacheIT {

	@Test
	public void testCache() {
		RandoObject rando = new RandoObject("RandoThing", 1);

		CacheContainer.getInstance().store("rando", rando);
		RandoObject returnedToMe = CacheContainer.getInstance().get("rando", RandoObject.class);
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());

		RandoObject rando2 = new RandoObject("RandoThing2", 2);

		CacheContainer.getInstance().store("rando2", rando2);
		RandoObject returnedToMe2 = CacheContainer.getInstance().get("rando2", RandoObject.class);
		assertNotNull(returnedToMe2);
		assertEquals("RandoThing2", returnedToMe2.getThing());
		assertEquals(2, returnedToMe2.getOtherThing());

		CacheContainer.getInstance().store("rando", rando);
		returnedToMe = CacheContainer.getInstance().get("rando", RandoObject.class);
		assertNotNull(returnedToMe);
		assertEquals("RandoThing", returnedToMe.getThing());
		assertEquals(1, returnedToMe.getOtherThing());

		CacheContainer.getInstance().store("rando2", rando2);
		returnedToMe2 = CacheContainer.getInstance().get("rando2", RandoObject.class);
		assertNotNull(returnedToMe2);
		assertEquals("RandoThing2", returnedToMe2.getThing());
		assertEquals(2, returnedToMe2.getOtherThing());
	}

}