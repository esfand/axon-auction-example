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
package org.fuin.axon.support.base;

import java.io.IOException;

import org.fuin.serialver4j.hessian.BaseDeserializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.caucho.hessian.io.AbstractHessianInput;

/**
 * Deserializes Joda {@link DateTime} objects.
 */
public final class JodaDateTimeDeserializer extends BaseDeserializer {

	@Override
	public final Class<?> getType() {
		return DateTime.class;
	}

	@Override
	protected final Object readValue(final AbstractHessianInput in) throws IOException {
		final long millis = in.readUTCDate();
		return new DateTime(millis, DateTimeZone.UTC);
	}

}
