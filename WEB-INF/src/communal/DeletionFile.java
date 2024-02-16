package communal;

import java.io.File;

public class DeletionFile {

	public DeletionFile() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	/**
	 * 渡されたパスのファイルを削除する
	 * @param path
	 */
	public static void Do(String path) {
		File file = new File(path);
		file.delete();
	}

}
