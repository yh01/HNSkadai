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
	String insertAddressMessage="",updateAddressMessage="",showAddressMessage="",address="",catchAddress="";
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
	if(request.getAttribute("catchAddress") != null){
		catchAddress = (String)request.getAttribute("catchAddress");
	}
%>
<body>
	<h1>住所情報登録アプリ/住所情報管理画面</h1>
	<br>
	<h2>住所情報の新規登録</h2>
	<form action="/HNS/SearchAddressAction" method="post">
		郵便番号を入力してください<br><input type="text" name="zip"><br>
		<input type="submit" value="検索">
	</form>
	<form action="/HNS/InsertAddressAction" method="post">
		住所を入力してください<br><input type="text" name="address" value="<%=catchAddress %>"><br>
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
	<%=showAddressMessage %><br>
	<%=address %>

	<h2>ログアウト</h2>
	<form action="/HNS/LogoutAction" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>