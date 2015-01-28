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

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.auction.common.AbstractOperationResult;
import org.fuin.auction.common.KeyValue;
import org.fuin.auction.common.UserState;

// GENERATED CODE - DO NOT EDIT!

/**
 * The state of the user was not NEW or RESET.
 */
public final class UserVerifyEmailFailedIllegalStateResult extends AbstractOperationResult {
	private static final long serialVersionUID = 100L;

	/** Unique ID of the result. */
	public static final int CODE = 107;

	/** Current state. */
	@NotNull
	private UserState current;

	/** Expected states. */
	@NotNull
	private List<UserState> expected;

	/**
	 * Default constructor for serialization.
	 */
	protected UserVerifyEmailFailedIllegalStateResult() {
		super(CODE, ERROR, "The user's state was ${current} and not as expected ${expected}");
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param current
	 *            Current state.
	 * @param expected
	 *            Expected states.
	 * 
	 */
	public UserVerifyEmailFailedIllegalStateResult(final UserState current,
	        final List<UserState> expected) {
		super(CODE, ERROR, replace(
		        "The user's state was ${current} and not as expected ${expected}", new KeyValue(
		                "current", current), new KeyValue("expected", expected)));
		this.current = current;
		this.expected = expected;
	}

	/**
	 * Returns: Current state.
	 * 
	 * @return Current
	 */
	public final UserState getCurrent() {
		return current;
	}

	/**
	 * Returns: Expected states.
	 * 
	 * @return Expected
	 */
	public final List<UserState> getExpected() {
		return expected;
	}

	@Override
	public final String toTraceString() {
		final ToStringBuilder builder = new ToStringBuilder(this);
		appendAbstractCommandResult(builder);
		builder.append("current", current).append("expected", expected);

		return builder.toString();
	}
}
