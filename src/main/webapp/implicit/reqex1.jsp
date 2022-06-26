<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클라이언트 및 서버 정보</title>
</head>
<body>
클라이언트IP(getRemoteAddr()) = <%= request.getRemoteAddr() %> <br></br>
요청정보길이(getContentLength()) = <%= request.getContentLength() %> <br></br>
요청정보 인코딩(getCharacterEncoding()) = <%= request.getCharacterEncoding() %> <br></br>
요청정보 컨텐트타입(getContentType()) = <%= request.getContentType() %> <br></br>
요청정보 프로토콜(getProtocol()) = <%= request.getProtocol() %> <br></br>
요청정보 전송방식(getMethod()) = <%= request.getMethod() %> <br></br>
요청 URL(getRequestURL().toString()) = <%= request.getRequestURL().toString() %> <br></br>
요청 URI(getRequestURI()) = <%= request.getRequestURI() %> <br></br>
컨텍스트 경로(getServerName()) = <%= request.getServerName() %> <br></br>
서버이름(getServerName()) = <%= request.getServerName() %> <br></br>
서버포트(getServerPort()) = <%= request.getServerPort() %> <br></br>
</body>
</html>