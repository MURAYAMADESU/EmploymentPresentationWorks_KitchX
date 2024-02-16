'use strict';

//windowスクロールイベント
window.addEventListener('scroll', function(){
    const eventTarget = document.getElementById('mainRight');   //id名を所得
    let scroll = window.scrollY;    //スクロール量を所得

    //300pxのスクロールを基準にイベントの発生
    if(scroll >= 150){
        eventTarget.classList.add('scrollEvent');
    }else{
        eventTarget.classList.remove('scrollEvent');
    }
});