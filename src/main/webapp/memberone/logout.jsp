<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 원래 제대로 하려면
	String loginID = (String)session.getAttribute("loginID");
	if(loginID != null) // 요 경우에만 아래 코드를 넣어야 한다
		session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<font size="4">
성공적으로 로그아웃 되었습니다<br><br>
<a href="login.jsp">로그인 페이지로 이동</a>
</font>
</body>
</html>