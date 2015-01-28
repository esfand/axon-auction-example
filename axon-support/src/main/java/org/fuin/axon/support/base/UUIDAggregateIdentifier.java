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

import java.io.Serializable;
import java.util.UUID;

import org.axonframework.domain.AggregateIdentifier;

/**
 * An aggregate identifier that is based on a UUID.
 */
public final class UUIDAggregateIdentifier implements AggregateIdentifier, Serializable {

	private static final long serialVersionUID = -5486703238473050628L;

	private final UUID uuid;

	/**
	 * Constructor that generates a random UUID.
	 */
	public UUIDAggregateIdentifier() {
		super();
		this.uuid = UUID.randomUUID();
	}

	/**
	 * Constrcutor with a given UUID.
	 * 
	 * @param uuidStr
	 *            String representation of a UUID - Not <code>null</code> an
	 *            parseable with {@link UUID#fromString(String)}.
	 * 
	 * @throws IllegalAggregateIdentifierException
	 *             Error parsing the aggregate identifier string.
	 */
	public UUIDAggregateIdentifier(final String uuidStr) throws IllegalAggregateIdentifierException {
		super();
		if (uuidStr == null) {
			throw new IllegalAggregateIdentifierException("null", UUIDAggregateIdentifier.class);
		}
		try {
			this.uuid = UUID.fromString(uuidStr);
		} catch (final IllegalArgumentException ex) {
			throw new IllegalAggregateIdentifierException(uuidStr, UUIDAggregateIdentifier.class);
		}
	}

	// CHECKSTYLE:OFF Generated code

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UUIDAggregateIdentifier other = (UUIDAggregateIdentifier) obj;
		return uuid.equals(other.uuid);
	}

	// CHECKSTYLE:ON

	@Override
	public final String asString() {
		return uuid.toString();
	}

	@Override
	public final String toString() {
		return uuid.toString();
	}

}
