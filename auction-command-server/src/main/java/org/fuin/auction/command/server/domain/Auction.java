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

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;

/**
 * Represents the process of buying and selling goods or services by offering
 * them up for bid, taking bids, and then selling the item to the highest
 * bidder.
 */
public final class Auction extends AbstractAnnotatedAggregateRoot {

	/**
	 * Constructor with id.
	 * 
	 * @param identifier
	 *            Unique id of the auction.
	 */
	public Auction(final AggregateIdentifier identifier) {
		super(identifier);
	}

}
