<%@page import="communal.dao.UserIdTranslation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="communal.dao.UserIdTranslation"%>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%
	//sessionのデータを読み込む
	String user_id = (String)session.getAttribute("user_id");
%>
<header>
        <!-- トップ_ログインや一言コメント -->
        <div id="topHeader" class="custom-line">
            <div class="flex">
                <p class="tag grid">日々の料理に新しいときめきを。</p>
            </div>
            <div class="flex">
            	<% if (user_id == null) { %>
                	<a href="./login.jsp" class="taga">ログイン</a>
                	<a href="./register.jsp" class="taga">新規登録</a>
                <% }else{
                	UserIdTranslation.Translation(user_id);
                	String name = UserIdTranslation.GetUserName(); 
                %>
                	<p class="grid"><%= name %>さん、おかえりなさい</p>
                <% } %>
            </div>
        </div>


        <!-- 検索やMyレシピへのアクセス -->
        <div id="bottomHeader" class="custom-line">
            <div class="flex">
                <div class="flex">
                    <a href="./index.jsp"><img src="./allSource/image/topIcon/KichX_icon.gif" alt="KichXアイコン" class="img"></a>
                    <h1 class="grid">KichX</h1>
                </div>
                <div class="center">
                    <div>
                        <form action="./search.jsp" method="post" id="formHeader" class="flex">
                            <input type="text" class="formText" placeholder="鶏肉・キャベツ・にんじん・豚バラ" name="searchData">
                            <button type="submit" class="formSubmit">
                                レシピ検索
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="flex">
				<a href="/employmentWorks/myrecipe" id="myRecipe" class="grid flex"> <img
					src="./allSource/image/icon/archive.svg" alt="ファイル" class="icon">
					<p>Myレシピ</p>
				</a>
                <a href="./writerecipe.jsp" id="WriteRecipe" class="grid flex">
                    <img src="./allSource/image/icon/brush.svg" alt="ペン" class="icon">
                    <p>レシピを書く</p>
                </a>
            </div>
        </div>
        </div>
    </header>