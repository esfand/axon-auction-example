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

/**
 * A state is not as expected.
 */
public abstract class AbstractIllegalStateException extends Exception {

	private static final long serialVersionUID = 2943719750064608089L;

	/**
	 * Constructor with states.
	 * 
	 * @param currentState
	 *            Current state.
	 * @param expected
	 *            Expected states.
	 * @param <T>
	 *            Concrete enum type.
	 */
	public <T extends Enum<T>> AbstractIllegalStateException(final Enum<T> currentState,
	        final Enum<T>... expected) {
		super("The current state was " + currentState + ", but expected: " + createStr(expected));
	}

	private static <T extends Enum<T>> String createStr(final Enum<T>... states) {
		final StringBuffer sb = new StringBuffer();
		for (final Enum<T> state : states) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(state.name());
		}
		return sb.toString();
	}

}
