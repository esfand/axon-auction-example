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
package org.fuin.auction.command.server.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;

import org.fuin.auction.command.server.domain.CategoryNameAlreadyExistException;
import org.fuin.auction.command.server.domain.UserEmailAlreadyExistsException;
import org.fuin.auction.command.server.domain.UserNameAlreadyExistsException;
import org.fuin.auction.command.server.domain.UserNameEmailCombinationAlreadyExistsException;
import org.fuin.auction.command.server.utils.AbstractJdbcHelper;
import org.fuin.auction.common.CategoryName;
import org.fuin.objects4j.EmailAddress;
import org.fuin.objects4j.UserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pure JDBC constraint service (Apache Derby!). Could be done more nice
 * (database independent for example...) but it's just to show that there is no
 * need that a "full-blown" Hibernate or JPA here...
 */
@Named
public final class ConstraintSetJdbc extends AbstractJdbcHelper implements ConstraintSet {

	private static final Logger LOG = LoggerFactory.getLogger(ConstraintSetJdbc.class);

	/**
	 * Default constructor.
	 */
	public ConstraintSetJdbc() {
		super(ConstraintSetJdbc.class, "/jdbc.properties");
	}

	@Override
	public final void add(final UserName userName, final EmailAddress email)
	        throws UserNameEmailCombinationAlreadyExistsException, UserNameAlreadyExistsException,
	        UserEmailAlreadyExistsException {

		try {

			if (!insert(userName, email)) {

				final String userNameStr = userName.toString();
				final String emailStr = email.toString();

				final UserNameEmail row = select(userName, email);
				if (row.getUserName().equals(userNameStr)) {
					if (row.getEmail().equals(emailStr)) {
						throw new UserNameEmailCombinationAlreadyExistsException();
					}
					throw new UserNameAlreadyExistsException();
				} else {
					if (row.getEmail().equals(emailStr)) {
						throw new UserEmailAlreadyExistsException();
					}
					// This should never happen because it doesn't fit to there
					// where clause.
					throw new RuntimeException("Unexpected result - Requested: userName='"
					        + userName + "', email='" + email + "' / Returned: userName='"
					        + row.getUserName() + "', email='" + row.getEmail() + "'");
				}
			}

		} catch (final SQLException ex) {
			final String message = "Error adding userName/email constraint!";
			LOG.error(message + " [SQLState=" + ex.getSQLState() + ", ErrorCode="
			        + ex.getErrorCode() + "]", ex);
			throw new RuntimeException(message);
		}

	}

	@Override
	public final void remove(final UserName userName, final EmailAddress email) {
		executeUpdateSilent(
		        "delete from COMMANDSERVER.USERNAME_EMAIL where USER_NAME=? and EMAIL=?", userName
		                .toString(), email.toString());
	}

	@Override
	public final void add(final CategoryName categoryName) throws CategoryNameAlreadyExistException {
		try {
			executeUpdate("insert into COMMANDSERVER.CATEGORY_NAMES (NAME) values (?)",
			        categoryName.toString());
		} catch (final SQLException ex) {
			// Duplicate key value in primary key
			if (!ex.getSQLState().equals("23505")) {
				throw new RuntimeException(ex);
			}
			throw new CategoryNameAlreadyExistException();
		}
	}

	@Override
	public final void remove(final CategoryName categoryName) {
		executeUpdateSilent("delete from COMMANDSERVER.CATEGORY_NAMES where NAME=?", categoryName
		        .toString());
	}

	/**
	 * Tries to insert the userid/email combination and wraps a
	 * "duplicate primary key" exception into a <code>false</code> return value.
	 * 
	 * @param userName
	 *            User's unique name.
	 * @param email
	 *            User's email address.
	 * 
	 * @return If the combination was inserted <code>true</code> else
	 *         <code>false</code> ("duplicate primary key").
	 * 
	 * @throws SQLException
	 *             Other error than "duplicate primary key".
	 */
	private boolean insert(final UserName userName, final EmailAddress email) throws SQLException {
		try {
			executeUpdate("insert into COMMANDSERVER.USERNAME_EMAIL (USER_NAME, EMAIL) "
			        + "values (?, ?)", userName.toString(), email.toString());
			return true;
		} catch (final SQLException ex) {
			// Duplicate key value in primary key
			if (!ex.getSQLState().equals("23505")) {
				throw ex;
			}
			return false;
		}
	}

	/**
	 * Selects user name and email based on user name or email address.
	 * 
	 * @param userName
	 *            User's unique name to find.
	 * @param email
	 *            Email address to find.
	 * 
	 * @return User name and email.
	 */
	private UserNameEmail select(final UserName userName, final EmailAddress email) {

		final List<UserNameEmail> list = selectSilent(
		        "select * from COMMANDSERVER.USERNAME_EMAIL where USER_NAME=? or EMAIL=?",
		        new UserNameEmailCreator(), userName.toString(), email.toString());
		if (list.size() == 0) {
			throw new IllegalStateException("Neither user name '" + userName + "' nor email '"
			        + email + "' found!");
		} else if (list.size() > 1) {
			throw new IllegalStateException("Found more than one entry for user name '" + userName
			        + "' or email '" + email + "'!");
		}
		return list.get(0);
	}

	/**
	 * Helper class to return result.
	 */
	private static final class UserNameEmail {

		private final String userName;

		private final String email;

		/**
		 * Constructor with user name and email.
		 * 
		 * @param userName
		 *            User name.
		 * @param email
		 *            Email address.
		 */
		public UserNameEmail(final String userName, final String email) {
			super();
			this.userName = userName;
			this.email = email;
		}

		/**
		 * Returns the user name.
		 * 
		 * @return user name.
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * Returns the email.
		 * 
		 * @return Email address.
		 */
		public String getEmail() {
			return email;
		}

	}

	/**
	 * Creates {@link UserNameEmail} instances from a JDBC result set.
	 */
	private static final class UserNameEmailCreator implements Creator<UserNameEmail> {

		@Override
		public final UserNameEmail create(final ResultSet rs) throws SQLException {
			return new UserNameEmail(rs.getString("USER_NAME"), rs.getString("EMAIL"));
		}

	}

}
