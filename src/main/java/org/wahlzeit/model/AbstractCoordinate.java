/**
 * AbstractCoordinate
 * <p>
 * Version: 1.0
 * <p>
 * Date: 25.11.2018
 * <p>
 * License: AGPLv3
 */

package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
    protected static double EPSILON = 0.0;
    private final String OBJECT_NOT_COORDINATE = "Argument must be of Type Coordinate";

    /**
     * @methodtype get
     * @return precision with which floating point variables are compared
     */
    @Override
    public double getEpsilon() {
        return EPSILON;
    }

    /**
     * Calculate the cartesian distance between two points
     * @methodtype get
     * @pre argument is not null
     * @post return vlaue is not null and positive
     * @param other other coordinate to calculate the cartesian distance between
     * @return cartesian distance between the two coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate other) {
        assertClassInvariants();
        assertIsNonNullArgument(other);
        double result = doGetCartesianDistance(other);
        assert (Double.isFinite(result));
        assert (result >= 0);
        assertClassInvariants();

        return result;
    }

    private double doGetCartesianDistance(Coordinate other) {
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        return Math.sqrt(Math.pow(thisCartesian.getXPosition() - otherCartesian.getXPosition(), 2)
                + Math.pow(thisCartesian.getYPosition() - otherCartesian.getYPosition(), 2)
                + Math.pow(thisCartesian.getZPosition() - otherCartesian.getZPosition(), 2));
    }

    /**
     * @methodtype comparison method
     * @pre argument is not null
     * @param other coordinate to compare this with
     * @return returns whether the two coordinates are at the same point in space
     */
    @Override
    public boolean isEqual(Coordinate other) {
        assertClassInvariants();
        assertIsNonNullArgument(other);

        boolean result = doIsEqual(other);

        assertClassInvariants();

        return result;
    }

    protected abstract boolean doIsEqual(Coordinate other);

    /**
     * Calculates the central angle between two points using the harvesine formula
     * @methodtype get
     * @pre parameter is not null
     * @post return value is not null and in intervall [0,360[
     * @param other coordinate to calculate the central angle between
     * @return central angle between the two coordinates
     */
    @Override
    public double getCentralAngle(Coordinate other) {
        assertClassInvariants();
        assertIsNonNullArgument(other);

        double result = doGetCentralAngle(other);

        assert (Double.isFinite(result));
        assert (result <=360 && result >= 0);
        assertClassInvariants();

        return result;
    }

    private double doGetCentralAngle(Coordinate other) {
        double intermediateA;
        double intermediateB;
        double intermediateC;
        SphericCoordinate otherSpheric = other.asSphericCoordinate();
        SphericCoordinate thisSpheric = this.asSphericCoordinate();

        intermediateC = Math.pow(Math.sin(Math.toRadians(Math.abs(thisSpheric.getPolarAngle() - otherSpheric.getPolarAngle()) / 2)), 2);
        intermediateB = Math.pow(Math.sin(Math.toRadians(Math.abs(thisSpheric.getAzimuthalAngle() - otherSpheric.getAzimuthalAngle()) / 2)), 2);
        intermediateA = Math.sqrt(intermediateB
                + Math.cos(Math.toRadians(thisSpheric.getPolarAngle()))
                * Math.cos(Math.toRadians(otherSpheric.getPolarAngle()))
                * intermediateC);

        return 2 * Math.asin(intermediateA);
    }

    /**
     * @methodtype conversion method
     * @return spherical representation of cartesian coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        SphericCoordinate result = doAsSphericCoordinate();
        assertClassInvariants();
        return result;
    }

    protected abstract SphericCoordinate doAsSphericCoordinate();

    /**
     * @methodtype conversion method
     * @return cartesian representation of cartesian coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        CartesianCoordinate result = doAsCartesianCoordinate();
        assertClassInvariants();
        return result;
    }

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    /**
     * Checks that the provided argument is not null
     * @methodtype assertion
     * @param argument
     */
    protected void assertIsNonNullArgument(Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException();
        }
    }

    protected void assertArgumentFiniteDouble(double d) {
        if (!Double.isFinite(d)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @pre argument is not null
     * @param other Object to compare with
     * @return true, if the two coordinates point to the same point in space
     */
    @Override
    public boolean equals(Object other) {
        assertClassInvariants();
        assertIsNonNullArgument(other);

        boolean result = doEquals(other);

        assertClassInvariants();

        return result;
    }

    protected abstract boolean doEquals(Object other);

    /**
     * Checks that the objects state is valid
     * @methodtype assertion
     */
    protected abstract void assertClassInvariants();

}
