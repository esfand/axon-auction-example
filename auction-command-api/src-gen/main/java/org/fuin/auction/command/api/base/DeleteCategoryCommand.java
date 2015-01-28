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
package org.fuin.auction.command.api.base;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.auction.common.InternalErrorResult;
import org.fuin.auction.common.Operation;
import org.fuin.objects4j.Contract;

/**
 * Deletes a category that is marked for deletion.
 */
public final class DeleteCategoryCommand implements Operation {
	private static final long serialVersionUID = 100L;
	private long version = serialVersionUID;

	/** Unique aggregate id. */
	@NotNull
	// @LongStr
	private String aggregateId;

	/**
	 * Default constructor for serialization.
	 */
	protected DeleteCategoryCommand() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param aggregateId
	 *            Aggregate id.
	 * 
	 */
	public DeleteCategoryCommand(final String aggregateId) {
		super();
		this.aggregateId = aggregateId;
		Contract.requireValid(this);
	}

	@Override
	public final long getVersion() {
		return version;
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
	 * Sets the aggregate id to a new value.
	 * 
	 * @param aggregateId
	 *            Unique id to set.
	 */
	public final void setAggregateId(final String aggregateId) {
		this.aggregateId = aggregateId;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateId", aggregateId).append("version",
		        version).toString();
	}

	@Override
	public final Set<Integer> getResultCodes() {
		final Set<Integer> codes = new HashSet<Integer>();
		codes.add(CategoryDeletedResult.CODE);
		codes.add(DeleteCategoryFailedIllegalStateResult.CODE);
		codes.add(AggregateIdNotFoundResult.CODE);
		codes.add(InvalidCommandResult.CODE);
		codes.add(InternalErrorResult.CODE);

		return codes;
	}
}
