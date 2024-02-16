package communal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

	public WriteFile() {
		//コンストラクター
	}
/**
 * 指定したパスにファイルを作成してデータを書き込むメゾット
 * @param path	ファイルを保存するパス
 * @param data	ファイルに書き込むデータ
 * @throws IOException	返される可能性のあるエラー
 */
	public static void Write(String data, String path) throws IOException{
		//記入ファイルの選択と書き込みデータの読み込み
		FileWriter fileWriter = new FileWriter(path, false);		//注 falseのめ追記は不可
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(data);		//データの書き込み
		bufferedWriter.close(); 		//ファイルを閉じる
	}
	
	/**
	 * 指定したパスにファイルを作成してデータを書き込むメゾット(追記可能)
	 * @param path	ファイルを保存するパス
	 * @param data	ファイルに書き込むデータ
	 * @throws IOException	返される可能性のあるエラー
	 */
		public static void WritePS(String data, String path) throws IOException{
			//記入ファイルの選択と書き込みデータの読み込み
			FileWriter fileWriter = new FileWriter(path, true);	
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(data);		//データの書き込み
			bufferedWriter.close(); 		//ファイルを閉じる
		}
}
