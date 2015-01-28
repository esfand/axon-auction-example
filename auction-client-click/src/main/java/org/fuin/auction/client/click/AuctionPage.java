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

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.util.Bindable;
import org.fuin.auction.command.api.base.AuctionCommandService;
import org.fuin.auction.common.OperationResult;
import org.fuin.objects4j.RenderClassInfo;
import org.fuin.objects4j.RenderFieldInfo;
import org.fuin.objects4j.TextFieldInfo;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * Base page layout for all application pages.
 */
public abstract class AuctionPage extends Page {

	private static final long serialVersionUID = 1L;

	/** Error message code for an internal error. */
	protected static final String INTERNAL_ERROR = "00001";

	private final AuctionCommandService commandService;

	private final Validator validator;

	@Bindable
	protected Form searchForm = new Form();

	/**
	 * Default constructor.
	 */
	public AuctionPage() {
		super();

		// TODO michael Refactor the following when switching to Spring
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		try {
			commandService = (AuctionCommandService) new HessianProxyFactory().create(
			        AuctionCommandService.class,
			        "http://localhost:8080/auction-command-server/AuctionCommandService");
		} catch (final MalformedURLException ex) {
			throw new RuntimeException(ex);
		}

		final TextField textField = new TextField("searchText");
		searchForm.add(textField);

		final Select select = new Select("searchCategory");
		select.setId("searchCategory");
		// TODO michael Move the categories into DB and create commands &
		// queries for it
		select.add(new Option(0, "All Categories"));
		select.add(new Option(1, "Antiques"));
		select.add(new Option(2, "Art"));
		select.add(new Option(3, "Baby"));
		select.add(new Option(4, "Books"));
		select.add(new Option(5, "Business & Industrial"));
		select.add(new Option(6, "Cameras & Photo"));
		select.add(new Option(7, "Cars, Boats, Vehicles & Parts"));
		select.add(new Option(8, "Cell Phones & PDAs"));
		select.add(new Option(9, "Clothing, Shoes & Accessories"));
		select.add(new Option(10, "Coins & Paper Money"));
		select.add(new Option(11, "Collectibles"));
		select.add(new Option(12, "Computers & Networking"));
		select.add(new Option(13, "Crafts"));
		select.add(new Option(14, "Dolls & Bears"));
		select.add(new Option(15, "DVDs & Movies"));
		select.add(new Option(16, "Electronics"));
		select.add(new Option(17, "Entertainment Memorabilia"));
		select.add(new Option(18, "Gift Cards & Coupons"));
		select.add(new Option(19, "Health & Beauty"));
		select.add(new Option(20, "Home & Garden"));
		select.add(new Option(21, "Jewelry & Watches"));
		select.add(new Option(22, "Music"));
		select.add(new Option(23, "Musical Instruments"));
		select.add(new Option(24, "Pet Supplies"));
		select.add(new Option(25, "Pottery & Glass"));
		select.add(new Option(26, "Real Estate"));
		select.add(new Option(27, "Specialty Services"));
		select.add(new Option(28, "Sporting Goods"));
		select.add(new Option(29, "Sports Mem, Cards & Fan Shop"));
		select.add(new Option(30, "Stamps"));
		select.add(new Option(31, "Tickets"));
		select.add(new Option(32, "Toys & Hobbies"));
		select.add(new Option(33, "Travel"));
		select.add(new Option(34, "Video Games"));
		select.add(new Option(99, "Everything Else"));
		select.setSelectedValues(Arrays.asList(new int[] { 0 }));
		searchForm.add(select);

		searchForm.add(new Submit("searchBtn", "  Search  ", this, "onSearchClick"));

	}

	@Override
	public final String getTemplate() {
		return "/auction-template.htm";
	}

	/**
	 * Returns the command service.
	 * 
	 * @return Command service.
	 */
	protected final AuctionCommandService getCommandService() {
		return commandService;
	}

	/**
	 * Sets the title of the page.
	 * 
	 * @param renderClassInfo
	 *            Render information for the class.
	 * @param defaultTitle
	 *            Default title.
	 */
	protected final void setTitle(final RenderClassInfo renderClassInfo, final String defaultTitle) {
		if (renderClassInfo.getLabelClassInfo() != null) {
			if (renderClassInfo.getLabelClassInfo().getText() == null) {
				addModel("title", defaultTitle);
			} else {
				addModel("title", renderClassInfo.getLabelClassInfo().getText());
			}
		} else {
			addModel("title", defaultTitle);
		}
	}

	/**
	 * Returns a formatted message for form page.
	 * 
	 * @param form
	 *            The current form.
	 * @param result
	 *            Command result.
	 * 
	 * @return Formatted and localized message or <code>null</code>.
	 */
	protected final String getMessage(final Form form, final OperationResult result) {

		form.clearErrors();
		if (result.isSuccess()) {
			form.clearValues();
			return getMessage(result);
		}
		form.setError(getMessage(result));
		return null;

	}

	/**
	 * Returns a formatted message.
	 * 
	 * @param result
	 *            Command result.
	 * 
	 * @return Formatted and localized message.
	 */
	protected final String getMessage(final OperationResult result) {

		final String msg = getMessage(result.getCodeStr());
		if (msg == null) {
			// We received an unknown (newer) message code.
			// Seems as if this client is not up-to-date
			return result.getText();
		}
		return msg;

	}

	/**
	 * Adds all fields of the renderClassInfo to the form.
	 * 
	 * @param renderClassInfo
	 *            Render information.
	 * @param form
	 *            Form to add the fields to.
	 */
	protected final void renderToForm(final RenderClassInfo<?> renderClassInfo, final Form form) {

		final List<RenderFieldInfo> fields = renderClassInfo.getRenderFields();
		for (final RenderFieldInfo field : fields) {

			// TODO michael Handle other controls than text fields
			if (field.getTextFieldInfo() != null) {
				final TextFieldInfo tf = field.getTextFieldInfo();
				final TextField textField;
				if (field.isPasswordField()) {
					textField = new PasswordField(tf.getField().getName(), field.getLabelText(),
					        field.isRequired());
				} else {
					textField = new BeanValidationTextField(validator, renderClassInfo.getClasz(),
					        tf.getField().getName(), field.getLabelText(), field.isRequired());
				}
				final Long minLength = field.getMinLength();
				if (minLength != null) {
					textField.setMinLength(minLength.intValue());
				}
				final Long maxLength = field.getMaxLength();
				if (maxLength != null) {
					textField.setMaxLength(maxLength.intValue());
				}
				if (tf.getWidth() > 0) {
					textField.setSize(tf.getWidth());
				}
				form.add(textField);
			}
		}

	}

	protected final Validator getValidator() {
		return validator;
	}

	/**
	 * Submits the data.
	 * 
	 * @return If the action was successful <code>true</code> else
	 *         <code>false</code>.
	 */
	public final boolean onSearchClick() {

		if (!searchForm.isValid()) {
			return true;
		}

		return false;

	}

}
