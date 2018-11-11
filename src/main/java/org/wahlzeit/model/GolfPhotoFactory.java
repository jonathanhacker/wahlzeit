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

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

/**
 * Specialization of PhotoFactory to create instances of GolfPhoto class
 */
public class GolfPhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());

	/**
	 * @methodtype constructor
	 */
	protected GolfPhotoFactory() {
		super();
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting GolfPhotoFactory").toString());
			setInstance(new GolfPhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(GolfPhotoFactory golfPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = golfPhotoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	@Override
	public GolfPhoto createPhoto() {
		return new GolfPhoto();
	}

	/**
	 * Creates a new golf photo with the specified id
	 */
	@Override
	public GolfPhoto createPhoto(PhotoId id) {
		return new GolfPhoto(id);
	}
	
	/**
	 * Creates a new golf photo with the specified par score
	 */
	public GolfPhoto createPhoto(int parScore) {
		return new GolfPhoto(parScore);
	}
	
	/**
	 * Creates a new golf photo with the specified id and par score
	 */
	public GolfPhoto createPhoto(PhotoId id, int parScore) {
		return new GolfPhoto(id, parScore);
	}

	/**
	 * loadPhoto has no implementation in superclass and is thus not overriden
	 */
	//public GolfPhoto loadPhoto(PhotoId id)

}