<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="DTO.LoginDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>住所登録画面</title>
</head>
<body>
<form action="/HNS/InsertAddressAction" method="post">
住所を入力してください<br><input type="text" name="address"><br>
<input type="submit" value="登録">
</form>
</body>
</html>