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

import org.wahlzeit.annotations.PatternInstance;

/**
 * An abstract coordinate class implementing the coordinate interface
 */
@PatternInstance(
		patternName = "Template Method",
		participants = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
)
public abstract class AbstractCoordinate implements Coordinate {
	protected static ValueObjectManager<Coordinate> coordinateManager = new ValueObjectManager<Coordinate>();
	
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();
	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		/*
		 * Precondition: The other object must not be null
		 */
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		/*
		 * By converting this and the other coordinate object, their class invariants are ensured.
		 */
		
		CartesianCoordinate cartesianThis = this.asCartesianCoordinate();
		CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
		
		double dx = cartesianThis.getX() - cartesianOther.getX();
		double dy = cartesianThis.getY() - cartesianOther.getY();
		double dz = cartesianThis.getZ() - cartesianOther.getZ();
		
		double result = Math.sqrt(dx * dx + dy * dy + dz * dz);
		/*
		 * Postcondition: The result is a valid nonnegative double value
		 */
		assert Double.isFinite(result);
		assert result + EPSILON >= 0;
		
		return result;
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getCentralAngle(Coordinate other) {
		/*
		 * Precondition: The other object must not be null
		 */
		if (other == null) {
			throw new IllegalArgumentException("the other coordinate must not be null");
		}
		
		/*
		 * By converting this and the other coordinate object, their class invariants are ensured.
		 */
		SphericCoordinate sphericThis = this.asSphericCoordinate();
		SphericCoordinate sphericOther = other.asSphericCoordinate();
		
		double dphi = Math.abs(sphericOther.getPhi() - sphericThis.getPhi());
		
		// formula from https://en.wikipedia.org/wiki/Great-circle_distance
		double a = Math.sin(sphericThis.getPhi()) * Math.sin(sphericOther.getPhi());
		double b = Math.cos(sphericThis.getPhi()) * Math.cos(sphericOther.getPhi()) * Math.cos(sphericOther.getTheta() - sphericThis.getTheta());
		double dsigma = Math.acos(a + b);
		
		/*
		 * Postcondition: The result is a valid double value between zero and π
		 */
		assert Double.isFinite(dsigma);
		assert dsigma + EPSILON >= 0 && dsigma - EPSILON <= Math.PI;
		return dsigma;
	}
	
	/**
	 * @methodtype boolean-query
	 */
	@Override
    public boolean equals (Object obj) {
		/*
		 * Precondition: None. If the other object does not meet specific criteria, it is not equal to this one.
		 */
		/*
		 * Postcondition: The return value has to be either true or false. Ensured by the language.
		 */
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
	public abstract boolean isEqual(Coordinate other);
	
	/*
	 * @methodtype assert
	 */
	public abstract void assertClassInvariants();
}
