package communal;

import java.io.File;
import java.io.IOException;

public class DirOperation {

	static String path = "/Users/macbook_m1/workspace/workspace/employmentWorks/RecipeFile/";
	
	public DirOperation() {
		// コンストラクター
	}

	public  static void CreateFile(String dirName, String fileName) throws IOException{
		File file = new File(path + dirName + "/" + fileName + ".txt");		//作成するファイルのパス
		file.createNewFile();	//ファイルの作成
	}
	
	public static void CreateDir(String dirName) throws IOException {
		File dir = new File(dirName);	//作成するディレクトリーのパス
		dir.mkdir(); 	//ディレクトリーの作成
	}
	
}
