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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.fuin.auction.command.server.events.CategoryCreatedEvent;
import org.fuin.auction.command.server.events.CategoryDeletedEvent;
import org.fuin.auction.command.server.events.CategoryMarkedForDeletionEvent;
import org.fuin.auction.common.CategoryName;
import org.fuin.auction.common.CategoryState;
import org.fuin.objects4j.TraceStringCapable;

// GENERATED CODE - DO NOT EDIT!

/**
 * Represents an auction category.
 */
public abstract class AbstractCategory extends AbstractAnnotatedAggregateRoot implements
        TraceStringCapable {
	/** Decriptive name. */
	private CategoryName name;

	/** Current state. */
	private CategoryState state;

	/**
	 * Constructor with id that fires NO EVENT.
	 * 
	 * @param identifier
	 *            Unique aggregate root id.
	 */
	public AbstractCategory(final AggregateIdentifier identifier) {
		super(identifier);
	}

	/**
	 * Returns: Decriptive name.
	 * 
	 * @return Name
	 */
	protected final CategoryName getName() {
		return name;
	}

	/**
	 * Sets: Decriptive name.
	 * 
	 * @param name
	 *            Value to set.
	 */
	protected final void setName(final CategoryName name) {
		this.name = name;
	}

	/**
	 * Returns: Current state.
	 * 
	 * @return State
	 */
	protected final CategoryState getState() {
		return state;
	}

	/**
	 * Sets: Current state.
	 * 
	 * @param state
	 *            Value to set.
	 */
	protected final void setState(final CategoryState state) {
		this.state = state;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("name", name).append("state", state).toString();
	}

	/**
	 * Marks an existing category for deletion. The following events are fired:
	 * {@link CategoryMarkedForDeletionEvent} *
	 * 
	 * @throws IllegalCategoryStateException
	 *             The category was not in an active state.
	 */
	public abstract void markForDeletion() throws IllegalCategoryStateException;

	/**
	 * Deletes a category that is marked for deletion. The following events are
	 * fired: {@link CategoryDeletedEvent} *
	 * 
	 * @throws IllegalCategoryStateException
	 *             The category is not in state MARKED_FOR_DELETION.
	 */
	public abstract void delete() throws IllegalCategoryStateException;

	/**
	 * Handles the {@link CategoryCreatedEvent} without checking any
	 * constraints.
	 * 
	 * @param event
	 *            Event.
	 */
	@EventHandler
	protected abstract void handle(CategoryCreatedEvent event);

	/**
	 * Handles the {@link CategoryMarkedForDeletionEvent} without checking any
	 * constraints.
	 * 
	 * @param event
	 *            Event.
	 */
	@EventHandler
	protected abstract void handle(CategoryMarkedForDeletionEvent event);

	/**
	 * Handles the {@link CategoryDeletedEvent} without checking any
	 * constraints.
	 * 
	 * @param event
	 *            Event.
	 */
	@EventHandler
	protected abstract void handle(CategoryDeletedEvent event);
}
