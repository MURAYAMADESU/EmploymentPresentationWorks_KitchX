'use strict';

/**
 * 非同期処理のためにDOMContentLoadedイベントを使用
 */
document.addEventListener('DOMContentLoaded', event => {
    document.querySelector('.heartButton').addEventListener('click', function () {
        const image = document.querySelector('.heart');
        image.src = './image/icon/heart-fill_red.svg';
    });
});

/**
 * レシピ詳細ページ用
 */
document.addEventListener('DOMContentLoaded', event => {
    document.querySelector('.heartButton').addEventListener('click', function () {
        const image = document.querySelector('.cuisineDescriptionHeart');
        image.src = './image/icon/heart-fill_red.svg';
    });
});