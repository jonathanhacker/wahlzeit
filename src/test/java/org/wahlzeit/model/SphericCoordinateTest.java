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
public class SphericCoordinateTest {

	private SphericCoordinate coordinate1;
	private SphericCoordinate coordinate2;
	private SphericCoordinate coordinate3;
	private SphericCoordinate coordinate4;
	
	@Before
	public void setUp() {
		coordinate1 = new SphericCoordinate(0.0, 0.0, 0.0);
		coordinate2 = new SphericCoordinate(1.8, Math.PI * 0.25, Math.PI * 0.125);
		coordinate3 = new SphericCoordinate(1.8, Math.PI * 0.25, Math.PI * 0.125);
		coordinate4 = new SphericCoordinate(1.8001, Math.PI * (1 + 0.25),Math.PI *  0.125);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRadius() {
		new SphericCoordinate(-1.0, 0, 0);
	}
	
	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(new CartesianCoordinate(0, 0, 0), coordinate1.asCartesianCoordinate());
		assertEquals(new CartesianCoordinate(1.175906668, 0.48707649, 1.272792206), coordinate2.asCartesianCoordinate());
		assertEquals(new CartesianCoordinate(-1.175971997, -0.487103550, -1.272862917), coordinate4.asCartesianCoordinate());
	}
	
	@Test
	public void testAsSphericalCoordinate() {
		assertEquals(coordinate1, coordinate1.asSphericCoordinate());
		assertEquals(coordinate2, coordinate2.asSphericCoordinate());
		assertEquals(coordinate2, coordinate3.asSphericCoordinate());
		assertEquals(coordinate4, coordinate4.asSphericCoordinate());
	}
	
	@Test
	public void testGetCentralAngle() {
		assertEquals(0.858885759, coordinate1.getCentralAngle(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCentralAngle(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCentralAngle(coordinate3), Coordinate.EPSILON);
		//assertEquals(Math.PI, coordinate3.getCentralAngle(coordinate4), Coordinate.EPSILON);
	}
	
	@Test
	public void testGetCartesianDistance() {
		assertEquals(1.8, coordinate1.getCartesianDistance(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCartesianDistance(coordinate2), Coordinate.EPSILON);
		assertEquals(0.0, coordinate2.getCartesianDistance(coordinate3), Coordinate.EPSILON);
		assertEquals(3.6001, coordinate3.getCartesianDistance(coordinate4), Coordinate.EPSILON);
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
