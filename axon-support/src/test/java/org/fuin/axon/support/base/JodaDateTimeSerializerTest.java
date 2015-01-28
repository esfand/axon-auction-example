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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.caucho.hessian.io.AbstractHessianOutput;

/**
 * Test {@link JodaDateTimeSerializer}.
 * 
 * @author Michael Schnell
 */
// TESTCODE:BEGIN
public class JodaDateTimeSerializerTest {

	private JodaDateTimeSerializer testee;

	@Before
	public final void setUp() {
		testee = new JodaDateTimeSerializer();
	}

	@After
	public final void tearDown() {
		testee = null;
	}

	@Test
	public final void testWriteValue() throws IOException {

		// PREPARE
		final AbstractHessianOutput out = mock(AbstractHessianOutput.class);
		final DateTime dateTime = new DateTime(0);
		final long millis = dateTime.toDateTime(DateTimeZone.UTC).getMillis();

		// TEST
		testee.writeValue(out, dateTime);

		// ASSERT
		verify(out).writeUTCDate(millis);

	}

}
// TESTCODE:END
