<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="vo" class="tommy.web.jstl.MemberVO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Example</title>
</head>
<body>
<c:set target="${vo}" property="name" value="ȫ�浿"/>
<c:set target="${vo}" property="email">
hong@hanmail.net
</c:set>
<c:set var="age" value="30"/>
<c:set target="${vo}" property="age" value="${age}"/>
<h3>ȸ������</h3>
<ul>
<li>�̸�: ${vo.name}</li>
<li>����: ${vo.email}</li>
<li>����: ${vo.age}</li>
</ul>
</body>
</html>