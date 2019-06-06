<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>あなたの社員番号とパスワードを入力してください。</h1>
<form action="/Bteam/Login" method="post">
社員番号:<input type="text" name="user_id"><br>
パスワード:<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>