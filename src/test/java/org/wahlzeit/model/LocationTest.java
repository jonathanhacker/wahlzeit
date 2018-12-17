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
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * All test cases of the class {@link Location}.
 */
public class LocationTest {

	@Test
	public void testLocationInitialization() {
		Coordinate coordinate = CartesianCoordinate.getCartesianCoordinate(1.23, 4.32, 78.76);
		Location location = new Location(coordinate);
		assertEquals(location.getCoordinate(), CartesianCoordinate.getCartesianCoordinate(1.23, 4.32, 78.76));
		assertEquals(location.getCoordinate(), coordinate);
		assertNotEquals(location.getCoordinate(), CartesianCoordinate.getCartesianCoordinate(0, 0, 0));
	}
}
