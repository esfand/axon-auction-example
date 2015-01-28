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
package org.fuin.auction.message.api;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.Label;
import org.fuin.objects4j.TextField;

/**
 * A new category was created.
 */
public final class CategoryCreatedMessage implements AuctionMessage {
	private static final long serialVersionUID = 100L;

	/** Unique aggregate id. */
	@NotNull
	// @LongStr
	private String aggregateId;

	/** Decriptive name. */
	@NotNull
	@Label("Category name")
	@TextField
	private String name;

	/**
	 * Default constructor for serialization.
	 */
	protected CategoryCreatedMessage() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param aggregateId
	 *            Aggregate id.
	 * @param name
	 *            Decriptive name.
	 * 
	 */
	public CategoryCreatedMessage(final String aggregateId, final String name) {
		super();
		this.aggregateId = aggregateId;
		this.name = name;
		Contract.requireValid(this);
	}

	/**
	 * Returns the aggregate id.
	 * 
	 * @return Unique id.
	 */
	public final String getAggregateId() {
		return aggregateId;
	}

	/**
	 * Returns: Decriptive name.
	 * 
	 * @return Name
	 */
	public final String getName() {
		return name;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateId", aggregateId).append("name", name)
		        .toString();
	}
}
