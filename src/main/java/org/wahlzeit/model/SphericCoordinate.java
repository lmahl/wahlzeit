/**
 * SphericCoordinate
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
 * Class that represents a point with spherical coordinates
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final double CIRCLE_DEGREES = 360.0;
	private static final String INVALID_RADIUS = "radius must be positive or 0";
	private static final String INVALID_AZIMUTH = "azimuthal angle must be in interval [0, 360[";
	private static final String INVALID_POLE = "polar angle must be in interval [0, 180[";

	private final double radius;
	private final double polarAngle;
	private final double azimuthalAngle;

	/**
	 * @methodype constructor
	 * @pre radius is not null and not smaller than 0
	 * @pre polar angle is not null and in interval [0,180[
	 * @pre azimuthal angle is not null and in interval[0,360[
	 * @post internal values are set to parameter values
	 * @param radius distance from point to cartesian origin
	 * @param polarAngle polar angle of the point
	 * @param azimuthalAngle azimuthal angle of the point
	 */
	public SphericCoordinate(double radius, double polarAngle, double azimuthalAngle) {
		log = Logger.getLogger(SphericCoordinate.class.getName());
		assertArgumentFiniteDouble(radius);
		assertArgumentFiniteDouble(polarAngle);
		assertArgumentFiniteDouble(azimuthalAngle);
		if(azimuthalAngle < 0 || azimuthalAngle >= 360){
			throw new IllegalArgumentException(INVALID_AZIMUTH);
		}
		if(polarAngle < 0 || polarAngle >= 180){
			throw new IllegalArgumentException(INVALID_POLE);
		}
		if(radius < 0){
			throw new IllegalArgumentException(INVALID_RADIUS);
		}

		this.radius = radius;
		this.polarAngle = polarAngle;
		this.azimuthalAngle = azimuthalAngle;
		EPSILON = 0.00001;

		assert(this.radius == radius);
		assert(this.azimuthalAngle == azimuthalAngle);
		assert(this.polarAngle == polarAngle);
		assertClassInvariants();
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

	@Override
	protected CartesianCoordinate doAsCartesianCoordinate() {
		double xPosition = this.radius * Math.sin(Math.toRadians(this.polarAngle))
				* Math.cos(Math.toRadians(this.azimuthalAngle));

		double yPosition = this.radius * Math.sin(Math.toRadians(this.polarAngle))
				* Math.sin(Math.toRadians(this.azimuthalAngle));

		double zPosition = this.radius * Math.cos(Math.toRadians(this.polarAngle));

		return new CartesianCoordinate(xPosition, yPosition, zPosition);
	}

	@Override
	protected SphericCoordinate doAsSphericCoordinate() {
		return this;
	}

	@Override
	protected boolean doIsEqual(Coordinate other) {
		SphericCoordinate otherSpheric = other.asSphericCoordinate();
		boolean isRadiusEqual = isDoubleEqual(this.radius, otherSpheric.getRadius());
		boolean isAzimuthalAngleEqual = isAngleEqual(this.azimuthalAngle, otherSpheric.getAzimuthalAngle());
		boolean isPolarAngleEqual = isAngleEqual(this.polarAngle, otherSpheric.getPolarAngle());

		if(isDoubleEqual(radius, 0.0)){
			return (isRadiusEqual);
		}
		return (isRadiusEqual && isAzimuthalAngleEqual && isPolarAngleEqual);
	}

	@Override
	protected boolean doEquals(Object other) {
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

	/**
	 * checks if two angles are equal within a specified precision and congruent on a 360 degree circle
	 * @methodype helper
	 * @return whether the two values are equal
	 */
	private boolean isAngleEqual(double a, double b) {
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
	private double getPositiveModulus(double value, double mod) {
		double result = value % mod;
		if (result < 0) {
			result = result + mod;
		}

		return result;
	}

	protected void assertClassInvariants(){
		assert (EPSILON >= 0);
		assert (Double.isFinite(radius));
		assert (radius >= 0);
		assert (Double.isFinite(azimuthalAngle));
		assert (Double.isFinite(polarAngle));
		assert (azimuthalAngle >= 0);
		assert (azimuthalAngle < 360);
		assert (polarAngle >= 0);
		assert (polarAngle < 180);
	}
}
