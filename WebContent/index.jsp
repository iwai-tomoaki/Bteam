<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// セッションスコープからユーザー情報を取得
Boolean result = (Boolean)request.getAttribute("loginResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Login.css">
<title>login</title>

<script type="text/javascript">
	// ログインの成否判定結果
	window.onload = function() {
		// nullチェック
		if (<%= result %> != null) {
			// ログイン失敗
			if (<%= result %> == false) {
				alert("ログインに失敗しました");
			}
		}
	}
</script>


</head>
<body>
<form action="/Bteam/Login" method="post">
	<div class="body">
		<div class="box">
			<div class="wave -one"></div>
			<div class="wave -two"></div>
			<div class="wave -three"></div>
			<div class = "title">ログイン</div>
		</div>
		<div class = "between2">
			<div>
				社員番号
			</div>
			<div class = "space">
				<input type="text" name="input_num" value="" size="24" pattern="^[0-9]+$" title="0～9の半角数字" style="width:170px;" required>
			</div>
		</div>
		<div class = "between">
			<div>
				パスワード
			</div>
			<div class = "space">
				<input type="password" name="input_pass" value="" size="24"  pattern="^[0-9A-Za-z]+$" title="0～9の半角数字、a～zまでの全角・半角アルファベット" style="width:170px;" required>
			</div>
		</div>
		<div class = "center">
			<input type="submit" value="ログイン" class="btn-flat-border" >
		</div>
	</div>
</form>
</body>
</html>

