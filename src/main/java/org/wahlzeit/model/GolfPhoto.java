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

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;

/**
 * A GolfPhoto represents a user-provided (uploaded) golf photo.
 * 
 * Takes part in the client-service-collaboration as client with Golf.
 */
@Subclass
public class GolfPhoto extends Photo {
	
	@Ignore
	private Golf golf; 
	
	/**
	 * @methodtype constructor
	 */
	public GolfPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public GolfPhoto(PhotoId myId) {
		super(myId);
	}
	
	/**
	 * @methodtype constructor
	 */
	public GolfPhoto(PhotoId myId, Golf golf) {
		super(myId);
		this.golf = golf;
	}
	
	/**
	 * @methodtype constructor
	 */
	public GolfPhoto(Golf golf) {
		super();
		this.golf = golf;
	}
	
	/**
	 * @methodtype getter
	 */
	public Golf getGolf() {
		return golf;
	}
	
	/**
	 * @methodtype setter
	 */
	public void setGolf(Golf golf) {
		this.golf = golf;
	}

}
