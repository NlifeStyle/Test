package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Ibean;
import bean.JspInfoBean;
import bean.UserBean;

import controller.BeanHandler;
import controller.SessionService;

/**
 * Servlet implementation class BeanTest
 */
@WebServlet("/BeanTest")
public class BeanTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private final static String NAME_PREFIX = "NAME";
	private UserBean user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BeanTest() {
		super();
		user = new UserBean();
		user.setName(NAME_PREFIX + count);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BeanHandler bh = SessionService.getBeanHandler(request);

		Ibean bean = bh.getBean(new JspInfoBean());
		if (bean != null) {
			bean.addServPath(request.getServletPath());
			bh.addBean(user);
		} else {
			throw new ServletException(
					"should not be null since it comes from startTest");
		}
		response.sendRedirect(request.getContextPath() + "/serv/start");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
