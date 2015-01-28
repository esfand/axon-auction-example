/*
 * Copyright (c) 2010. Axon Auction Example
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fuin.axon.support.base;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TESTCODE:BEGIN
public final class UUIDAggregateIdentifierFactoryTest {

	private UUIDAggregateIdentifierFactory testee;

	@Before
	public final void setUp() {
		testee = new UUIDAggregateIdentifierFactory();
	}

	@After
	public final void tearDown() {
		testee = null;
	}

	@Test
	public final void testCreate() {
		assertThat(testee.create()).isInstanceOf(UUIDAggregateIdentifier.class);
	}

	@Test
	public final void testFromString() throws IllegalAggregateIdentifierException {
		final String uuidString = UUID.randomUUID().toString();
		assertThat(testee.fromString(uuidString)).isInstanceOf(UUIDAggregateIdentifier.class);
	}

	@Test(expected = IllegalAggregateIdentifierException.class)
	public final void testFromStringException() throws IllegalAggregateIdentifierException {
		testee.fromString("abc");
	}

}
// TESTCODE:END
