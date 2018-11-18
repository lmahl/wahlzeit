/**
 * SphericCoordinatTest
 *
 * Version: 1.0
 *
 * Date: 18.11.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SphericCoordinateTest {

    private SphericCoordinate s1;
    private SphericCoordinate s2;
    private SphericCoordinate origin;

    @Before
    public void setUp() throws Exception {
        s1 = new SphericCoordinate(5.4, 38, 22);
        s2 = new SphericCoordinate(-3.4, -45, 550);
        origin = new SphericCoordinate(0, 90,90);
    }

    @Test
    public void testInitialization() {
        assertEquals(5.4, s1.getRadius(), s1.getEpsilon());
        assertEquals(38, s1.getPolarAngle(), s1.getEpsilon());
        assertEquals(22, s1.getAzimuthalAngle(), s1.getEpsilon());

        assertEquals(-3.4, s2.getRadius(), s1.getEpsilon());
        assertEquals(-45, s2.getPolarAngle(), s1.getEpsilon());
        assertEquals(550, s2.getAzimuthalAngle(), s1.getEpsilon());
    }

    @Test
    public void testEquals(){
        SphericCoordinate originPointer = origin;
        SphericCoordinate originCopy = new SphericCoordinate(origin.getRadius(), origin.getPolarAngle(), origin.getAzimuthalAngle());
        SphericCoordinate coordinate = new SphericCoordinate(3.0, 4.0, 5.0);
        SphericCoordinate second = new SphericCoordinate(3.0, 4.0, 5.0);
        SphericCoordinate secondExtra = new SphericCoordinate(3.0, 364.0, 365.0);
        SphericCoordinate secondNegative = new SphericCoordinate(3.0, -356.0, -355.0);

        assert (origin.equals(originPointer));
        assert (origin.equals(originCopy));
        assert (!origin.equals(coordinate));
        assert (coordinate.equals(second));

        assert (origin.isEqual(originPointer));
        assert (origin.isEqual(originCopy));
        assert (!origin.isEqual(coordinate));
        assert (coordinate.isEqual(second));

        assert (second.isEqual(secondNegative));
        assert (second.isEqual(secondExtra));
    }

    @Test
    public void testGetCartesianDistance(){
        assertEquals(8.7645291032, s1.getCartesianDistance(s2), s1.getEpsilon());
        assertEquals(8.7645291032, s2.getCartesianDistance(s1), s2.getEpsilon());

        assertEquals(3.4, s2.getCartesianDistance(origin), s2.getEpsilon());
        assertEquals(3.4, origin.getCartesianDistance(s2), origin.getEpsilon());
    }

    @Test
    public void testSphericConversion(){
        assertEquals(s1, s1.asSphericCoordinate());
        assertEquals(s2, s2.asSphericCoordinate());
        assertEquals(origin, origin.asSphericCoordinate());
    }

    @Test
    public void testCartesianConversion(){
        CartesianCoordinate c1 = s1.asCartesianCoordinate();
        CartesianCoordinate c2 = s2.asCartesianCoordinate();
        SphericCoordinate s1Extra = new SphericCoordinate(5.4, 398, 382);
        SphericCoordinate s1Negative = new SphericCoordinate(5.4,  -322, -338);
        CartesianCoordinate c1Extra = s1Extra.asCartesianCoordinate();
        CartesianCoordinate c1Negative = s1Negative.asCartesianCoordinate();

        assertEquals(3.082489451, c1.getXPosition(), c1.getEpsilon());
        assertEquals(1.245406579, c1.getYPosition(), c1.getEpsilon());
        assertEquals(4.255258069, c1.getZPosition(), c1.getEpsilon());

        assertEquals(-2.367638417, c2.getXPosition(), c2.getEpsilon());
        assertEquals(-0.417478533, c2.getYPosition(), c2.getEpsilon());
        assertEquals(-2.404163056, c2.getZPosition(), c2.getEpsilon());

        assertEquals(s1, s1Extra);
        assertEquals(s1, s1Negative);
    }

    @Test
    public void testGetCentralAngle(){
        assertEquals(1.1868238 ,origin.getCentralAngle(s1), s1.getEpsilon());
        assertEquals(1.1868238 ,s1.getCentralAngle(origin), s1.getEpsilon());
    }
}
