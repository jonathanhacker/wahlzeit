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
public class CartesianCoordinate extends AbstractCoordinate {
	
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
		/*
		 * Precondition: all given coordinates have to be finite 
		 */
		if (!Double.isFinite(x) || !Double.isFinite(y) || !Double.isFinite(z))
			throw new IllegalArgumentException();
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		/*
		 * Postcondition: the new object satisfies our invariants
		 */
		assertClassInvariants();
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
		/*
		 * Postcondition: this object satisfies our invariants
		 */
		assertClassInvariants();
		
		return this;
	}

	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		/*
		 * Precondition: the object satisfies the invariants before conversion
		 */
		assertClassInvariants();
		
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
		
		SphericCoordinate result = new SphericCoordinate(radius, theta, phi);
		
		/*
		 * Postcondition: the converted object satisfies our invariants
		 */
		result.assertClassInvariants();
		return result;
	}
	
	/*
	 * @methodtype assert
	 */
	public void assertClassInvariants() {
		/*
		 * The requirements that have to be met for a Cartesian Coordinate to be valid.
		 * The coordinates have to be valid real numbers, their domain is not determined.
		 */
		Double.isFinite(x);
		Double.isFinite(y);
		Double.isFinite(z);
	}

}
