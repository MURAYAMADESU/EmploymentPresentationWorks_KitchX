package communal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DleteRecipe {

	public DleteRecipe() {
		// コンストラクター
	}

	/*
	 * 引数に渡されたユーザIDとレシピ名を検索しすでに同一のものが登録されていないか確認する。
	 * @param String user_id
	 * @param Strign recipe_name
	 * @return 同一のものがあれば"recipe_id"なければ"null"
	 */
	public static void Dlete(String recipe_id) {
		//各種変数の宣言
		String SQL = "delete from t_recipe where recipe_id = ?"; //実行SQLの格納用
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;

		//データベース検索
		try {

			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, recipe_id);

			ps.executeUpdate(); //SQLの実行
			
		} catch (SQLException e) {
			ERROR.append(e);
		} catch (Exception e) {
			ERROR.append(e);
		} finally {
			//リソースの解放
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					ps.close();
			} catch (SQLException e) {
				ERROR.append(e);
			}
		}
	}
}
