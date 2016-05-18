<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<form action="/HNS/LoginAction" method="post">
名前：<input type="text" name="name"><br>
パスワード：<input type="text" name="pass"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>