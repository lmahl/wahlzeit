/**
 * CoordinateTest
 *
 * Version: 1.0
 *
 * Date: 28.10.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CoordinateTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAssignment(){
		Coordinate coordinate;
		coordinate = new CartesianCoordinate(1,2,3);
		coordinate = new SphericCoordinate(1,2,3);
	}

	@Test
	public void testConversion(){
		CartesianCoordinate c1 = new CartesianCoordinate(1,2,3);
		SphericCoordinate s1 = c1.asSphericCoordinate();
		CartesianCoordinate c1Back = s1.asCartesianCoordinate();

		assertEquals(c1, s1);
		assertEquals(s1, c1Back);
		assertEquals(c1, c1Back);
	}

}
