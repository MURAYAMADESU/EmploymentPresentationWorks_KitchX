<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="js">

	<%@include file="/allSource/includeTemplateJspFile/head.jsp" %>

<body>

    <main id="loginMain">
        <form action="./index.jsp" id="loginForm">
            <img src="./allSource/image/topIcon/KichX_icon.gif" alt=""><br>
            <h4>${ result }</h4>
            <button>ホームに戻る</button>
        </form>
    </main>

    <!-- フッターの設定 -->
    <%@include file="/allSource/includeTemplateJspFile/footer.jsp" %>

</body>

</html>