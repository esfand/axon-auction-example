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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fuin.objects4j.Contract;
import org.fuin.objects4j.TraceStringCapable;

/**
 * Category object.
 */
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable, TraceStringCapable {

	private static final long serialVersionUID = 6141278146291023883L;

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "NAME", length = 40, nullable = false)
	private String name;

	@NotNull
	@Column(name = "ACTIVE", nullable = false)
	private Boolean active;

	/**
	 * Package visible default constructor for persistence.
	 */
	Category() {
		super();
	}

	/**
	 * Constructor with mandatory attributes.
	 * 
	 * @param id
	 *            Category's aggregate id
	 * @param name
	 *            Name of the category
	 * @param active
	 *            Determines if the category is active or not.
	 */
	public Category(final Long id, final String name, final Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		Contract.requireValid(this);
	}

	/**
	 * Returns the id.
	 * 
	 * @return Unique id.
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * Sets the id to a new value.
	 * 
	 * @param id
	 *            Unique id to set.
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Returns the category name.
	 * 
	 * @return Category name.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the category name to a new value.
	 * 
	 * @param name
	 *            Category name to set.
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
	public final String toTraceString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("active",
		        active).toString();
	}

	@Override
	public final String toString() {
		return name;
	}

}
