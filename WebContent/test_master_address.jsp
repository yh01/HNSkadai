<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test_master</title>
</head>
<body>
<%
	String testMasterAddressMessage="";
	if(request.getAttribute("testMasterAddressMessage") != null){
		testMasterAddressMessage = (String)request.getAttribute("testMasterAddressMessage");
	}
%>
	<form action="/HNS/TestMasterAddressAction" method="post">
		住所を入力してください:<input type="text" name="address"><br>
		<input type="submit" value="登録/更新">
	</form>
	<%= testMasterAddressMessage %>
</body>
</html>