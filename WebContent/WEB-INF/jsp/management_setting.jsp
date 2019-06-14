<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
User loginUserName = (User) session.getAttribute("loginUserName");
Boolean management_result = (Boolean)request.getAttribute("managementResult");
Boolean changeUser_result = (Boolean)request.getAttribute("changeUserResult");
Boolean change_result = (Boolean)request.getAttribute("changeResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" >
<link rel="stylesheet" type="text/css" href="StyleManage.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />

<script type="text/javascript">
	function managementStart() {
		var message = "指定したユーザーの情報を変更します、よろしいですか？";
		// OKボタンを押したとき
		if (confirm(message)) {
			return true;
		} else {
			return false;
		}
	}

	function changeUserStart() {
		var message = "指定したユーザーのパスワードを変更します、よろしいですか？";
		// OKボタンを押したとき
		if (confirm(message)) {
			return true;
		} else {
			return false;
		}
	}

	function changeStart() {
		var message = "自身のパスワードを変更します、よろしいですか？";
		// OKボタンを押したとき
		if (confirm(message)) {
			return true;
		} else {
			return false;
		}
	}

	// 成否判定結果
	window.onload = function() {
		// nullチェック
		if (<%= changeUser_result %> != null) {
			// 変更に成功
			if (<%= changeUser_result %> == true) {
				alert("パスワード変更に成功しました");
			} else {
				alert("パスワード変更に失敗しました");
			}
		}
		if (<%= management_result %> != null) {
			// 変更に成功
			if (<%= management_result %> == true) {
				alert("情報変更に成功しました");
			} else {
				alert("情報変更に失敗しました");
			}
		}
		if (<%= change_result %> != null) {
			// 変更に成功
			if (<%= change_result %> == true) {
				alert("パスワード変更に成功しました");
			} else {
				alert("パスワード変更に失敗しました");
			}
		}
	}
</script>

</head>
<body>
<h1>セキワカル</h1>
    <form action="/Bteam/ManagementSetting" method="post" onSubmit="return managementStart()">
<div class = "center_change">
	<div class="main">
		<div class = "top">
            <h2>ユーザー情報変更</h2>
        </div>
	<div class = "start">
            <div class = "marginL30">
                社員番号
                </div>
            <div>
                <input type="text" name="emp_num" value="" size="24" maxlength='7' style="width:170px;" required>
            </div>
        </div>

        <div class = "start">
            <div class = "marginL30">
                ユーザー名
                </div>
            <div>
                <input type="text" name="emp_name" value="" size="24" maxlength='15' style="width:170px;" required>
            </div>
        </div>

        <div class = "start">
            <div class = "marginL30">
                パスワード
                </div>
            <div>
                <input type="password" name="pass" value="" size="24" maxlength='10' style="width:170px;" required>
            </div>
        </div>

        <div class = "start">
            <div class = "marginL30">
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
            <div class = "marginL30">
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
            <div class = "marginL30">
                権限付与
                </div>
            <div>
                <input type="radio" name="auth_id" value="2"> 管理者
                <input type="radio" name="auth_id" value="1" checked>通常
            </div>
        </div>
        <button class = "submit" value = "">変更</button>
	</div>
</div>
</form>


<form action="/Bteam/ManagementUser" method="post" onSubmit="return changeUserStart()">
<div class="center_change">
    <div class = "main">
        <div class = "top">
            <h2>パスワードのみの変更</h2>
        </div>
        <div class = "start">
            <div class = "marginL30">
                社員番号
                </div>
            <div>
                <input type="text" name="emp_num" value="" size="24" pattern="^[0-9]+$" maxlength='7' style="width:170px;" required>
            </div>
        </div>
        <div class = "start">
            <div class = "marginL30">
                パスワード
                </div>
            <div>
                <input type="password" name="new_pass" value="" size="24" pattern="^[0-9A-Za-z]+$" maxlength='10' style="width:170px;">
            </div>
        </div>
        <button class = "submit" value = "">変更</button>
    </div>
</div>
</form>

<form action="/Bteam/SettingUesr" method="post" onSubmit="return changeStart()">
<div class="center_change">
    <div class = "main">
        <div class = "top">
            <h2>自身のパスワード変更</h2>
        </div>

        <div class = "start">
            <div class = "marginL30">
                新しいパスワード
                </div>
            <div>
                <input type="password" name="new_pass" value="" size="24" pattern="^[0-9A-Za-z]+$" maxlength='10' style="width:170px;">
            </div>
        </div>
        <button class = "submit" value = "">変更</button>
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
				        <button type="submit" name="select" value='all' class="menu__link">2019:Bteam</button>
				        </ul>
				        </nav>
				        </section>
                     </ul>
                 </div>
             <div class="info">
                 <div class="logo"><img src="Test_icon.png" alt="写真" width="100px" height="100px"></div><div class="name">
                 <c:out value="${loginUserName.emp_name}" /></div>
                 <div class="quote"></div>
                 <div class="social">
                 </div>

                     <a href="index.jsp" onclick="return confirm('ログアウトします')" >Logout</a>
                     </div>
                 </div>
            </div>

    </body>
</html>