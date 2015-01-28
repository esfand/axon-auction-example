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

import org.junit.Test;

//TESTCODE:BEGIN
public final class LongAggregateIdentifierTest {

	@Test
	public final void testLongConstructor() throws IllegalAggregateIdentifierException {
		final Long value = 4711L;
		final LongAggregateIdentifier testee = new LongAggregateIdentifier(value);
		assertThat(testee.asString()).isEqualTo(testee.toString());
		assertThat(testee.asLong()).isEqualTo(value);
	}

	@Test
	public final void testStringConstructor() throws IllegalAggregateIdentifierException {
		final Long value = 4711L;
		final String idStr = value.toString();
		final LongAggregateIdentifier testee = new LongAggregateIdentifier(idStr);
		assertThat(testee.asString()).isEqualTo(testee.toString());
		assertThat(testee.asLong()).isEqualTo(value);
	}

	@Test(expected = IllegalAggregateIdentifierException.class)
	public final void testStringConstructorWithException()
	        throws IllegalAggregateIdentifierException {
		new LongAggregateIdentifier("abc");
	}

}
// TESTCODE:END
