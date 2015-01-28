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

import java.util.UUID;

import org.axonframework.serializer.AggregateIdentifierConverter;
import org.fuin.serialver4j.hessian.UUIDDeserializer;
import org.fuin.serialver4j.hessian.UUIDSerializer;
import org.joda.time.DateTime;

import com.caucho.hessian.io.ExtSerializerFactory;
import com.caucho.hessian.io.SerializerFactory;
import com.thoughtworks.xstream.XStream;

/**
 * Helper methods.
 */
public final class AxonSupportUtils {

	/**
	 * Private constructor to avoid instantiation.
	 */
	private AxonSupportUtils() {
		throw new UnsupportedOperationException("You cannot create an instance of a utility class!");
	}

	/**
	 * Creates a Hessian serializer factory instance initialized for use with an
	 * Axon Event Store.
	 * 
	 * @return New instance.
	 */
	public static SerializerFactory createSerializerFactory() {

		final ExtSerializerFactory extSerializerFactory = new ExtSerializerFactory();
		extSerializerFactory.addSerializer(DateTime.class, new JodaDateTimeSerializer());
		extSerializerFactory.addDeserializer(DateTime.class, new JodaDateTimeDeserializer());
		extSerializerFactory.addSerializer(UUID.class, new UUIDSerializer());
		extSerializerFactory.addDeserializer(UUID.class, new UUIDDeserializer());
		final SerializerFactory serializerFactory = new SerializerFactory();
		serializerFactory.addFactory(extSerializerFactory);
		return serializerFactory;

	}

	/**
	 * Creates an XStream instance initialized for use with an Axon Event Store.
	 * 
	 * @return New instance.
	 */
	public static XStream createXStream() {
		final XStream xstream = new XStream();
		xstream.registerConverter(new JodaTimeConverter());
		xstream.addImmutableType(UUID.class);
		xstream.registerConverter(new AggregateIdentifierConverter());
		xstream.aliasType("dateTime", DateTime.class);
		xstream.aliasType("uuid", UUID.class);
		return xstream;
	}

}
