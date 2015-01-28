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

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.click.control.TextField;
import org.fuin.objects4j.Contract;

/**
 * Validates the content of the field using Bean Validation (JSR-303).
 * 
 * @param <T>
 *            Type of the bean class this field belongs to.
 */
public final class BeanValidationTextField<T> extends TextField {

	private static final long serialVersionUID = 1L;

	private final Validator validator;

	private final Class<T> beanClass;

	/**
	 * Construct the TextField with the given name. The default text field size
	 * is 20 characters.
	 * 
	 * @param validator
	 *            Validator to use.
	 * @param beanClass
	 *            Class of the bean that contains the field with
	 *            <code>name</code> and the validation annotations.
	 * @param name
	 *            the name of the field
	 */
	public BeanValidationTextField(final Validator validator, final Class<T> beanClass,
	        final String name) {
		super(name);
		Contract.requireArgNotNull("validator", validator);
		Contract.requireArgNotNull("beanClass", beanClass);
		this.validator = validator;
		this.beanClass = beanClass;
	}

	/**
	 * Construct the TextField with the given name and required status. The
	 * default text field size is 20 characters.
	 * 
	 * @param validator
	 *            Validator to use.
	 * @param beanClass
	 *            Class of the bean that contains the field with
	 *            <code>name</code> and the validation annotations.
	 * @param name
	 *            the name of the field
	 * @param required
	 *            the field required status
	 */
	public BeanValidationTextField(final Validator validator, final Class<T> beanClass,
	        final String name, final boolean required) {
		super(name, required);
		Contract.requireArgNotNull("validator", validator);
		Contract.requireArgNotNull("beanClass", beanClass);
		this.validator = validator;
		this.beanClass = beanClass;
	}

	/**
	 * Construct the TextField with the given name and label. The default text
	 * field size is 20 characters.
	 * 
	 * @param validator
	 *            Validator to use.
	 * @param beanClass
	 *            Class of the bean that contains the field with
	 *            <code>name</code> and the validation annotations.
	 * @param name
	 *            the name of the field
	 * @param label
	 *            the label of the field
	 */
	public BeanValidationTextField(final Validator validator, final Class<T> beanClass,
	        final String name, final String label) {
		super(name, label);
		Contract.requireArgNotNull("validator", validator);
		Contract.requireArgNotNull("beanClass", beanClass);
		this.validator = validator;
		this.beanClass = beanClass;
	}

	/**
	 * Construct the TextField with the given name, label and required status.
	 * The default text field size is 20 characters.
	 * 
	 * @param validator
	 *            Validator to use.
	 * @param beanClass
	 *            Class of the bean that contains the field with
	 *            <code>name</code> and the validation annotations.
	 * @param name
	 *            the name of the field
	 * @param label
	 *            the label of the field
	 * @param required
	 *            the field required status
	 */
	public BeanValidationTextField(final Validator validator, final Class<T> beanClass,
	        final String name, final String label, final boolean required) {
		super(name, label, required);
		Contract.requireArgNotNull("validator", validator);
		Contract.requireArgNotNull("beanClass", beanClass);
		this.validator = validator;
		this.beanClass = beanClass;
	}

	/**
	 * Construct the TextField with the given name, label and size.
	 * 
	 * @param validator
	 *            Validator to use.
	 * @param beanClass
	 *            Class of the bean that contains the field with
	 *            <code>name</code> and the validation annotations.
	 * @param name
	 *            the name of the field
	 * @param label
	 *            the label of the field
	 * @param size
	 *            the size of the field
	 */
	public BeanValidationTextField(final Validator validator, final Class<T> beanClass,
	        final String name, final String label, final int size) {
		super(name, label, size);
		Contract.requireArgNotNull("validator", validator);
		Contract.requireArgNotNull("beanClass", beanClass);
		this.validator = validator;
		this.beanClass = beanClass;
	}

	/**
	 * Construct the TextField with the given name, label, size and required
	 * status.
	 * 
	 * @param validator
	 *            Validator to use.
	 * @param beanClass
	 *            Class of the bean that contains the field with
	 *            <code>name</code> and the validation annotations.
	 * @param name
	 *            the name of the field
	 * @param label
	 *            the label of the field
	 * @param size
	 *            the size of the field
	 * @param required
	 *            the field required status
	 */
	public BeanValidationTextField(final Validator validator, final Class<T> beanClass,
	        final String name, final String label, final int size, final boolean required) {
		super(name, label, size, required);
		Contract.requireArgNotNull("validator", validator);
		Contract.requireArgNotNull("beanClass", beanClass);
		this.validator = validator;
		this.beanClass = beanClass;
	}

	@Override
	public final void validate() {
		setError(null);

		super.validate();

		if (isValid() && getValue().length() > 0) {
			final String value = getValue();

			final Set<ConstraintViolation<T>> constraintViolations = validator.validateValue(
			        beanClass, getName(), value, Default.class);
			if (constraintViolations.size() > 0) {
				// Only show the first message
				setError(getLabel() + ": " + constraintViolations.iterator().next().getMessage());
			}
		}

	}

}
