<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%	
	//アカウントが作成されたの確認プログラム
	String comment = null;
	String ERROR = (request.getAttribute("ERROR")).toString();
	if(ERROR == null){
		comment = "エラーが発生しました。";
	}else{
		comment = "登録が完了しました";
	}
%>

<!DOCTYPE html>
<html lang="jp">

<%@include file="./allSource/includeTemplateJspFile/head.jsp" %>

<body>

    <main id="registerMain">
        <form action="./index.jsp" id="registerForm">
            <img src="allSource/image/topIcon/KichX_icon.gif" alt=""><br>
            <h2><%= comment %></h2><br>
            <button>ホーム画面へ</button><br>
            <p><%= ERROR %></p>
        </form>
    </main>

    <!-- フッターの設定 -->
<%@include file="allSource/includeTemplateJspFile/footer.jsp" %>

</body>

</html>