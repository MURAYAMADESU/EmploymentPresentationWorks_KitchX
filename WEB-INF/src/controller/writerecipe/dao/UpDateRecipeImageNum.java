package controller.writerecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import communal.dao.DatabaseConnectionManager;

public class UpDateRecipeImageNum {

	public UpDateRecipeImageNum() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
		
		/**
		 * セットされたデータを元にSQL分を実行するためのメゾット
		 * @return 全て処理を完了させた場合 null そうでない場合エラーコードを返す。
		 */
		public static void upDateImageNum(Integer imageNum, String recipe_id) {
			//各種変数の宣言
			Connection con = null;
			PreparedStatement pre = null;
			StringBuffer ERROR = new StringBuffer();
			String SQL = "UPDATE t_recipe SET recipe_image = ? WHERE recipe_id = ?";
			
			//SQL文の実行
			try {
				
				//実行準備
				con = DatabaseConnectionManager.coonnection();
				pre = con.prepareStatement(SQL);
				
				//SQL文にValueを追加する。
				pre.setString(1, imageNum.toString());
				pre.setString(2, recipe_id.toString());
				
				//SQLの実行
				pre.executeUpdate();
				
				//エラーの受け取りと実行
			} catch (SQLException e) {
				ERROR.append(e);
			} catch (Exception e) {
				ERROR.append(e);
			}finally {
				//各種オブジェクトのクローズ(片付け)
				try {
					if (pre != null) pre.close();
					if (con != null) con.close();
				}catch (SQLException e) {
					ERROR.append(e);
				}
				
				//実行結果の出力
				if(ERROR != null) {
					System.out.print(ERROR.toString());
				}
				
			}
			
		}

	}
