<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.lang.reflect.Method"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>requestURL:</h4>
	<%=request.getRequestURL()%>
	<h4>requestURI:</h4>
	<%=request.getRequestURI()%><p>
		<%
			String url = null;
			Method[] methods = request.getClass().getDeclaredMethods();
			StringBuffer sb = new StringBuffer("#########################<br>");
			for (Method m : methods) {
				m.setAccessible(true);
				sb.append(m.getName() + ": ");
				Class type = m.getReturnType();
				if (type == String.class || type == Integer.class
						|| type == Boolean.class || type == Long.class
						|| type == Double.class || type == Float.class
						|| type == Short.class) {
					sb.append("return type =["+type.getSimpleName()+"]");
					try {
						sb.append(" value ="+m.invoke(request));
					} catch (Exception e) {
						System.out.println("Exception when invoke "
								+ m.getName() + "\n" + e.getMessage());
					} finally {
						sb.append("<br>");
					}
				}else{
					sb.append("return type =["+type.getSimpleName()+"]");
					try {
						sb.append(" value ="+m.invoke(request).toString());
					} catch (Exception e) {
						System.out.println("Exception when invoke "
								+ m.getName() + "\n" + e.getMessage());
					} finally {
						sb.append("<br>");
					}
				}
			}
			out.println(sb.toString());
		%>
	
</body>
</html>