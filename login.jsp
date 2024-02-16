<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="jp">

<%@include file="allSource/includeTemplateJspFile/head.jsp" %>

<body>

    <main id="loginMain">
        <form action="/employmentWorks/login" id="loginForm" method="post">
            <a href="./index.jsp"><img src="allSource/image/topIcon/KichX_icon.gif" alt=""></a>
            <div>
            	<h4 class="errorResult">${ result }</h4><br>
                <input type="text" placeholder="メールアドレスまたは電話番号" id="loginText" name="mail">
            </div>
            <div>
                <input type="password" placeholder="パスワードを入力" id="loginPasswd" name="pass">
            </div>
            <a href="./register.jsp">新規登録はこちら</a><br>
            <button>ログイン</button>
        </form>
    </main>

    <!-- フッターの設定 -->
	<%@include file="allSource/includeTemplateJspFile/footer.jsp" %>

</body>

</html>