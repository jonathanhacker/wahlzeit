/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
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

package org.wahlzeit.services;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	private final String emailAddressString1 = "mail1@domain.com";
	private final String emailAddressString2 = "mail2@domain.com";
	private final String emailAddressString3 = "mail2@domain.com";
	private final String emptyString = "";
	
	private EmailAddress emailAddress1;
	private EmailAddress emailAddress2;
	private EmailAddress emailAddress3;
	private EmailAddress emptyEmailAddress;
	
	@Before
	public void setUp() {
		emailAddress1 = EmailAddress.getFromString(emailAddressString1);
		emailAddress2 = EmailAddress.getFromString(emailAddressString2);
		emailAddress3 = EmailAddress.getFromString(emailAddressString3);
		emptyEmailAddress = EmailAddress.getFromString(emptyString);
	}
	
	/**
	 *
	 */
	public EmailAddressTest(String name) {
		super(name);
	}

	/**
	 *
	 */
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google

		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

	/**
	 *
	 */
	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}

	/**
	 *
	 */
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}
	
	/**
	 *
	 */
	@Test
	public void testAsString() {
		assertEquals(emailAddress1.asString(), emailAddressString1);
		assertEquals(emailAddress2.asString(), emailAddressString3);
		assertNotEquals(emailAddress1.asString(), emailAddressString2);
	}
	
	/**
	 *
	 */
	@Test
	public void testAsInternetAddress() {
		assertNotNull(emailAddress1.asInternetAddress());
	}
	
	/**
	 *
	 */
	@Test
	public void testIsEqual() {
		assertTrue(emailAddress1.isEqual(emailAddress1));
		assertFalse(emailAddress1.isEqual(emailAddress2));
		assertTrue(emailAddress2.isEqual(emailAddress3));
	}
	
	/**
	 *
	 */
	@Test
	public void testIsValid() {
		assertTrue(emailAddress1.isValid());
		assertFalse(emptyEmailAddress.isValid());
	}
	
	/**
	 *
	 */
	@Test
	public void testIsEmpty() {
		assertFalse(emailAddress1.isEmpty());
		assertTrue(emptyEmailAddress.isEmpty());
	}

}

