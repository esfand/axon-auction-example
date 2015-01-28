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

import java.util.ArrayList;
import java.util.List;

import org.axonframework.domain.AggregateIdentifier;
import org.fuin.auction.command.server.events.UserCreatedEvent;
import org.fuin.auction.command.server.events.UserEmailVerifiedEvent;
import org.fuin.auction.command.server.events.UserPasswordChangedEvent;
import org.fuin.auction.common.UserState;
import org.fuin.objects4j.EmailAddress;
import org.fuin.objects4j.Password;
import org.fuin.objects4j.PasswordSha512;
import org.fuin.objects4j.SecurityToken;
import org.fuin.objects4j.UserName;

/**
 * Represents a user in the auction system.
 */
public final class User extends AbstractUser {

	/**
	 * Constructor with id that fires NO EVENT.
	 * 
	 * @param identifier
	 *            Unique aggregate root id.
	 */
	public User(final AggregateIdentifier identifier) {
		super(identifier);
		setUserState(UserState.NEW);
	}

	/**
	 * Constructor that fires a {@link UserCreatedEvent}.
	 * 
	 * @param identifier
	 *            New id previously generated.
	 * @param userName
	 *            Human readable unique name of the user.
	 * @param password
	 *            User password.
	 * @param email
	 *            Password.
	 */
	public User(final AggregateIdentifier identifier, final UserName userName,
	        final Password password, final EmailAddress email) {
		super(identifier);
		apply(new UserCreatedEvent(userName, new PasswordSha512(password), email,
		        new SecurityToken()));
	}

	@Override
	public final void changePassword(final Password oldPw, final Password newPw)
	        throws PasswordMismatchException {

		final PasswordSha512 oldPassword = new PasswordSha512(oldPw);
		if (!getPassword().equals(oldPw)) {
			throw new PasswordMismatchException();
		}

		apply(new UserPasswordChangedEvent(oldPassword, new PasswordSha512(newPw)));

	}

	@Override
	public final void verifyEmail(final String token) throws IllegalUserStateException,
	        SecurityTokenException {

		if (!(getUserState().equals(UserState.NEW) || getUserState().equals(UserState.RESET))) {
			final List<UserState> expected = new ArrayList<UserState>();
			expected.add(UserState.NEW);
			expected.add(UserState.RESET);
			throw new IllegalUserStateException(getUserState(), expected);
		}
		if (!getVerificationToken().toString().equals(token)) {
			throw new SecurityTokenException();
		}

		apply(new UserEmailVerifiedEvent());

	}

	@Override
	protected final void handle(final UserCreatedEvent event) {
		setEmail(event.getEmail());
		setPassword(event.getPassword());
		setUserName(event.getUserName());
		setVerificationToken(event.getSecurityToken());
	}

	@Override
	protected final void handle(final UserPasswordChangedEvent event) {
		setPassword(event.getNewPassword());
	}

	@Override
	protected final void handle(final UserEmailVerifiedEvent event) {
		setUserState(UserState.ACTIVE);
	}

}
