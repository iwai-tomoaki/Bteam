<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Stayle.css">
<meta charset="UTF-8">
<title>パスワード再設定</title>
</head>
<% %>	<%--未確認エラー(equalsで代用してエラーを消している) --%>
<body class="set">

h
<form action="/Bteam/Setting" method="post">
社員番号:<input type="text" name="pass"><br>
再設定のパスワード:<input type="password" name="newpass"><br>
再設定のパスワード:<input type="password" name="newpass"><br>
<input type="submit" value="再設定">
</form>
<%  %>
<form action="/Bteam/Setting" method="post">
前のパスワード:<input type="text" name="pass"><br>
新しいパスワード:<input type="password" name="newpass"><br>
新しいパスワード:<input type="password" name="newpass"><br>
<input type="submit" value="再設定">
</form>
<%  %>
</body>
</html>