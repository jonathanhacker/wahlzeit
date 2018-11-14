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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class CoordinateTest {

	private CartesianCoordinate cartesianOrigin;
	private CartesianCoordinate cartesianCoordinate;
	private SphericCoordinate sphericOrigin;
	private SphericCoordinate sphericCoordinate;
	
	@Before
	public void setUp() {
		cartesianOrigin = new CartesianCoordinate(0.0, 0.0, 0.0);
		cartesianCoordinate = new CartesianCoordinate(2, 0, 0);
		sphericOrigin = new SphericCoordinate(0.0, 0.0, 0.0);
		sphericCoordinate = new SphericCoordinate(2, Math.PI/2, 0);
	}
	
	@Test
	public void testConversion() {
		assertEquals(sphericCoordinate.asCartesianCoordinate(), cartesianCoordinate);
		assertEquals(cartesianCoordinate.asSphericCoordinate(), sphericCoordinate);
	}
	
	@Test
	public void testGetCartesianDistance() {
		assertEquals(cartesianOrigin.getCartesianDistance(sphericCoordinate), sphericOrigin.getCartesianDistance(cartesianCoordinate), Coordinate.EPSILON);
		assertEquals(sphericOrigin.getCartesianDistance(cartesianCoordinate), cartesianOrigin.getCartesianDistance(sphericCoordinate), Coordinate.EPSILON);
	}
	
	@Test
	public void testGetCentralAngle() {
		assertEquals(cartesianCoordinate.getCentralAngle(sphericCoordinate), sphericCoordinate.getCentralAngle(cartesianCoordinate), Coordinate.EPSILON);
	}
}
