<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
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
                    <span>sample</span>
                    <!-- POSTにLogoutをリクエストする -->
                    <form name="Logout" method="post">
                        <a href="index.jsp" onclick="return confirm('ログアウトします')">ログアウト</a>
                    </form>
                </div>
            </div>
        </div>
    </header>
<form action="/Bteam/NewUser" method="post">

	<div class = "start">
        <div class = "ml30">
            ユーザー名
            </div>
        <div>
            <input type="text" name="emp_name" value="" size="24">
        </div>
    </div>

	<div class = "start">
           <div class = "ml30">
               追加ユーザの社員番号
           </div>
           <div>
               <input type="text" name="emp_num" value="" size="24">
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
	            <option value="" >選択してください</option>
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
		            <option value="" >選択してください</option>
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
            <input type="radio" name="auth_id" value="2"> 管理者
            <input type="radio" name="auth_id" value="1" checked>通常
        </div>
    </div>
<input type="submit" value="新規登録">
</form>
<footer>
    <div class = "footer-conf">
        <div class = "footer-contents">
            <FORM>
				<INPUT type="button" value="戻る" onClick="history.back()">
			</FORM>
        </div>
    </div>
    </footer>
</body>
</html>