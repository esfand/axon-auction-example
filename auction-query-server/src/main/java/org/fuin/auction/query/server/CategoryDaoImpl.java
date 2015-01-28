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
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import com.trg.dao.jpa.GenericDAOImpl;
//import com.trg.search.Search;
//import com.trg.search.jpa.JPASearchProcessor;

/**
 * Category Data Access Object (DAO) implementation.
 */
@Named
public final class CategoryDaoImpl extends GenericDAOImpl<Category, Long> implements CategoryDao {

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
	public final List<Category> findAllActive() {
		return search(new Search().addFilterEqual("active", true));
	}

}
