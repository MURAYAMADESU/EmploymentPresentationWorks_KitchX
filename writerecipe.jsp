<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="communal.dao.UserIdTranslation"%>
<% //文字エンコードの指定
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	//sessionのデータを読み込む
	String user_id = (String)session.getAttribute("user_id");
	
	if (user_id == null) {
		response.sendRedirect("/employmentWorks/login.jsp");
	}
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
							<input type="text" class="formText" placeholder="鶏肉　キャベツ　にんじん" name="searchData">
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





	<main id="writeRecipeMain">
		<div id="writeRecipeMainData">
			<div id="writeRecipeMainDataTop">
				<h3>レシピを書く</h3>
				<p>あなたのオリジナルレシピ、家庭の味をみんなにシェア！</p>
			</div>
			<h3 class="errorResult">${ ERROR }</h3>
			<form id="writeRecipeName" method="post" 　accept-charset="UTF-8"
				enctype="multipart/form-data" action="/employmentWorks/writerecipe">
				<div id="writeRecipeTitle">
					<h5>レシピのタイトル</h5>
					<p># 下記全てあとから変更可能です(レシピの作り方部分以外全て挿入必須です)</p>
				</div>
				<!-- レシピタイトル部 -->
				<input type="text" id="writeRecipeTitleForm"
					placeholder="例: 30分でOK! 主婦の味方ナスの味噌煮" name="recipe_title">
				<!-- レシピトップ画像とトップ説明部 -->
				<h5 id="writeRecipeDescriptionTopTitle">トップ説明</h5>
				<div id="writeRecipeImageInputForm">
					<div id="writeRecipeImageForm">
						<!-- 写真挿入 -->
						<label id="imageForm"> <img
							src="./allSource/image/icon/camera.svg" alt="写真"> <input
							type="file" accept="image/*" name="recipe_top_image"
							id="inputTitleImage">
						</label>
					</div>
					<div id="writeRecipeDescriptionForm">
						<!-- 紹介文と材料 -->
						<p>レシピの紹介文(検索画面で表示されます)</p>
						<textarea wrap="soft" id="writeRecipeDescriptionTitle"
							name="recipe_introductory_essay"
							placeholder="ナスを使ったシンプルでおいしい家庭料理！ナスを味噌ベースのソースで煮込み、甘辛く仕上げま、ナスのジューシーさと味噌のコクが絶妙にマッチした、簡単で美味しい料理！！"></textarea>
						<p>材料(2人分)</p>
						<textarea wrap="soft" id="writeRecipeDescriptionMaterial"
							name="recipe_material"
							placeholder="ナス: 2～3本（中サイズ） 味噌: 2～3大さじ 砂糖: 少々 酒: 1～2大さじ みりん: 1～2大さじ しょうゆ: 少々 だし汁または水: 適量 ごま油: 少々 ねぎや生姜のみじん切り、ごまなど"></textarea>
					</div>
				</div>


				<!-- レシピの作り方部 -->
				<div>
					<div id="writeRecipeImageDescriptionTitle">
						<h5>レシピの作り方</h5>
						<p>作り方を入力してください(説明文を挿入する場合、写真も必須です)</p>
					</div>
				</div>
				<!-- 一行につき4マス -->
				<div class="writeRecipeImageDescriptionBlok">
					<p>1</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_1">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_1"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>2</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_2">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_2"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>3</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_3">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_3"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>4</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_4">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_4"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>5</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_5">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_5"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>6</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_6">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_6"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>7</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_7">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_7"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>8</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_8">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_8"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>9</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_9">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_9"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>10</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_10">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_10"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>11</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_11">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_11"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>12</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_12">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_12"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>13</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_13">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_13"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>14</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_14">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_14"></textarea>
				</div>
				<div class="writeRecipeImageDescriptionBlok">
					<p>15</p>
					<input type="file" accept="image/*"
						name="inputRecipeDescriptionImage_15">
					<textarea wrap="soft" placeholder="写真の説明を記入してください"
						name="inputRecipeDescriptionText_15"></textarea>
				</div>

				<!-- 各種コメント -->
				<div id="writeRecipeComment">
					<div id="point">
						<h5>ポイント</h5>
						<textarea wrap="soft" name="recipe_point"
							placeholder="自由な時間に限りがある主婦の皆さんでも簡単にできる、料理を作りました。味が濃いので熱中症の予防なります。お酒のお供にも最適な一品です！"></textarea>
					</div>
					<div id="why">
						<h5>きっかけ</h5>
						<textarea wrap="soft" name="recipe_why"
							placeholder="私のお気に入り料理を皆さんにも食べて欲しかったので。"></textarea>
					</div>
				</div>


				<!-- 送信ボタン -->
				<button id="writeRecipeSubmitButton">
					<p>レシピを投稿する</p>
				</button>
			</form>
		</div>
	</main>




	<!-- フッターの設定 -->
	<%@include file="./allSource/includeTemplateJspFile/footer.jsp"%>

</body>

</html>