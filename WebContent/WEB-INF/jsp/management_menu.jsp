<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%--aaaaaaaaa --%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" >
<link rel="stylesheet" href="StyleManage.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
<title>管理者メニュー</title>
</head>
<body>
    <div class="fontsize">
    	<h1>セキワカル</h1>
    </div>
    <form action="/Bteam/Menu" method="post">
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
                    <div class="social">
                    </div>
                    <div class="icon_center">
                    	<a href="/Bteam/Logout" onclick="return confirm('ログアウトします')">Logout</a>
                    </div>

                </div>
            </div>
            <div class="testsize25">
            	<table class = "center">		<!-- tableを設定して縦に綺麗に並ぶようにする -->
                    <tr><th>名前</th><th>社員番号</th><th>所属部署</th><th>在席状況</th></tr>
                    <c:forEach var="myUser" items="${my_user}">		<!-- セッションスコープに保存したuserListの数分ループ実行 -->
                    <tr><th><c:out value="${myUser.emp_name}" /></th>		<!-- userListに保存したUserの名前情報を一行ずつ出力 -->
                        <th><c:out value="${myUser.emp_num}" /></th>
						<th><c:out value="${myUser.divi_name}" /></th>
                        <!-- データベース内の在席情報の値を参照、不在なら==0、在席なら==1の処理が実行される、ボタンに表示しているので押すと在席が切り替わる -->
                        <c:choose>
                        <c:when test="${myUser.pres_status == 0}"><th><button class="leaving" type="submit" name="change" value="${myUser.emp_num}">不在</button></th></c:when>
                        <c:when test="${myUser.pres_status == 1}"><th><button class="enrollment" type="submit" name="change" value="${myUser.emp_num}">在席</button></th></c:when>
                        </c:choose>
                    </c:forEach>
                </table>
            </div>
            <c:if test="${not empty userList}">
                <table class = "center">
                    <tr><th>名前</th><th>社員番号</th><th>所属部署</th><th>在席状況</th></tr>
                    <c:forEach var="user_List" items="${userList}">
                    <tr><th><c:out value="${user_List.emp_name}" /></th>
                        <th><c:out value="${user_List.emp_num}" /></th>
						<th><c:out value="${user_List.divi_name}" /></th>
                        <c:choose>
                        <c:when test="${user_List.pres_status == 0}"><th><button class="leaving" type="submit" name="change" value="${user_List.emp_num}">不在</button></th></c:when>
                        <c:when test="${user_List.pres_status == 1}"><th><button class="enrollment" type="submit" name="change" value="${user_List.emp_num}">在席</button></th></c:when>
                        </c:choose>
                    </c:forEach>
                </table>
            </c:if>
    </div>
</form>
</body>
</html>