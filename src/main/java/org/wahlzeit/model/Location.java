/**
 * Location
 *
 * Version: 1.0
 *
 * Date: 28.10.2018
 *
 * Licence: AGPLv3
 */

package org.wahlzeit.model;

/**
 * Class that represents the a global location
 */
public class Location {
	private Coordinate coordinate;

	public Location(Coordinate coordinate){
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate(){
		return this.coordinate;
	}
}
