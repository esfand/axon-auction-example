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
package org.fuin.auction.command.server.domain;

import java.util.List;

import org.fuin.auction.common.KeyValue;
import org.fuin.auction.common.UserState;

// GENERATED CODE - DO NOT EDIT!

/**
 * The user's state was not as expected.
 */
public class IllegalUserStateException extends AbstractBaseException {
	private static final long serialVersionUID = 1L;

	/** Current state. */
	private UserState current;

	/** Expected states. */
	private List<UserState> expected;

	/**
	 * Constructor with all attributes.
	 * 
	 * @param current
	 *            Current state.
	 * @param expected
	 *            Expected states.
	 * 
	 */
	public IllegalUserStateException(final UserState current, final List<UserState> expected) {
		super(replace("The user's state was ${current} and not as expected ${expected}",
		        new KeyValue("current", current), new KeyValue("expected", expected)));
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
}
