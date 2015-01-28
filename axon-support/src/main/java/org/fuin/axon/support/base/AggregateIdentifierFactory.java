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

import org.axonframework.domain.AggregateIdentifier;

/**
 * Creates {@link AggregateIdentifier} instances.
 */
public interface AggregateIdentifierFactory {

	/**
	 * Creates a new instance.
	 * 
	 * @return New instance.
	 */
	public AggregateIdentifier create();

	/**
	 * Creates a new instance from a string.
	 * 
	 * @param aggregateId
	 *            String representation of the id.
	 * 
	 * @return Id.
	 * 
	 * @throws IllegalAggregateIdentifierException
	 *             The string cannot be converted into an aggregate id.
	 */
	public AggregateIdentifier fromString(String aggregateId)
	        throws IllegalAggregateIdentifierException;

}
