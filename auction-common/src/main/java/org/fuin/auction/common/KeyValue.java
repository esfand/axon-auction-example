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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.fuin.objects4j.Contract;

/**
 * Container for a key and a value.
 */
public final class KeyValue {

	private final String key;

	private final Object value;

	/**
	 * Constructor with key and value.
	 * 
	 * @param key
	 *            Key.
	 * @param value
	 *            Value.
	 */
	public KeyValue(final String key, final Object value) {
		super();
		Contract.requireArgNotNull("key", key);
		Contract.requireArgNotNull("value", value);
		this.key = key;
		this.value = value;
	}

	/**
	 * Returns the key.
	 * 
	 * @return Key.
	 */
	public final String getKey() {
		return key;
	}

	/**
	 * Returns the value.
	 * 
	 * @return Value.
	 */
	public final Object getValue() {
		return value;
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
	@SuppressWarnings("unchecked")
	public static String replace(final String message, final KeyValue... keyValue) {
		if (keyValue == null) {
			return message;
		}
		final Map<String, String> map = new HashMap<String, String>();
		for (final KeyValue kv : keyValue) {
			if (kv.getValue() instanceof Collection) {
				final StringBuffer sb = new StringBuffer();
				final Collection coll = (Collection) kv.getValue();
				int count = 0;
				for (final Object entry : coll) {
					if (count > 0) {
						sb.append(", ");
					}
					sb.append(entry.toString());
					count++;
				}
				map.put(kv.getKey(), sb.toString());
			} else {
				map.put(kv.getKey(), kv.getValue().toString());
			}
		}
		return replaceVars(message, map);
	}

	/**
	 * Replaces all variables inside a string with values from a map.
	 * 
	 * @param str
	 *            Text with variables (Format: ${key} ) - May be
	 *            <code>null</code> or empty.
	 * @param vars
	 *            Map with key/values (both of type <code>String</code> - Cannot
	 *            be <code>null</code>.
	 * 
	 * @return String with replaced variables. Unknown variables will remain
	 *         unchanged.
	 */
	public static String replaceVars(final String str, final Map vars) {

		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return str;
		}

		if (vars == null) {
			return str;
		}

		final StringBuffer sb = new StringBuffer();

		int end = -1;
		int from = 0;
		int start = -1;
		while ((start = str.indexOf("${", from)) > -1) {
			sb.append(str.substring(end + 1, start));
			end = str.indexOf('}', start + 1);
			if (end == -1) {
				// No closing bracket found...
				sb.append(str.substring(start));
				from = str.length();
			} else {
				final String key = str.substring(start + 2, end);
				final String value = (String) vars.get(key);
				if (value == null) {
					sb.append("${");
					sb.append(key);
					sb.append("}");
				} else {
					sb.append(value);
				}
				from = end + 1;
			}
		}

		sb.append(str.substring(from));

		return sb.toString();
	}

}
