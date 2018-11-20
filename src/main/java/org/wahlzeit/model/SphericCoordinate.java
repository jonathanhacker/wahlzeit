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
	 * The angle of elevation from the equator.
	 */
	private final double theta;
	/**
	 * The angle from the zero meridian.
	 */
	private final double phi;
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double radius, double theta, double phi) {
		if (!assertValidRadius(radius)) {
			throw new IllegalArgumentException("radius has to be non-negative");
		}
		
		this.radius = radius;
		this.theta = theta;
		this.phi = phi;
	}
	
	public boolean assertValidRadius(double radius) {
		return radius + EPSILON >= 0;
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
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}
	
}
