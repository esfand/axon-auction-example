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

import javax.inject.Named;

import org.axonframework.domain.AggregateIdentifier;
import org.fuin.auction.command.api.base.UserChangePasswordCommand;
import org.fuin.auction.command.api.base.UserPasswordChangedResult;
import org.fuin.auction.command.server.domain.PasswordMismatchException;
import org.fuin.auction.command.server.domain.User;
import org.fuin.auction.common.OperationResult;
import org.fuin.objects4j.Password;

/**
 * Handler for managing {@link UserChangePasswordCommand} commands.
 */
@Named
public class UserChangePasswordCommandHandler extends AbstractUserChangePasswordCommandHandler {

	@Override
	protected final OperationResult handleIntern(final UserChangePasswordCommand command)
	        throws PasswordMismatchException {

		final AggregateIdentifier id = toAggregateId(command.getAggregateId());
		final Password oldPw = new Password(command.getOldPassword());
		final Password newPw = new Password(command.getNewPassword());

		final User user = getRepository().load(id);

		user.changePassword(oldPw, newPw);

		return new UserPasswordChangedResult();

	}

}
