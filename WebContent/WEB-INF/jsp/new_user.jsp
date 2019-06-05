<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<form action="/Bteam/User_new" method="post">
ユーザー名:<input type="text" name="name"><br>
パスワード:<input type="text" name="pass"><br>
所属部署:<input type="text" name="devi"><br>
勤務地:<input type="text" name="work"><br>
<input type="submit" value="新規登録">
</form>
</body>
</html>