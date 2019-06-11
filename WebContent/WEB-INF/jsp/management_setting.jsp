<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="New_User.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
</head>
<body>
    <!-- 以下はヘッダー -->
    <header class="head">
        <div class="head-container">
            <div class="head-contents">
                <div class="items">
                    <i class="fas fa-address-book"></i>
                    <span>smaple</span>
                    <form name="Logout" method="post">
                        <a href="index.jsp" onclick="return confirm('ログアウトします')" value="Logout">ログアウト</a>
                    </form>
            </div>
        </div>
    </header>

    <!-- 基本的なメイン画面 -->
    <form action="/Bteam/ManagementSetting" method="POST" >
    <div class = "main">
        <div class = "top">
            <h2>ユーザー情報変更</h2>
        </div>
        <div class = "start">
            <div class = "ml30">
                社員番号
                </div>
            <div>
                <input type="text" name="emp_num" value="" size="24">
            </div>
        </div>

        <div class = "start">
            <div class = "ml30">
                登録名
                </div>
            <div>
                <input type="text" name="emp_name" value="" size="24">
            </div>
        </div>

        <div class = "start">
            <div class = "ml30">
                パスワード
                </div>
            <div>
                <input type="password" name="pass" value="" size="24">
            </div>
        </div>

        <div class = "start">
            <div class = "ml30">
                部署
            </div>
            <div>
		        <div class="cp_ipselect cp_sl04">
		             <select required name="divi_id">
		             <option value="" hidden>選択してください</option>
		             <option value="1">東京</option>
		             <option value="2">東京（開発）</option>
		             <option value="3">宮崎</option>
		             <option value="4">札幌</option>
		             </select>
		         </div>
	     	</div>
	     </div>

	     <div class = "start">
            <div class = "ml30">
                勤務地
                </div>
            <div>
		        <div class="cp_ipselect cp_sl04">
		             <select required name ="workPlace_id">
		             <option value="" hidden>選択してください</option>
		             <option value="1">東京</option>
		             <option value="2">宮崎</option>
		             <option value="3">札幌</option>
		             </select>
		         </div>
		     </div>
	     </div>


	     <div class = "start">
            <div class = "ml30">
                権限付与
                </div>
            <div>
                <input type="radio" name="auth_id" value="0"> 管理者
                <input type="radio" name="auth_id" value="1" checked>通常
            </div>
        </div>
        <button class = "bg-add-submit2" value = "">変更</button>
	</div>
	</form>

    <!-- 以下はフッター -->
    <footer>
    <div class = "footer-conf">
        <div class = "footer-contents">
            <a class="mypens" href="menu.jsp">戻る</a>
        </div>
    </div>
    </footer>
    </body>
</html>