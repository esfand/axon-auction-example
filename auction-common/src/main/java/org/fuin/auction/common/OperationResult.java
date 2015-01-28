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

import org.fuin.objects4j.Ensures;
import org.fuin.objects4j.TraceStringCapable;

/**
 * Result of executing an operation.
 */
public interface OperationResult extends Serializable, TraceStringCapable {

	/**
	 * Returns the information if the operation was successful.
	 * 
	 * @return If the operation was executed without error or warning
	 *         <code>true</code> else <code>false</code>.
	 */
	public boolean isSuccess();

	/**
	 * Returns the information if the operation was partly successful.
	 * 
	 * @return If the operation was executed with a warning <code>true</code>
	 *         else <code>false</code>.
	 */
	public boolean isWarning();

	/**
	 * Returns the information if the operation was unsuccessful.
	 * 
	 * @return If the operation was executed with an error <code>true</code>
	 *         else <code>false</code>.
	 */
	public boolean isError();

	/**
	 * Returns the id of the message.
	 * 
	 * @return Unique id of the message.
	 */
	public int getCode();

	/**
	 * Returns a five character code left padded with zeros.
	 * 
	 * @return Code with leading zeros.
	 */
	@Ensures("\result!=null")
	public String getCodeStr();

	/**
	 * Returns the message.
	 * 
	 * @return Message text.
	 */
	@Ensures("\result!=null")
	public String getText();

}
