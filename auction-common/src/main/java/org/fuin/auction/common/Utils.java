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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Layer independent utilities for the auction application.
 */
public final class Utils {

	/**
	 * Private constructor to avoid instantiation.
	 */
	private Utils() {
		throw new UnsupportedOperationException("You cannot create an instance of a utility class!");
	}

	/**
	 * Returns the project information relative to the given class.
	 * 
	 * @param clasz
	 *            Class to use for getting the project information resource
	 *            from.
	 * @param propertiesFilename
	 *            Name and path of the properties file.
	 * 
	 * @return Project information.
	 * 
	 * @throws FailedToLoadProjectInfoException
	 *             The project properties resource relative to the given class
	 *             was not found or could not be loaded for other reasons.
	 */
	public static ProjectInfo getProjectInfo(final Class<?> clasz, final String propertiesFilename)
	        throws FailedToLoadProjectInfoException {

		try {
			return new ProjectInfo(loadProperties(clasz, propertiesFilename));
		} catch (final IOException ex) {
			throw new FailedToLoadProjectInfoException(clasz, propertiesFilename, new IOException(
			        "Resource '" + propertiesFilename + "' not found!"));
		} catch (final RuntimeException ex) {
			throw new FailedToLoadProjectInfoException(clasz, propertiesFilename, ex);
		}

	}

	/**
	 * Loads a properties file.
	 * 
	 * @param clasz
	 *            Class to use for getting the project information resource
	 *            from.
	 * @param propertiesFilename
	 *            Name and path of the properties file.
	 * 
	 * @return Properties.
	 * 
	 * @throws IOException
	 *             Error loading the properties.
	 */
	public static Properties loadProperties(final Class<?> clasz, final String propertiesFilename)
	        throws IOException {

		final Properties props = new Properties();

		final InputStream inStream = clasz.getResourceAsStream(propertiesFilename);
		if (inStream == null) {
			throw new IOException("Resource '" + propertiesFilename + "' not found!");
		}
		try {
			props.load(inStream);
		} finally {
			inStream.close();
		}

		return props;

	}

	/**
	 * Creates an error message from an exception. Some exceptions (for example
	 * {@link NullPointerException}) don't carry a message. In this case the
	 * simple name of the exception is used for creating the error message.
	 * 
	 * @param ex
	 *            Exception to create an error message for.
	 * 
	 * @return Error message - Always non-<code>null</code> and not empty.
	 */
	public static String createMessage(final Exception ex) {
		final String msg;
		if ((ex.getMessage() == null) || (ex.getMessage().trim().length() == 0)) {
			msg = ex.getClass().getSimpleName();
		} else {
			msg = ex.getMessage();
		}
		return msg;
	}

}
