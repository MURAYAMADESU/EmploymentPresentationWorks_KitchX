<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="jp">

<%@include file="/allSource/includeTemplateJspFile/head.jsp" %>

<body>

    <main id="registerMain">
        <form action="/employmentWorks/register" id="registerForm" method="post">
            <a href="./index.jsp"><img src="allSource/image/topIcon/KichX_icon.gif" alt=""></a>
            <h4 class="errorResult">${ ERROR }</h4><br>
            <div>
                <input type="text" placeholder="ユーザー名" id="registerText" name="name">
            </div>
            <div>
                <input type="text" placeholder="メールアドレス" id="registerText" name="mail">
            </div>
            <div>
                <input type="password" placeholder="パスワードを入力" id="registerPasswd" name="pass">
            </div>
            <a href="./login.jsp">ログインはこちら</a><br>
            <button>登録</button>
        </form>
    </main>

    <!-- フッターの設定 -->
<%@include file="/allSource/includeTemplateJspFile/footer.jsp" %>

</body>

</html>