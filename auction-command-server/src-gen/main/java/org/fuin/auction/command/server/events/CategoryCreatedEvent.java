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
package org.fuin.auction.command.server.events;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.axonframework.domain.DomainEvent;
import org.fuin.auction.common.CategoryName;
import org.fuin.objects4j.Contract;

// GENERATED CODE - DO NOT EDIT!

/**
 * A new category was created.
 */
public final class CategoryCreatedEvent extends DomainEvent implements ExtendedDomainEvent {
	private static final long serialVersionUID = 100L;

	/** Decriptive name. */
	private CategoryName name;

	/**
	 * Default constructor for serialization.
	 */
	protected CategoryCreatedEvent() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param name
	 *            Decriptive name.
	 * 
	 */
	public CategoryCreatedEvent(final CategoryName name) {
		super();
		this.name = name;
		Contract.requireValid(this);
	}

	/**
	 * Returns: Decriptive name.
	 * 
	 * @return Name
	 */
	public final CategoryName getName() {
		return name;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateIdentifier", getAggregateIdentifier())
		        .append("aggregateVersion", getAggregateVersion()).append("name", name).toString();
	}
}
