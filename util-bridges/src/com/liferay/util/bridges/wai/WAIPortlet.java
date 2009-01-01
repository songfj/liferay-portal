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

package com.liferay.util.bridges.wai;

import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="WAIPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class WAIPortlet extends GenericPortlet {

	public static final String CONNECTOR_IFRAME = "iframe";

	public static final String CONNECTOR_INCLUDE = "include";

	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		_connector = GetterUtil.getString(
			portletConfig.getInitParameter("wai.connector"), CONNECTOR_IFRAME);
	}

	public void processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (renderRequest.getWindowState().equals(WindowState.MAXIMIZED)) {
			invokeApplication(renderRequest, renderResponse);
		}
		else {
			renderNormalWindowState(renderRequest, renderResponse);
		}
	}

	protected void forward(
			HttpServletRequest request, HttpServletResponse response,
			String path)
		throws PortletException {

		RequestDispatcher requestDispatcher =
			request.getRequestDispatcher(path);

		try {
			requestDispatcher.forward(request, response);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (ServletException se) {
			throw new PortletException(se);
		}
	}

	protected void invokeApplication(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		HttpServletRequest request =
			(HttpServletRequest)renderRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_REQUEST);
		HttpServletResponse response =
			(HttpServletResponse)renderRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_RESPONSE);

		String portletName = getPortletConfig().getPortletName();

		String friendlyURL = (String)request.getAttribute("FRIENDLY_URL");

		int pos = friendlyURL.indexOf(_MAPPING);

		StringBuilder contextPath = new StringBuilder();

		contextPath.append(friendlyURL.substring(0, pos + _MAPPING.length()));
		contextPath.append(StringPool.SLASH);
		contextPath.append(portletName);

		pos = friendlyURL.indexOf(portletName);

		String pathInfo = friendlyURL.substring(pos + portletName.length());

		Map<String, String[]> params = new HashMap<String, String[]>(
			request.getParameterMap());

		params.remove(_APP_URL);

		String queryString = HttpUtil.parameterMapToString(params, false);

		String appUrl = ParamUtil.getString(
			renderRequest, _APP_URL, StringPool.SLASH);

		if (_connector.equals(CONNECTOR_IFRAME)) {
			request.setAttribute(
				_APP_URL, renderRequest.getContextPath() + appUrl);

			String iframeExtraHeight = GetterUtil.getString(
				getPortletConfig().getInitParameter(
					"wai.connector.iframe.height.extra"),
				"40");

			renderRequest.setAttribute(
				"wai.connector.iframe.height.extra", iframeExtraHeight);

			forward(request, response, _JSP_IFRAME);
		}
		else if (_connector.equals(CONNECTOR_INCLUDE)) {
			HttpServletRequest waiRequest = new WAIHttpServletRequest(
				request, contextPath.toString(), pathInfo, queryString, params);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(
				appUrl);

			try {
				requestDispatcher.forward(waiRequest, response);
			}
			catch (ServletException se) {
				throw new PortletException(se);
			}
		}
	}

	protected void renderNormalWindowState(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		HttpServletRequest request =
			(HttpServletRequest)renderRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_REQUEST);
		HttpServletResponse response =
			(HttpServletResponse)renderRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_RESPONSE);

		PortletURL renderURL = renderResponse.createRenderURL();

		renderURL.setWindowState(WindowState.MAXIMIZED);

		renderRequest.setAttribute("renderURL", renderURL.toString());

		forward(request, response, _JSP_NORMAL_WINDOW_STATE);
	}

	private static final String _MAPPING = "waiapp";

	private static final String _APP_URL = "appURL";

	private static final String _JSP_DIR = "/WEB-INF/jsp/liferay/wai";

	private static final String _JSP_IFRAME = _JSP_DIR + "/iframe.jsp";

	private static final String _JSP_NORMAL_WINDOW_STATE =
		_JSP_DIR + "/normal_window_state.jsp";

	private static Log _log = LogFactory.getLog(WAIPortlet.class);

	private String _connector;

}