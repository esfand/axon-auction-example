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
package org.fuin.auction.command.server.utils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.fuin.auction.common.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Pure JDBC constraint service (Apache Derby!). Could be done more nice
 * (database independent for example...) but it's just to show that there is no
 * need that a "full-blown" Hibernate or JPA here...
 * 
 * TODO michael Use Spring configuration instead hard coded properties in
 * constructor.
 */
public abstract class AbstractJdbcHelper {

	private final DataSource dataSource;

	/**
	 * Constructor with JDBC properties.
	 * 
	 * @param clasz
	 *            Class to use for loading the properties.
	 * @param jdbcPropResName
	 *            Name of the properties resource with JDBC values
	 *            (Keys='driverclass', 'url', 'username', 'password').
	 */
	public AbstractJdbcHelper(final Class<?> clasz, final String jdbcPropResName) {
		try {
			final Properties jdbcProperties = loadJdbcProperties(clasz, jdbcPropResName);
			final ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass(jdbcProperties.getProperty("driverclass"));
			cpds.setJdbcUrl(jdbcProperties.getProperty("url"));
			cpds.setUser(jdbcProperties.getProperty("username"));
			cpds.setPassword(jdbcProperties.getProperty("password"));
			this.dataSource = cpds;
		} catch (final PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Returns the data source.
	 * 
	 * @return Data source.
	 */
	protected final DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Executes an SQL select that returns zero or one entry with
	 * {@link SQLException} wrapped into a {@link RuntimeException}.
	 * 
	 * @param sql
	 *            SQL statement that may contain arguments (?) used for a
	 *            prepared statement.
	 * @param creator
	 *            Helper to create object instances from the result set.
	 * @param args
	 *            Arguments to set in the correct order.
	 * 
	 * @param <T>
	 *            Type of objects returned.
	 * 
	 * @return Object or <code>null</code>.
	 * 
	 * @throws NonUniqueResultException
	 *             The result contained two or more rows.
	 */
	protected final <T> T selectUniqueSilent(final String sql, final Creator<T> creator,
	        final Object... args) throws NonUniqueResultException {

		try {
			return selectUnique(sql, creator, args);
		} catch (final SQLException ex) {
			throw new RuntimeException(ex);
		}

	}

	/**
	 * Executes an SQL select that returns zero or one entry.
	 * 
	 * @param sql
	 *            SQL statement that may contain arguments (?) used for a
	 *            prepared statement.
	 * @param creator
	 *            Helper to create object instances from the result set.
	 * @param args
	 *            Arguments to set in the correct order.
	 * 
	 * @param <T>
	 *            Type of objects returned.
	 * 
	 * @return Object or <code>null</code>.
	 * 
	 * @throws SQLException
	 *             Error executing the SQL update. qthrows
	 * @throws NonUniqueResultException
	 *             The result contained two or more rows.
	 */
	protected final <T> T selectUnique(final String sql, final Creator<T> creator,
	        final Object... args) throws SQLException, NonUniqueResultException {

		final List<T> list = select(sql, creator, args);
		if (list.size() == 0) {
			return null;
		}
		if (list.size() > 1) {
			throw new NonUniqueResultException("The query returned " + list.size() + " rows: "
			        + sql);
		}
		return list.get(0);

	}

	/**
	 * Executes an SQL select with {@link SQLException} wrapped into a
	 * {@link RuntimeException}.
	 * 
	 * @param sql
	 *            SQL statement that may contain arguments (?) used for a
	 *            prepared statement.
	 * @param creator
	 *            Helper to create object instances from the result set.
	 * @param args
	 *            Arguments to set in the correct order.
	 * 
	 * @param <T>
	 *            Type of objects returned.
	 * 
	 * @return List of objects returned.
	 */
	protected final <T> List<T> selectSilent(final String sql, final Creator<T> creator,
	        final Object... args) {
		try {
			return select(sql, creator, args);
		} catch (final SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Executes an SQL select.
	 * 
	 * @param sql
	 *            SQL statement that may contain arguments (?) used for a
	 *            prepared statement.
	 * @param creator
	 *            Helper to create object instances from the result set.
	 * @param args
	 *            Arguments to set in the correct order.
	 * 
	 * @param <T>
	 *            Type of objects returned.
	 * 
	 * @return List of objects returned.
	 * 
	 * @throws SQLException
	 *             Error executing the SQL update.
	 */
	protected final <T> List<T> select(final String sql, final Creator<T> creator,
	        final Object... args) throws SQLException {
		final Connection con = dataSource.getConnection();
		try {
			final PreparedStatement stmt = con.prepareStatement(sql);
			try {
				for (int i = 0; i < args.length; i++) {
					set(stmt, i + 1, args[i]);
				}
				final ResultSet rs = stmt.executeQuery();
				try {
					final List<T> list = new ArrayList<T>();
					while (rs.next()) {
						list.add(creator.create(rs));
					}
					return list;
				} finally {
					rs.close();
				}
			} finally {
				stmt.close();
			}
		} finally {
			con.close();
		}
	}

	/**
	 * Executes an SQL update with {@link SQLException} wrapped into a
	 * {@link RuntimeException}.
	 * 
	 * @param sql
	 *            SQL statement that may contain arguments (?) used for a
	 *            prepared statement.
	 * @param args
	 *            Arguments to set in the correct order.
	 * 
	 * @return Number of rows updated.
	 */
	protected final int executeUpdateSilent(final String sql, final Object... args) {
		try {
			return executeUpdate(sql, args);
		} catch (final SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Executes an SQL update.
	 * 
	 * @param sql
	 *            SQL statement that may contain arguments (?) used for a
	 *            prepared statement.
	 * @param args
	 *            Arguments to set in the correct order.
	 * 
	 * @return Number of rows updated.
	 * 
	 * @throws SQLException
	 *             Error executing the SQL update.
	 */
	protected final int executeUpdate(final String sql, final Object... args) throws SQLException {
		final Connection con = dataSource.getConnection();
		try {
			final PreparedStatement stmt = con.prepareStatement(sql);
			try {
				for (int i = 0; i < args.length; i++) {
					set(stmt, i + 1, args[i]);
				}
				return stmt.executeUpdate();
			} finally {
				stmt.close();
			}
		} finally {
			con.close();
		}
	}

	/**
	 * Sets the arguments in the prepared statement based on their type.
	 * 
	 * @param stmt
	 *            Statement with parameters.
	 * @param parameterIndex
	 *            Parameter index.
	 * @param arg
	 *            List of arguments in the correct order. The arguments cannot
	 *            be <code>null</code> because the type is determined based in
	 *            the value.
	 * 
	 * @throws SQLException
	 *             Error setting the arguments.
	 */
	protected final void set(final PreparedStatement stmt, final int parameterIndex,
	        final Object arg) throws SQLException {
		if (arg instanceof String) {
			stmt.setString(parameterIndex, (String) arg);
		} else if (arg instanceof Short) {
			stmt.setShort(parameterIndex, (Short) arg);
		} else if (arg instanceof Integer) {
			stmt.setInt(parameterIndex, (Integer) arg);
		} else if (arg instanceof Long) {
			stmt.setLong(parameterIndex, (Long) arg);
		} else if (arg instanceof Boolean) {
			stmt.setBoolean(parameterIndex, (Boolean) arg);
		} else if (arg instanceof Date) {
			stmt.setDate(parameterIndex, (Date) arg);
		} else if (arg instanceof Double) {
			stmt.setDouble(parameterIndex, (Double) arg);
		} else if (arg instanceof Float) {
			stmt.setFloat(parameterIndex, (Float) arg);
		} else if (arg instanceof BigDecimal) {
			stmt.setBigDecimal(parameterIndex, (BigDecimal) arg);
		} else {
			throw new IllegalArgumentException("Unknown argument type: " + arg.getClass().getName());
		}
	}

	/**
	 * Creates a new object instance from a given result set entry.
	 * 
	 * @param <T>
	 *            Type of the instances returned.
	 */
	public static interface Creator<T> {

		/**
		 * Creates a new instance from the current row of the result set.
		 * 
		 * @param rs
		 *            Result set.
		 * 
		 * @return New instance.
		 * 
		 * @throws SQLException
		 *             Error reading from the result set.
		 */
		public T create(ResultSet rs) throws SQLException;

	}

	/**
	 * Returns the JDBC properties.
	 * 
	 * @param clasz
	 *            Class to use for getting the project information resource
	 *            from.
	 * @param name
	 *            Name and path of the properties file.
	 * 
	 * @return JDBC properties.
	 */
	protected final Properties loadJdbcProperties(final Class<?> clasz, final String name) {
		try {
			return Utils.loadProperties(clasz, name);
		} catch (final IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
