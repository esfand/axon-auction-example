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

import java.lang.reflect.Constructor;

import org.axonframework.util.SerializationException;
import org.joda.time.DateTime;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Serializes and deserializes Joda Time objects with XStream.
 * 
 * @author Allard Buijze
 */
public final class JodaTimeConverter implements Converter {

	@Override
	@SuppressWarnings("unchecked")
	public boolean canConvert(final Class type) {
		return (type != null) && DateTime.class.getPackage().equals(type.getPackage());
	}

	@Override
	public void marshal(final Object source, final HierarchicalStreamWriter writer,
	        final MarshallingContext context) {
		writer.setValue(source.toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object unmarshal(final HierarchicalStreamReader reader,
	        final UnmarshallingContext context) {
		try {
			final Class requiredType = context.getRequiredType();
			final Constructor constructor = requiredType.getConstructor(Object.class);
			return constructor.newInstance(reader.getValue());
		} catch (final Exception e) {
			throw new SerializationException(String.format(
			        "An exception occurred while deserializing a Joda Time object: %s", context
			                .getRequiredType().getSimpleName()), e);
		}
	}

}
