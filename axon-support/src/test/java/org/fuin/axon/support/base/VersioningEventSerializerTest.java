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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import org.axonframework.domain.DomainEvent;
import org.fuin.serialver4j.base.ClassesHistory;
import org.fuin.serialver4j.base.DeserializationException;
import org.fuin.serialver4j.base.SimpleConverterFactory;
import org.fuin.serialver4j.base.VersioningJavaSerializer;
import org.fuin.serialver4j.base.VersioningSerializer;
import org.junit.Test;

/**
 * Test {@link VersioningEventSerializer}.
 * 
 * @author Michael Schnell
 */
// TESTCODE:BEGIN
public class VersioningEventSerializerTest {

	@Test
	public final void testCreate() {

		// PREPARE
		final VersioningSerializer serializer = new VersioningJavaSerializer(new ClassesHistory(
		        new SimpleConverterFactory()));
		final int bufSize = 1234;

		// TEST
		final VersioningEventSerializer testee = new VersioningEventSerializer(serializer, bufSize);

		// ASSERT
		assertThat(testee.getBufSize()).isEqualTo(bufSize);
		assertThat(testee.getSerializer()).isSameAs(serializer);

	}

	@Test
	public final void testSerialize() throws IOException {

		// PREPARE
		final byte[] objBytes = new byte[2048];
		new Random().nextBytes(objBytes);
		final VersioningSerializer serializer = new VersioningSerializer() {
			@Override
			public final Object deserialize(final InputStream in) throws IOException,
			        DeserializationException {
				return null;
			}

			@Override
			public final void serialize(final OutputStream out, final Object obj)
			        throws IOException {
				out.write(objBytes);
			}
		};
		final VersioningEventSerializer testee = new VersioningEventSerializer(serializer, 512);
		final DomainEvent event = mock(DomainEvent.class);

		// TEST
		final byte[] result = testee.serialize(event);

		// ASSERT
		assertThat(result).isEqualTo(objBytes);

	}

	@Test
	public final void testDeserialize() {

		// PREPARE
		final int objByteCount = 1024;
		final byte[] receivedBytes = new byte[objByteCount + 1];
		final DomainEvent event = mock(DomainEvent.class);
		final VersioningSerializer serializer = new VersioningSerializer() {
			@Override
			public final Object deserialize(final InputStream in) throws IOException,
			        DeserializationException {
				assertThat(in.read(receivedBytes)).isEqualTo(objByteCount);
				return event;
			}

			@Override
			public final void serialize(final OutputStream out, final Object obj)
			        throws IOException {
			}
		};
		final VersioningEventSerializer testee = new VersioningEventSerializer(serializer, 512);
		final byte[] objBytes = new byte[objByteCount];
		new Random().nextBytes(objBytes);

		// TEST
		final DomainEvent result = testee.deserialize(objBytes);

		// ASSERT
		assertThat(result).isSameAs(event);
		assertThat(Arrays.copyOf(receivedBytes, objByteCount)).isEqualTo(objBytes);

	}

}
// TESTCODE:END
