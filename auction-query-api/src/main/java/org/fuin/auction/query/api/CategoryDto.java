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

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.TraceStringCapable;

/**
 * Auction category data transfer object.
 */
public final class CategoryDto implements Comparable<CategoryDto>, Serializable, TraceStringCapable {

	private static final long serialVersionUID = -1304986535539462213L;

	@NotNull
	@Min(1)
	private Long id;

	@NotNull
	@Size(min = 1, max = 40)
	private String name;

	@NotNull
	private Boolean active;

	/**
	 * Default constructor for serialization.
	 */
	public CategoryDto() {
		super();
	}

	/**
	 * Constructor with id string and name.
	 * 
	 * @param idStr
	 *            Unique category id as string.
	 * @param name
	 *            Name of the category.
	 * @param active
	 *            Determines if the category is active or not.
	 */
	public CategoryDto(final String idStr, final String name, final Boolean active) {
		this(Long.valueOf(idStr), name, active);
	}

	/**
	 * Constructor with id and name.
	 * 
	 * @param id
	 *            Unique category id.
	 * @param name
	 *            Name of the category.
	 * @param active
	 *            Determines if the category is active or not.
	 */
	public CategoryDto(final Long id, final String name, final Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		Contract.requireValid(this);
	}

	/**
	 * Returns the unique category id.
	 * 
	 * @return Unique id.
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * Sets the unique category id to a new value.
	 * 
	 * @param id
	 *            Value to set.
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Returns the category name.
	 * 
	 * @return Name of the category.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the category name to a new value.
	 * 
	 * @param name
	 *            Value to set.
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns if the category is active or not..
	 * 
	 * @return If the category is usable for new auctions <code>true</code> else
	 *         <code>false</code>.
	 */
	public final boolean isActive() {
		if (active == null) {
			return false;
		}
		return active;
	}

	/**
	 * Sets if the category is active or not.
	 * 
	 * @param active
	 *            If the category is usable for new auctions <code>true</code>
	 *            else <code>false</code>.
	 */
	public final void setActive(final Boolean active) {
		this.active = active;
	}

	@Override
	public final int compareTo(final CategoryDto theOther) {
		return id.compareTo(theOther.id);
	}

	// CHECKSTYLE:OFF Generated code...
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CategoryDto other = (CategoryDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// CHECKSTYLE:OFN

	@Override
	public final String toTraceString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("active",
		        active).toString();
	}

	@Override
	public final String toString() {
		return name;
	}

}
