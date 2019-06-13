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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="StyleMenu.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
</head>
<body>
    <h1>座席管理システム</h1>
    <form action="/Bteam/Menu" method="post">
    <div>
        <div id="nav" class="nav">
                <div class="icon">
                	<ul>
                        <li><a title="TOPへ" href="#"><i class="fa fa-home "></i></a></li>
                        <li><a title="パスワード再設定" href="/Bteam/SettingUser"><i class="fa fa-cog "></i></a></li>
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

                        <a href="index.jsp" onclick="return confirm('ログアウトします')" value="Logout">Logout</a>

                </div>
            </div>
            <c:if test="${not empty userList}">		<!-- ログインした直後はuserListが空なので付けないとエラーが発生する、部署を選択すればuserListに値が入るのでif文内が実行される、userListはMenu.javaのセッションスコープから取得 -->
                <table class = "center">		<!-- tableを設定して縦に綺麗に並ぶようにする -->
                    <tr><th>名前</th><th>社員番号</th><th>在籍</th></tr>
                    <c:forEach var="user_List" items="${userList}">		<!-- セッションスコープに保存したuserListの数分ループ実行 -->
                    <tr><th><c:out value="${user_List.emp_name}" /></th>		<!-- userListに保存したUserの名前情報を一行ずつ出力 -->
                        <th><c:out value="${user_List.emp_num}" /></th>
                        <c:choose>
                        <c:when test="${user_List.pres_status == 0}"><th><button type="submit" name="changeup" value="${user_List.emp_num}">不在</button></th></c:when>
                        <c:when test="${user_List.pres_status == 1}"><th><button type="submit" name="changedown" value="${user_List.emp_num}">在席</button></th></c:when>
                        </c:choose>
                    </c:forEach>
                </table>
            </c:if>
    </div>
</form>
</body>
</html>