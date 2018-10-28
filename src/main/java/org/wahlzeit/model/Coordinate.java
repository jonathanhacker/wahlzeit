/*
 * Copyright (c) 2018-2019 by Jonathan Hacker
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
 * A coordinate represents a three dimensional location
 */
public class Coordinate {
	
	/**
	 * the difference below which two doubles are considered equal.
	 */
	private static final double EPSILON = 1e-7;
	
	/**
	 * x, y, z representing the coordinate in the respective dimension
	 */
	private double x;
	private double y;
	private double z;
	
	/**
	 * @methodtype constructor
	 */
	public Coordinate(double x, double y, double z) {
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
	 * @methodtype set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * @methodtype get
	 */
	public double getDistance(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		double dz = this.z - other.z;
		
	    return Math.sqrt(dx * dx + dy * dy + dz * dz); 
	}

	/**
	 *
	 */
	public boolean isEqual(Coordinate other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (Math.abs(other.x - x) > EPSILON)
			return false;
		if (Math.abs(other.y - y) > EPSILON)
			return false;
		if (Math.abs(other.z - z) > EPSILON)
			return false;
		return true;
	}

	/**
	 *
	 */
	@Override
    public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        if (obj instanceof Coordinate){
            return isEqual((Coordinate) obj);
        }
        return false;
    }

}
