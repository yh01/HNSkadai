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
	String name=(String)session.getAttribute("name"),
		showAddressMessage="",showPhoneNumber="",showName="",
		address="",
		zip="",
		phoneNumber="",
		catchAddress="",
		catchName="",
		catchPhoneNumber="",
		managementAddressMessage="",
		nameMessage="",
		phoneMessage="",
		addressMessage="",
		masterMessage="",
		showAddress="";
	if(request.getAttribute("showAddressMessage") != null){
		showAddressMessage = (String)request.getAttribute("showAddressMessage");
	}
	if(request.getAttribute("nameMessage") != null){
		nameMessage = (String)request.getAttribute("nameMessage");
	}
	if(request.getAttribute("addressMessage") != null){
		addressMessage = (String)request.getAttribute("addressMessage");
	}
	if(request.getAttribute("phoneMessage") != null){
		phoneMessage = (String)request.getAttribute("phoneMessage");
	}
	if(request.getAttribute("address") != null){
		address = (String)request.getAttribute("address");
	}
	if(request.getAttribute("zip") != null){
		zip = (String)request.getAttribute("zip");
	}
	if(request.getAttribute("catchAddress") != null){
		catchAddress = (String)request.getAttribute("catchAddress");
	}
	if(request.getAttribute("showPhoneNumber") != null){
		showPhoneNumber = (String)request.getAttribute("showPhoneNumber");
	}
	if(request.getAttribute("showAddress") != null){
		showAddress = (String)request.getAttribute("showAddress");
	}
	if(request.getAttribute("showName") != null){
		showName= (String)request.getAttribute("showName");
	}
	if(request.getAttribute("managementAddressMessage") != null){
		managementAddressMessage = (String)request.getAttribute("managementAddressMessage");
	}
	if(request.getAttribute("masterMessage") != null){
		masterMessage = (String)request.getAttribute("masterMessage");
	}
%>
<body>
	<h1>住所情報登録アプリ/住所情報管理画面</h1>
	<br>
	<h2>住所情報の新規登録or更新</h2>
	<form action="/HNS/SearchAddressAction" method="post">
		郵便番号を入力してください<br><input type="text" name="zip" value="<%=zip %>"><br>
		<input type="submit" value="検索">
	</form>
	<form action="/HNS/InsertOrUpdateAddressAction" method="post">
		住所を入力してください:<input type="text" name="address" value="<%=catchAddress %>"><%=addressMessage %><br>
		氏名を入力してください:<input type="text" name="name" value="<%=catchName %>"><%=nameMessage %><br>
		電話番号を入力してください:<input type="text" name="phoneNumber" value="<%=catchPhoneNumber %>"><%=phoneMessage %><br>
		<input type="submit" value="登録/更新">
	</form>
	<%=managementAddressMessage %>
	<%=masterMessage %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h2>住所情報表示</h2>
	<%=name %>さんの情報
	<%=showAddressMessage %><br>
	住所：<%=showAddress %><br>
	氏名：<%=showName %><br>
	電話番号：<%=showPhoneNumber %><br>

	<h2>ログアウト</h2>
	<form action="/HNS/LogoutAction" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>