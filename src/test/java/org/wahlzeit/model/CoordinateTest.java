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

import org.junit.Test;

/**
 * Test class for {@link Guest}.
 */
public class CoordinateTest {

	@Test
	public void testCoordinateInitialization() {
		Coordinate coordinate = new Coordinate(3.4, 4.5, 5.6);
		assert(coordinate.getPosition().length == 3);
		assert(coordinate.getXPosition() == 3.4);
		assert(coordinate.getYPosition() == 4.5);
		assert(coordinate.getZPosition() == 5.6);
		assert(coordinate.getXPosition() == coordinate.getPosition()[0]);
		assert(coordinate.getYPosition() == coordinate.getPosition()[1]);
		assert(coordinate.getZPosition() == coordinate.getPosition()[2]);
	}

	@Test
	public void testCoordinateEquals() {
		Coordinate origin = new Coordinate(0,0,0);
		Coordinate originPointer = origin;
		Coordinate originCopy = new Coordinate(origin.getXPosition(), origin.getYPosition(), origin.getZPosition());
		Coordinate coordinate = new Coordinate(3.4, 4.5, 5.6);
		Coordinate second = new Coordinate(3.4, 4.5, 5.6);

		assert (origin.equals(originPointer));
		assert (origin.equals(originCopy));
		assert (!origin.equals(coordinate));
		assert (coordinate.equals(second));

		assert (origin.isEqual(originPointer));
		assert (origin.isEqual(originCopy));
		assert (!origin.isEqual(coordinate));
		assert (coordinate.isEqual(second));

	}

	@Test
	public void testCoordinateDistance() {
		Coordinate origin = new Coordinate(0,0,0);
		Coordinate originPointer = origin;
		Coordinate second = new Coordinate(3,4,5);

		assert (origin.getDistance(originPointer) == 0);
		assert (originPointer.getDistance(origin) == 0);
		assert (second.getDistance(origin) == Math.sqrt(9+16+25));
	}
}
