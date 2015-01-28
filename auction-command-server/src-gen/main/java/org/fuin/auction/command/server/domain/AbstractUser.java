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
import org.fuin.auction.command.server.events.UserCreatedEvent;
import org.fuin.auction.command.server.events.UserEmailVerifiedEvent;
import org.fuin.auction.command.server.events.UserPasswordChangedEvent;
import org.fuin.auction.common.UserState;
import org.fuin.objects4j.EmailAddress;
import org.fuin.objects4j.Password;
import org.fuin.objects4j.PasswordSha512;
import org.fuin.objects4j.SecurityToken;
import org.fuin.objects4j.TraceStringCapable;
import org.fuin.objects4j.UserName;

// GENERATED CODE - DO NOT EDIT!

/**
 * Represents a user in the auction system.
 */
public abstract class AbstractUser extends AbstractAnnotatedAggregateRoot implements
        TraceStringCapable {
	/** Human readable unique name of the user. */
	private UserName userName;

	/** Email address. */
	private EmailAddress email;

	/** Current state of the user. */
	private UserState userState;

	/** Hash of the user's password. */
	private PasswordSha512 password;

	/** Generated security token to verify the email address. */
	private SecurityToken verificationToken;

	/**
	 * Constructor with id that fires NO EVENT.
	 * 
	 * @param identifier
	 *            Unique aggregate root id.
	 */
	public AbstractUser(final AggregateIdentifier identifier) {
		super(identifier);
	}

	/**
	 * Returns: Human readable unique name of the user.
	 * 
	 * @return UserName
	 */
	protected final UserName getUserName() {
		return userName;
	}

	/**
	 * Sets: Human readable unique name of the user.
	 * 
	 * @param userName
	 *            Value to set.
	 */
	protected final void setUserName(final UserName userName) {
		this.userName = userName;
	}

	/**
	 * Returns: Email address.
	 * 
	 * @return Email
	 */
	protected final EmailAddress getEmail() {
		return email;
	}

	/**
	 * Sets: Email address.
	 * 
	 * @param email
	 *            Value to set.
	 */
	protected final void setEmail(final EmailAddress email) {
		this.email = email;
	}

	/**
	 * Returns: Current state of the user.
	 * 
	 * @return UserState
	 */
	protected final UserState getUserState() {
		return userState;
	}

	/**
	 * Sets: Current state of the user.
	 * 
	 * @param userState
	 *            Value to set.
	 */
	protected final void setUserState(final UserState userState) {
		this.userState = userState;
	}

	/**
	 * Returns: Hash of the user's password.
	 * 
	 * @return Password
	 */
	protected final PasswordSha512 getPassword() {
		return password;
	}

	/**
	 * Sets: Hash of the user's password.
	 * 
	 * @param password
	 *            Value to set.
	 */
	protected final void setPassword(final PasswordSha512 password) {
		this.password = password;
	}

	/**
	 * Returns: Generated security token to verify the email address.
	 * 
	 * @return VerificationToken
	 */
	protected final SecurityToken getVerificationToken() {
		return verificationToken;
	}

	/**
	 * Sets: Generated security token to verify the email address.
	 * 
	 * @param verificationToken
	 *            Value to set.
	 */
	protected final void setVerificationToken(final SecurityToken verificationToken) {
		this.verificationToken = verificationToken;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("userName", userName).append("email", email)
		        .append("userState", userState).append("password", password).append(
		                "verificationToken", verificationToken).toString();
	}

	/**
	 * Changes the user's password. The following events are fired:
	 * {@link UserPasswordChangedEvent} *
	 * 
	 * @param oldPassword
	 *            Old clear text password.
	 * @param newPassword
	 *            New clear text password.
	 * 
	 @throws PasswordMismatchException
	 *             The user's password was not equal to the old password sent
	 *             with the command.
	 */
	public abstract void changePassword(final Password oldPassword, final Password newPassword)
	        throws PasswordMismatchException;

	/**
	 * Verifies the user's email by checking the verification token. The
	 * following events are fired: {@link UserEmailVerifiedEvent} *
	 * 
	 * @param securityToken
	 *            Token to compare with the internal token.
	 * 
	 @throws IllegalUserStateException
	 *             The state of the user was not NEW or RESET.
	 * @throws SecurityTokenException
	 *             The security token was not equal to the one sent with email.
	 */
	public abstract void verifyEmail(final String securityToken) throws IllegalUserStateException,
	        SecurityTokenException;

	/**
	 * Handles the {@link UserCreatedEvent} without checking any constraints.
	 * 
	 * @param event
	 *            Event.
	 */
	@EventHandler
	protected abstract void handle(UserCreatedEvent event);

	/**
	 * Handles the {@link UserPasswordChangedEvent} without checking any
	 * constraints.
	 * 
	 * @param event
	 *            Event.
	 */
	@EventHandler
	protected abstract void handle(UserPasswordChangedEvent event);

	/**
	 * Handles the {@link UserEmailVerifiedEvent} without checking any
	 * constraints.
	 * 
	 * @param event
	 *            Event.
	 */
	@EventHandler
	protected abstract void handle(UserEmailVerifiedEvent event);
}
