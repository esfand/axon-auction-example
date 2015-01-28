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

import javax.validation.constraints.NotNull;

import org.fuin.objects4j.Contract;

/**
 * The project properties resource relative to the given class was not found or
 * could not be loaded for other reasons.
 */
public final class FailedToLoadProjectInfoException extends Exception {

	private static final long serialVersionUID = 1L;

	@NotNull
	private final Class<?> clasz;

	@NotNull
	private final String propertiesFilename;

	/**
	 * Constructor with class and cause.
	 * 
	 * @param clasz
	 *            Class used to get the project info resource from.
	 * @param propertiesFilename
	 *            Name and path of the properties file.
	 * @param cause
	 *            The cause.
	 */
	public FailedToLoadProjectInfoException(final Class<?> clasz, final String propertiesFilename,
	        final Throwable cause) {
		super("Resource '" + propertiesFilename + "' not found!", cause);
		this.clasz = clasz;
		this.propertiesFilename = propertiesFilename;
		Contract.requireValid(this);
	}

	/**
	 * Returns the class used to get the project info resource from.
	 * 
	 * @return Class.
	 */
	public final Class<?> getClasz() {
		return clasz;
	}

	/**
	 * Returns the name of the properties file in the class path.
	 * 
	 * @return Name and path.
	 */
	public final String getPropertiesFilename() {
		return propertiesFilename;
	}

}
