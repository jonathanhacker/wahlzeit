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

public class GolfPhotoTest {
	
	/**
	 *
	 */
	@Test
	public void testGolfPhoto() {
		GolfManager golfManager = GolfManager.getInstance();
		Golf golf1 = golfManager.createGolf("forest", 4);
		Golf golf2 = golfManager.createGolf("meadow", 4);
		GolfPhoto golfPhoto1 = new GolfPhoto(golf1);
		GolfPhoto golfPhoto2 = new GolfPhoto(new PhotoId(0), golf2);
		
		assertNotEquals(golfPhoto1, golfPhoto2);
		assertEquals(golfPhoto1.getGolf().getParScore(), golfPhoto2.getGolf().getParScore());
	}

}
