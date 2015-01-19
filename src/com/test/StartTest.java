package com.test;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Ibean;
import bean.JspInfoBean;

import controller.BeanHandler;
import controller.SessionService;

/**
 * Servlet implementation class StartTest
 */
@WebServlet("/StartTest")
public class StartTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("remoteIP", getRemortIP(request));
		request.setAttribute("servInfo",
				"request.getServletPath()= " + request.getServletPath());
		request.setAttribute("reqGetMethod", showAllMethod(request));
		// request.setAttribute("respGetMethod", showAllMethod(response));
		request.getSession().setAttribute("remoteUserInfo",
				getRemoteInfo(request));
		// request.getRequestDispatcher("/JSP/servStart.jsp").forward(request,
		// response);

		beanTest(request, response);
		response.sendRedirect(request.getContextPath() + "/serv/bean");
		// response.sendRedirect("JSP/servStart.jsp");
		// PrintWriter out = response.getWriter();
		// out.println(showAllMethod(request));
		// out.println(showAllMethod(response));
		// out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String showAllMethod(Object obj) {
		return showAllMethod(obj, true);
	}

	private String showAllMethod(Object obj, boolean isDebug) {
		Method[] methods = obj.getClass().getMethods();
		StringBuffer sb = new StringBuffer("<br><h1>Show all get method of "
				+ obj.getClass().getCanonicalName() + " </h1><br>");
		for (Method m : methods) {
			m.setAccessible(true);
			if (isDebug)
				System.out.println(m.getName());
			if (!m.getName().startsWith("get")
					|| m.getName().startsWith("getOutputStream")) {
				continue;
			}
			sb.append(m.getName() + ": ");
			Class<?> type = m.getReturnType();
			if (type == String.class || type == Integer.class
					|| type == Boolean.class || type == Long.class
					|| type == Double.class || type == Float.class
					|| type == Short.class) {
				sb.append("return type =[" + type.getSimpleName() + "]");
				try {
					if (isDebug)
						System.out.println("value =" + m.invoke(obj));
					sb.append(" value =" + m.invoke(obj));
				} catch (Exception e) {

					System.out.println("Exception when invoke " + m.getName()
							+ "\n" + e.getMessage());
				} finally {
					sb.append("<br>");
				}
			} else {
				sb.append("return type =[" + type.getSimpleName() + "]");
				try {
					if (isDebug)
						System.out.println("value =" + m.invoke(obj));
					sb.append(" value =" + m.invoke(obj).toString());
				} catch (Exception e) {

					System.out.println("Exception when invoke " + m.getName()
							+ "\n" + e.getMessage());
				} finally {
					sb.append("<br>");
				}
			}
		}
		return sb.toString();
	}

	public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	public String getRemoteInfo(HttpServletRequest req) {
		StringBuffer result = new StringBuffer();
		result.append("Remote Address = " + req.getRemoteAddr());
		result.append("<br>Remote User = " + req.getRemoteUser());
		return result.toString();
	}

	private void beanTest(HttpServletRequest request,
			HttpServletResponse response) {

		BeanHandler bh = SessionService.getBeanHandler(request);
		Ibean bean = bh.getBean(new JspInfoBean());
		if (bean == null) {
			JspInfoBean jBean = new JspInfoBean();
			jBean.addServPath(request.getServletPath());
			jBean.setSessionId("" + count++);
			bh.addBean(jBean);
		} else {
			JspInfoBean jBean = new JspInfoBean();
			jBean.setSessionId("already exist" + count++);
			jBean.addServPath(request.getServletPath());
			bh.addBean(bean);
		}

	}
}
