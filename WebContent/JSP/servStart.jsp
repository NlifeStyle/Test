<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     <%request.setAttribute("pageCreateTime", "2015 12:08:07 PM");%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=request.getServletPath() %>
<br>
${servInfo}<br>
${remoteUserInfo}
<br>
${remoteIP}
</body>
</html>