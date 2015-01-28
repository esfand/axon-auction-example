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
package org.fuin.auction.command.server.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.axonframework.eventhandling.FullConcurrencyPolicy;
import org.axonframework.eventhandling.annotation.AsynchronousEventListener;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.fuin.auction.command.server.events.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Responsible for sending or receiving mails.
 */
@Named
@AsynchronousEventListener(sequencingPolicyClass = FullConcurrencyPolicy.class)
public class MailManager {

	private static final Logger LOG = LoggerFactory.getLogger(MailManager.class);

	@Inject
	private JavaMailSender mailSender;

	@Inject
	private VelocityEngine velocityEngine;

	@Inject
	@Named("mailProperties")
	private Properties mailProperties;

	/**
	 * Sets the mail sender to a new value.
	 * 
	 * @param mailSender
	 *            Mail sender to set.
	 */
	protected final void setMailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Sets the velocity engine to a new value.
	 * 
	 * @param velocityEngine
	 *            Engine to set.
	 */
	protected final void setVelocityEngine(final VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * Sets the mail properties.
	 * 
	 * @param props
	 *            Mail properties.
	 */
	protected final void setMailProperties(final Properties props) {
		this.mailProperties = props;
	}

	/**
	 * Creates a welcome mail with a unique identifier to verify the email
	 * address.
	 * 
	 * @param event
	 *            Event to handle.
	 */
	@EventHandler
	public final void handle(final UserCreatedEvent event) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("SEND user created mail to " + event.getEmail() + " [securityToken='"
			        + event.getSecurityToken() + "']");
		}

		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(final MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(event.getEmail().toString());
				message.setFrom(mailProperties.getProperty("sender"));
				final Map<String, String> varMap = new HashMap<String, String>();
				varMap.put("email", event.getEmail().toString());
				varMap.put("userName", event.getUserName().toString());
				varMap.put("securityToken", event.getSecurityToken().toString());
				final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				        "user-created-mail.vm", varMap);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);

	}

}
