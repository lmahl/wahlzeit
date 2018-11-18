/**
 * Coordinate
 *
 * Version: 1.0
 *
 * Date: 18.11.2018
 *
 * License: AGPLv3
 */

package org.wahlzeit.model;

/**
 * Interface to represent a coordinate in space
 */
public interface Coordinate {

	/**
	 * @methodtype conversion method
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
	 * @param other coordinate to compare this with
	 * @return returns whether the two coordinates are at the same point in space
	 */
	boolean isEqual(Coordinate other);

	/**
	 * Calculates the central angle between two points
	 * @methodtype get
	 * @param other coordinate to calculate the central angle between
	 * @return central angle between the two coordinates
	 */
	double getCentralAngle(Coordinate other);

	/**
	 * @methodtype get
	 * @return precision with which floating point variables are compared
	 */
	double getEpsilon();

	/**
	 * Calculate the cartesian distance between two points
	 * @methodtype get
	 * @param other other coordinate to calculate the cartesian distance between
	 * @return cartesian distance between the two coordinates
	 */
	double getCartesianDistance(Coordinate other);
}
