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

/**
 * Base API directly related to the auction example. The design is by intention 
 * language independent. Commands should therefore mostly contain simple attributes 
 * like {@link String}, {@link Integer} or {@link Boolean}. To make some type checking
 * possible the attributes are annotated with javax validation constraints. Attributes 
 * are by intention not final because this is not supported by all serialization methods.
 */
package org.fuin.auction.command.api.base;

