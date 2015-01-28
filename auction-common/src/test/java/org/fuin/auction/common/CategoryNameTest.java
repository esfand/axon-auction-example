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
package org.fuin.auction.common;

import static org.fest.assertions.Assertions.assertThat;

import org.fuin.objects4j.ContractViolationException;
import org.junit.Test;

//TESTCODE:BEGIN
public final class CategoryNameTest {

	@Test
	public final void testCreateValid() {
		final String name = "abcdef";
		assertThat(new CategoryName(name).toString()).isEqualTo(name);
		assertThat(new CategoryName(name).length()).isEqualTo(name.length());
	}

	@Test(expected = ContractViolationException.class)
	public final void testCreateEmpty() {
		new CategoryName("");
	}

	@Test(expected = ContractViolationException.class)
	public final void testCreateTooLong() {
		new CategoryName("123456789.123456789.123456789.123456789.1");
	}

}
// TESTCODE:END
