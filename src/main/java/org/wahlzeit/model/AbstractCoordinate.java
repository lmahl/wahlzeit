/**
 * AbstractCoordinate
 *
 * Version: 1.0
 *
 * Date: 25.11.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{
    protected static double EPSILON = 0.0;

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
     * @param other other coordinate to calculate the cartesian distance between
     * @return cartesian distance between the two coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate other) {
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        return Math.sqrt(Math.pow(thisCartesian.getXPosition()- otherCartesian.getXPosition(), 2)
                + Math.pow(thisCartesian.getYPosition() - otherCartesian.getYPosition(), 2)
                + Math.pow(thisCartesian.getZPosition() - otherCartesian.getZPosition(), 2));
    }

    /**
     * Calculates the central angle between two points using the harvesine formula
     * @methodtype get
     * @param other coordinate to calculate the central angle between
     * @return central angle between the two coordinates
     */
    @Override
    public double getCentralAngle(Coordinate other) {
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

}
