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
import org.fuin.auction.command.api.base.UserVerifyEmailCommand;
import org.fuin.auction.command.api.base.UserVerifyEmailFailedIllegalStateResult;
import org.fuin.auction.command.api.base.UserVerifyEmailFailedTokenWrongResult;
import org.fuin.auction.command.server.domain.IllegalUserStateException;
import org.fuin.auction.command.server.domain.SecurityTokenException;
import org.fuin.auction.common.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// GENERATED CODE - DO NOT EDIT!

/**
 * Handler for managing {@link UserVerifyEmailCommand} commands.
 */
public abstract class AbstractUserVerifyEmailCommandHandler extends AbstractUserCommandHandler {
	private static final Logger LOG = LoggerFactory
	        .getLogger(AbstractUserVerifyEmailCommandHandler.class);

	/**
	 * Verifies the user's email by checking the verification token.
	 * 
	 * @param command
	 *            Command to handle.
	 * 
	 * @return Result of the command.
	 */
	@CommandHandler
	public final OperationResult handle(final UserVerifyEmailCommand command) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle command: " + command.toTraceString());
		}

		try {
			return handleIntern(command);
		} catch (final IllegalUserStateException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new UserVerifyEmailFailedIllegalStateResult(ex.getCurrent(), ex.getExpected());
		} catch (final SecurityTokenException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new UserVerifyEmailFailedTokenWrongResult();
		} catch (final AggregateNotFoundException ex) {
			LOG.info(ex.getMessage() + ": " + command.toTraceString());

			return new AggregateIdNotFoundResult();
		}
	}

	/**
	 * Verifies the user's email by checking the verification token.
	 * 
	 * @param command
	 *            Valid command to handle.
	 * 
	 * @return Result of the command.
	 * 
	 * @throws IllegalUserStateException
	 *             The state of the user was not NEW or RESET.
	 * @throws SecurityTokenException
	 *             The security token was not equal to the one sent with email.
	 */
	protected abstract OperationResult handleIntern(final UserVerifyEmailCommand command)
	        throws IllegalUserStateException, SecurityTokenException;
}
