<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="dao.DivisionDAO"%>
<%@ page import="model.User"%>
<%
    //セッションスコープからユーザー情報を取得
    //エラーかも？
    User loginUser = (User)session.getAttribute("Division_DAO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこぶつ</title>
</head>
<body>
<h1>どこぶつログイン</h1>
<% if(loginUser != null){ %>
	<p> ログインに成功しました。</p>
	<p>ようこそ<%= loginUser.getEmp_name() %></p>

<% }else{ %>
	<p>ログインに失敗しました</p>
	<a href="/docoTsubu/">TOPへ</a>
<% } %>
</body>
</html>