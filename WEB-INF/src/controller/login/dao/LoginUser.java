package controller.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class LoginUser {
	/*
	 * 引数に渡されたメールとパスワードからログイン可能か判断する
	 * 可能な場合"null"が渡され,不可な場合エラーが渡される。
	 * @param mail メール
	 * @param pass パスワード
	 * @return null or ErrorCode
	 */
	public static String login(String mail, String pass){

		//各種変数の宣言
		String SQL = "select user_id from t_user where mail = ? and user_pass = ? "; //実行SQLの格納用
		String result = null;
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//引数にデータが入っているかの確認
		try {
			if (mail == null || pass == null) {
				throw new IllegalAccessException();
			}
		} catch (IllegalAccessException e) {
			ERROR.append(e);
		}

		//データベース検索
		try {
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			ps = con.prepareStatement(SQL); //SQL文のセット
			ps.setString(1, mail);
			ps.setString(2, pass);

			rs = ps.executeQuery(); //SQLの実行

			rs.next(); //カーソルを移動させる
			result = rs.getString("user_id");

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
		}

		//データの送信必要であれエラーをコンソールに出力する。
		System.out.print(ERROR);
		return result;
	}

}
