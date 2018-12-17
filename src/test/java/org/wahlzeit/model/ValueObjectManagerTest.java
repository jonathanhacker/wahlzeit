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

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * All test cases of the class {@link Coordinate}.
 */
public class ValueObjectManagerTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidValueObject() {
		new ValueObjectManager<Object>().getValueObject(null);
	}
	
	@Test
	public void testIdenticalArguments() {
		Coordinate a = SphericCoordinate.getSphericCoordinate(0.1, 0.2, 0.3);
		Coordinate b = SphericCoordinate.getSphericCoordinate(0.1, 0.2, 0.3);
		assertSame(a, b);
	}
	
	@Test
	public void testArgumentsEpsilon() {
		Coordinate a = SphericCoordinate.getSphericCoordinate(0.1, 0.2, 0.3);
		Coordinate b = SphericCoordinate.getSphericCoordinate(0.1 + Coordinate.EPSILON / 10, 0.2, 0.3);
		assertSame(a, b);
	}
	
	@Test
	public void testDifferentArguments() {
		Coordinate a = SphericCoordinate.getSphericCoordinate(0.1, 0.2, 0.3);
		Coordinate b = SphericCoordinate.getSphericCoordinate(0.2, 0.4, 0.6);
		assertNotSame(a, b);
	}
	
}
