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

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.validation.DateStr;
import org.fuin.objects4j.validation.NotEmpty;

/**
 * Information about a project artifact (JAR, WAR).
 */
public final class ProjectInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Key for the name of the project. */
	public static final String PROPERTY_NAME = "name";

	/** Key for the version of the project. */
	public static final String PROPERTY_VERSION = "version";

	/** Key for the build timestamp. */
	public static final String PROPERTY_BUILD_TIMESTAMP = "buildTimestamp";

	/** Format of the build timestamp. */
	public static final String BUILD_TIMESTAMP_FORMAT = "yyyy-MM-dd_HH-mm";

	@NotEmpty
	private final String name;

	@NotEmpty
	private final String version;

	@NotNull
	@DateStr(BUILD_TIMESTAMP_FORMAT)
	private final String buildTimestamp;

	/**
	 * Constructor with data to populate the instance with.
	 * 
	 * @param props
	 *            Properties to get the values from.
	 */
	public ProjectInfo(final Properties props) {
		super();

		name = props.getProperty(PROPERTY_NAME);
		version = props.getProperty(PROPERTY_VERSION);
		buildTimestamp = props.getProperty(PROPERTY_BUILD_TIMESTAMP);

		Contract.requireValid(this);

	}

	/**
	 * Returns the project name.
	 * 
	 * @return Name of the project.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the version of the project.
	 * 
	 * @return Version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Returns the build timestamp.
	 * 
	 * @return Date and time of the build.
	 */
	public String getBuildTimestamp() {
		return buildTimestamp;
	}

	/**
	 * Returns the build timestamp converted into a date.
	 * 
	 * @return Date and time of the build.
	 */
	public Date getBuildTimestampAsDate() {
		final SimpleDateFormat sdf = new SimpleDateFormat(BUILD_TIMESTAMP_FORMAT);
		try {
			return sdf.parse(buildTimestamp);
		} catch (final ParseException ex) {
			// Should never happen as it's checked in the constructor!
			throw new IllegalStateException("The value '" + buildTimestamp
			        + "' of the property 'buildTimestamp' was not in the format '"
			        + BUILD_TIMESTAMP_FORMAT + "'!");
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append("version", version).append(
		        "buildTimestamp", buildTimestamp).toString();
	}

}
