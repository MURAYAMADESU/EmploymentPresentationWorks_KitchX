<%@page import="java.awt.RadialGradientPaint"%>
<%@page import="java.util.concurrent.LinkedTransferQueue"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="communal.dao.UserIdTranslation"%>
<%@ page import="communal.dao.RecipeIdTranslation"%>
<%@ page import="communal.StringAlignment"%>
<%@ page import="communal.ReadFile" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.io.File" %>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	//sessionのデータを読み込む
	String user_id = (String)session.getAttribute("user_id");
	String userLogPath = getServletContext().getRealPath("./UserLog/");
	
	//履歴が存在するかどう確認する。
	File file = new File(userLogPath + user_id + ".txt");
	
	if( !(file.exists()) ){
		request.setAttribute("ERROR", "履歴がありません");
		RequestDispatcher dispatcher = request.getRequestDispatcher("./error.jsp");
		dispatcher.forward(request, response);
	}
	
	String tmp = ReadFile.GetData(userLogPath + user_id + ".txt");		//ユーザのログイン履歴を読み込む
	String[] allRecipeId = tmp.split(",");	//CSVファイル形式のデータを読み込みHashSetでデータの被りをなくす
	
	Set<Integer> numbers = new HashSet<>();
	for(String i : allRecipeId){
		numbers.add(Integer.parseInt(i));
	}
%>
<!DOCTYPE html>
<html lang="jp">

<%@include file="allSource/includeTemplateJspFile/head.jsp"%>

<body>


	<header>
		<!-- トップ_ログインや一言コメント -->
		<div id="topHeader" class="custom-line">
			<div class="flex">
				<p class="tag grid">日々の料理に新しいときめきを。</p>
			</div>
			<div class="flex">
				<%
				UserIdTranslation.Translation(user_id);
				String name = UserIdTranslation.GetUserName(); 
				%>
				<a class="tag grid"><%= name %>さん、おかえりなさい</a>
			</div>
		</div>
		<!-- 検索やMyレシピへのアクセス -->
		<div id="bottomHeader" class="custom-line">
			<div class="flex">
				<div class="flex">
					<a href="./index.jsp"><img
						src="./allSource/image/topIcon/KichX_icon.gif" alt="KichXアイコン"
						class="img"></a>
					<h1 class="grid">KichX</h1>
				</div>
				<div class="center">
					<div>
						<form action="/employmentWorks/search.jsp" method="post" id="formHeader"
							　
							class="flex">
							<input type="text" class="formText" placeholder="鶏肉　キャベツ　にんじん">
							<button type="submit" class="formSubmit">レシピ検索</button>
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
	</header>

	<main>
		<!-- メイン部_右ナビゲーションからレシピ一覧まで -->
		<div id="userLog">
				<h6 id="mainDish">
					<p>最近見たレシピ</p>
				</h6>
				<!-- レシピをくし返し出力する -->
				<%
				for(Integer recipe : numbers){
					
					//レシピIDを元に各種データを手にいれる
					RecipeIdTranslation.Translation(Integer.valueOf(recipe).toString());
					//ユーザIDを元に各種データを手にいれる
					UserIdTranslation.Translation(RecipeIdTranslation.GetUserID());
				%>
				<div class="logAllRecipe">
					<img src="./RecipeFile/<%= recipe %>/recipe_top_image.jpg" alt="料理"
						class="logRecipePhoto">
					<div>
						<form id="writeRecipeName" method="post" 　accept-charset="UTF-8"
							action="/employmentWorks/cuisinedescription">
							<input type="text" value="<%= recipe %>" style="display: none;"
								name="recipe_id">
							<button type="submit" class="allDishTitle">
								<p><%= StringAlignment.AlignmentBigTitle(RecipeIdTranslation.GetRecipeTitle()) %></p>
							</button>
						</form>
						<p class="allDishMaterial"><%= StringAlignment.AlignmentBigIntroductoryEesay(RecipeIdTranslation.GetRecipeIntroductoryEesay()) %></p>
						<p class="allDishDescription"><%= StringAlignment.AlignmentBigMaterial(RecipeIdTranslation.GetRecipeMaterial()) %></p>
						<p class="allDishName">by <%= UserIdTranslation.GetUserName() %>さん</p>
					</div>
				</div>
				<% } %>
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