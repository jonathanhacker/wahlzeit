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
 * An abstract coordinate class implementing the coordinate interface
 */
public abstract class AbstractCoordinate implements Coordinate {
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();
	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		double dx = cartesianThis.getX() - cartesianOther.getX();
		double dy = cartesianThis.getY() - cartesianOther.getY();
		double dz = cartesianThis.getZ() - cartesianOther.getZ();
		
	    return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		
		SphericCoordinate sphericThis = this.asSphericCoordinate();
		SphericCoordinate sphericOther = other.asSphericCoordinate();
		
		double dphi = Math.abs(sphericOther.getPhi() - sphericThis.getPhi());
		
		// formula from https://en.wikipedia.org/wiki/Great-circle_distance
		double a = Math.sin(sphericThis.getPhi()) * Math.sin(sphericOther.getPhi());
		double b = Math.cos(sphericThis.getPhi()) * Math.cos(sphericOther.getPhi()) * Math.cos(sphericOther.getTheta() - sphericThis.getTheta());
		double dsigma = Math.acos(a + b);
		return dsigma;
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
	
	/**
	 * @methodtype boolean-query
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		if (Math.abs(cartesianOther.getX() - cartesianThis.getX()) > EPSILON)
			return false;
		if (Math.abs(cartesianOther.getY() - cartesianThis.getY()) > EPSILON)
			return false;
		if (Math.abs(cartesianOther.getZ() - cartesianThis.getZ()) > EPSILON)
			return false;
		return true;
	}
}
