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
import org.fuin.objects4j.Label;
import org.fuin.objects4j.TextField;

/**
 * Creates a new category.
 */
public final class CreateCategoryCommand implements Operation {
	private static final long serialVersionUID = 100L;
	private long version = serialVersionUID;

	/** Decriptive name. */
	@NotNull
	@Label("Category name")
	@TextField
	private String name;

	/**
	 * Default constructor for serialization.
	 */
	protected CreateCategoryCommand() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param name
	 *            Decriptive name.
	 * 
	 */
	public CreateCategoryCommand(final String name) {
		super();
		this.name = name;
		Contract.requireValid(this);
	}

	@Override
	public final long getVersion() {
		return version;
	}

	/**
	 * Sets: Decriptive name.
	 * 
	 * @param name
	 *            Value to set.
	 */
	public final void setName(final String name) {
		this.name = name;
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
		return new ToStringBuilder(this).append("name", name).append("version", version).toString();
	}

	@Override
	public final Set<Integer> getResultCodes() {
		final Set<Integer> codes = new HashSet<Integer>();
		codes.add(CategoryCreatedResult.CODE);
		codes.add(CreateCategoryFailedNameAlreadyExistResult.CODE);
		codes.add(InvalidCommandResult.CODE);
		codes.add(InternalErrorResult.CODE);

		return codes;
	}
}
