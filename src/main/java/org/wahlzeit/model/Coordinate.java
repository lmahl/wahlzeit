package org.wahlzeit.model;

/**
 * Class that represents a single three dimensional cartesian coordinate
 */
public class Coordinate {
	private double xPosition = 0;
	private double yPosition= 0;
	private double zPosition = 0;

	public Coordinate(double xPosition, double yPosition, double zPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.zPosition = zPosition;
	}

	public double[] getPosition(){
		return new double[]{xPosition, yPosition, zPosition};
	}

	public double getXPosition(){
		return  this.xPosition;
	}

	public double getYPosition(){
		return  this.yPosition;
	}

	public double getZPosition(){
		return  this.zPosition;
	}

	public boolean isEqual(Coordinate other){
		return this.xPosition == other.getXPosition()
				&& this.yPosition == other.getYPosition()
				&& this.zPosition == other.getZPosition();
	}

	public double getDistance(Coordinate other) {
		return Math.sqrt(Math.pow(this.xPosition - other.getXPosition(), 2)
				+ Math.pow(this.yPosition - other.getYPosition(), 2)
				+ Math.pow(this.zPosition - other.getZPosition(), 2));
	}

	@Override
	public boolean equals(Object other){
		if(!(other instanceof Coordinate)){
			return false;
		}
		Coordinate otherCoordinate = (Coordinate)other;
		return this.isEqual(otherCoordinate);
	}
}