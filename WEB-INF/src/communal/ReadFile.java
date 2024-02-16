package communal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

	public ReadFile() {
		// コンストラクター
	}

	public static String GetData(String path) {
		
		String tmp = null;
		StringBuffer tmpdata = new StringBuffer();
		
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));		//ファイルの読み込み
			while ((tmp = br.readLine()) != null) {
				tmpdata.append(tmp);
			}
			br.close(); 	//ファイルを閉じる
		} catch (Exception e) {
			System.out.print(e);
		}
		String result = tmpdata.toString();;
		return result;
	}
	
}
