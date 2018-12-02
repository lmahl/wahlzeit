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

import static junit.framework.TestCase.assertEquals;

public class CartesianCoordinateTest {
    private CartesianCoordinate c1;
    private CartesianCoordinate c2;
    private CartesianCoordinate origin;

    @Before
    public void setUp() throws Exception {
        c1 = new CartesianCoordinate(3.0, 4.0, 5.0);
        c2 = new CartesianCoordinate(-3.1, -4.2, -5.3);
        ;
        origin = new CartesianCoordinate(0, 0, 0);
    }

    @Test
    public void testInitialization() {
        assertEquals(3, c1.getPosition().length);
        assertEquals(3.0, c1.getXPosition(), c1.getEpsilon());
        assertEquals(4.0, c1.getYPosition(), c1.getEpsilon());
        assertEquals(5.0, c1.getZPosition(), c1.getEpsilon());
        assertEquals(c1.getXPosition(), c1.getPosition()[0], c1.getEpsilon());
        assertEquals(c1.getYPosition(), c1.getPosition()[1], c1.getEpsilon());
        assertEquals(c1.getZPosition(), c1.getPosition()[2], c1.getEpsilon());
        assert (c1 instanceof AbstractCoordinate);
        assert (c1 instanceof Coordinate);
    }

    @Test
    public void testEquals() {
        CartesianCoordinate originPointer = origin;
        CartesianCoordinate originCopy = new CartesianCoordinate(origin.getXPosition(), origin.getYPosition(), origin.getZPosition());
        CartesianCoordinate coordinate = new CartesianCoordinate(3.0, 4.0, 5.0);
        CartesianCoordinate second = new CartesianCoordinate(3.0, 4.0, 5.0);

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
    public void testGetCartesianDistance() {
        Coordinate originPointer = origin;

        assert (origin.getCartesianDistance(originPointer) == 0);
        assert (originPointer.getCartesianDistance(origin) == 0);
        assert (c1.getCartesianDistance(origin) == Math.sqrt(9 + 16 + 25));
    }

    @Test
    public void testSphericConversion() {
        SphericCoordinate s1 = c1.asSphericCoordinate();
        SphericCoordinate s2 = c2.asSphericCoordinate();
        SphericCoordinate sOrigin = origin.asSphericCoordinate();

        assertEquals(7.0710678118655, s1.getRadius(), s1.getEpsilon());
        assertEquals(45.0, s1.getPolarAngle(), s1.getEpsilon());
        assertEquals(53.130102354156, s1.getAzimuthalAngle(), s1.getEpsilon());

        assertEquals(7.4390859653589, s2.getRadius(), s2.getEpsilon());
        assertEquals(44.565140015307, s2.getPolarAngle(), s2.getEpsilon());
        assertEquals(53.569141879838, s2.getAzimuthalAngle(), s2.getEpsilon());

        assertEquals(0, sOrigin.getRadius(), sOrigin.getEpsilon());
        assertEquals(90, sOrigin.getPolarAngle(), sOrigin.getEpsilon());
        assertEquals(90, sOrigin.getAzimuthalAngle(), sOrigin.getEpsilon());
    }

    @Test
    public void testCartesianConversion() {
        assertEquals(c1, c1.asCartesianCoordinate());
    }

    @Test
    public void testGetCentralAngle() {
        assertEquals(0.6435011, origin.getCentralAngle(c1), c1.getEpsilon());
        assertEquals(0.6435011, c1.getCentralAngle(origin), c1.getEpsilon());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalXPostion(){
        Coordinate cart = new CartesianCoordinate(Double.POSITIVE_INFINITY, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalYPostion(){
        Coordinate cart = new CartesianCoordinate(0, Double.POSITIVE_INFINITY, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalZPostion(){
        Coordinate cart = new CartesianCoordinate(0, 0, Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqualsNull(){
        c1.equals(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsEqualNull(){
        c1.isEqual(null);
    }
    
}
