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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.objects4j.Requires;

/**
 * Basic result of sending/executing a operation.
 */
public abstract class AbstractOperationResult implements OperationResult {

	private static final long serialVersionUID = 2861284299330260251L;

	private boolean success;

	private boolean warning;

	private boolean error;

	@Min(0)
	private int code;

	@NotNull
	private String text;

	/**
	 * Default constructor.
	 */
	protected AbstractOperationResult() {
		super();
		success = false;
		warning = false;
		code = -1;
		text = null;
	}

	/**
	 * Constructor with all values.
	 * 
	 * @param code
	 *            Code.
	 * @param type
	 *            Type of result.
	 * @param text
	 *            Result description.
	 */
	@Requires("code>0 && type!=null && text!=null")
	protected AbstractOperationResult(final int code, final OperationResultType type,
	        final String text) {
		this.success = type.equals(OperationResultType.SUCCESS);
		this.warning = type.equals(OperationResultType.WARNING);
		this.error = type.equals(OperationResultType.ERROR);
		this.code = code;
		this.text = text;
	}

	@Override
	public final boolean isSuccess() {
		return success;
	}

	@Override
	public final boolean isWarning() {
		return warning;
	}

	@Override
	public final boolean isError() {
		return error;
	}

	@Override
	public final int getCode() {
		return code;
	}

	@Override
	public final String getCodeStr() {
		return StringUtils.leftPad("" + code, 5, "0");
	}

	@Override
	public final String getText() {
		return text;
	}

	/**
	 * Appends all properties to the builder.
	 * 
	 * @param builder
	 *            Builder to append key/values.
	 */
	protected final void appendAbstractCommandResult(final ToStringBuilder builder) {
		builder.append("success", success).append("warning", warning).append("error", error)
		        .append("code", code).append("text", text);
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
