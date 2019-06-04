<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード再設定</title>
</head>
<% if(loginUser = 1) {%>
<body>
<form action="/Bteam/Setting" method="post">
再設定のパスワード:<input type="password" name="newpass"><br>
再設定のパスワード:<input type="password" name="newpass"><br>
<input type="submit" value="再設定">
</form>
<% } else { %>
<form action="/Bteam/Setting" method="post">
前のパスワード:<input type="text" name="pass"><br>
新しいパスワード:<input type="password" name="newpass"><br>
新しいパスワード:<input type="password" name="newpass"><br>
<input type="submit" value="再設定">
</form>
<% } %>
</body>
</html>