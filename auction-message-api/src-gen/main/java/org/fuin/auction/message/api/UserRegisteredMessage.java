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
package org.fuin.auction.message.api;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.Label;
import org.fuin.objects4j.TextField;
import org.fuin.objects4j.validation.EmailAddressStr;
import org.fuin.objects4j.validation.PasswordSha512Str;
import org.fuin.objects4j.validation.UUIDStr;
import org.fuin.objects4j.validation.UserNameStr;

/**
 * A new user was created.
 */
public final class UserRegisteredMessage implements AuctionMessage {
	private static final long serialVersionUID = 100L;

	/** Unique aggregate id. */
	@NotNull
	@UUIDStr
	private String aggregateId;

	/** Human readable unique name of the user. */
	@NotNull
	@UserNameStr
	@Label("User name")
	@TextField(width = 50)
	private String userName;

	/** Password hash. */
	@NotNull
	@PasswordSha512Str
	private String password;

	/** Email address. */
	@NotNull
	@EmailAddressStr
	@Label("Email address")
	@TextField(width = 50)
	private String email;

	/** Generated security token to verify the email address. */
	@NotNull
	@Label("Security Token")
	@TextField(width = 100)
	private String securityToken;

	/**
	 * Default constructor for serialization.
	 */
	protected UserRegisteredMessage() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param aggregateId
	 *            Aggregate id.
	 * @param userName
	 *            Human readable unique name of the user.
	 * @param password
	 *            Password hash.
	 * @param email
	 *            Email address.
	 * @param securityToken
	 *            Generated security token to verify the email address.
	 * 
	 */
	public UserRegisteredMessage(final String aggregateId, final String userName,
	        final String password, final String email, final String securityToken) {
		super();
		this.aggregateId = aggregateId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.securityToken = securityToken;
		Contract.requireValid(this);
	}

	/**
	 * Returns the aggregate id.
	 * 
	 * @return Unique id.
	 */
	public final String getAggregateId() {
		return aggregateId;
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
	 * Returns: Password hash.
	 * 
	 * @return Password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Returns: Email address.
	 * 
	 * @return Email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Returns: Generated security token to verify the email address.
	 * 
	 * @return SecurityToken
	 */
	public final String getSecurityToken() {
		return securityToken;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateId", aggregateId).append("userName",
		        userName).append("password", password).append("email", email).append(
		        "securityToken", securityToken).toString();
	}
}
