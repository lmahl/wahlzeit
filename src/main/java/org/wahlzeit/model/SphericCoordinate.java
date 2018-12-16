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

import org.wahlzeit.model.exceptions.ConversionFailedException;
import org.wahlzeit.model.exceptions.InvariantsIllegalException;
import org.wahlzeit.services.LogBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Class that represents a point with spherical coordinates
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final double CIRCLE_DEGREES = 360.0;
	private static final String INVALID_RADIUS = "radius must be positive or 0";
	private static final String INVALID_AZIMUTH = "azimuthal angle must be in interval [0, 360[";
	private static final String INVALID_POLE = "polar angle must be in interval [0, 180[";
	private final static String EPSILON_VIOLATED = "EPSILON must be >= 0";
	private final static String LOG_EPSILON_VIOLATED = "Class invariants violated: EPSILON must be >= 0";
	private final static String RADIUS_VIOLATED = "Radius must be >= 0";
	private final static String LOG_RADIUS_VIOLATED = "Class invariants violated: Radius must be >= 0";
	private final static String AZIMUTH_VIOLATED = "Azimuth must be in interval [0, 360[";
	private final static String LOG_AZIMUTH_VIOLATED = "Class invariants violated: Azimuth must be in interval [0, 360[";
	private final static String POLAR_VIOLATED = "Polar angle must be in interval [0, 180[";
	private final static String LOG_POLAR_VIOLATED = "Class invariants violated: Polar angle must be in interval [0, 180[";
	private final static String CARTESIAN_CONVERSION_FAILED = "Failed to convert to CartesianCoordinate";
	private final static String LOG_CARTESIAN_CONVERSION_FAILED = "Failed to convert to CartesianCoordinate";

	private static Map sphericCoordinates = new HashMap<String, SphericCoordinate>();

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
	private SphericCoordinate(double radius, double polarAngle, double azimuthalAngle) {
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
	 * Create an Instance of Spheric Coordinate.
	 * @param radius distance from point to cartesian origin
	 * @param polarAngle polar angle of the point
	 * @param azimuthalAngle azimuthal angle of the point
	 * @return Cartesian coordinate with specified Values
	 */
	public static SphericCoordinate createSphericCoordinate(double radius, double polarAngle, double azimuthalAngle){

		String key = radius + " " + polarAngle + " " + azimuthalAngle;
		SphericCoordinate result = (SphericCoordinate) sphericCoordinates.get(key);

		if(result == null){
			result = new SphericCoordinate(radius, polarAngle, azimuthalAngle);
			sphericCoordinates.put(key, result);
		}

		return result;

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
	protected CartesianCoordinate doAsCartesianCoordinate() throws ConversionFailedException {
		double xPosition = this.radius * Math.sin(Math.toRadians(this.polarAngle))
				* Math.cos(Math.toRadians(this.azimuthalAngle));

		double yPosition = this.radius * Math.sin(Math.toRadians(this.polarAngle))
				* Math.sin(Math.toRadians(this.azimuthalAngle));

		double zPosition = this.radius * Math.cos(Math.toRadians(this.polarAngle));

		CartesianCoordinate result;
		try{
			result = CartesianCoordinate.createCartesianCoordinate(xPosition, yPosition, zPosition);
		} catch (IllegalArgumentException e){
			ConversionFailedException ex = new ConversionFailedException(CARTESIAN_CONVERSION_FAILED , e);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_CARTESIAN_CONVERSION_FAILED, ex).toString());
			throw ex;
		}
		return result;
	}

	@Override
	protected SphericCoordinate doAsSphericCoordinate() {
		return this;
	}

	@Override
	protected boolean doIsEqual(Coordinate other) {
		SphericCoordinate otherSpheric;
		try{
			otherSpheric = other.asSphericCoordinate();
		} catch (ConversionFailedException e){
			return false;
		}
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
		if (EPSILON < 0){
			InvariantsIllegalException ex = new InvariantsIllegalException(EPSILON_VIOLATED);
			log.severe(LogBuilder.createSystemMessage().
					addException(LOG_EPSILON_VIOLATED, ex).toString());
			throw (ex);
		}

		if (!Double.isFinite(radius) || radius < 0){
			InvariantsIllegalException ex = new InvariantsIllegalException(RADIUS_VIOLATED);
			log.severe(LogBuilder.createSystemMessage().
					addException(LOG_RADIUS_VIOLATED, ex).toString());
			throw (ex);
		}

		if (!Double.isFinite(azimuthalAngle) || azimuthalAngle < 0 || azimuthalAngle >= 360){
			InvariantsIllegalException ex = new InvariantsIllegalException(AZIMUTH_VIOLATED);
			log.severe(LogBuilder.createSystemMessage().
					addException(LOG_AZIMUTH_VIOLATED, ex).toString());
			throw (ex);
		}

		if (!Double.isFinite(polarAngle) || polarAngle < 0 || polarAngle >= 180){
			InvariantsIllegalException ex = new InvariantsIllegalException(POLAR_VIOLATED);
			log.severe(LogBuilder.createSystemMessage().
					addException(LOG_POLAR_VIOLATED, ex).toString());
			throw (ex);
		}

	}
}
