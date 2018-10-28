package org.wahlzeit.model;

import org.junit.Test;

/**
 * Test class for {@link Location}.
 */
public class LocationTest {
	/**
	 *
	 */
	@Test
	public void testAssociatedCoordinates(){
		Location location = new Location(new Coordinate(0,0,0));
		assert (location.getCoordinate() != null);
	}
}
