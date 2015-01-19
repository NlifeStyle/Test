package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import controller.BeanHandler;
import controller.SessionService;

/**
 * Servlet Filter implementation class SessionBeanFetchFilter
 */
@WebFilter("/SessionBeanFetchFilter")
public class SessionBeanFetchFilter implements Filter {
	private String beanHandlerName;

	/**
	 * Default constructor.
	 */
	public SessionBeanFetchFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute(getBeanHanderName()) == null) {
			BeanHandler bh = new BeanHandler();
			req.getSession().setAttribute(getBeanHanderName(), bh);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private String getBeanHanderName() {
		if (this.beanHandlerName == null) {
			beanHandlerName = SessionService.getBeanHanderName();
		}
		return beanHandlerName;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
