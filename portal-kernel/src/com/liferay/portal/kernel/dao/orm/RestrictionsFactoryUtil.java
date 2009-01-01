/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.kernel.dao.orm;

import java.util.Collection;
import java.util.Map;

/**
 * <a href="RestrictionsFactoryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class RestrictionsFactoryUtil {

	public static Criterion allEq(Map propertyNameValues) {
		return getRestrictionsFactory().allEq(propertyNameValues);
	}

	public static Criterion and(Criterion lhs, Criterion rhs) {
		return getRestrictionsFactory().and(lhs, rhs);
	}

	public static Criterion between(String propertyName, Object lo, Object hi) {
		return getRestrictionsFactory().between(propertyName, lo, hi);
	}

	public static Conjunction conjunction() {
		return getRestrictionsFactory().conjunction();
	}

	public static Disjunction disjunction() {
		return getRestrictionsFactory().disjunction();
	}

	public static Criterion eq(String propertyName, Object value) {
		return getRestrictionsFactory().eq(propertyName, value);
	}

	public static Criterion eqProperty(
		String propertyName, String otherPropertyName) {

		return getRestrictionsFactory().eqProperty(
			propertyName, otherPropertyName);
	}

	public static Criterion ge(String propertyName, Object value) {
		return getRestrictionsFactory().ge(propertyName, value);
	}

	public static Criterion geProperty(
		String propertyName, String otherPropertyName) {

		return getRestrictionsFactory().geProperty(
			propertyName, otherPropertyName);
	}

	public static RestrictionsFactory getRestrictionsFactory() {
		return _restrictionsFactory;
	}

	public static Criterion gt(String propertyName, Object value) {
		return getRestrictionsFactory().gt(propertyName, value);
	}

	public static Criterion gtProperty(
		String propertyName, String otherPropertyName) {

		return getRestrictionsFactory().gtProperty(
			propertyName, otherPropertyName);
	}

	public static Criterion ilike(String propertyName, Object value) {
		return getRestrictionsFactory().ilike(propertyName, value);
	}

	public static Criterion in(String propertyName, Collection values) {
		return getRestrictionsFactory().in(propertyName, values);
	}

	public static Criterion in(String propertyName, Object[] values) {
		return getRestrictionsFactory().in(propertyName, values);
	}

	public static Criterion isEmpty(String propertyName) {
		return getRestrictionsFactory().isEmpty(propertyName);
	}

	public static Criterion isNotEmpty(String propertyName) {
		return getRestrictionsFactory().isNotEmpty(propertyName);
	}

	public static Criterion isNotNull(String propertyName) {
		return getRestrictionsFactory().isNotNull(propertyName);
	}

	public static Criterion isNull(String propertyName) {
		return getRestrictionsFactory().isNull(propertyName);
	}

	public static Criterion le(String propertyName, Object value) {
		return getRestrictionsFactory().le(propertyName, value);
	}

	public static Criterion leProperty(
		String propertyName, String otherPropertyName) {

		return getRestrictionsFactory().leProperty(
			propertyName, otherPropertyName);
	}

	public static Criterion like(String propertyName, Object value) {
		return getRestrictionsFactory().like(propertyName, value);
	}

	public static Criterion lt(String propertyName, Object value) {
		return getRestrictionsFactory().lt(propertyName, value);
	}

	public static Criterion ltProperty(
		String propertyName, String otherPropertyName) {

		return getRestrictionsFactory().ltProperty(
			propertyName, otherPropertyName);
	}

	public static Criterion ne(String propertyName, Object value) {
		return getRestrictionsFactory().ne(propertyName, value);
	}

	public static Criterion neProperty(
		String propertyName, String otherPropertyName) {

		return getRestrictionsFactory().neProperty(
			propertyName, otherPropertyName);
	}

	public static Criterion not(Criterion expression) {
		return getRestrictionsFactory().not(expression);
	}

	public static Criterion or(Criterion lhs, Criterion rhs) {
		return getRestrictionsFactory().or(lhs, rhs);
	}

	public void setRestrictionsFactory(
		RestrictionsFactory restrictionsFactory) {

		_restrictionsFactory = restrictionsFactory;
	}

	public static Criterion sizeEq(String propertyName, int size) {
		return getRestrictionsFactory().sizeEq(propertyName, size);
	}

	public static Criterion sizeGe(String propertyName, int size) {
		return getRestrictionsFactory().sizeGe(propertyName, size);
	}

	public static Criterion sizeGt(String propertyName, int size) {
		return getRestrictionsFactory().sizeGt(propertyName, size);
	}

	public static Criterion sizeLe(String propertyName, int size) {
		return getRestrictionsFactory().sizeLe(propertyName, size);
	}

	public static Criterion sizeLt(String propertyName, int size) {
		return getRestrictionsFactory().sizeLt(propertyName, size);
	}

	public static Criterion sizeNe(String propertyName, int size) {
		return getRestrictionsFactory().sizeNe(propertyName, size);
	}

	private static RestrictionsFactory _restrictionsFactory;

}