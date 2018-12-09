/**
 * SphericCoordinatTest
 * <p>
 * Version: 1.0
 * <p>
 * Date: 18.11.2018
 * <p>
 * License: AGPLv3
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.exceptions.ContractPostconditionViolatedException;

import static junit.framework.TestCase.assertEquals;

public class SphericCoordinateTest {

	private SphericCoordinate s1;
	private SphericCoordinate s2;
	private SphericCoordinate origin;

	@Before
	public void setUp() throws Exception {
		s1 = new SphericCoordinate(5.4, 38, 22);
		s2 = new SphericCoordinate(12, 179, 359);
		origin = new SphericCoordinate(0, 90, 90);
	}

	@Test
	public void testInitialization() {
		assertEquals(5.4, s1.getRadius(), s1.getEpsilon());
		assertEquals(38, s1.getPolarAngle(), s1.getEpsilon());
		assertEquals(22, s1.getAzimuthalAngle(), s1.getEpsilon());

		assertEquals(12, s2.getRadius(), s2.getEpsilon());
		assertEquals(179, s2.getPolarAngle(), s2.getEpsilon());
		assertEquals(359, s2.getAzimuthalAngle(), s2.getEpsilon());

		assert (s1 instanceof AbstractCoordinate);
		assert (s1 instanceof Coordinate);
	}

	@Test
	public void testEquals() {
		SphericCoordinate originPointer = origin;
		SphericCoordinate originCopy = new SphericCoordinate(origin.getRadius(), origin.getPolarAngle(), origin.getAzimuthalAngle());
		SphericCoordinate coordinate = new SphericCoordinate(3.0, 4.0, 5.0);
		SphericCoordinate second = new SphericCoordinate(3.0, 4.0, 5.0);

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
	public void testGetCartesianDistance() throws ContractPostconditionViolatedException {
		assertEquals(16.552607510, s1.getCartesianDistance(s2), s1.getEpsilon());
		assertEquals(16.552607510, s2.getCartesianDistance(s1), s2.getEpsilon());

		assertEquals(12, s2.getCartesianDistance(origin), s2.getEpsilon());
		assertEquals(12, origin.getCartesianDistance(s2), origin.getEpsilon());
	}

	@Test
	public void testSphericConversion() {
		assertEquals(s1, s1.asSphericCoordinate());
		assertEquals(s2, s2.asSphericCoordinate());
		assertEquals(origin, origin.asSphericCoordinate());
	}

	@Test
	public void testCartesianConversion() {
		CartesianCoordinate c1 = s1.asCartesianCoordinate();
		CartesianCoordinate c2 = s2.asCartesianCoordinate();

		assertEquals(3.082489451, c1.getXPosition(), c1.getEpsilon());
		assertEquals(1.245406579, c1.getYPosition(), c1.getEpsilon());
		assertEquals(4.255258069, c1.getZPosition(), c1.getEpsilon());

		assertEquals(0.2093969802, c2.getXPosition(), c2.getEpsilon());
		assertEquals(-0.003655037, c2.getYPosition(), c2.getEpsilon());
		assertEquals(-11.99817234, c2.getZPosition(), c2.getEpsilon());
	}

	@Test
	public void testGetCentralAngle() throws ContractPostconditionViolatedException{
		assertEquals(1.1868238, origin.getCentralAngle(s1), s1.getEpsilon());
		assertEquals(1.1868238, s1.getCentralAngle(origin), s1.getEpsilon());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTooSmallRadius(){
		Coordinate sph = new SphericCoordinate(-1,2,3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAzimuthSmallRadius(){
		Coordinate sph = new SphericCoordinate(0,2,-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAzimuthTooBig(){
		Coordinate sph = new SphericCoordinate(0,2,360);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPolarTooSmall(){
		Coordinate sph = new SphericCoordinate(0,-1,1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPolarTooBig(){
		Coordinate sph = new SphericCoordinate(0,180,360);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEqualsNull(){
		s1.equals(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsEqualNull(){
		s1.isEqual(null);
	}
}
