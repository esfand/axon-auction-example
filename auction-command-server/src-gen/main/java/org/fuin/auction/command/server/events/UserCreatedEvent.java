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
package org.fuin.auction.command.server.events;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.axonframework.domain.DomainEvent;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.EmailAddress;
import org.fuin.objects4j.PasswordSha512;
import org.fuin.objects4j.SecurityToken;
import org.fuin.objects4j.UserName;

// GENERATED CODE - DO NOT EDIT!

/**
 * A new user was created.
 */
public final class UserCreatedEvent extends DomainEvent implements ExtendedDomainEvent {
	private static final long serialVersionUID = 100L;

	/** Human readable unique name of the user. */
	private UserName userName;

	/** Password hash. */
	private PasswordSha512 password;

	/** Email address. */
	private EmailAddress email;

	/** Generated security token to verify the email address. */
	private SecurityToken securityToken;

	/**
	 * Default constructor for serialization.
	 */
	protected UserCreatedEvent() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
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
	public UserCreatedEvent(final UserName userName, final PasswordSha512 password,
	        final EmailAddress email, final SecurityToken securityToken) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.securityToken = securityToken;
		Contract.requireValid(this);
	}

	/**
	 * Returns: Human readable unique name of the user.
	 * 
	 * @return UserName
	 */
	public final UserName getUserName() {
		return userName;
	}

	/**
	 * Returns: Password hash.
	 * 
	 * @return Password
	 */
	public final PasswordSha512 getPassword() {
		return password;
	}

	/**
	 * Returns: Email address.
	 * 
	 * @return Email
	 */
	public final EmailAddress getEmail() {
		return email;
	}

	/**
	 * Returns: Generated security token to verify the email address.
	 * 
	 * @return SecurityToken
	 */
	public final SecurityToken getSecurityToken() {
		return securityToken;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateIdentifier", getAggregateIdentifier())
		        .append("aggregateVersion", getAggregateVersion()).append("userName", userName)
		        .append("password", password).append("email", email).append("securityToken",
		                securityToken).toString();
	}
}
