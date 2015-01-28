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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

//TESTCODE:BEGIN
public class ProjectInfoTest {

	@Test
	public final void testProjectInfo() throws ParseException {

		// PREPARE
		final SimpleDateFormat sdf = new SimpleDateFormat(ProjectInfo.BUILD_TIMESTAMP_FORMAT);
		final String name = "Project X";
		final String version = "1.2.3";
		final String buildTimestamp = sdf.format(new Date());
		final Properties props = new Properties();
		props.put(ProjectInfo.PROPERTY_NAME, name);
		props.put(ProjectInfo.PROPERTY_VERSION, version);
		props.put(ProjectInfo.PROPERTY_BUILD_TIMESTAMP, buildTimestamp);

		// TEST
		final ProjectInfo testee = new ProjectInfo(props);

		// VERIFY
		assertThat(testee.getName()).isEqualTo(name);
		assertThat(testee.getVersion()).isEqualTo(version);
		assertThat(testee.getBuildTimestamp()).isEqualTo(buildTimestamp);

	}

}
// TESTCODE:END
