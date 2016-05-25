<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String a;
	String [] bbb = null;
	for (int i=0; i < 10; i++){
		a = Integer.toString(i);
		bbb[i] = a;
	}


	String[] aaa = {"a","a"};
	String[] test=aaa;
%>
<%for(int i = 0; i < test.length; i++){%>
	<input type="checkbox" name="vehicle" value="<%=test[i] %>"><%=test[i] %><br>
<% } %>
</body>
</html>