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
package org.fuin.axon.support.base;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.DomainEvent;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.domain.SimpleDomainEventStream;
import org.axonframework.domain.StubDomainEvent;
import org.axonframework.domain.UUIDAggregateIdentifier;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.fuin.serialver4j.base.ClassesHistory;
import org.fuin.serialver4j.base.SimpleConverterFactory;
import org.fuin.serialver4j.base.VersioningJavaSerializer;
import org.fuin.serialver4j.base.VersioningSerializer;
import org.fuin.serialver4j.hessian.VersioningBurlapSerializer;
import org.fuin.serialver4j.hessian.VersioningHessian2Serializer;
import org.fuin.serialver4j.xstream.VersioningXStreamSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests event store with different serializers.
 * 
 * @author Allard Buijze
 * @author Michael Schnell
 */
// TESTCODE:BEGIN
public class FileSystemEventStoreTest {

	private AggregateIdentifier aggregateIdentifier;

	@Before
	public final void setUp() {
		aggregateIdentifier = new UUIDAggregateIdentifier();
	}

	@After
	public final void tearDown() {
		aggregateIdentifier = null;
	}

	@Test
	public final void testSaveStreamAndReadBackInBurlap() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningBurlapSerializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testSaveStreamAndReadBackIn(eventStore);

	}

	@Test
	public final void testSaveStreamAndReadBackInXStream() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningXStreamSerializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testSaveStreamAndReadBackIn(eventStore);

	}

	@Test
	public final void testSaveStreamAndReadBackInHessian2() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningHessian2Serializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testSaveStreamAndReadBackIn(eventStore);

	}

	@Test
	public final void testSaveStreamAndReadBackInJava() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningJavaSerializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testSaveStreamAndReadBackIn(eventStore);

	}

	@Test
	public final void testAppendSnapShotBurlap() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningBurlapSerializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testAppendSnapShot(eventStore);

	}

	@Test
	public final void testAppendSnapShotXStream() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningXStreamSerializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testAppendSnapShot(eventStore);

	}

	@Test
	public final void testAppendSnapShotHessian2() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningHessian2Serializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testAppendSnapShot(eventStore);

	}

	@Test
	public final void testAppendSnapShotJava() {

		// PREPARE
		final VersioningSerializer versioningSerializer = createVersioningJavaSerializer();
		final FileSystemEventStore eventStore = createFileSystemEventStore(versioningSerializer);

		// TEST & ASSERT
		testAppendSnapShot(eventStore);

	}

	private VersioningBurlapSerializer createVersioningBurlapSerializer() {
		return new VersioningBurlapSerializer(new ClassesHistory("versionUID",
		        new SimpleConverterFactory()), 512, AxonSupportUtils.createSerializerFactory());
	}

	private VersioningXStreamSerializer createVersioningXStreamSerializer() {
		return new VersioningXStreamSerializer(new ClassesHistory("versionUID",
		        new SimpleConverterFactory()), AxonSupportUtils.createXStream(), 1024);
	}

	private VersioningHessian2Serializer createVersioningHessian2Serializer() {
		return new VersioningHessian2Serializer(new ClassesHistory("versionUID",
		        new SimpleConverterFactory()), 512, AxonSupportUtils.createSerializerFactory());
	}

	private VersioningJavaSerializer createVersioningJavaSerializer() {
		return new VersioningJavaSerializer(new ClassesHistory("versionUID",
		        new SimpleConverterFactory()));
	}

	private FileSystemEventStore createFileSystemEventStore(
	        final VersioningSerializer versioningSerializer) {
		final FileSystemEventStore eventStore = new FileSystemEventStore(
		        new VersioningEventSerializer(versioningSerializer));
		eventStore.setBaseDir(new File("target"));
		return eventStore;
	}

	private void testSaveStreamAndReadBackIn(final FileSystemEventStore eventStore) {

		final StubDomainEvent event1 = new StubDomainEvent(aggregateIdentifier, 0);
		final StubDomainEvent event2 = new StubDomainEvent(aggregateIdentifier, 1);
		final StubDomainEvent event3 = new StubDomainEvent(aggregateIdentifier, 2);

		final DomainEventStream stream = new SimpleDomainEventStream(event1, event2, event3);
		eventStore.appendEvents("test", stream);

		final DomainEventStream eventStream = eventStore.readEvents("test", aggregateIdentifier);
		final List<DomainEvent> domainEvents = new ArrayList<DomainEvent>();
		while (eventStream.hasNext()) {
			domainEvents.add(eventStream.next());
		}

		assertEquals(event1, domainEvents.get(0));
		assertEquals(event2, domainEvents.get(1));
		assertEquals(event3, domainEvents.get(2));
	}

	private void testAppendSnapShot(final FileSystemEventStore eventStore) {
		final AtomicInteger counter = new AtomicInteger(0);

		writeEvents(eventStore, counter, 5);
		eventStore.appendSnapshotEvent("snapshotting", new StubDomainEvent(aggregateIdentifier, 4));
		writeEvents(eventStore, counter, 5);
		eventStore.appendSnapshotEvent("snapshotting", new StubDomainEvent(aggregateIdentifier, 9));
		writeEvents(eventStore, counter, 5);
		eventStore
		        .appendSnapshotEvent("snapshotting", new StubDomainEvent(aggregateIdentifier, 14));
		writeEvents(eventStore, counter, 2);

		final DomainEventStream eventStream = eventStore.readEvents("snapshotting",
		        aggregateIdentifier);
		final List<DomainEvent> actualEvents = new ArrayList<DomainEvent>();
		while (eventStream.hasNext()) {
			actualEvents.add(eventStream.next());
		}
		assertEquals(new Long(14), actualEvents.get(0).getSequenceNumber());
		assertEquals(3, actualEvents.size());
	}

	private void writeEvents(final FileSystemEventStore eventStore, final AtomicInteger counter,
	        final int numberOfEvents) {
		final List<DomainEvent> events = new ArrayList<DomainEvent>();
		for (int t = 0; t < numberOfEvents; t++) {
			events.add(new StubDomainEvent(aggregateIdentifier, counter.getAndIncrement()));
		}
		eventStore.appendEvents("snapshotting", new SimpleDomainEventStream(events));
	}

}
// TESTCODE:END
