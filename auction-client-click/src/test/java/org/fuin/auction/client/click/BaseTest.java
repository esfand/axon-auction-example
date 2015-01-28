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
package org.fuin.auction.client.click;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Properties;

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
		AssertDependencies.assertRules(this.getClass(), "/auction-client-click-dependencies.xml", classesDir);
	}

	@Test
	// CHECKSTYLE:OFF Allow conditional operator for the test
	public final void testEveryResultCodeHasAMessage() throws MalformedURLException {

		final Properties messages = loadProperties("/auction-command-api-messages.properties");

		final StringBuffer sb = new StringBuffer();
		final String[] locales = new String[] { "", "de" };
		for (final String locale : locales) {
			final Properties props = loadProperties("/click-page"
			        + ("".equals(locale) ? "" : "_" + locale) + ".properties");
			final Iterator<?> it = messages.keySet().iterator();
			while (it.hasNext()) {
				final String key = (String) it.next();
				if (!props.containsKey(key)) {
					if (sb.length() > 0) {
						sb.append(", ");
					}
					sb.append(("".equals(locale) ? "" : locale + "-") + key);
				}
			}
		}
		if (sb.length() > 0) {
			fail("Missing Result Codes: " + sb.toString());
		}
	}

	// CHECKSTYLE:ON

	private Properties loadProperties(final String name) {
		try {
			final Properties props = new Properties();
			final InputStream inStream = this.getClass().getResourceAsStream(name);
			if (inStream == null) {
				throw new IllegalArgumentException("Resource '" + name + "' was not found!");
			}
			try {
				props.load(inStream);
			} finally {
				inStream.close();
			}
			return props;
		} catch (final IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
// TESTCODE:END
