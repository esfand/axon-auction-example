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
package org.fuin.auction.query.server;

//import com.trg.dao.jpa.GenericDAO;

import com.googlecode.genericdao.dao.jpa.GenericDAO;


/**
 * User Data Access Object (DAO) interface.
 */
public interface AuctionUserDao extends GenericDAO<AuctionUser, Long> {

	/**
	 * Load a user by it's aggregate id.
	 * 
	 * @param aggregateId
	 *            User's aggregate id to find.
	 * 
	 * @return User or <code>null</code> if the id was not found.
	 */
	public AuctionUser findByAggregateId(String aggregateId);

	/**
	 * Loads the user id that belongs to the given security token.
	 * 
	 * @param securityToken
	 *            Token to find the user aggregate id for.
	 * 
	 * @return User's aggregate id or <code>null</code> if the token was not
	 *         found.
	 */
	public String findUserIdBySecurityToken(String securityToken);

}
