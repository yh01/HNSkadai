<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<%
	String loginMessage="",createUserMessage="",sessionTest="";
	if(request.getAttribute("loginMessage") != null){
		loginMessage = (String)request.getAttribute("loginMessage");
	}
	if(request.getAttribute("createUserMessage") != null){
		createUserMessage = (String)request.getAttribute("createUserMessage");
	}
	if(session.getAttribute("id") != null){
		sessionTest = (String)request.getAttribute("id");
	}
%>
<body>
	<h1>住所情報登録アプリ/ログイン・新規登録画面</h1><%= sessionTest %>

	<h2>ログイン</h2>
	<form action="/HNS/LoginAction" method="post">
		名前：<input type="text" name="name"><br>
		パスワード：<input type="text" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
		<%= loginMessage %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h2>新規登録</h2>
	<form action="/HNS/CreateUserAction" method="post">
		名前：<input type="text" name="name"><br>
		パスワード：<input type="text" name="pass"><br>
		<input type="submit" value="新規登録">
	</form>
		<%= createUserMessage %>
</body>
</html>