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

import static org.fuin.auction.common.OperationResultType.SUCCESS;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.auction.common.AbstractOperationResult;
import org.fuin.auction.common.KeyValue;
import org.fuin.objects4j.validation.UUIDStr;

// GENERATED CODE - DO NOT EDIT!

/**
 * A new user was successfully created.
 */
public final class UserCreatedResult extends AbstractOperationResult {
	private static final long serialVersionUID = 100L;

	/** Unique ID of the result. */
	public static final int CODE = 104;

	/** Unique identifier. */
	@NotNull
	@UUIDStr
	private String id;

	/**
	 * Default constructor for serialization.
	 */
	protected UserCreatedResult() {
		super(CODE, SUCCESS,
		        "You registered successfully! A confirmation email has been sent to you.");
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param id
	 *            Unique identifier.
	 * 
	 */
	public UserCreatedResult(final String id) {
		super(CODE, SUCCESS, replace(
		        "You registered successfully! A confirmation email has been sent to you.",
		        new KeyValue("id", id)));
		this.id = id;
	}

	/**
	 * Returns: Unique identifier.
	 * 
	 * @return Id
	 */
	public final String getId() {
		return id;
	}

	@Override
	public final String toTraceString() {
		final ToStringBuilder builder = new ToStringBuilder(this);
		appendAbstractCommandResult(builder);
		builder.append("id", id);

		return builder.toString();
	}
}
