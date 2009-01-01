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

package com.liferay.util.bridges.jsf.sun;

import java.util.AbstractMap;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * <a href="LiferayApplicationMap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
 */
public class LiferayApplicationMap extends AbstractMap<String, Object> {

	public LiferayApplicationMap(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	public Set<Entry<String, Object>> entrySet() {
		throw new UnsupportedOperationException();
	}

	public Object get(Object key) {
		return _servletContext.getAttribute(key.toString());
	}

	public Object put(String key, Object value) {
		Object previousValue = get(key);

		_servletContext.setAttribute(key.toString(), value);

		return previousValue;
	}

	public Object remove(Object key) {
		Object value = null;

		if (key != null) {
			value = get(key);

			_servletContext.removeAttribute(key.toString());
		}

		return value;
	}

	private ServletContext _servletContext;

}