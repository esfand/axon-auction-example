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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.junit.Ignore;

/**
 * Test {@link JodaTimeConverter}.
 * 
 * @author Michael Schnell
 */
// TESTCODE:BEGIN
public class JodaTimeConverterTest {

	private JodaTimeConverter testee;

	@Before
	public final void setUp() {
		testee = new JodaTimeConverter();
	}

	@After
	public final void tearDown() {
		testee = null;
	}

	@Test
	public final void testCanConvert() {
		assertThat(testee.canConvert(this.getClass())).isFalse();
		assertThat(testee.canConvert(DateTime.class)).isTrue();
	}

    @Ignore
	@Test
	public final void testMarshal() {

		// PREPARE
		final HierarchicalStreamWriter writer  = mock(HierarchicalStreamWriter.class);
		final MarshallingContext       context = mock(MarshallingContext.class);
		final DateTime localDateTime = new DateTime(0);

		// TEST
		testee.marshal(localDateTime, writer, context);

		// ASSERT
		verify(writer).setValue("1970-01-01T01:00:00.000+01:00"); 
                             // "1970-01-01T02:00:00.000+02:00"

	}

	@Test
	public final void testUnmarshal() {

		// PREPARE
		final HierarchicalStreamReader reader = mock(HierarchicalStreamReader.class);
		final UnmarshallingContext context = mock(UnmarshallingContext.class);
		when(context.getRequiredType()).thenReturn(DateTime.class);
		when(reader.getValue()).thenReturn("1970-01-01T01:00:00.000+01:00");

		// TEST
		final Object result = testee.unmarshal(reader, context);

		// ASSERT
		assertThat(result).isEqualTo(new DateTime(0));

	}

}
// TESTCODE:END
