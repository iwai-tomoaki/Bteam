<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Stayle.css">
<title>login</title>
</head>
<body>
<form action="/Bteam/Login" method="post">
	<div class="body">
		<div class="box">
			<div class="wave -one"></div>
			<div class="wave -two"></div>
			<div class="wave -three"></div>
			<div class = "title">ログイン画面</div>
		</div>
		<div class = "between2">
			<div>
				ユーザID
			</div>
			<div class = "space">
				<input type="text" name="num" value="" size="24">
			</div>
		</div>
		<div class = "between">
			<div>
				password
			</div>
			<div class = "space">
				<input type="password" name="pass" value="" size="24">
			</div>
		</div>
		<div class = "center">
			<input type="submit" value="ログイン" class="btn-flat-border">
		</div>
	</div>
</form>
</body>
</html>

