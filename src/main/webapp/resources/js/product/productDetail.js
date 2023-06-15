console.log("productDetail JS is loaded");

//돋보기--------------------------------------------------------
$(function () {
 
    var target = $('.target');
    //1
    var zoom = target.data('zoom');
 
    $(".product-imageArea")
        .on('mousemove', magnify)
        .prepend("<div class='magnifier'></div>")
        .children('.magnifier').css({
            "background": "url('" + target.attr("src") + "') no-repeat",
            // 2
            "background-size": target.width() * zoom + "px " + target.height() * zoom+ "px"
        });
 
    var magnifier = $('.magnifier');
 
    function magnify(e) {
 
        // 마우스 위치에서 .magnify의 위치를 차감해 컨테이너에 대한 마우스 좌표를 얻는다.
        var mouseX = e.pageX - $(this).offset().left;
        var mouseY = e.pageY - $(this).offset().top;
 
        // 컨테이너 밖으로 마우스가 벗어나면 돋보기를 없앤다.
        if (mouseX < $(this).width()*0.8 && mouseY < $(this).height() && mouseX > 0 && mouseY > 0) {
            magnifier.fadeIn(100);
        } else {
            magnifier.fadeOut(100);
        }
 
        //돋보기가 존재할 때
        if (magnifier.is(":visible")) {
 
            // 3
            var rx = -(mouseX * zoom - magnifier.width() /2 );
            var ry = -(mouseY * zoom - magnifier.height() /2 );
 
            //돋보기를 마우스 위치에 따라 움직인다.
            //돋보기의 width, height 절반을 마우스 좌표에서 차감해 마우스와 돋보기 위치를 일치시킨다.
            var px = mouseX - magnifier.width() / 2;
            var py = mouseY - magnifier.height() / 2;
 
            //적용
            magnifier.css({
                left: px,
                top: py,
                backgroundPosition: rx + "px " + ry + "px"
            });
        }
    }
});
//돋보기 End--------------------------------------------------------

// 문의 남기기 페이지로 이동
function redirectToReview() {
    window.open('http://localhost:8080/stroke/product/productQnAWrite');
}

// heart----------------------------------------------------

// wishList에서 데이터 가져오기
var wishList = JSON.parse(sessionStorage.getItem('wishList'));

//heart End----------------------------------------------------

//Cookies----------------------------------------------------
// 페이지 로드 후 실행할 함수
function onPageLoad() {
  var url = window.location.href;
  var productId = getProductIDFromURL(url);
  console.log("productId::" + productId);

  if (productId) {
    var recentProducts = getCookie('recent_products');
    var recentProductIds = recentProducts ? recentProducts.split('/') : [];
    
    if (!recentProductIds.includes(productId)) {
      recentProductIds.push(productId);
    }

    // 최대 10개까지 유지
    if (recentProductIds.length > 10) {
      recentProductIds.shift();
    }

    recentProducts = recentProductIds.join('/');

    // 24시간 후의 시간 객체 생성
    var expirationDate = new Date();
    expirationDate.setTime(expirationDate.getTime() + (24 * 60 * 60 * 1000));
    console.log("expirationDate::" + expirationDate);

    // 쿠키에 recent_products 저장 (유효기간: 24시간)
    setCookie('recent_products', recentProducts, expirationDate);
  }
}

// 페이지 로드 이벤트 리스너 등록
window.addEventListener('load', onPageLoad);


function setCookie(cookieName, value, expirationDate) {
  var cookieValue = escape(value) + ((expirationDate == null) ? '' : '; expires=' + expirationDate.toUTCString());
  document.cookie = cookieName + '=' + cookieValue;
}

function getCookie(cookieName) {
  var name = cookieName + '=';
  var decodedCookie = decodeURIComponent(document.cookie);
  var cookieArray = decodedCookie.split(';');

  for (var i = 0; i < cookieArray.length; i++) {
    var cookie = cookieArray[i];
    while (cookie.charAt(0) == ' ') {
      cookie = cookie.substring(1);
    }
    if (cookie.indexOf(name) == 0) {
      return cookie.substring(name.length, cookie.length);
    }
  }
  return '';
}

//url에서 productId를 가져오는 함수
function getProductIDFromURL(url) {
  var regex = /[?&]product_id=(\d+)/;
  var match = regex.exec(url);
  if (match && match[1]) {
    return match[1];
  }
  return null;
}

//Cookies End----------------------------------------------------