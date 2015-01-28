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
package org.fuin.auction.client.test;

import static org.fest.assertions.Assertions.assertThat;

import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.validation.Validation;
import javax.validation.Validator;

import org.fuin.auction.command.api.base.AuctionCommandService;
import org.fuin.auction.common.OperationResult;
import org.fuin.auction.query.api.AuctionQueryService;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.caucho.hessian.client.HessianProxyFactory;

//TESTCODE:BEGIN
public abstract class AbstractUseCaseTest extends AbstractJdbcHelper {

	private static AuctionCommandService commandService;

	private static AuctionQueryService queryService;

	private static Validator validator;

	public AbstractUseCaseTest() {
		super(AbstractUseCaseTest.class, "/jdbc.properties");
	}

	@BeforeClass
	public static final void beforeClass() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		try {
			final HessianProxyFactory factory = new HessianProxyFactory();
			commandService = (AuctionCommandService) factory.create(AuctionCommandService.class,
			        "http://localhost:8080/auction-command-server/AuctionCommandService");
			queryService = (AuctionQueryService) factory.create(AuctionQueryService.class,
			        "http://localhost:8080/auction-query-server/AuctionQueryService");
		} catch (final MalformedURLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@AfterClass
	public static final void afterClass() {
		validator = null;
		commandService = null;
	}

	protected final void deleteAllDatabaseData() {
		try {
			executeUpdate("delete from QUERYSERVER.AUCTION_USER");
			executeUpdate("delete from QUERYSERVER.CATEGORY");
			executeUpdate("delete from COMMANDSERVER.CATEGORY_NAMES");
			executeUpdate("delete from COMMANDSERVER.USERNAME_EMAIL");
		} catch (final SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	protected static AuctionCommandService getCommandService() {
		return commandService;
	}

	protected static AuctionQueryService getQueryService() {
		return queryService;
	}

	protected static Validator getValidator() {
		return validator;
	}

	protected final void assertSuccess(final OperationResult result,
	        final Class<? extends OperationResult> expectedResultClass) {
		assertThat(result).isNotNull();
		assertThat(result.isSuccess()).as(result.getText()).isTrue();
		assertThat(result.isError()).isFalse();
		assertThat(result.isWarning()).isFalse();
		assertThat(result).isInstanceOf(expectedResultClass);
	}

	protected final void waitUntil(final Condition condition, final int seconds) {
		int count = 0;
		while (!condition.isTrue() && (count < seconds)) {
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			count++;
		}
	}

	public static interface Condition {

		public boolean isTrue();

	}

}
// TESTCODE:END
