<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="Stayle.css">

<title>パスワード再設定</title>
</head>
<body>
<form action="/Bteam/SettingUesr" method="post">
新しいパスワード:<input type="password" name="pass"><br>
<input type="submit" value="再設定">
</form>
</body>
</html>