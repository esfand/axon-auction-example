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

import org.junit.Test;

//TESTCODE:BEGIN
public final class UUIDAggregateIdentifierTest {

	@Test
	public final void testDefaultConstructor() {
		final UUIDAggregateIdentifier testee = new UUIDAggregateIdentifier();
		assertThat(testee.asString()).isEqualTo(testee.toString());
		assertThat(UUID.fromString(testee.asString())).isNotNull();
	}

	@Test
	public final void testStringConstructor() throws IllegalAggregateIdentifierException {
		final UUID uuid = UUID.randomUUID();
		final String uuidStr = uuid.toString();
		final UUIDAggregateIdentifier testee = new UUIDAggregateIdentifier(uuidStr);
		assertThat(testee.asString()).isEqualTo(uuidStr);
		assertThat(testee.asString()).isEqualTo(testee.toString());
	}

	@Test(expected = IllegalAggregateIdentifierException.class)
	public final void testStringConstructorWithException()
	        throws IllegalAggregateIdentifierException {
		new UUIDAggregateIdentifier("abc");
	}

}
// TESTCODE:END
