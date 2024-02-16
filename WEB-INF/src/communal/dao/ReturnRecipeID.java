package communal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class ReturnRecipeID {

	public ReturnRecipeID() {
		// コンストラクター
	}
	
	/**
	 * 
	 */
	public static String Get() {
		//各種変数の宣言
		String SQL =  "select recipe_id from t_recipe where recipe_trash_key = 1"; //実行SQLの格納用
		String result = null;
		Integer resultCount = 0;
		StringBuffer tmpData = new StringBuffer();
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		ArrayList<String> arrayList = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		//データベース検索
		try {
			
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			stmt = con.createStatement(); //SQL文のセット

			rs = stmt.executeQuery(SQL); //SQLの実行

			while (rs.next()) {		//該当するデータを全て取り出す
				arrayList.add(rs.getString("recipe_id"));
			}
			
			//利用可能なレシピIDがいくるあるか
			if(arrayList.size() <= 20) {
				resultCount = arrayList.size();
			} else {
				resultCount = 20;
			}
			
			//値を全てランダムにシャッフル
			Collections.shuffle(arrayList);
			
			//CSV形式にデータを加工する
			for(Integer i = 0; i < resultCount; i++) {
				tmpData.append(arrayList.get(i) + ",");
			}
			
			//余分なデータを取り出して送る。
			result = tmpData.toString();
			result = result.substring(0, result.length()-1);


		} catch (SQLException e) {
			ERROR.append(e);
		} catch (Exception e) {
			ERROR.append(e);
		} finally {
			//リソースの解放
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				ERROR.append(e);
			}
				System.out.print(ERROR.toString());
		}
		
		return result;
	}
	
	/**
	 * 
	 */
	public static String GetSearch(String searchData) {
		
		//各種変数の宣言
		String result = null;	//戻り値
		StringBuffer splitData = new StringBuffer();		//検索データの一時的な保存場所
		Integer resultCount = 0;		//表示レシピ数
		StringBuffer SQL =  new StringBuffer();  //実行SQLの格納用
		StringBuffer resultData = new StringBuffer();	//文字列の一時的な保存場所
		StringBuffer ERROR = new StringBuffer(); //ERROR格納用
		ArrayList<String> listData = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//引数を元に検索用を分ける　"SELECT recipe_id FROM t_recipe WHERE recipe_material  LIKE '%" + searchData + "%' or recipe_title  LIKE '%" + searchData + "%'"

		try {
			
			for(String tmp : searchData.split(" |　")) {
				splitData.append(tmp + ",");
			}
			
			//SQL分の組み立て
			SQL.append("SELECT recipe_id FROM t_recipe WHERE");
			for(String tmp : (splitData.toString()).split(",")) {
				SQL.append(" recipe_material  LIKE '%" + tmp + "%' or recipe_title  LIKE '%" + tmp + "%' or");
			}	
			
			con = DatabaseConnectionManager.coonnection(); //データベースのコネクションを所得

			stmt = con.createStatement(); //SQL文のセット

			rs = stmt.executeQuery((SQL.toString()).substring(0, SQL.length()-2)); //SQLの実行

			while (rs.next()) {		//該当するデータを全て取り出す
				listData.add(rs.getString("recipe_id"));
			}
			
			//利用可能なレシピIDがいくるあるか
			if(listData.size() <= 15) {
				resultCount = listData.size();
			} else {
				resultCount = 15;
			}
			
			//値を全てランダムにシャッフル
			Collections.shuffle(listData);
			
			//CSV形式にデータを加工する
			for(Integer i = 0; i < resultCount; i++) {
				resultData.append(listData.get(i) + ",");
			}
			
			//余分なデータを取り出して送る。
			result = resultData.toString();
			result = result.substring(0, result.length()-1);


		} catch (SQLException e) {
			ERROR.append(e);
		} catch (Exception e) {
			ERROR.append(e);
		} finally {
			//リソースの解放
			try {
				if (rs != null)
					rs.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				ERROR.append(e);
			}
				System.out.println(ERROR.toString());
		}
		
		return result;
	}

}
