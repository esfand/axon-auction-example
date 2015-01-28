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
import org.fuin.auction.command.api.base.RegisterUserCommand;
import org.fuin.auction.command.api.base.RegisterUserFailedUserEmailAlreadyExistsResult;
import org.fuin.auction.command.api.base.RegisterUserFailedUserNameAlreadyExistsResult;
import org.fuin.auction.command.api.base.RegisterUserFailedUserNameEmailCombinationAlreadyExistsResult;
import org.fuin.auction.command.server.domain.UserEmailAlreadyExistsException;
import org.fuin.auction.command.server.domain.UserNameAlreadyExistsException;
import org.fuin.auction.command.server.domain.UserNameEmailCombinationAlreadyExistsException;
import org.fuin.auction.common.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// GENERATED CODE - DO NOT EDIT!

/**
 * Handler for managing {@link RegisterUserCommand} commands.
 */
public abstract class AbstractRegisterUserCommandHandler extends AbstractUserCommandHandler {
	private static final Logger LOG = LoggerFactory
	        .getLogger(AbstractRegisterUserCommandHandler.class);

	/**
	 * Registers a new user.
	 * 
	 * @param command
	 *            Command to handle.
	 * 
	 * @return Result of the command.
	 */
	@CommandHandler
	public final OperationResult handle(final RegisterUserCommand command) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle command: " + command.toTraceString());
		}

		try {
			return handleIntern(command);
		} catch (final UserEmailAlreadyExistsException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new RegisterUserFailedUserEmailAlreadyExistsResult();
		} catch (final UserNameEmailCombinationAlreadyExistsException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new RegisterUserFailedUserNameEmailCombinationAlreadyExistsResult();
		} catch (final UserNameAlreadyExistsException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new RegisterUserFailedUserNameAlreadyExistsResult();
		}
	}

	/**
	 * Registers a new user.
	 * 
	 * @param command
	 *            Valid command to handle.
	 * 
	 * @return Result of the command.
	 * 
	 * @throws UserEmailAlreadyExistsException
	 *             The email address is already registered by another user
	 * @throws UserNameEmailCombinationAlreadyExistsException
	 *             The combination of user name and email is already registered
	 * @throws UserNameAlreadyExistsException
	 *             The name is already used by another user
	 */
	protected abstract OperationResult handleIntern(final RegisterUserCommand command)
	        throws UserEmailAlreadyExistsException, UserNameEmailCombinationAlreadyExistsException,
	        UserNameAlreadyExistsException;
}
