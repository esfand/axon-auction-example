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
package org.axonframework.domain;

import java.io.Serializable;

/**
 * @author Allard Buijze
 */
public class StubDomainEvent extends DomainEvent implements Serializable {

	private static final long serialVersionUID = 834667054977749990L;

	private final long versionUID = serialVersionUID;

	/**
	 * Default constructor.
	 */
	public StubDomainEvent() {
		super();
	}

	/**
	 * Constructor with sequence number.
	 * 
	 * @param sequenceNumber
	 *            Sequence number.
	 */
	public StubDomainEvent(final long sequenceNumber) {
		super();
		setSequenceNumber(sequenceNumber);
	}

	/**
	 * Constructor with aggregate identifier.
	 * 
	 * @param aggregateIdentifier
	 *            Aggregate identifier.
	 */
	public StubDomainEvent(final AggregateIdentifier aggregateIdentifier) {
		super();
		setAggregateIdentifier(aggregateIdentifier);
	}

	/**
	 * Constructor with aggregate identifier and sequence number.
	 * 
	 * @param aggregateIdentifier
	 *            Aggregate identifier.
	 * @param sequenceNumber
	 *            Sequence number.
	 */
	public StubDomainEvent(final AggregateIdentifier aggregateIdentifier, final long sequenceNumber) {
		super();
		setAggregateIdentifier(aggregateIdentifier);
		setSequenceNumber(sequenceNumber);
	}

	/**
	 * Returns the instance version UID. If this object is serialized and
	 * deserialized this may differ from the static
	 * <code>serialVersionUID</code>.
	 * 
	 * @return Version of the instance.
	 */
	public final long getVersionUID() {
		return versionUID;
	}

	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder("StubDomainEvent aggregate [");
		sb.append(getAggregateIdentifier());
		sb.append("] sequenceNo [");
		sb.append(getSequenceNumber());
		sb.append("]");
		return sb.toString();
	}
}
