/**
 * SphericCoordinate
 *
 * Version: 1.0
 *
 * Date: 18.11.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

/**
 * Class that represents a point with spherical coordinates
 */
public class SphericCoordinate extends AbstractCoordinate{

    private static final double CIRCLE_DEGREES = 360.0;

    private final double radius;
    private final double polarAngle;
    private final double azimuthalAngle;

    /**
     * @methodype constructor
     * @param radius distance from point to cartesian origin
     * @param polarAngle polar angle of the point
     * @param azimuthalAngle azimuthal angle of the point
     */
    public SphericCoordinate(double radius, double polarAngle, double azimuthalAngle){
        this.radius = radius;
        this.polarAngle = polarAngle;
        this.azimuthalAngle = azimuthalAngle;
        EPSILON = 0.00001;
    }

    /**
     * @methodtype get
     * @return distance from point to cartesian origin
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype get
     * @return polar angle of the point
     */
    public double getPolarAngle() {
        return polarAngle;
    }

    /**
     * @methodtype get
     * @return azimuthal angle of the point
     */
    public double getAzimuthalAngle() {
        return azimuthalAngle;
    }

    /**
     * @methodtype conversion method
     * @return cartesian representation of spherical coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double xPosition = this.radius * Math.sin(Math.toRadians(this.polarAngle))
                * Math.cos(Math.toRadians(this.azimuthalAngle));

        double yPosition = this.radius * Math.sin(Math.toRadians(this.polarAngle))
                * Math.sin(Math.toRadians(this.azimuthalAngle));

        double zPosition = this.radius * Math.cos(Math.toRadians(this.polarAngle));

        return new CartesianCoordinate(xPosition, yPosition, zPosition);
    }

    /**
     * @methodtype conversion method
     * @return spherical representation of spherical coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * @methodtype comparison method
     * @param other coordinate to compare this with
     * @return returns whether the two coordinates are at the same point in space
     */
    @Override
    public boolean isEqual(Coordinate other) {
        SphericCoordinate otherSpheric = other.asSphericCoordinate();
        boolean isRadiusEqual = isDoubleEqual(this.radius, otherSpheric.getRadius());
        boolean isAzimuthalAngleEqual = isAngleEqual(this.azimuthalAngle, otherSpheric.getAzimuthalAngle());
        boolean isPolarAngleEqual = isAngleEqual(this.polarAngle, otherSpheric.getPolarAngle());

        return (isRadiusEqual && isAzimuthalAngleEqual && isPolarAngleEqual);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Coordinate)){
            return false;
        }
        Coordinate otherCoordinate = (Coordinate)other;
        return this.isEqual(otherCoordinate);
    }

    /**
     * checks if two double values are equal within a specified precision
     * @methodype helper
     * @return whether the two values are equal
     */
    private boolean isDoubleEqual(double a, double b){
        return (Math.abs(a - b) < this.getEpsilon());
    }

    /**
     * checks if two angles are equal within a specified precision and congruent on a 360 degree circle
     * @methodype helper
     * @return whether the two values are equal
     */
    private boolean isAngleEqual (double a, double b){
        double modA = getPositiveModulus(a, CIRCLE_DEGREES);
        double modB = getPositiveModulus(b, CIRCLE_DEGREES);
        return isDoubleEqual(modA, modB);
    }

    /**
     * @methodtype helper
     * @param value
     * @param mod
     * @return returns value % mod, where a possible negative value is converted to a positive one
     */
    private double getPositiveModulus(double value, double mod){
        double result = value % mod;
        if (result < 0){
            result = result + mod;
        }

        return result;
    }
}
