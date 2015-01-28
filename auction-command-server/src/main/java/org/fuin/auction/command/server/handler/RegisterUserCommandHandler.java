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

import javax.inject.Inject;
import javax.inject.Named;

import org.fuin.auction.command.api.base.RegisterUserCommand;
import org.fuin.auction.command.api.base.UserCreatedResult;
import org.fuin.auction.command.server.base.ConstraintSet;
import org.fuin.auction.command.server.domain.User;
import org.fuin.auction.command.server.domain.UserEmailAlreadyExistsException;
import org.fuin.auction.command.server.domain.UserNameAlreadyExistsException;
import org.fuin.auction.command.server.domain.UserNameEmailCombinationAlreadyExistsException;
import org.fuin.auction.common.OperationResult;
import org.fuin.objects4j.EmailAddress;
import org.fuin.objects4j.Password;
import org.fuin.objects4j.UserName;

/**
 * Handler for managing {@link RegisterUserCommand} commands.
 */
@Named
public class RegisterUserCommandHandler extends AbstractRegisterUserCommandHandler {

	@Inject
	private ConstraintSet constraintSet;

	@Override
	protected final OperationResult handleIntern(final RegisterUserCommand command)
	        throws UserEmailAlreadyExistsException, UserNameEmailCombinationAlreadyExistsException,
	        UserNameAlreadyExistsException {

		final UserName userName = new UserName(command.getUserName());
		final EmailAddress emailAddress = new EmailAddress(command.getEmail());
		final Password password = new Password(command.getPassword());

		constraintSet.add(userName, emailAddress);

		final User user = new User(getAggregateIdFactory().create(), userName, password,
		        emailAddress);
		getRepository().add(user);

		return new UserCreatedResult(user.getIdentifier().toString());

	}

}
