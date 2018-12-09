/**
 * CartesianCoordinate
 * <p>
 * Version: 1.0
 * <p>
 * Date: 18.11.2018
 * <p>
 * License: AGPLv3
 */

package org.wahlzeit.model;

import java.util.logging.Logger;

/**
 * Class that represents a point with cartesian coordinates
 */
public class CartesianCoordinate extends AbstractCoordinate {

	private final double xPosition;
	private final double yPosition;
	private final double zPosition;

	/**
	 * @methodtype constructor
	 * @post internal values are set to parameter values
	 * @param xPosition position on the x-axis of the coordinate space
	 * @param yPosition position on the y-axis of the coordinate space
	 * @param zPosition position on the z-axis of the coordinate space
	 */
	public CartesianCoordinate(double xPosition, double yPosition, double zPosition) {
        log = Logger.getLogger(CartesianCoordinate.class.getName());

		assertArgumentFiniteDouble(xPosition);
		assertArgumentFiniteDouble(yPosition);
		assertArgumentFiniteDouble(zPosition);

		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.zPosition = zPosition;
		EPSILON = 0.00001;

		assert (this.xPosition == xPosition);
		assert (this.yPosition == yPosition);
		assert (this.zPosition == zPosition);
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 * @return an array containing x, y and z cartesian coordinate values
	 */
	public double[] getPosition() {
		return new double[]{xPosition, yPosition, zPosition};
	}

	/**
	 * @methodtype get
	 * @return cartesian coordinate value for x-axis
	 */
	public double getXPosition() {
		return this.xPosition;
	}

	/**
	 * @methodtype get
	 * @return cartesian coordinate value for y-axis
	 */
	public double getYPosition() {
		return this.yPosition;
	}

	/**
	 * @methodtype get
	 * @return cartesian coordinate value for z-axis
	 */
	public double getZPosition() {
		return this.zPosition;
	}

	@Override
	public CartesianCoordinate doAsCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate doAsSphericCoordinate() {
		double radius;
		double polarAngle;
		double azimuthalAngle;
		double intermediate;

		radius = Math.sqrt(this.xPosition * this.xPosition +
				this.yPosition * this.yPosition +
				this.zPosition * this.zPosition);

		if (isDoubleEqual(this.zPosition, 0.0)) {
			polarAngle = 90;
		} else {
			intermediate = Math.sqrt(this.xPosition * this.xPosition + this.yPosition * this.yPosition) / this.zPosition;
			polarAngle = Math.abs(Math.toDegrees(Math.atan(intermediate)));
		}

		if (isDoubleEqual(this.xPosition, 0.0)) {
			azimuthalAngle = 90;
		} else {
			azimuthalAngle = Math.abs(Math.toDegrees(Math.atan(this.yPosition / this.xPosition)));
		}

		return new SphericCoordinate(radius, polarAngle, azimuthalAngle);
	}

	@Override
	protected boolean doIsEqual(Coordinate other) {
		CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
		boolean isXEqual = isDoubleEqual(this.xPosition, otherCartesian.getXPosition());
		boolean isYEqual = isDoubleEqual(this.yPosition, otherCartesian.getYPosition());
		boolean isZEqual = isDoubleEqual(this.zPosition, otherCartesian.getZPosition());
		return (isXEqual && isYEqual && isZEqual);
	}

	@Override
	protected boolean doEquals(Object other){
		if (!(other instanceof Coordinate)) {
			return false;
		}
		Coordinate otherCoordinate = (Coordinate) other;
		return this.isEqual(otherCoordinate);
	}

	/**
	 * checks if two double values are equal within a specified precision
	 * @methodype helper
	 * @return whether the two values are equal
	 */
	private boolean isDoubleEqual(double a, double b) {
		return (Math.abs(a - b) < this.getEpsilon());
	}

	@Override
	protected void assertClassInvariants() {
		assert (EPSILON >= 0);
		assert (Double.isFinite(xPosition));
		assert (Double.isFinite(yPosition));
		assert (Double.isFinite(zPosition));
	}
}
