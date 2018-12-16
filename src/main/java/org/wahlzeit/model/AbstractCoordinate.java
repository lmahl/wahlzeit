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


import org.wahlzeit.model.exceptions.ContractPostconditionViolatedException;
import org.wahlzeit.model.exceptions.ConversionFailedException;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public abstract class AbstractCoordinate implements Coordinate {
	protected static double EPSILON = 0.0;
	private final String ARGUMENT_NULL = "Argument must not be null";
	private final String LOG_ARGUMENT_NULL = "Illegal argument with value null found";
	private final String DOUBLE_INFINITE = "Argument must not be infinte or NAN";
	private final String LOG_DOUBLE_INFINITE = "Illegal argument with double value infinite or NAN found";
	private final String DISTANCE_ERROR = "Cartesian distance result is < 0 or infinite";
	private final String LOG_DISTANCE_ERROR = "Contract violated: Cartesian distance result is < 0 or infinite";
	private final String ANGLE_ERROR = "Central Angle result is not between o and 360 or infinite";
	private final String LOG_ANGLE_ERROR = "Central Angle result is not between o and 360 or infinite";

	protected Logger log = Logger.getLogger(AbstractCoordinate.class.getName());

	/**
	 * @methodtype get
	 * @return precision with which floating point variables are compared
	 */
	@Override
	public double getEpsilon() {
		return EPSILON;
	}

	@Override
	public double getCartesianDistance(Coordinate other) throws IllegalArgumentException, ContractPostconditionViolatedException {
		assertClassInvariants();
		assertIsNonNullArgument(other);
		double result = doGetCartesianDistance(other);

		if(!Double.isFinite(result) || result < 0){
			ContractPostconditionViolatedException ex = new ContractPostconditionViolatedException(DISTANCE_ERROR);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_DISTANCE_ERROR, ex).toString());
			throw (ex);
		}
		assertClassInvariants();

		return result;
	}

	private double doGetCartesianDistance(Coordinate other) throws ContractPostconditionViolatedException {
		CartesianCoordinate otherCartesian;
		CartesianCoordinate thisCartesian;
		try{
			otherCartesian = other.asCartesianCoordinate();
			thisCartesian = this.asCartesianCoordinate();
		} catch (ConversionFailedException e){
			ContractPostconditionViolatedException ex = new ContractPostconditionViolatedException("Failed to provide result for method getCartesianDistance", e);
			log.warning(LogBuilder.createSystemMessage().
					addException("Failed to provide result for method getCartesianDistance", ex).toString());
			throw (ex);
		}
		return Math.sqrt(Math.pow(thisCartesian.getXPosition() - otherCartesian.getXPosition(), 2)
				+ Math.pow(thisCartesian.getYPosition() - otherCartesian.getYPosition(), 2)
				+ Math.pow(thisCartesian.getZPosition() - otherCartesian.getZPosition(), 2));
	}

	@Override
	public boolean isEqual(Coordinate other) throws IllegalArgumentException{
		assertClassInvariants();
		assertIsNonNullArgument(other);

		boolean result = doIsEqual(other);

		assertClassInvariants();

		return result;
	}

	protected abstract boolean doIsEqual(Coordinate other);


	@Override
	public double getCentralAngle(Coordinate other) throws IllegalArgumentException, ContractPostconditionViolatedException{
		assertClassInvariants();
		assertIsNonNullArgument(other);

		double result = doGetCentralAngle(other);

		if (!Double.isFinite(result) || !(result <=360 && result >= 0)){
			ContractPostconditionViolatedException ex = new ContractPostconditionViolatedException(DISTANCE_ERROR);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_DISTANCE_ERROR, ex).toString());
			throw (ex);
		}
		assertClassInvariants();

		return result;
	}

	private double doGetCentralAngle(Coordinate other) throws ContractPostconditionViolatedException {
		double intermediateA;
		double intermediateB;
		double intermediateC;

		SphericCoordinate otherSpheric;
		SphericCoordinate thisSpheric;
		try{
			otherSpheric = other.asSphericCoordinate();
			thisSpheric = this.asSphericCoordinate();
		} catch (ConversionFailedException e){
			ContractPostconditionViolatedException ex = new ContractPostconditionViolatedException("Failed to provide result for method getCentralAngle", e);
			log.warning(LogBuilder.createSystemMessage().
					addException("Failed to provide result for method getCentralAngle", ex).toString());
			throw (ex);
		}

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
	public SphericCoordinate asSphericCoordinate() throws ConversionFailedException{
		assertClassInvariants();
		SphericCoordinate result = doAsSphericCoordinate();
		assertClassInvariants();
		return result;
	}

	protected abstract SphericCoordinate doAsSphericCoordinate() throws ConversionFailedException;

	/**
	 * @methodtype conversion method
	 * @return cartesian representation of cartesian coordinate
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() throws ConversionFailedException {
		assertClassInvariants();
		CartesianCoordinate result = doAsCartesianCoordinate();
		assertClassInvariants();
		return result;
	}

	protected abstract CartesianCoordinate doAsCartesianCoordinate() throws ConversionFailedException;

	/**
	 * Checks that the provided argument is not null
	 * @methodtype assertion
	 * @param argument
	 */
	protected void assertIsNonNullArgument(Object argument) {
		if (argument == null) {
			IllegalArgumentException ex = new IllegalArgumentException(ARGUMENT_NULL);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_ARGUMENT_NULL, ex).toString());
			throw ex;
		}
	}

	/**
	 * Checks that the provided argument is finite
	 * @methodtype assertion
	 * @param d argument
	 */
	protected void assertArgumentFiniteDouble(double d) {
		if (!Double.isFinite(d)) {
			IllegalArgumentException ex = new IllegalArgumentException(DOUBLE_INFINITE);
			log.warning(LogBuilder.createSystemMessage().
					addException(LOG_DOUBLE_INFINITE, ex).toString());
			throw ex;
		}
	}

	/**
	 * @pre argument is not null
	 * @param other Object to compare with
	 * @return true, if the two coordinates point to the same point in space
	 */
	@Override
	public boolean equals(Object other) throws IllegalArgumentException{
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
