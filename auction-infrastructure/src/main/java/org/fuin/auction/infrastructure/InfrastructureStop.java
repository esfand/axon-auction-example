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
package org.fuin.auction.infrastructure;

import java.net.InetAddress;

import org.apache.activemq.console.Main;
import org.apache.derby.drda.NetworkServerControl;

/**
 * Shut down the Axon Auction Example infrastructure.
 */
public final class InfrastructureStop {

	/**
	 * Private constructor to avoid instantiation.
	 */
	private InfrastructureStop() {
		throw new UnsupportedOperationException("You cannot create an instance of a utility class!");
	}

	/**
	 * Starts the application.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(final String[] args) {

		final Runnable stopDerby = new Runnable() {
			public void run() {
				try {
					final NetworkServerControl control = new NetworkServerControl(InetAddress
					        .getByName("localhost"), 1527);
					control.shutdown();
				} catch (final Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		};
		new Thread(stopDerby).start();

		final Runnable stopActiveMq = new Runnable() {
			public void run() {
				Main.main(new String[] { "stop" });
			}
		};
		new Thread(stopActiveMq).start();

	}

}
