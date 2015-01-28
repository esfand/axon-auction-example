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
import org.axonframework.repository.AggregateNotFoundException;
import org.fuin.auction.command.api.base.AggregateIdNotFoundResult;
import org.fuin.auction.command.api.base.UserChangePasswordCommand;
import org.fuin.auction.command.api.base.UserChangePasswordMismatchResult;
import org.fuin.auction.command.server.domain.PasswordMismatchException;
import org.fuin.auction.common.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// GENERATED CODE - DO NOT EDIT!

/**
 * Handler for managing {@link UserChangePasswordCommand} commands.
 */
public abstract class AbstractUserChangePasswordCommandHandler extends AbstractUserCommandHandler {
	private static final Logger LOG = LoggerFactory
	        .getLogger(AbstractUserChangePasswordCommandHandler.class);

	/**
	 * Changes the user's password.
	 * 
	 * @param command
	 *            Command to handle.
	 * 
	 * @return Result of the command.
	 */
	@CommandHandler
	public final OperationResult handle(final UserChangePasswordCommand command) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle command: " + command.toTraceString());
		}

		try {
			return handleIntern(command);
		} catch (final PasswordMismatchException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new UserChangePasswordMismatchResult();
		} catch (final AggregateNotFoundException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new AggregateIdNotFoundResult();
		}
	}

	/**
	 * Changes the user's password.
	 * 
	 * @param command
	 *            Valid command to handle.
	 * 
	 * @return Result of the command.
	 * 
	 * @throws PasswordMismatchException
	 *             The user's password was not equal to the old password sent
	 *             with the command.
	 */
	protected abstract OperationResult handleIntern(final UserChangePasswordCommand command)
	        throws PasswordMismatchException;
}
