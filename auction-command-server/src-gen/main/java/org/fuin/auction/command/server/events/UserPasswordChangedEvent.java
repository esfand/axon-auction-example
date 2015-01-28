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
import org.fuin.objects4j.PasswordSha512;

// GENERATED CODE - DO NOT EDIT!

/**
 * The password of a user was changed.
 */
public final class UserPasswordChangedEvent extends DomainEvent implements ExtendedDomainEvent {
	private static final long serialVersionUID = 100L;

	/** Old password hash. */
	private PasswordSha512 oldPassword;

	/** New password hash. */
	private PasswordSha512 newPassword;

	/**
	 * Default constructor for serialization.
	 */
	protected UserPasswordChangedEvent() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param oldPassword
	 *            Old password hash.
	 * @param newPassword
	 *            New password hash.
	 * 
	 */
	public UserPasswordChangedEvent(final PasswordSha512 oldPassword,
	        final PasswordSha512 newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		Contract.requireValid(this);
	}

	/**
	 * Returns: Old password hash.
	 * 
	 * @return OldPassword
	 */
	public final PasswordSha512 getOldPassword() {
		return oldPassword;
	}

	/**
	 * Returns: New password hash.
	 * 
	 * @return NewPassword
	 */
	public final PasswordSha512 getNewPassword() {
		return newPassword;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateIdentifier", getAggregateIdentifier())
		        .append("aggregateVersion", getAggregateVersion()).append("oldPassword",
		                oldPassword).append("newPassword", newPassword).toString();
	}
}
