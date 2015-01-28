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

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import com.trg.dao.jpa.GenericDAOImpl;
//import com.trg.search.Search;
//import com.trg.search.jpa.JPASearchProcessor;

/**
 * User Data Access Object (DAO) implementation.
 */
@Named
public final class AuctionUserDaoImpl extends GenericDAOImpl<AuctionUser, Long> implements
        AuctionUserDao {

	@PersistenceContext
	@Override
	public final void setEntityManager(final EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Inject
	@Override
	public void setSearchProcessor(final JPASearchProcessor searchProcessor) {
		super.setSearchProcessor(searchProcessor);
	}

	@Override
	public final AuctionUser findByAggregateId(final String aggregateId) {
		return searchUnique(new Search().addFilterEqual("aggregateId", aggregateId));
	}

	@Override
	public final String findUserIdBySecurityToken(final String token) {
		final AuctionUser user = searchUnique(new Search().addFilterEqual("securityToken", token));
		if (user == null) {
			return null;
		}
		return user.getAggregateId();
	}

}
