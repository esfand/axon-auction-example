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

import static org.fuin.auction.common.OperationResultType.ERROR;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.auction.common.AbstractOperationResult;

/**
 * The Aggregate id was not found.
 */
public final class AggregateIdNotFoundResult extends AbstractOperationResult {

	private static final long serialVersionUID = 100L;

	/** Unique error code. */
	public static final int CODE = 3;

	/**
	 * Default constructor.
	 */
	public AggregateIdNotFoundResult() {
		super(CODE, ERROR, "The Aggregate id was not found");
	}

	@Override
	public final String toTraceString() {
		final ToStringBuilder builder = new ToStringBuilder(this);
		appendAbstractCommandResult(builder);
		return builder.toString();
	}

}
