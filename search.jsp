<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.xml.crypto.dsig.keyinfo.RetrievalMethod"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="communal.dao.GetMaxAutoIncrement"%>
<%@ page import="communal.dao.UserIdTranslation"%>
<%@ page import="communal.dao.RecipeIdTranslation"%>
<%@ page import="communal.StringAlignment"%>
<%@ page import="communal.dao.UserIdTranslation"%>
<%@ page import="communal.ReadFile" %>
<%@ page import="communal.dao.ReturnRecipeID" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="communal.dao.DeleteRecipeId" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.io.File" %>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	//パスの所得
	String userLogPath = getServletContext().getRealPath("./UserLog/");

	//sessionのデータを読み込む
	String user_id = (String)session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html lang="jp">

<%@include file="/allSource/includeTemplateJspFile/head.jsp"%>

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
						<form action="./search.jsp" method="post" id="formHeader"
							　
							class="flex">
							<input type="text" class="formText" placeholder="鶏肉　キャベツ　にんじん" value="<%= request.getParameter("searchData") %>" name="searchData">
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
		<div class="mainLeft">
			<!-- メインレシピ記載部 -->
			<div class="mainLeftContent">
				<h6 id="mainDish">
					<a>検索結果</a>
				</h6>
				<!-- レシピをくし返し出力する -->
				<%
				String searchData = request.getParameter("searchData");
				String ReturnRecipeIdTmp = ReturnRecipeID.GetSearch(searchData);
				if(ReturnRecipeIdTmp.isEmpty()){
					request.setAttribute("ERROR", "該当するものがありません");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./error.jsp");
					dispatcher.forward(request, response);
				}
				
				String[] ReturnRecipeID = ReturnRecipeIdTmp.split(",");
				for(String i : ReturnRecipeID){
					
					//レシピIDを元に各種データを手にいれる
					RecipeIdTranslation.Translation(String.valueOf(i));
					//ユーザIDを元に各種データを手にいれる
					UserIdTranslation.Translation(RecipeIdTranslation.GetUserID());
				%>
				<div class="allRecipe">
					<img src="./RecipeFile/<%= i %>/recipe_top_image.jpg" alt="料理"
						class="recipePhoto">
					<div>
						<form id="writeRecipeName" method="post" 　accept-charset="UTF-8"
							action="/employmentWorks/cuisinedescription">
							<input type="text" value="<%= i %>" style="display: none;"
								name="recipe_id">
							<button type="submit" class="allDishTitle">
								<p><%= StringAlignment.AlignmentTitle(RecipeIdTranslation.GetRecipeTitle()) %></p>
							</button>
						</form>
						<p class="allDishMaterial"><%= StringAlignment.AlignmentIntroductoryEesay(RecipeIdTranslation.GetRecipeIntroductoryEesay()) %></p>
						<p class="allDishDescription"><%= StringAlignment.AlignmentMaterial(RecipeIdTranslation.GetRecipeMaterial()) %></p>
						<p class="allDishName">
							by
							<%= UserIdTranslation.GetUserName() %>さん
						</p>
					</div>
				</div>
				<% } %>
			</div>
		</div>


		<!-- トップ右部(常時固定)_カレンダーやおすすめの表示 -->
		<div id="mainRight">
			<!-- おすすめの表示 -->
			<div id="weekRecipe">
				<p class="bold">最近見たレシピ</p>
				<div>
					<a href="/employmentWorks/log">一覧を見る</a> <img
						src="./allSource/image/icon/caret-right-fill.svg" alt="右矢印">
				</div>
			</div>
			<div class="mainRightData">
				<%
				if( user_id == null ){ 
				%>
				<ul>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt=""> <a></a></li>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt="">
						<p>-- 登録後利用可能 --</p></li>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt=""> <a></a></li>
				</ul>
				<%} else {
					//履歴があるか確認
					File file = new File(userLogPath + user_id + ".txt");
					if(file.exists()){

						String tmp = ReadFile.GetData(userLogPath + user_id + ".txt");		//ユーザのログイン履歴を読み込む
						String[] allRecipeId = tmp.split(",");	//CSVファイル形式のデータを読み込みHashSetでデータの被りをなくす
				
						Set<Integer> numbers = new HashSet<>();
						for(String i : allRecipeId){
							numbers.add(Integer.parseInt(i));
							String deleterecipeid = DeleteRecipeId.Get(i);
							if(deleterecipeid == null){
								numbers.remove(Integer.parseInt(i));
							}
						}
						
						Integer count = numbers.size();
						
				
						ArrayList<Integer> arrayList = new ArrayList<>();
						for(Integer recipe : numbers){
							arrayList.add(recipe);
						}
					
						for(Integer i = 0; i < count; i++){
						//レシピIDを元に各種データを手にいれる
						RecipeIdTranslation.Translation(Integer.valueOf(arrayList.get(i)).toString());
						//ユーザIDを元に各種データを手にいれる
						UserIdTranslation.Translation(RecipeIdTranslation.GetUserID());
				%>
				<ul>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt=""> 
							<form method="post" 　accept-charset="UTF-8"
							action="/employmentWorks/cuisinedescription">
							<input type="text" value="<%= arrayList.get(i) %>" style="display: none;"
								name="recipe_id">
							<button type="submit" class="allDishTitle">
								<p><%= StringAlignment.AlignmentSmallTitle(RecipeIdTranslation.GetRecipeTitle()) %></p>
							</button>
						</form>
					</li>
				</ul>
				<% 		}
						
					} else {
				%>
				<ul>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt=""> <a></a></li>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt="">
						<p>-- 閲覧履歴がありません --</p></li>
					<li><img src="./allSource/image/icon/chevron-double-right.svg"
						alt=""> <a></a></li>
				</ul>
				<%
					}
					
				}
				%>
			</div>
		</div>
	</main>




	<!-- フッターの設定 -->
	<%@include file="/allSource/includeTemplateJspFile/footer.jsp"%>
	</div>
</body>
</html>