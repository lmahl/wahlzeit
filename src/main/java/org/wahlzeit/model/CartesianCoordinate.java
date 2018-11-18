/**
 * CartesianCoordinate
 *
 * Version: 1.0
 *
 * Date: 18.11.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

/**
 * Class that represents a point with cartesian coordinates
 */
public class CartesianCoordinate implements Coordinate {
    private static final double EPSILON = 0.00001;

    private final double xPosition;
    private final double yPosition;
    private final double zPosition;

    /**
     * @methodtype constructor
     * @param xPosition position on the x-axis of the coordinate space
     * @param yPosition position on the y-axis of the coordinate space
     * @param zPosition position on the z-axis of the coordinate space
     */
    public CartesianCoordinate(double xPosition, double yPosition, double zPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
    }

    /**
     * @methodtype get
     * @return an array containing x, y and z cartesian coordinate values
     */
    public double[] getPosition(){
        return new double[]{xPosition, yPosition, zPosition};
    }

    /**
     * @methodtype get
     * @return cartesian coordinate value for x-axis
     */
    public double getXPosition(){
        return  this.xPosition;
    }

    /**
     * @methodtype get
     * @return cartesian coordinate value for y-axis
     */
    public double getYPosition(){
        return  this.yPosition;
    }

    /**
     * @methodtype get
     * @return cartesian coordinate value for z-axis
     */
    public double getZPosition(){
        return  this.zPosition;
    }

    /**
     * @methodtype conversion method
     * @return cartesian representation of cartesian coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * @methodtype conversion method
     * @return spherical representation of cartesian coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius;
        double polarAngle;
        double azimuthalAngle;
        double intermediate;

        radius = Math.sqrt(this.xPosition * this.xPosition +
                this.yPosition * this.yPosition +
                this.zPosition * this.zPosition);

        if(isDoubleEqual(this.zPosition, 0.0)){
            polarAngle = 90;
        } else {
            intermediate = Math.sqrt(this.xPosition * this.xPosition + this.yPosition * this.yPosition) / this.zPosition;
            polarAngle = Math.abs(Math.toDegrees(Math.atan(intermediate)));
        }

        if(isDoubleEqual(this.xPosition, 0.0)) {
            azimuthalAngle = 90;
        }else{
            azimuthalAngle = Math.abs(Math.toDegrees(Math.atan(this.yPosition / this.xPosition)));
        }

        return new SphericCoordinate(radius, polarAngle, azimuthalAngle);
    }

    /**
     * @methodtype comparison method
     * @param other coordinate to compare this with
     * @return returns whether the two coordinates are at the same point in space
     */
    @Override
    public boolean isEqual(Coordinate other) {
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        boolean isXEqual = isDoubleEqual(this.xPosition, otherCartesian.getXPosition());
        boolean isYEqual = isDoubleEqual(this.yPosition, otherCartesian.getYPosition());
        boolean isZEqual = isDoubleEqual(this.zPosition, otherCartesian.getZPosition());
        return (isXEqual && isYEqual && isZEqual);
    }

    /**
     * Calculates the central angle between two points using the implementation of ShpericCoordinate
     * @methodtype get
     * @param other coordinate to calculate the central angle between
     * @return central angle between the two coordinates
     */
    @Override
    public double getCentralAngle(Coordinate other) {
        return this.asSphericCoordinate().getCentralAngle(other);
    }

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
        return Math.sqrt(Math.pow(this.xPosition - otherCartesian.getXPosition(), 2)
                + Math.pow(this.yPosition - otherCartesian.getYPosition(), 2)
                + Math.pow(this.zPosition - otherCartesian.getZPosition(), 2));
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
}
