<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Stayle_Manegi.css">
<meta charset="UTF-8">
<title>ユーザー管理</title>
</head>
<body class="user_manage">
<form action="/Bteam/User_delete" method="post" class="new"><br>
ユーザーの新規登録：<input type="submit" value="新規登録"><br><br>
</form>
<form action="/Bteam/User_new" method="post" class="dele"><br>
ユーザーの削除：<input type="submit" value="削除"><br>
</form>
</body>
</html>