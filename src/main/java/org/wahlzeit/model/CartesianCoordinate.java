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
	CartesianCoordinate(double x, double y, double z) {
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
	
	public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		CartesianCoordinate newObject = new CartesianCoordinate(x, y, z);
		Coordinate valueObject = coordinateManager.getValueObject(newObject);
		return valueObject.asCartesianCoordinate();
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
		
		SphericCoordinate result = SphericCoordinate.getSphericCoordinate(radius, theta, phi);
		
		/*
		 * Postcondition: the converted object satisfies our invariants
		 */
		result.assertClassInvariants();
		return result;
	}
	
	/**
	 * @methodtype get
	 */
	public boolean isEqual(Coordinate other) {
		if (!(other instanceof CartesianCoordinate)) {
			return false;
		}
		CartesianCoordinate cartesianOther = (CartesianCoordinate) other;

		if (Math.abs(cartesianOther.getX() - this.getX()) > EPSILON)
			return false;
		if (Math.abs(cartesianOther.getY() - this.getY()) > EPSILON)
			return false;
		if (Math.abs(cartesianOther.getZ() - this.getZ()) > EPSILON)
			return false;
		return true;
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
