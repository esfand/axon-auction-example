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
package org.fuin.auction.client.click;

import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.util.Bindable;
import org.fuin.auction.command.api.base.RegisterUserCommand;
import org.fuin.auction.common.OperationResult;
import org.fuin.objects4j.RenderClassInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register new user page.
 */
public class RegisterUserPage extends AuctionPage {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegisterUserPage.class);

	/** Message code for an successful registration. */
	protected static final String SUCCESS = "0009";

	@Bindable
	protected Form form = new Form();

	@Bindable
	protected String msg;

	/**
	 * Default constructor.
	 */
	public RegisterUserPage() {
		super();

		final RenderClassInfo<RegisterUserCommand> renderClassInfo;
		renderClassInfo = new RenderClassInfo<RegisterUserCommand>(RegisterUserCommand.class,
		        getContext().getLocale());
		setTitle(renderClassInfo, "New User Registration");
		renderToForm(renderClassInfo, form);
		form.add(new Submit("ok", "  OK  ", this, "onOkClick"));

	}

	/**
	 * Submits the data.
	 * 
	 * @return If the action was successful <code>true</code> else
	 *         <code>false</code>.
	 */
	public final boolean onOkClick() {

		if (!form.isValid()) {
			return true;
		}

		try {
			final RegisterUserCommand cmd = new RegisterUserCommand(form.getField("userName")
			        .getValue(), form.getField("password").getValue(), form.getField("email")
			        .getValue());

			final OperationResult result = getCommandService().send(cmd);
			msg = getMessage(form, result);
		} catch (final RuntimeException ex) {
			final String msg = getMessage(INTERNAL_ERROR);
			LOG.error(msg, ex);
			form.setError(msg);
		}

		return false;

	}

}
