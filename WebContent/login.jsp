<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Stayle.css">
<title>login</title>
</head>
<body>
<form action="/Bteam/Login" method="post">
    <div class = "main">

        <div class="box">
            <div class="wave -one"></div>
            <div class="wave -two"></div>
            <div class="wave -three"></div>
            <div class = "title">ログイン画面</div>
        </div>
        <hr>
        <div class = "between">
            <th>
                ユーザID
            </th>
            <td class = "space">
                <input type="text" name="user_id" value="" size="24">
            </td>
        </div>

        <div class = "between">
                <th>
                    password
                </th>
                <td class = "space">
                    <input type="password" name="password" value="" size="24">
                </td>
            </div>
        <div class = "center">
            <input type="submit" class="btn-flat-border">BUTTON</a>
        </div>
    </div>
            </form>
</body>
</html>

