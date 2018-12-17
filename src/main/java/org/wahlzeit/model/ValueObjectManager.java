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

import java.util.concurrent.ConcurrentHashMap;

/* A class that manages shared value objects */
public class ValueObjectManager<T> {
	/* There is no ConcurrentHashSet, so we use a Map and ignore the value */
	private ConcurrentHashMap<T, T> valueObjects;
	
	public ValueObjectManager() {
		valueObjects = new ConcurrentHashMap<>();
	}

	/*
	 * @param obj the object for which a unique value object should be returned
	 * @return the value object corresponding to the given object
	 * @methodtype get
	 */
	public T getValueObject(T obj) {
		if (obj == null) {
			throw new IllegalArgumentException("can't manage value object for null");
		}
		
		for (T valueObject : valueObjects.keySet()) {
			if (valueObject.equals(obj)) {
				return valueObject;
			}
		}
		
		valueObjects.put(obj, obj);
        return obj;

    }
}
