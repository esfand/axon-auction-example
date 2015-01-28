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

/**
 * Verifies the user's email by checking the verification token.
 */
public final class UserVerifyEmailCommand implements Operation {
	private static final long serialVersionUID = 100L;
	private long version = serialVersionUID;

	/** Token to compare with the internal token. */
	@NotNull
	@Label("Security Token")
	@TextField(width = 100)
	private String securityToken;

	/**
	 * Default constructor for serialization.
	 */
	protected UserVerifyEmailCommand() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param securityToken
	 *            Token to compare with the internal token.
	 * 
	 */
	public UserVerifyEmailCommand(final String securityToken) {
		super();
		this.securityToken = securityToken;
		Contract.requireValid(this);
	}

	@Override
	public final long getVersion() {
		return version;
	}

	/**
	 * Sets: Token to compare with the internal token.
	 * 
	 * @param securityToken
	 *            Value to set.
	 */
	public final void setSecurityToken(final String securityToken) {
		this.securityToken = securityToken;
	}

	/**
	 * Returns: Token to compare with the internal token.
	 * 
	 * @return SecurityToken
	 */
	public final String getSecurityToken() {
		return securityToken;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("securityToken", securityToken).append("version",
		        version).toString();
	}

	@Override
	public final Set<Integer> getResultCodes() {
		final Set<Integer> codes = new HashSet<Integer>();
		codes.add(UserEmailVerifiedResult.CODE);
		codes.add(UserVerifyEmailFailedIllegalStateResult.CODE);
		codes.add(UserVerifyEmailFailedTokenWrongResult.CODE);
		codes.add(AggregateIdNotFoundResult.CODE);
		codes.add(InvalidCommandResult.CODE);
		codes.add(InternalErrorResult.CODE);

		return codes;
	}
}
