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
package org.fuin.auction.common;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

// TESTCODE:BEGIN
public final class UtilsTest {

	@Test
	public final void testGetProjectInfo() throws FailedToLoadProjectInfoException {

		// TEST
		final ProjectInfo projectInfo = Utils.getProjectInfo(Utils.class, "/auction-common.properties");

		// VERIFY
		assertThat(projectInfo.getName()).isEqualTo("Axon Auction Example - Common");
		assertThat(projectInfo.getVersion()).isNotEmpty();
		assertThat(projectInfo.getBuildTimestamp()).isNotNull();

	}

	@Test(expected = FailedToLoadProjectInfoException.class)
	public final void testGetProjectInfoNotFound() throws FailedToLoadProjectInfoException {

		// TEST
		Utils.getProjectInfo(Utils.class, "unknown-config.properties");

	}

	@Test
	public final void testCreateMessage() {

		// PREPARE
		final String msg = "Abc-123";

		// TEST & VERIFY
		assertThat(Utils.createMessage(new NullPointerException())).isEqualTo(
		        "NullPointerException");
		assertThat(Utils.createMessage(new NullPointerException(""))).isEqualTo(
		        "NullPointerException");
		assertThat(Utils.createMessage(new RuntimeException(msg))).isEqualTo(msg);

	}

}
// TESTCODE:END
