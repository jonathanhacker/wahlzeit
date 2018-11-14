/*
2 * Copyright (c) 2018-2019 by Jonathan Hacker
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

/**
 * A coordinate represents a three dimensional location with cartesian coordinates
 */
public class SphericCoordinate implements Coordinate {
	
	/**
	 * x, y, z representing the coordinate in the respective dimension
	 */
	private final double radius;
	private final double theta;
	private final double phi;
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double radius, double theta, double phi) {
		this.radius = radius;
		this.theta = theta;
		this.phi = phi;
	}

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @methodtype get
	 */
	public double getTheta() {
		return theta;
	}


	/**
	 * @methodtype get
	 */
	public double getPhi() {
		return phi;
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		
		return new CartesianCoordinate(x, y, z);
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		return cartesianOther.getCartesianDistance(this);
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		
		SphericCoordinate sphericOther = other.asSphericCoordinate();
		
		double dphi = Math.abs(sphericOther.phi - this.phi);
		
		// formula from https://en.wikipedia.org/wiki/Great-circle_distance
		double a = Math.sin(this.phi) * Math.sin(sphericOther.phi);
		double b = Math.cos(this.phi) * Math.cos(sphericOther.phi) * Math.cos(sphericOther.theta - theta);
		double dsigma = Math.acos(a + b);
		return dsigma;
	}

	/**
	 * @methodtype boolean-query
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		return other.asCartesianCoordinate().isEqual(this);
	}

	/**
	 * @methodtype boolean-query
	 */
	@Override
    public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
        if (obj instanceof Coordinate){
            return isEqual((Coordinate) obj);
        }
        return false;
    }

}
