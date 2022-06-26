<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<link href="styleEx01.css" type="text/css" rel="stylesheet"/>
</head>
<!-- 로그인창 테이블 가로길이 %로 지정 -->
<!-- td 총 합 100% -->
<!-- 테이블 가로길이%안에서 열마다 비율로 조정 -->
<!-- input창 길이조절은..? -->

<body>
<form method="post" action="#">
<table width="40%" border="1">
	<tr>
		<td colspan="2" align="center">회원 로그인</td>
	</tr>
	<tr>
		<td align="right" width="30%">아이디: </td>
		<td width="70%">&nbsp;&nbsp;
		<input type="text" name="id" size="20"/></td>
	</tr>
	<tr>
		<td align="right" width="30%">비밀번호: </td>
		<td width="70%">&nbsp;&nbsp;
		<input type="password" name="pass" size="20"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인"/>&nbsp;&nbsp;
			<input type="button" value="회원가입" onClick="javascript:window.location='regFormEx01.jsp'"/>
		</td>
	</tr>
</table>
</form>


</body>
</html>