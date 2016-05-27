<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String nName="",nPass="",hexName="",hexPass="";
	if(request.getAttribute("hexName") != null){
		hexName = (String)request.getAttribute("hexName");
	}
	if(request.getAttribute("hexPass") != null){
		hexPass = (String)request.getAttribute("hexPass");
	}
	if(request.getAttribute("nName") != null){
		nName = (String)request.getAttribute("nName");
	}
	if(request.getAttribute("nPass") != null){
		nPass = (String)request.getAttribute("nPass");
	}
%>
	<h2>テスト</h2>
	<form action="/HNS/CodeTestAction" method="post">
		<input type="submit" value="hex変換">
	</form>
	<form action="/HNS/HexTestAction" method="post">
		名前：<input type="text" name="hName" value="<%=hexName %>"><br>
		パス：<input type="text" name="hPass" value="<%=hexPass %>"><br>
		<input type="submit" value="hexインサート">
	</form>
	<form action="/HNS/UnHexTestAction" method="post">
		<input type="submit" value="文字変換">
	</form>
	<%=nName %><br>
	<%=nPass %>
</body>
</html>