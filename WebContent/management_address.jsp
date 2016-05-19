<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="DTO.LoginDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>住所情報管理画面</title>
</head>
<%
	String insertAddressMessage="",updateAddressMessage="",showAddressMessage="",address="";
	if(request.getAttribute("insertAddressMessage") != null){
		insertAddressMessage = (String)request.getAttribute("insertAddressMessage");
	}
	if(request.getAttribute("updateAddressMessage") != null){
		updateAddressMessage = (String)request.getAttribute("updateAddressMessage");
	}
	if(request.getAttribute("showAddressMessage") != null){
		showAddressMessage = (String)request.getAttribute("showAddressMessage");
	}
	if(request.getAttribute("address") != null){
		address = (String)request.getAttribute("address");
	}
%>
<body>
	<h2>住所情報の新規登録</h2>
	<form action="/HNS/InsertAddressAction" method="post">
		住所を入力してください<br><input type="text" name="address"><br>
		<input type="submit" value="登録">
	</form>
	<%=insertAddressMessage %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<h2>住所情報の更新</h2>
	<form action="/HNS/UpdateAddressAction" method="post">
		住所を入力してください<br><input type="text" name="address"><br>
		<input type="submit" value="更新">
	</form>
	<%=updateAddressMessage %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<h2>住所情報表示</h2>
	<form action="/HNS/ShowAddressAction" method="post">
		<input type="submit" value="表示">
	</form>
	<%=address %><br>
	<%=showAddressMessage %>
</body>
</html>