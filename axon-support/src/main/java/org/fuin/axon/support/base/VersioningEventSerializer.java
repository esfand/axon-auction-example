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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.axonframework.domain.DomainEvent;
import org.axonframework.eventstore.EventSerializer;
import org.fuin.serialver4j.base.DeserializationException;
import org.fuin.serialver4j.base.VersioningSerializer;

/**
 * Serializer that is able to use several {@link VersioningSerializer}.
 */
public final class VersioningEventSerializer implements EventSerializer {

	private final VersioningSerializer serializer;

	private final int bufSize;

	/**
	 * Constructor with serializer. The internal buffer size for deserializing
	 * events is set to 1024.
	 * 
	 * @param serializer
	 *            Serializer to use.
	 */
	public VersioningEventSerializer(final VersioningSerializer serializer) {
		this(serializer, 1024);
	}

	/**
	 * Constructor with serializer.
	 * 
	 * @param serializer
	 *            Serializer to use.
	 * @param bufSize
	 *            Buffer size when deserializing events.
	 */
	public VersioningEventSerializer(final VersioningSerializer serializer, final int bufSize) {
		super();
		this.serializer = serializer;
		this.bufSize = bufSize;
	}

	@Override
	public final DomainEvent deserialize(final byte[] serializedEvent) {
		final ByteArrayInputStream in = new ByteArrayInputStream(serializedEvent);
		try {
			return (DomainEvent) serializer.deserialize(in);
		} catch (final IOException ex) {
			throw new RuntimeException(ex);
		} catch (final DeserializationException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public final byte[] serialize(final DomainEvent event) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream(bufSize);
		try {
			serializer.serialize(out, event);
		} catch (final IOException ex) {
			throw new RuntimeException(ex);
		}
		return out.toByteArray();
	}

	/**
	 * Returns the versioning serializer.
	 * 
	 * @return Serializer used.
	 */
	public final VersioningSerializer getSerializer() {
		return serializer;
	}

	/**
	 * Returns the buffer size when deserializing events.
	 * 
	 * @return Buffer size.
	 */
	public final int getBufSize() {
		return bufSize;
	}

}
