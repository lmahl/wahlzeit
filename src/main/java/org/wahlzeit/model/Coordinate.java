/**
 * Coordinate
 * <p>
 * Version: 1.0
 * <p>
 * Date: 18.11.2018
 * <p>
 * License: AGPLv3
 */

package org.wahlzeit.model;

import org.wahlzeit.model.exceptions.ContractPostconditionViolatedException;

/**
 * Interface to represent a coordinate in space
 * For any Representation, EPSILON Value must not be smaller than 0
 * For Spherical Representation, azimuth angle has to be in intervall [0,360[
 * For Spherical Representation, polar angle has to be in intervall [0,180[
 * For Spherical Representation, radius must not be smaller then 0
 */
public interface Coordinate {

	/**
	 * @methodtype conversion method
	 * @post return value is not null
	 * @return cartesian representation of coordinate
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * @methodtype conversion method
	 * @return spherical representation of coordinate
	 */
	SphericCoordinate asSphericCoordinate();

	/**
	 * @methodtype comparison method
	 * @pre argument is not null
	 * @param other coordinate to compare this with
	 * @return returns whether the two coordinates are at the same point in space
	 */
	boolean isEqual(Coordinate other) throws IllegalArgumentException;

	/**
	 * Calculates the central angle between two points using the harvesine formula
	 * @methodtype get
	 * @pre parameter is not null
	 * @post return value is not null and in intervall [0,360[
	 * @param other coordinate to calculate the central angle between
	 * @return central angle between the two coordinates
	 */
	double getCentralAngle(Coordinate other) throws IllegalArgumentException, ContractPostconditionViolatedException;

	/**
	 * @methodtype get
	 * @return precision with which floating point variables are compared
	 */
	double getEpsilon();

	/**
	 * Calculate the cartesian distance between two points
	 * @methodtype get
	 * @pre argument is not null
	 * @post return vlaue is not null and positive
	 * @param other other coordinate to calculate the cartesian distance between
	 * @return cartesian distance between the two coordinates
	 */
	double getCartesianDistance(Coordinate other) throws IllegalArgumentException, ContractPostconditionViolatedException;
}
