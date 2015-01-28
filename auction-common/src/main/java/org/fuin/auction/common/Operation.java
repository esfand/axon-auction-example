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
import java.util.Set;

import org.fuin.objects4j.Ensures;
import org.fuin.objects4j.TraceStringCapable;

/**
 * Common behavior shared by all operations.
 */
public interface Operation extends Serializable, TraceStringCapable {

	/**
	 * Returns the version of this instance. This may differ from the
	 * <code>serialVersionUID</code> because the version of the class used to
	 * serialize is an earlier version of the class used to deserialize the
	 * instance.
	 * 
	 * @return Instance version.
	 */
	public long getVersion();

	/**
	 * Returns a list of possible results.
	 * 
	 * @return Array of result codes.
	 */
	@Ensures("\result!=null")
	public Set<Integer> getResultCodes();

}
