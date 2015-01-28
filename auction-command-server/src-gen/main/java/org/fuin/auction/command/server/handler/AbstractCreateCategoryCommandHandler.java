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
package org.fuin.auction.command.server.handler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.fuin.auction.command.api.base.CreateCategoryCommand;
import org.fuin.auction.command.api.base.CreateCategoryFailedNameAlreadyExistResult;
import org.fuin.auction.command.server.domain.CategoryNameAlreadyExistException;
import org.fuin.auction.common.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// GENERATED CODE - DO NOT EDIT!

/**
 * Handler for managing {@link CreateCategoryCommand} commands.
 */
public abstract class AbstractCreateCategoryCommandHandler extends AbstractCategoryCommandHandler {
	private static final Logger LOG = LoggerFactory
	        .getLogger(AbstractCreateCategoryCommandHandler.class);

	/**
	 * Creates a new category.
	 * 
	 * @param command
	 *            Command to handle.
	 * 
	 * @return Result of the command.
	 */
	@CommandHandler
	public final OperationResult handle(final CreateCategoryCommand command) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle command: " + command.toTraceString());
		}

		try {
			return handleIntern(command);
		} catch (final CategoryNameAlreadyExistException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new CreateCategoryFailedNameAlreadyExistResult();
		}
	}

	/**
	 * Creates a new category.
	 * 
	 * @param command
	 *            Valid command to handle.
	 * 
	 * @return Result of the command.
	 * 
	 * @throws CategoryNameAlreadyExistException
	 *             The category name already exists
	 */
	protected abstract OperationResult handleIntern(final CreateCategoryCommand command)
	        throws CategoryNameAlreadyExistException;
}
