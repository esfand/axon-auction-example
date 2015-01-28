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
package org.fuin.auction.command.api.base;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.auction.common.InternalErrorResult;
import org.fuin.auction.common.Operation;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.Label;
import org.fuin.objects4j.TextField;
import org.fuin.objects4j.validation.EmailAddressStr;
import org.fuin.objects4j.validation.PasswordStr;
import org.fuin.objects4j.validation.UserNameStr;

/**
 * Registers a new user.
 */
public final class RegisterUserCommand implements Operation {
	private static final long serialVersionUID = 100L;
	private long version = serialVersionUID;

	/** Human readable unique name of the user. */
	@NotNull
	@UserNameStr
	@Label("User name")
	@TextField(width = 50)
	private String userName;

	/** Clear text password. */
	@NotNull
	@PasswordStr
	@Label("Password")
	@TextField
	private String password;

	/** Email address. */
	@NotNull
	@EmailAddressStr
	@Label("Email address")
	@TextField(width = 50)
	private String email;

	/**
	 * Default constructor for serialization.
	 */
	protected RegisterUserCommand() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param userName
	 *            Human readable unique name of the user.
	 * @param password
	 *            Clear text password.
	 * @param email
	 *            Email address.
	 * 
	 */
	public RegisterUserCommand(final String userName, final String password, final String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		Contract.requireValid(this);
	}

	@Override
	public final long getVersion() {
		return version;
	}

	/**
	 * Sets: Human readable unique name of the user.
	 * 
	 * @param userName
	 *            Value to set.
	 */
	public final void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * Returns: Human readable unique name of the user.
	 * 
	 * @return UserName
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * Sets: Clear text password.
	 * 
	 * @param password
	 *            Value to set.
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Returns: Clear text password.
	 * 
	 * @return Password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Sets: Email address.
	 * 
	 * @param email
	 *            Value to set.
	 */
	public final void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Returns: Email address.
	 * 
	 * @return Email
	 */
	public final String getEmail() {
		return email;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("userName", userName).append("password", password)
		        .append("email", email).append("version", version).toString();
	}

	@Override
	public final Set<Integer> getResultCodes() {
		final Set<Integer> codes = new HashSet<Integer>();
		codes.add(UserCreatedResult.CODE);
		codes.add(RegisterUserFailedUserEmailAlreadyExistsResult.CODE);
		codes.add(RegisterUserFailedUserNameEmailCombinationAlreadyExistsResult.CODE);
		codes.add(RegisterUserFailedUserNameAlreadyExistsResult.CODE);
		codes.add(InvalidCommandResult.CODE);
		codes.add(InternalErrorResult.CODE);

		return codes;
	}
}
