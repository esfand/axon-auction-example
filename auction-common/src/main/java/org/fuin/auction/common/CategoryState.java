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
package org.fuin.auction.common;

/**
 * Name of an auction category.
 */
public enum CategoryState {

	/** The category was created but is not yet usable. */
	INITIAL,

	/** The category is usable for creating a new auction. */
	ACTIVE,

	/**
	 * The category is marked for deletion and can not be used for create a new
	 * auction any more.
	 */
	MARKED_FOR_DELETION,

	/**
	 * The category is deleted an no longer usable in any ways.
	 */
	DELETED

}
