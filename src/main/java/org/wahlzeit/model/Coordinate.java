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
 * A coordinate represents a location in 3D space
 */
public interface Coordinate {
	/**
	 * the difference below which two doubles are considered equal.
	 */
	public static final double EPSILON = 1e-7;

	public CartesianCoordinate asCartesianCoordinate();
	public double getCartesianDistance(Coordinate other);
	public SphericCoordinate asSphericCoordinate();
	public double getCentralAngle(Coordinate other);
	public boolean isEqual(Coordinate other);
}
