package controller.writerecipe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class SerchUpLoadImageNum {

    public SerchUpLoadImageNum() {
        // コンストラクター
    }

    public static Integer ReturnNum(HttpServletRequest rq) throws ServletException, IOException {

        // 各種変数の定義
        Integer returnNum = 0;
        Part imageData = null;

        for (Integer i = 1; i <= 10; i++) {
            // リクエストから写真データを取り出す。
            imageData = rq.getPart("inputRecipeDescriptionImage_" + i.toString());
            if (imageData != null) {
                String fileName = imageData.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) { // データが入っているか判定する
                    returnNum++;
                }
            }
        }
        return returnNum;
    }
}
