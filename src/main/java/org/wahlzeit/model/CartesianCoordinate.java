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
public class CartesianCoordinate implements Coordinate {
	
	/**
	 * x, y, z representing the coordinate in the respective dimension
	 */
	private final double x;
	private final double y;
	private final double z;
	
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}


	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
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
		
		double dx = this.x - cartesianOther.x;
		double dy = this.y - cartesianOther.y;
		double dz = this.z - cartesianOther.z;
		
	    return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		double radius = Math.sqrt(x * x + y * y + z * z);
		double theta = Double.NaN;
		if (radius < EPSILON) {
			theta = 0;
		} else {
			theta = Math.acos(z / radius);
		}
		double phi = Double.NaN;
		if (radius < EPSILON) {
			phi = 0;
		} else {
			phi = Math.atan2(y,  x);
		}
		
		return new SphericCoordinate(radius, theta, phi);
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
		
		return sphericOther.getCentralAngle(this);
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
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		if (Math.abs(cartesianOther.x - x) > EPSILON)
			return false;
		if (Math.abs(cartesianOther.y - y) > EPSILON)
			return false;
		if (Math.abs(cartesianOther.z - z) > EPSILON)
			return false;
		return true;
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
