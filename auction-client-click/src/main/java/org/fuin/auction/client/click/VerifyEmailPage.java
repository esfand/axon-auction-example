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
import org.fuin.auction.command.api.base.UserVerifyEmailCommand;
import org.fuin.auction.common.OperationResult;
import org.fuin.objects4j.RenderClassInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page for verifying the email address.
 */
public final class VerifyEmailPage extends AuctionPage {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(VerifyEmailPage.class);

	/** Message code for an successful registration. */
	protected static final String SUCCESS = "0010";

	@Bindable
	protected String userId;

	@Bindable
	protected String token;

	@Bindable
	protected String msg;

	@Bindable
	protected Form form = new Form();

	/**
	 * Default constructor.
	 */
	public VerifyEmailPage() {
		super();

		final RenderClassInfo<UserVerifyEmailCommand> renderClassInfo;
		renderClassInfo = new RenderClassInfo<UserVerifyEmailCommand>(UserVerifyEmailCommand.class,
		        getContext().getLocale());
		setTitle(renderClassInfo, "Verify Email");
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
			final UserVerifyEmailCommand cmd = new UserVerifyEmailCommand(getSecurityToken());
			final OperationResult result = getCommandService().send(cmd);
			msg = getMessage(form, result);
		} catch (final RuntimeException ex) {
			final String msg = getMessage(INTERNAL_ERROR);
			LOG.error(msg, ex);
			form.setError(msg);
		}
		return false;

	}

	@Override
	public void onRender() {
		super.onRender();
		if ((userId != null)
		        && ((getUserAggregateId() == null) || (getUserAggregateId().length() == 0))) {
			setUserAggregateId(userId);
		}
		if ((token != null) && ((getSecurityToken() == null) || (getSecurityToken().length() == 0))) {
			setSecurityToken(token);
		}
	}

	// TODO michael 07.11.2010 Handle form validation with JSR-303
	// We need to get rid of this dumb mapping between command and form fields!

	private String getSecurityToken() {
		return form.getField("securityToken").getValue();
	}

	private void setSecurityToken(final String securityToken) {
		form.getField("securityToken").setValue(securityToken);
	}

	private String getUserAggregateId() {
		return form.getField("aggregateId").getValue();
	}

	private void setUserAggregateId(final String userId) {
		form.getField("aggregateId").setValue(userId);
	}

}
