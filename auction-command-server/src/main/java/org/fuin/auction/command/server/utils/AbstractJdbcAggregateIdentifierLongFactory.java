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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.axonframework.domain.AggregateIdentifier;
import org.fuin.axon.support.base.AggregateIdentifierFactory;
import org.fuin.axon.support.base.IllegalAggregateIdentifierException;
import org.fuin.axon.support.base.LongAggregateIdentifier;

/**
 * Creates a unique long id based on a JDBC query.
 */
public abstract class AbstractJdbcAggregateIdentifierLongFactory extends AbstractJdbcHelper
        implements AggregateIdentifierFactory {

	private final String sequenceSql;

	/**
	 * Constructor with all necessary values.
	 * 
	 * @param clasz
	 *            Class to use for loading the properties.
	 * @param jdbcPropResName
	 *            Name of the properties resource with JDBC values
	 *            (Keys='driverclass', 'url', 'username', 'password').
	 * @param sequenceSql
	 *            SQL that returns a new sequence value.
	 */
	public AbstractJdbcAggregateIdentifierLongFactory(final Class<?> clasz,
	        final String jdbcPropResName, final String sequenceSql) {
		super(clasz, jdbcPropResName);
		this.sequenceSql = sequenceSql;
	}

	@Override
	public final AggregateIdentifier fromString(final String valueStr)
	        throws IllegalAggregateIdentifierException {
		return new LongAggregateIdentifier(valueStr);
	}

	@Override
	public final AggregateIdentifier create() {
		final Creator<Long> creator = new Creator<Long>() {
			@Override
			public Long create(final ResultSet rs) throws SQLException {
				return rs.getLong(1);
			}
		};
		try {
			final Long id = selectUniqueSilent(sequenceSql, creator);
			return new LongAggregateIdentifier(id);
		} catch (final NonUniqueResultException ex) {
			throw new RuntimeException(ex);
		} catch (final IllegalAggregateIdentifierException ex) {
			throw new RuntimeException(ex);
		}
	}

}
