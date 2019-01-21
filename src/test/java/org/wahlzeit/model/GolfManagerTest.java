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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * All test cases of the class {@link GolfManager}.
 */
public class GolfManagerTest {
	
	GolfManager golfManager = GolfManager.getInstance();

	@Test
	public void sameTypeInMap() {
		GolfType forest1 = golfManager.getGolfType("forest");
		GolfType forest2 = golfManager.getGolfType("forest");

		assertEquals(forest1,  forest2);
	}
	
	@Test
	public void golfsWithSameProperties() {
		Golf golf1 = golfManager.createGolf("forest", 4);
		Golf golf2 = golfManager.createGolf("forest", 4);
		
		assertEquals(golf1.getType(), golf2.getType());
		assertEquals(golf1.getParScore(), golf2.getParScore());
		assertNotSame(golf1, golf2);
	}
	
}
