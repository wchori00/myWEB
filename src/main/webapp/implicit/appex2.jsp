<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서버 정보 출력</title>
</head>
<body>
서버정보: <%= application.getServerInfo() %><br></br>
서블릿 규약 메이저 버전: <%= application.getMajorVersion() %><br></br>
서블릿 규약 마이너 버전: <%= application.getMinorVersion() %>
</body>
</html>

<!-- 
서버정보: Apache Tomcat/9.0.63
서블릿 규약 메이저 버전: 4
서블릿 규약 마이너 버전: 0

=> 톰캣 9.0에 4.0버전 이라는 뜻
-->