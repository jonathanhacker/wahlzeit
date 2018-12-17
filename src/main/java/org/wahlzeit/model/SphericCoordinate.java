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
 * A coordinate represents a three dimensional location with cartesian coordinates
 */
public class SphericCoordinate extends AbstractCoordinate {
	
	/**
	 * The distance from the origin. Has to be non-negative 
	 */
	private final double radius;
	/**
	 * The angle of elevation from the equator. Has to lie between -π and +π.
	 */
	private final double theta;
	/**
	 * The angle from the zero meridian. Has to lie between 0 and 2π.
	 */
	private final double phi;
	
	/*
	 * @methodtype constructor
	 */
	SphericCoordinate(double radius, double theta, double phi) {
		/*
		 * Precondition: the given arguments have to suffice our range requirements
		 */
		if (!isRadiusValid(radius) || !isThetaValid(theta) || !isPhiValid(phi))
			throw new IllegalArgumentException();
		
		this.radius = radius;
		this.theta = theta;
		this.phi = phi;
		
		/*
		 * Postcondition: the new object satisfies our invariants
		 */
		assertClassInvariants();
	}
	
	public static SphericCoordinate getSphericCoordinate(double radius, double theta, double phi) {
		SphericCoordinate newObject = new SphericCoordinate(radius, theta, phi);
		Coordinate valueObject = coordinateManager.getValueObject(newObject);
		return valueObject.asSphericCoordinate();
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
		/*
		 * Precondition: the current object has to be valid before conversion
		 */
		assertClassInvariants();
		
		double x = radius * Math.sin(theta) * Math.cos(phi);
		double y = radius * Math.sin(theta) * Math.sin(phi);
		double z = radius * Math.cos(theta);
		
		CartesianCoordinate result = CartesianCoordinate.getCartesianCoordinate(x, y, z);
		
		/*
		 * Postcondition: the converted object has to satisfy its invariants 
		 */
		result.assertClassInvariants();
		
		return result;
	}
	
	/**
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		/*
		 * Postcondition: this object satisfies our invariants
		 */
		
		assertClassInvariants();
		
		return this;
	}
	
	/**
	 * @methodtype get
	 */
	public boolean isEqual(Coordinate other) {
		if (!(other instanceof SphericCoordinate)) {
			return false;
		}
		SphericCoordinate sphericOther = (SphericCoordinate) other;

		if (Math.abs(sphericOther.getRadius() - this.getRadius()) > EPSILON)
			return false;
		if (Math.abs(sphericOther.getTheta() - this.getTheta()) > EPSILON)
			return false;
		if (Math.abs(sphericOther.getPhi() - this.getPhi()) > EPSILON)
			return false;
		return true;
	}
	
	/*
	 * @methodtype assert
	 */
	@Override
    public void assertClassInvariants (){
		/*
		 * for a spheric coordinate to be valid, all its attributes have to be valid.
		 */
		assert isRadiusValid(this.radius);
		assert isThetaValid(this.theta);
		assert isPhiValid(this.phi);
    }
	
	/*
	 * @methodtype boolean-query
	 */
	 private static boolean isRadiusValid(double radius) {
		boolean finite = Double.isFinite(radius);
		/*
        * the radius has to be nonnegative
        */
        boolean domain = radius + EPSILON >= 0;
        return finite && domain;
	}
	
	/*
	 * @methodtype boolean-query
	 */
	 private static boolean isThetaValid(double theta) {
		boolean finite = Double.isFinite(theta);
		/*
		 * θ has to lie between -π and π
		 */
		boolean domain = theta + EPSILON >= -Math.PI && theta - EPSILON < Math.PI;
		return finite && domain;
	}
	 
	/*
	 * @methodtype boolean-query
	 */
	private static boolean isPhiValid(double phi) {
		boolean finite = Double.isFinite(phi);
		/*
		 * φ has to lie between zero and 2π
		 */
		boolean domain = phi + EPSILON >= 0 && phi - EPSILON < 2 * Math.PI;
		return finite && domain;
	}
	
}
