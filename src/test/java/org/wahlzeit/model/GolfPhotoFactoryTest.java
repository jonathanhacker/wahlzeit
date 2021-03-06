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

import org.junit.Test;

public class GolfPhotoFactoryTest {
	
	/**
	 *
	 */
	@Test
	public void testSingleton() {
		PhotoFactory golfFactory1 = GolfPhotoFactory.getInstance();
		PhotoFactory golfFactory2 = GolfPhotoFactory.getInstance();
		
		assertEquals(golfFactory1, golfFactory2);
	}

	/**
	 *
	 */
	@Test
	public void testCreatedPhotoType() {
		GolfPhotoFactory.instance = null;
		PhotoFactory golfFactory = GolfPhotoFactory.getInstance();

		Photo photo = golfFactory.createPhoto();
		
		assertEquals(GolfPhoto.class, photo.getClass());
	}

}
