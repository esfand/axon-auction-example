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
package org.fuin.auction.query.server;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.auction.common.UserState;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.TraceStringCapable;
import org.fuin.objects4j.validation.EmailAddressStr;
import org.fuin.objects4j.validation.UUIDStr;
import org.fuin.objects4j.validation.UserNameStr;

/**
 * User object.
 */
@Entity
@Table(name = "AUCTION_USER")
public class AuctionUser implements Serializable, TraceStringCapable {

	private static final long serialVersionUID = 8435312185589868237L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@NotNull
	@UUIDStr
	@Column(name = "AGGREGATE_ID", length = 37, nullable = false)
	private String aggregateId;

	@NotNull
	@UserNameStr
	@Column(name = "USER_NAME", length = 20, nullable = false)
	private String userName;

	@NotNull
	@EmailAddressStr
	@Column(name = "EMAIL", length = 320, nullable = false)
	private String email;

	@NotNull
	@Column(name = "STATE", nullable = false)
	private UserState state;

	@NotNull
	@Column(name = "PASSWORD", length = 130, nullable = false)
	private String password;

	@Column(name = "SECURITY_TOKEN", length = 40)
	private String securityToken;

	/**
	 * Package visible default constructor for persistence.
	 */
	AuctionUser() {
		super();
	}

	/**
	 * Constructor with mandatory attributes.
	 * 
	 * @param aggregateId
	 *            User's aggregate id
	 * @param userName
	 *            User id.
	 * @param email
	 *            Email address.
	 * @param state
	 *            Current state of the user.
	 * @param password
	 *            SHA512 hashed password.
	 * @param securityToken
	 *            The security token.
	 */
	public AuctionUser(final String aggregateId, final String userName, final String email,
	        final UserState state, final String password, final String securityToken) {
		super();
		this.aggregateId = aggregateId;
		this.userName = userName;
		this.email = email;
		this.state = state;
		this.password = password;
		this.securityToken = securityToken;
		Contract.requireValid(this);
	}

	/**
	 * Returns the internal id.
	 * 
	 * @return Unique id.
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * Sets the internal id to a new value.
	 * 
	 * @param id
	 *            Unique id to set.
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Returns the user's aggregate id.
	 * 
	 * @return Aggregate id.
	 */
	public final String getAggregateId() {
		return aggregateId;
	}

	/**
	 * Sets the user's aggregate id to a new value.
	 * 
	 * @param aggregateId
	 *            Aggregate id to set.
	 */
	public final void setAggregateId(final String aggregateId) {
		this.aggregateId = aggregateId;
	}

	/**
	 * Returns the user name.
	 * 
	 * @return User name.
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name to a new value.
	 * 
	 * @param userName
	 *            User name to set.
	 */
	public final void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * Returns the email address.
	 * 
	 * @return Email.
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Sets the email address to a new value.
	 * 
	 * @param email
	 *            Email to set.
	 */
	public final void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Returns the state of the user.
	 * 
	 * @return User's state.
	 */
	public final UserState getState() {
		return state;
	}

	/**
	 * Sets the state of the user to a new value.
	 * 
	 * @param state
	 *            User's state to set.
	 */
	public final void setState(final UserState state) {
		this.state = state;
	}

	/**
	 * Returns the password.
	 * 
	 * @return SHA-512 hash that is Base64 encoded.
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Sets the password to a new value.
	 * 
	 * @param password
	 *            SHA-512 hash that is Base64 encoded.
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Returns the security token associated with the user.
	 * 
	 * @return Security token or <code>null</code>.
	 */
	public final String getSecurityToken() {
		return securityToken;
	}

	/**
	 * Sets the security token associated with the user.
	 * 
	 * @param securityToken
	 *            Security token or <code>null</code>.
	 */
	public final void setSecurityToken(final String securityToken) {
		this.securityToken = securityToken;
	}

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("aggregateId", aggregateId).append("userName",
		        userName).append("email", email).append("state", state)
		        .append("password", password).append("securityToken", securityToken).toString();
	}

}
