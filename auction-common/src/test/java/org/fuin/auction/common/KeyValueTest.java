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

import org.junit.Test;

//TESTCODE:BEGIN
public class KeyValueTest {

	@Test
	public final void testConstruction() {
		final String key = "abc";
		final Long value = 4711L;
		final KeyValue testee = new KeyValue(key, value);
		assertThat(testee.getKey()).isEqualTo(key);
		assertThat(testee.getValue()).isEqualTo(value);
	}

	@Test
	public final void testReplace() {
		assertThat(KeyValue.replace(null)).isEqualTo(null);
		assertThat(KeyValue.replace("")).isEqualTo("");
		assertThat(KeyValue.replace("abc")).isEqualTo("abc");
		assertThat(KeyValue.replace("${a}abc")).isEqualTo("${a}abc");
		assertThat(KeyValue.replace("abc${d}")).isEqualTo("abc${d}");
		assertThat(KeyValue.replace("a${b}c")).isEqualTo("a${b}c");
		assertThat(KeyValue.replace("${a}abc", new KeyValue("a", "x"))).isEqualTo("xabc");
		assertThat(KeyValue.replace("abc${d}", new KeyValue("d", "x"))).isEqualTo("abcx");
		assertThat(KeyValue.replace("a${b}c", new KeyValue("b", "x"))).isEqualTo("axc");
	}

}
// TESTCODE:END
