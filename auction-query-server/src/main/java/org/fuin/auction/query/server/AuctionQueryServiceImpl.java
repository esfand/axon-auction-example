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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.fuin.auction.common.FailedToLoadProjectInfoException;
import org.fuin.auction.common.Utils;
import org.fuin.auction.query.api.AuctionQueryService;
import org.fuin.auction.query.api.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the {@link AuctionQueryService}.
 */
@Named
public class AuctionQueryServiceImpl implements AuctionQueryService {

	private static final Logger LOG = LoggerFactory.getLogger(AuctionQueryServiceImpl.class);

	@Inject
	private CategoryDao categoryDao;

	@Inject
	private AuctionUserDao userDao;

	@Override
	public final String getVersion() {
		try {
			return Utils.getProjectInfo(this.getClass(), "/auction-query-server.properties")
			        .getVersion();
		} catch (final FailedToLoadProjectInfoException ex) {
			final String message = "Cannot retrieve version!";
			LOG.error(message, ex);
			throw new RuntimeException(message);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public final List<CategoryDto> findAllCategories() {
		final List<Category> categories = categoryDao.findAll();
		final List<CategoryDto> result = new ArrayList<CategoryDto>();
		for (final Category category : categories) {
			// TODO michael Create domain object to dto converter structure
			result.add(new CategoryDto(category.getId(), category.getName(), category.isActive()));
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public final List<CategoryDto> findAllActiveCategories() {
		final List<Category> categories = categoryDao.findAllActive();
		final List<CategoryDto> result = new ArrayList<CategoryDto>();
		for (final Category category : categories) {
			// TODO michael Create domain object to dto converter structure
			result.add(new CategoryDto(category.getId(), category.getName(), category.isActive()));
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public final String findUserIdBySecurityToken(final String securityToken) {
		return userDao.findUserIdBySecurityToken(securityToken);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public final CategoryDto findById(final Long id) {
		final Category category = categoryDao.find(id);
		// TODO michael Create domain object to dto converter structure
		return new CategoryDto(category.getId(), category.getName(), category.isActive());
	}

}
