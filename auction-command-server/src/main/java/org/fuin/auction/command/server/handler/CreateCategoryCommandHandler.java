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
package org.fuin.auction.command.server.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.fuin.auction.command.api.base.CategoryCreatedResult;
import org.fuin.auction.command.api.base.CreateCategoryCommand;
import org.fuin.auction.command.server.base.ConstraintSet;
import org.fuin.auction.command.server.domain.Category;
import org.fuin.auction.command.server.domain.CategoryNameAlreadyExistException;
import org.fuin.auction.common.CategoryName;
import org.fuin.auction.common.OperationResult;
import org.fuin.axon.support.base.LongAggregateIdentifier;

/**
 * Handler for managing {@link CreateCategoryCommand} commands.
 */
@Named
public class CreateCategoryCommandHandler extends AbstractCreateCategoryCommandHandler {

	@Inject
	private ConstraintSet constraintSet;

	@Override
	protected final OperationResult handleIntern(final CreateCategoryCommand command)
	        throws CategoryNameAlreadyExistException {

		final CategoryName categoryName = new CategoryName(command.getName());
		constraintSet.add(categoryName);

		final Category category = new Category(getAggregateIdFactory().create(), categoryName);
		getRepository().add(category);

		return new CategoryCreatedResult(((LongAggregateIdentifier) category.getIdentifier())
		        .asLong());

	}

}
