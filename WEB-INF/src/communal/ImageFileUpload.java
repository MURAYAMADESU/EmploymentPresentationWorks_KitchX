package communal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class ImageFileUpload {

	public ImageFileUpload() {
		// コンストラクター
	}
	
	/*
	 *	指定されたディレクトリーにフォーム写真を保存するメゾット
	 * @param HttpServletRequest
	 * @param String input/fileの識別キー
	 * @param String SavePath
	 */
	public static void UpLoad(HttpServletRequest rq, String formImageName, String path) throws IOException, ServletException{
		Part imageData = rq.getPart(formImageName);	//写真データを所得
		imageData.write(path + "/" + formImageName + ".jpg");
	}

	/*
	 *	指定されたディレクトリーにrecipe_top_imageと言う名前で写真を保存するメゾット
	 * @param HttpServletRequest
	 * @param String input/fileの識別キー
	 * @param String SavePath
	 */
	public static void UpLoadTop(HttpServletRequest rq, String path) throws IOException, ServletException{
		Part imageData = rq.getPart("recipe_top_image");	//写真データを所得
		imageData.write(path + "/recipe_top_image.jpg" );
	}
}
