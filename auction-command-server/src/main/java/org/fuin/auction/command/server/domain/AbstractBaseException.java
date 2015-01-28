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
package org.fuin.auction.command.server.domain;

import org.fuin.auction.common.KeyValue;

/**
 * Base class for all exceptions.
 */
public abstract class AbstractBaseException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with message.
	 * 
	 * @param message
	 *            Error message.
	 */
	public AbstractBaseException(final String message) {
		super(message);
	}

	/**
	 * Replaces all variables in the format "${NAME}" with the corresponding
	 * value. NAME is the name of a key from the <code>keyValue</code> array.
	 * 
	 * @param message
	 *            Message to replace.
	 * @param keyValue
	 *            Array of key values or <code>null</code>.
	 * 
	 * @return Replaced message.
	 */
	protected static String replace(final String message, final KeyValue... keyValue) {
		return KeyValue.replace(message, keyValue);
	}

}
