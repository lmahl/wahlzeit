/**
 * LocationTest
 *
 * Version: 1.0
 *
 * Date: 28.10.2018
 *
 * License: AGPLv3
 */

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
		Location location = new Location(new CartesianCoordinate(0,0,0));
		assert (location.getCoordinate() != null);
	}
}
