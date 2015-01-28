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
package org.fuin.auction.command.server.base;

import org.fuin.auction.common.OperationResult;
import org.fuin.objects4j.Contract;

/**
 * A result was invalid.
 */
public class InvalidResultException extends Exception {

	private static final long serialVersionUID = 8151877886020885741L;

	private final OperationResult result;

	/**
	 * Constructor with message.
	 * 
	 * @param message
	 *            Error message.
	 * @param result
	 *            Result that caused the error.
	 */
	public InvalidResultException(final String message, final OperationResult result) {
		super(message);
		Contract.requireArgNotNull("result", result);
		this.result = result;
	}

	/**
	 * Constructor with message and cause.
	 * 
	 * @param message
	 *            Error message.
	 * @param cause
	 *            Original exception.
	 * @param result
	 *            Result that caused the error.
	 */
	public InvalidResultException(final String message, final Throwable cause,
	        final OperationResult result) {
		super(message, cause);
		Contract.requireArgNotNull("result", result);
		this.result = result;
	}

	/**
	 * Constructor with cause.
	 * 
	 * @param cause
	 *            Original exception.
	 * @param result
	 *            Result that caused the error.
	 */
	public InvalidResultException(final Throwable cause, final OperationResult result) {
		super(cause);
		Contract.requireArgNotNull("result", result);
		this.result = result;
	}

	/**
	 * Return the result that caused the error.
	 * 
	 * @return Result.
	 */
	public final OperationResult getResult() {
		return result;
	}

}
