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

import org.axonframework.domain.AggregateIdentifier;
import org.fuin.auction.command.api.base.UserEmailVerifiedResult;
import org.fuin.auction.command.api.base.UserVerifyEmailCommand;
import org.fuin.auction.command.server.domain.IllegalUserStateException;
import org.fuin.auction.command.server.domain.SecurityTokenException;
import org.fuin.auction.command.server.domain.User;
import org.fuin.auction.common.OperationResult;
import org.fuin.auction.query.api.AuctionQueryService;

/**
 * Handler for managing {@link UserVerifyEmailCommand} commands.
 */
@Named
public class UserVerifyEmailCommandHandler extends AbstractUserVerifyEmailCommandHandler {

	@Inject
	@Named("queryService")
	private AuctionQueryService queryService;

	@Override
	protected final OperationResult handleIntern(final UserVerifyEmailCommand command)
	        throws IllegalUserStateException, SecurityTokenException {

		final String securityToken = command.getSecurityToken();

		final String idStr = queryService.findUserIdBySecurityToken(securityToken);
		if (idStr == null) {
			throw new SecurityTokenException();
		}

		final AggregateIdentifier id = toAggregateId(idStr);

		final User user = getRepository().load(id);

		user.verifyEmail(securityToken);

		return new UserEmailVerifiedResult();

	}

}
