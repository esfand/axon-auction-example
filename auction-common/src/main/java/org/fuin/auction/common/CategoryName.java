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

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fuin.objects4j.Contract;
import org.fuin.objects4j.Immutable;
import org.fuin.objects4j.Requires;

/**
 * Name of an auction category.
 */
@Immutable
public final class CategoryName implements Comparable<CategoryName>, Serializable {

	private static final long serialVersionUID = -5713940537379394412L;

	@NotNull
	@Size(min = 1, max = 40)
	private final String categoryName;

	/**
	 * Constructor with category name.
	 * 
	 * @param categoryName
	 *            Category name.
	 */
	@Requires("(categoryName!=null) && (categoryName.length>=1) && (categoryName.length<=40)")
	public CategoryName(final String categoryName) {
		super();
		this.categoryName = categoryName.trim();
		Contract.requireValid(this);
	}

	// CHECKSTYLE:OFF Generated hashCode + equals
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CategoryName other = (CategoryName) obj;
		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		return true;
	}

	// CHECKSTYLE:ON

	@Override
	public final int compareTo(final CategoryName obj) {
		final CategoryName other = (CategoryName) obj;
		return this.categoryName.compareTo(other.categoryName);
	}

	/**
	 * Returns the length of the category name.
	 * 
	 * @return Number of characters.
	 */
	public final int length() {
		return categoryName.length();
	}

	@Override
	public final String toString() {
		return categoryName;
	}

}
