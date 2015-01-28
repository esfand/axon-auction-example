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
package org.fuin.auction.client.swing;

import java.io.File;

import org.fuin.units4j.AssertCoverage;
import org.fuin.units4j.AssertDependencies;
import org.junit.Ignore;
import org.junit.Test;

//TESTCODE:BEGIN
public class BaseTest {

	@Ignore
	// TODO michael Uncomment after real classes are implemented
	@Test
	public final void testCoverage() {
		AssertCoverage.assertEveryClassHasATest(new File("src/main/java"));
	}

    @Ignore
	@Test
	public final void testAssertDependencies() {
		final File classesDir = new File("target/classes");
		AssertDependencies.assertRules(this.getClass(), "/auction-client-swing-dependencies.xml", classesDir);
	}

}
// TESTCODE:END
