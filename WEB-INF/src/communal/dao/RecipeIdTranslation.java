package communal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeIdTranslation {

	public RecipeIdTranslation() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//各種変数を宣言する
	private static String user_id = null;
	private static String recipe_title = null;
	private static String recipe_introductory_essay = null;
	private static String recipe_point = null;
	private static String recipe_why= null;
	private static String recipe_material = null;
	private static Integer recipe_image = 0;

	/*
	 * 引数に渡されたUserIDを元に様々なデータを得る
	 * @param recipe_id
	 */
	public static void Translation(String recipe_id) {

		//各種変数の宣言
		String SQL = "select user_id, recipe_title, recipe_introductory_essay, recipe_image, recipe_material, recipe_point, recipe_why from t_recipe where recipe_id = ? "; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベース検索
		try {

			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, recipe_id);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる

			user_id  = rs.getString("user_id");
			recipe_title = rs.getString("recipe_title");
			recipe_introductory_essay = rs.getString("recipe_introductory_essay");
			recipe_material = rs.getString("recipe_material");
			recipe_point  = rs.getString("recipe_point");
			recipe_why = rs.getString("recipe_why");
			recipe_image  = rs.getInt("recipe_image");

		} catch (SQLException e) {
			ERROR.append(e);
		} catch (Exception e) {
			ERROR.append(e);
		} finally {
			//リソースの解放
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					ps.close();
			} catch (SQLException e) {
				ERROR.append(e);
			}

			System.out.print(ERROR.toString());
		}

	}

	public static String GetUserID() {
		return user_id;
	}

	public static String GetRecipeTitle() {
		return recipe_title;
	}

	public static String GetRecipeIntroductoryEesay() {
		return recipe_introductory_essay;
	}
	
	public static String GetRecipeMaterial() {
		return recipe_material;
	}
	
	public static String GetRecipeWhy() {
		return recipe_why;
	}
	
	public static String GetRecipePoint() {
		return recipe_point;
	}
	
	public static Integer GetRecipeImage() {
		return recipe_image;
	}

}
