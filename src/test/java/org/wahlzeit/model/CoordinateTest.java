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

	private static final double EPSILON = 1e-7;
	
	private Coordinate coordinate1;
	private Coordinate coordinate2;
	private Coordinate coordinate3;
	private Coordinate coordinate4;
	private Coordinate coordinate5;
	
	@Before
	public void setUp() {
		coordinate1 = new Coordinate(0.0, 0.0, 0.0);
		coordinate2 = new Coordinate(2.0, 5.0, 10.0);
		coordinate3 = new Coordinate(2.0, 5.0, 10.0);
		coordinate4 = new Coordinate(2.00001, 5.0, 10.0);
		coordinate5 = new Coordinate(-1.8, 2.4, -3.1);
	}
	
	@Test
	public void testGetDistance() {
		assertEquals(Math.sqrt(129.0), coordinate1.getDistance(coordinate2), EPSILON);
		assertEquals(0.0, coordinate2.getDistance(coordinate2), EPSILON);
		assertEquals(0.0, coordinate2.getDistance(coordinate3), EPSILON);
		assertEquals(13.885604056, coordinate2.getDistance(coordinate5), EPSILON);
	}
	
 	@Test(expected = IllegalArgumentException.class) 
	public void testGetDistanceNull() {
		coordinate1.getDistance(null);
	}
	
 	@Test
	public void testIsEqual() {
		assertTrue(coordinate1.isEqual(coordinate1));
		assertTrue(coordinate2.isEqual(coordinate3));
		assertFalse(coordinate1.isEqual(null));
		assertFalse(coordinate1.isEqual(coordinate5));
		assertFalse(coordinate3.isEqual(coordinate4));
	}
 	
 	@Test
	public void testEquals() {
		assertTrue(coordinate1.equals(coordinate1));
		assertTrue(coordinate2.equals(coordinate3));
		assertFalse(coordinate1.equals(null));
		assertFalse(coordinate1.equals(coordinate5));
		assertFalse(coordinate3.equals(coordinate4));
	}
}
