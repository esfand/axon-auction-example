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

import org.fuin.auction.common.CategoryState;
import org.fuin.auction.common.KeyValue;

// GENERATED CODE - DO NOT EDIT!

/**
 * The category's state was not as expected.
 */
public class IllegalCategoryStateException extends AbstractBaseException {
	private static final long serialVersionUID = 1L;

	/** Current state. */
	private CategoryState current;

	/** Expected states. */
	private List<CategoryState> expected;

	/**
	 * Constructor with all attributes.
	 * 
	 * @param current
	 *            Current state.
	 * @param expected
	 *            Expected states.
	 * 
	 */
	public IllegalCategoryStateException(final CategoryState current,
	        final List<CategoryState> expected) {
		super(replace("The category's state was ${current} and not as expected ${expected}",
		        new KeyValue("current", current), new KeyValue("expected", expected)));
		this.current = current;
		this.expected = expected;
	}

	/**
	 * Returns: Current state.
	 * 
	 * @return Current
	 */
	public final CategoryState getCurrent() {
		return current;
	}

	/**
	 * Returns: Expected states.
	 * 
	 * @return Expected
	 */
	public final List<CategoryState> getExpected() {
		return expected;
	}
}
