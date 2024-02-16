<%@page import="java.awt.RadialGradientPaint"%>
<%@page import="java.util.concurrent.LinkedTransferQueue"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="communal.dao.RecipeIdTranslation"%>
<%@ page import="communal.dao.UserIdTranslation"%>
<%@ page import="communal.ReadFile" %>
<%@ page import="communal.StringAlignment" %>
<%@ page import="communal.WriteFile" %>
<%@ page import="java.io.File" %>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	//各種変数の宣言
	String userid = (String)session.getAttribute("user_id");
	String recipe_id = request.getParameter("recipe_id");	//レシピIDを受け取る
	String path = getServletContext().getRealPath("./RecipeFile/");
	String userLogPath = getServletContext().getRealPath("./UserLog");
	String recipeDescriptionText = null;
	File file = new File(userLogPath + "/" + userid + ".txt");
	
	//ログイン済みのユーザであれば履歴を残す
	if(userid != null){
		if(file.exists()){
			WriteFile.WritePS("," + recipe_id , userLogPath + "/" + userid + ".txt");
		}else{
			WriteFile.WritePS(recipe_id , userLogPath + "/" + userid + ".txt");
		}
	}
	 
	//レシピIDから各種データを手にいれる
	RecipeIdTranslation.Translation(recipe_id);
%>
<!DOCTYPE html>
<html lang="jp">

<%@include file="allSource/includeTemplateJspFile/head.jsp"%>

<body>
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
                            <input type="text" class="formText" placeholder="鶏肉　キャベツ　にんじん" name="searchData">
                            <button type="submit" class="formSubmit">
                                レシピ検索
                            </button>
                        </form>
                    </div>
                </div>
            </div>
			<div class="flex">
					 <a href="./index.jsp" id="WriteRecipe" class="grid flex"> <img
					src="./allSource/image/icon/house-fill.svg" alt="家" class="icon">
					<p>ホームに戻る</p>
				</a>
			</div>
        </div>
        </div>
    </header>

	<main id="writeRecipeMain">
		<div id="writeRecipeMainData">
			<form id="writeRecipeName" method="post" accept-charset="UTF-8"
				enctype="multipart/form-data" action="/employmentWorks/writerecipe" style="margin-top: 1vw;">
				<div id="writeRecipeTitle">
					<h5>レシピのタイトル</h5>
				</div>
				<!-- レシピタイトル部 -->
				<input type="text" id="writeRecipeTitleForm"
					placeholder="例: 30分でOK! 主婦の味方ナスの味噌煮"
					value="<%= RecipeIdTranslation.GetRecipeTitle() %>" disabled>
				<!-- レシピトップ画像とトップ説明部 -->
				<h5 id="writeRecipeDescriptionTopTitle">トップ説明</h5>
				<div id="writeRecipeImageInputForm">
					<div id="writeRecipeImageForm">
						<img src="./RecipeFile/<%= recipe_id %>/recipe_top_image.jpg"
							alt="写真" id="imageForm">
					</div>
					<img id="outputTitleImage">
					<!-- 写真を追加した時に追加した写真を表示させるimgタグ -->
					<div id="writeRecipeDescriptionForm">
						<p>レシピの紹介文</p>
						<textarea wrap="soft" id="writeRecipeDescriptionTitle"
							name="recipe_introductory_essay" disabled><%= RecipeIdTranslation.GetRecipeIntroductoryEesay() %></textarea>
						<p>材料(2人分)</p>
						<textarea wrap="soft" id="writeRecipeDescriptionMaterial"
							name="recipe_material" disabled><%= RecipeIdTranslation.GetRecipeMaterial() %></textarea>
					</div>
				</div>


				<!-- レシピの作り方部 -->
				<div>
					<div id="writeRecipeImageDescriptionTitle">
						<h5>レシピの作り方</h5>
					</div>
				</div>
				<!-- 一行につき4マス -->
				<% for(Integer i = 1; i <= RecipeIdTranslation.GetRecipeImage(); i++) { %>
				<div class="inputRecipeDescriptionImage">
					<p><%= i %></p>
					<img
						src="./RecipeFile/<%= recipe_id %>/inputRecipeDescriptionImage_<%= i %>.jpg"
						alt="料理写真">
						<% recipeDescriptionText = ReadFile.GetData(path + recipe_id + "/inputRecipeDescriptionText_" + i + ".txt"); %>
					<textarea wrap="soft" disabled><%= recipeDescriptionText %></textarea>
				</div>
				<% } %>
				<!-- 各種コメント -->
				<div id="writeRecipeComment">
					<div id="point">
						<h5>ポイント</h5>
						<textarea wrap="soft" disabled><%= RecipeIdTranslation.GetRecipePoint() %></textarea>
					</div>
					<div id="why">
						<h5>きっかけ</h5>
						<textarea wrap="soft" disabled><%= RecipeIdTranslation.GetRecipeWhy() %></textarea>
					</div>
				</div>
			</form>
		</div>
	</main>




	<!-- フッターの設定 -->
	<footer>
		<div id="copyright">copyright © 2023 MurayamaKousuke. All rights
			reserved.</div>
	</footer>
	</div>
</body>

</html>