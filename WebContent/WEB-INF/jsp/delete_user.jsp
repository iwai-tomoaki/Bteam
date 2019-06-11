<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除</title>
</head>
<body>
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
        </div>
    </header>
<form action="/Bteam/DeleteUser" method="post">
	<div class = "main">
	        <div class = "top">
	            <h2>ユーザー削除</h2>
	        </div>

	        <div class = "start">
	            <div class = "ml30">
	                削除するユーザの社員番号
	                </div>
	            <div>
	                <input type="text" name="emp_num" value="" size="24">
	            </div>
	        </div>


	        <div class="submit">
	            <button type="submit" name="pass_cust" value="値">削除</button>
	        </div>

	        <div class = "red_text">
	            ※これは強制的にユーザーを削除します。<br>
	              削除しても問題のないユーザーか再度一度確認してください。
	        </div>
	    </div>
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