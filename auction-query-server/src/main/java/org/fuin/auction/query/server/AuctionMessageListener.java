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

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.fuin.auction.common.UserState;
import org.fuin.auction.message.api.CategoryCreatedMessage;
import org.fuin.auction.message.api.CategoryDeletedMessage;
import org.fuin.auction.message.api.CategoryMarkedForDeletionMessage;
import org.fuin.auction.message.api.UserEmailVerifiedMessage;
import org.fuin.auction.message.api.UserPasswordChangedMessage;
import org.fuin.auction.message.api.UserRegisteredMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Handles incoming JMS messages.
 */
@Named
public class AuctionMessageListener implements MessageListener {

	private static final Logger LOG = LoggerFactory.getLogger(AuctionMessageListener.class);

	@Inject
	private AuctionUserDao userDao;

	@Inject
	private CategoryDao categoryDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public final void onMessage(final Message message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug(message.toString());
		}

		try {

			if (message instanceof ObjectMessage) {
				final ObjectMessage objectMessage = (ObjectMessage) message;
				try {
					final Serializable obj = objectMessage.getObject();
					if (obj instanceof CategoryCreatedMessage) {
						handleMessage((CategoryCreatedMessage) obj);
					} else if (obj instanceof CategoryMarkedForDeletionMessage) {
						handleMessage((CategoryMarkedForDeletionMessage) obj);
					} else if (obj instanceof CategoryDeletedMessage) {
						handleMessage((CategoryDeletedMessage) obj);
					} else if (obj instanceof UserRegisteredMessage) {
						handleMessage((UserRegisteredMessage) obj);
					} else if (obj instanceof UserEmailVerifiedMessage) {
						handleMessage((UserEmailVerifiedMessage) obj);
					} else if (obj instanceof UserPasswordChangedMessage) {
						handleMessage((UserPasswordChangedMessage) obj);
					} else {
						LOG.warn("Received non-Auction message: " + obj);
					}
				} catch (final JMSException ex) {
					LOG.error("Error reading object from message", ex);
				}
			} else {
				LOG.warn("Received non-Object message: " + message);
			}

		} catch (final RuntimeException ex) {
			LOG.error("Error handling message", ex);
		}

	}

	private void handleMessage(final CategoryCreatedMessage message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle: " + message.toTraceString());
		}
		final Long id = Long.parseLong(message.getAggregateId());
		categoryDao.persist(new Category(id, message.getName().toString(), true));

	}

	private void handleMessage(final CategoryMarkedForDeletionMessage message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle: " + message.toTraceString());
		}

		final Long id = Long.parseLong(message.getAggregateId());
		final Category category = categoryDao.find(id);
		category.setActive(false);

	}

	private void handleMessage(final CategoryDeletedMessage message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle: " + message.toTraceString());
		}

		final Long id = Long.parseLong(message.getAggregateId());
		final Category category = categoryDao.find(id);
		categoryDao.remove(category);

	}

	private void handleMessage(final UserRegisteredMessage message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle: " + message.toTraceString());
		}

		userDao.persist(new AuctionUser(message.getAggregateId(), message.getUserName().toString(),
		        message.getEmail().toString(), UserState.NEW, message.getPassword().toString(),
		        message.getSecurityToken().toString()));

	}

	private void handleMessage(final UserEmailVerifiedMessage message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle: " + message.toTraceString());
		}

		final AuctionUser user = userDao.findByAggregateId(message.getAggregateId().toString());
		user.setState(UserState.ACTIVE);
		user.setSecurityToken(null);

	}

	private void handleMessage(final UserPasswordChangedMessage message) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Handle: " + message.toTraceString());
		}

		final AuctionUser user = userDao.findByAggregateId(message.getAggregateId().toString());
		user.setPassword(message.getPassword().toString());

	}

}
