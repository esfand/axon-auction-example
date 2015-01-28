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
package org.fuin.auction.query.api;

import java.util.List;

/**
 * Auction query service.
 */
public interface AuctionQueryService {

	/**
	 * Returns the version of the server.
	 * 
	 * @return Server version.
	 */
	public String getVersion();

	/**
	 * Returns all categories.
	 * 
	 * @return List of all categories.
	 */
	public List<CategoryDto> findAllCategories();

	/**
	 * Returns all active categories.
	 * 
	 * @return List of all active categories.
	 */
	public List<CategoryDto> findAllActiveCategories();

	/**
	 * Returns a category by it's id.
	 * 
	 * @param id
	 *            Id of the category to find.
	 * 
	 * @return Category or <code>null</code> if the id was not found.
	 */
	public CategoryDto findById(Long id);

	/**
	 * Returns the user id that belongs to the given security token.
	 * 
	 * @param securityToken
	 *            Token to find the user aggregate id for.
	 * 
	 * @return Aggregate id or <code>null</code> if the token was not found.
	 */
	public String findUserIdBySecurityToken(String securityToken);

}
