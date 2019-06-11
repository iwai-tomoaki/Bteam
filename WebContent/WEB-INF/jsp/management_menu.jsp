<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" type="text/css" href="StayleManage.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
        <link rel="stylesheet" href="StyleManage.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
</head>
<body>
    <!-- 以下はヘッダー -->
    <header class="head">
        <!-- 色、高さ、位置指定 -->
        <div class="head-container">
            <!-- 中に入るアイコン、ログアウト等の位置指定 -->
            <div class="head-contents">
                <!-- 入れてあげる要素の隙間を作ったげる -->
                <div class="items">
                    <!-- 人アイコンを作る -->
                    <i class="fas fa-address-book"></i>
                    <!-- サンプルを表示 -->
                    <span>smaple</span>
                    <!-- POSTにLogoutをリクエストする -->
                    <form name="Logout" method="post">
                        <a href="index.jsp" onclick="return confirm('ログアウトします')" value="Logout">ログアウト</a>
                    </form>
                </div>
            </div>
        </div>
    </header>

    <!-- 基本的なメイン画面 -->
    <div class = "set">
        <div class = "main">
            <div class = "center2">
                <h2>Menu</h2>
            </div>

            <div class="registration">
                    <div class = "registration">
                        <button class = "bg-add-submit" onclick="location.href=
                        './User_Del.html'">全件表示</button>
                    </div>
                </div>
            <!-- 上のボタン配置２つ -->
            <div class="registration">
                <div class = "registration">
                    <button class = "bg-add-submit" onclick="location.href=
                    './User_Del.html'">東京</button>
                </div>
                <div class = "registration">
                    <button class = "bg-add-submit" onclick="location.href=
                    './User_Del.html'">東京（開発）</button>
                </div>
            </div>

            <div class="registration">
                <div class = "registration">
                    <button class = "bg-add-submit" onclick="location.href=
                    './User_Del.html'">札幌</button>
                </div>
                <div class = "registration">
                    <button class = "bg-add-submit" onclick="location.href=
                    './User_Del.html'">宮崎</button>
                </div>
            </div>
        </div>


        <div class = "main">
            <div class = "registration">
            	<form action="/Bteam/DeleteUser" method="get" >
                	<button class = "bg-add-submit2" value = "">既存ユーザー削除</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>