package com.tainy.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 解决request里getinputstream只能获取一次值得问题
 *
 * @author Tainy
 * @date 2018/1/14
 */

public class HttpServletRequestReplacedFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new RequestWrapper((HttpServletRequest) request);
		}
		if (requestWrapper == null) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(requestWrapper, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}