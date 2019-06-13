<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
User loginUserName = (User) session.getAttribute("loginUserName");
Boolean result = (Boolean)request.getAttribute("deleteResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="StyleManage.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />

<title>ユーザー削除</title>

<script type="text/javascript">
	function deleteStart() {
		var message = "削除を実行します、よろしいですか？";
		// OKボタンを押したとき
		if (confirm(message)) {
			return true;
		} else {
			return false;
		}
	}
	// 削除の成否判定結果
	window.onload = function() {
		// nullチェック
		if (<%= result %> != null) {
			// 削除に成功
			if (<%= result %> == true) {
				alert("削除に成功しました");
			} else {
				alert("削除に失敗しました");
			}
		}
	}
</script>

</head>
<body>
<form action="/Bteam/DeleteUser" method="post" onSubmit="return deleteStart()">
<div class = "center_change">
	<div class="main">
		<div class = "top">
            <h2>ユーザー削除</h2>
        </div>
	<div class = "start">
        <div class = "MarginL30">
             削除するユーザの社員番号
            </div>
	            <div>
	                <input type="text" name="emp_num" value="" size="24" pattern="^[0-9]+$">
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
	</div>
</form>
<div>
    <div id="nav" class="nav">
    	<div class="icon">
    	<ul>
            <li><a title="TOPへ" href="/Bteam/ManagementMenu" ><i class="fa fa-home "></i></a></li>
            <li><a title="新規ユーザー登録" href="/Bteam/NewUser"><i class="fa far fa-address-card "></i></a></li>
            <li><a title="ユーザー削除へ" href="/Bteam/DeleteUser"><i class="fas fa-eraser "></i></a></li>
            <li><a title="ユーザー管理へ" href="/Bteam/ManagementSetting"><i class="fa fa-cog "></i></a></li>
        </ul>
        </div>
        <div class="text">
        <ul>
        <section>
        <nav role="navigation">
        <ul class="menu__list">
        <li class="menu__item">
        <button type="submit" name="select" value='all' class="menu__link">All</button>
        </li>
        <li class="menu__item">
        <button type="submit" name="select" value='tokyo' class="menu__link">Tokyo</button>
        </li>
        <li class="menu__item">
        <button type="submit" name="select" value='tokyo_make' class="menu__link">Tokyo_make</button>
        </li>
        <li class="menu__item">
        <button type="submit" name="select" value='sapporo' class="menu__link">sapporo</button>
        </li>
        <li class="menu__item">
        <button type="submit" name="select" value='miyazaki' class="menu__link">miyazaki</button>
        </li>
        </ul>
        </nav>
        </section>
        </ul>
    </div>
<div class="info">
    <div class="logo"><img src="Test_icon.png" alt="写真" width="100px" height="100px"></div><div class="name"><c:out value="${loginUserName.emp_name}" /></div>
    <div class="quote"></div>
    <div class="social"></div>

        <a href="index.jsp" onclick="return confirm('ログアウトします')" value="Logout">Logout</a>
         </div>
     </div>
</div>
</body>
</html>