'use strict';
//非同期処理のためHTMLファイルが全て読み込んだら実行するようにする。
document.addEventListener('DOMContentLoaded', function(){
    //各種変数の定義
    const imageInput = document.getElementById('inputTitleImage');
    const previewImage = document.getElementById('outputTitleImage');
    const writeRecipeImageForm = document.getElementById('writeRecipeImageForm');

    //画像のプレビュー表示
    imageInput.addEventListener('change', function(data) {
        //ファイルオプジェクトで1つ目の画像ファイルを所得する。
        const dataFile = data.target.files[0];
        //FileReaderオブジェクトの生成
        const reader = new FileReader();
        reader.readAsDataURL(dataFile); //読み込んだファイルをDataURLに変換
        reader.onload = function(tmp){    //読み込み後onloadイベントハンドラーで処理を実行
            previewImage.src = tmp.target.result; //srcにDataURLを指定
            previewImage.style.display = 'block';
            writeRecipeImageForm.style.display = 'none';
        }
    });
});