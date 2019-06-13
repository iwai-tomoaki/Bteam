<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
User loginUserName = (User) session.getAttribute("loginUserName");
%>
<?$changeup = $_POST['changeup'] + 1;?>
<?$changedown = $_POST['changedown'] + 1;?>
<%--aaaaaaaaa --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="StyleMenu.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
<title>パスワード再設定</title>
</head>
<body>
<h1>座席管理システム</h1>
<form action="/Bteam/SettingUesr" method="post" >
<div class="center_change">
    <div class = "main">
        <div class = "top">
            <h2>パスワード変更</h2>
        </div>

        <div class = "start">
            <div class = "ml30">
                新しいパスワード
                </div>
            <div>
                <input type="password" name="new_pass" value="" size="24">
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
                   <li><a title="TOPへ" title="TOPへ"  href="javascript:history.back()" ><i class="fa fa-home "></i></a></li>
                   <li><a title="パスワード再設定" href="/Bteam/ManagementSetting"><i class="fa fa-cog "></i></a></li>
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
                 <div class="logo"><img src="Test_icon.png" alt="写真" width="100px" height="100px"></div><div class="name">
                 <c:out value="${loginUserName.emp_name}" /></div>
                 <div class="quote"></div>
                 <div class="social">
                 </div>

                     <a href="index.jsp" onclick="return confirm('ログアウトします')" value="Logout">Logout</a>
                     </div>
                 </div>
            </div>

</body>
</html>