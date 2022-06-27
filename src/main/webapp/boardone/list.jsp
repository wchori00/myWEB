<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "tommy.web.boardone.BoardDAO" %>
<%@ page import = "tommy.web.boardone.BoardVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "view/color.jsp" %>
<%!
// 수정<1>
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
// 수정<2>
int count = 0;
int number = 0;
List<BoardVO> articleList = null;
BoardDAO dbPro = BoardDAO.getInstance();
count = dbPro.getArticleCount(); // 전체 글수
if(count > 0) {
	articleList = dbPro.getArticles(); // 수정<3>
}
number = count; // 수정<4>
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%= bodyback_c %>">
<center><b>글목록(전체 글: <%= count %>)</b>
<table width="700">
<tr>
<td align="right" bgcolor="<%= value_c %>">
<a href="writeForm.jsp">글쓰기</a>
</td>
</table>
<%
	if(count == 0) {
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
<td align="center">
게시판에 저장된 글이 없습니다
</td>
</tr>
</table>
<%	}else { %>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
<tr height="30" bgcolor="<%= value_c %>">
<th align="center" width="50">번  호</th>
<th align="center" width="250">제    목</th>
<th align="center" width="100">작성자</th>
<th align="center" width="150">작성일</th>
<th align="center" width="50">조  회</th>
<th aligh="center" width="100">IP</th>
</tr>
<%
	for(int i = 0; i < articleList.size(); i++) {
		BoardVO article = (BoardVO)articleList.get(i);
%>
<tr height="30">
<td align="center" width="50"> <%= number-- %> </td>
<td width = "250">
<!-- 수정<5> -->
<a href="content.jsp?num=<%=article.getNum() %>&pageNum = 1">
<!-- 수정<6> -->
<%=article.getSubject() %></a>
<% if(article.getReadcount() >= 20) {%>
<img src = "images/hot.gif" border = "0" height="16"><%} %></td>
<td align = "center" width = "100">
<a href="mailto:<%= article.getEmail() %>"><%= article.getWriter() %></a></td>
<td align="center" width="150">
<%= sdf.format(article.getRegdate()) %></td>
<td align="center" width="50"><%=article.getReadcount() %></td>
<td align="center" width="100"><%=article.getIp() %></td>
</tr>
<%	} %>
</table>
<%} %>
<!-- 수정<7> -->

</center>
</body>
</html>