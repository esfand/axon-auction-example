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
import org.fuin.objects4j.validation.PasswordStr;
import org.fuin.objects4j.validation.UUIDStr;

/**
 * Changes the user's password.
 */
public final class UserChangePasswordCommand implements Operation {
	private static final long serialVersionUID = 100L;
	private long version = serialVersionUID;

	/** Unique aggregate id. */
	@NotNull
	@UUIDStr
	private String aggregateId;

	/** Old clear text password. */
	@NotNull
	@PasswordStr
	@Label("Old password")
	@TextField(width = 50)
	private String oldPassword;

	/** New clear text password. */
	@NotNull
	@PasswordStr
	@Label("New password")
	@TextField(width = 50)
	private String newPassword;

	/**
	 * Default constructor for serialization.
	 */
	protected UserChangePasswordCommand() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param aggregateId
	 *            Aggregate id.
	 * @param oldPassword
	 *            Old clear text password.
	 * @param newPassword
	 *            New clear text password.
	 * 
	 */
	public UserChangePasswordCommand(final String aggregateId, final String oldPassword,
	        final String newPassword) {
		super();
		this.aggregateId = aggregateId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		Contract.requireValid(this);
	}

	@Override
	public final long getVersion() {
		return version;
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
	 * Sets the aggregate id to a new value.
	 * 
	 * @param aggregateId
	 *            Unique id to set.
	 */
	public final void setAggregateId(final String aggregateId) {
		this.aggregateId = aggregateId;
	}

	/**
	 * Sets: Old clear text password.
	 * 
	 * @param oldPassword
	 *            Value to set.
	 */
	public final void setOldPassword(final String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * Returns: Old clear text password.
	 * 
	 * @return OldPassword
	 */
	public final String getOldPassword() {
		return oldPassword;
	}

	/**
	 * Sets: New clear text password.
	 * 
	 * @param newPassword
	 *            Value to set.
	 */
	public final void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Returns: New clear text password.
	 * 
	 * @return NewPassword
	 */
	public final String getNewPassword() {
		return newPassword;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateId", aggregateId).append("version",
		        version).toString();
	}

	@Override
	public final Set<Integer> getResultCodes() {
		final Set<Integer> codes = new HashSet<Integer>();
		codes.add(UserPasswordChangedResult.CODE);
		codes.add(UserChangePasswordMismatchResult.CODE);
		codes.add(AggregateIdNotFoundResult.CODE);
		codes.add(InvalidCommandResult.CODE);
		codes.add(InternalErrorResult.CODE);

		return codes;
	}
}
