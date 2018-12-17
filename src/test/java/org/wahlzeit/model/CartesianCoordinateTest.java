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
public class CartesianCoordinateTest {

	private CartesianCoordinate coordinate1;
	private CartesianCoordinate coordinate2;
	private CartesianCoordinate coordinate3;
	private CartesianCoordinate coordinate4;
	
	@Before
	public void setUp() {
		coordinate1 = CartesianCoordinate.getCartesianCoordinate(0.0, 0.0, 0.0);
		coordinate2 = CartesianCoordinate.getCartesianCoordinate(-1.8, 2.4, -3.1);
		coordinate3 = CartesianCoordinate.getCartesianCoordinate(-1.8, 2.4, -3.1);
		coordinate4 = CartesianCoordinate.getCartesianCoordinate(2.00001, 5.0, 10.0);
	}
	
	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(coordinate1, coordinate1.asCartesianCoordinate());
		assertEquals(coordinate2, coordinate2.asCartesianCoordinate());
		assertEquals(coordinate2, coordinate3.asCartesianCoordinate());
		assertEquals(coordinate4, coordinate4.asCartesianCoordinate());
	}
	
	@Test
	public void testAsSphericalCoordinate() {
		assertEquals(SphericCoordinate.getSphericCoordinate(0, 0, 0), coordinate1.asSphericCoordinate());
		assertEquals(SphericCoordinate.getSphericCoordinate(4.313930922, 2.372586465, 2.214297436), coordinate2.asSphericCoordinate());
		assertEquals(coordinate2, coordinate3.asSphericCoordinate());
		assertEquals(SphericCoordinate.getSphericCoordinate(11.357818452, 0.493984248, 1.190288225), coordinate4.asSphericCoordinate());
	}
	
	@Test
	public void testGetCartesianDistance() {
		assertEquals(4.313930922, coordinate1.getCartesianDistance(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCartesianDistance(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCartesianDistance(coordinate3), Coordinate.EPSILON);
		assertEquals(13.885606793, coordinate3.getCartesianDistance(coordinate4), Coordinate.EPSILON);
	}
	
	@Test
	public void testGetCentralAngle() {
		assertEquals(0.0, coordinate2.getCentralAngle(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCentralAngle(coordinate3), Coordinate.EPSILON);
		assertEquals(0.62614456, coordinate3.getCentralAngle(coordinate4), Coordinate.EPSILON);
		assertEquals(Math.PI/2, CartesianCoordinate.getCartesianCoordinate(1, 0, 0).getCentralAngle(CartesianCoordinate.getCartesianCoordinate(0, 1, 0)), Coordinate.EPSILON);
		assertEquals(Math.PI/2, CartesianCoordinate.getCartesianCoordinate(2, 0, 0).getCentralAngle(CartesianCoordinate.getCartesianCoordinate(0, 0, 2)), Coordinate.EPSILON);
	}
	
 	@Test(expected = IllegalArgumentException.class) 
 	public void testGetCartesianDistanceNull() {
 		coordinate1.getCartesianDistance(null);
	}
	@Test(expected = IllegalArgumentException.class)
 	public void testGetCentralAngleNull() {
		coordinate1.getCentralAngle(null);
	}
	
 	@Test
	public void testIsEqual() {
		assertTrue(coordinate1.isEqual(coordinate1));
		assertTrue(coordinate2.isEqual(coordinate3));
		assertFalse(coordinate1.isEqual(null));
		assertFalse(coordinate3.isEqual(coordinate4));
	}
 	
 	@Test
	public void testEquals() {
		assertTrue(coordinate1.equals(coordinate1));
		assertTrue(coordinate2.equals(coordinate3));
		assertFalse(coordinate1.equals(null));
		assertFalse(coordinate3.equals(coordinate4));
	}
}
