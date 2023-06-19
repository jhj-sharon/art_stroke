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
  var cookieValue = escape(value) + ((expirationDate == null) ? '' : '; expires=' + expirationDate.toUTCString()) + '; path=/';

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

//ConfirmPopup----------------------------------------------------
function wishPopShow() {
  var wishPop = document.querySelector('.wishPop');
  wishPop.style.display = 'block';
}

function CartPopShow() {
  var cartPop = document.querySelector('.cartPop');
  cartPop.style.display = 'block';
}
//ConfirmPopup End----------------------------------------------------

//wishList ----------------------------------------------------

$(document).ready(function() {
  $('.wishlist').on('click', function(event) {
    event.preventDefault();  // 기본 동작 방지

    // 버튼의 id에서 상품 ID 추출
    var productId = $(this).attr('id').split('-')[0];

    // Ajax 요청 수행
    $.ajax({
      url: 'wishListDetail',  
      method: 'POST',       
      data: {
        productId: productId  
      },
      success: function(response) {
        
        if(response === 1) {
          console.log('등록완');  
          wishPopShow();

        }else{
          alert('관심상품 등록에 실패했습니다. 다시시도 해주세요');
        }
      },
      error: function(xhr, status, error) {
        
        console.error(error);  
      }
    });
  });
});


//wishList End----------------------------------------------------

//Cart ----------------------------------------------------

$(document).ready(function() {
  $('.basket').on('click', function(event) {
    event.preventDefault();  // 기본 동작 방지

    // 버튼의 id에서 상품 ID 추출
    var productId = $(this).attr('id').split('-')[0];

    // 필수 옵션 선택값 추출
    var option1 = $('#option1').val();
    var option2 = $('#option2').val();
    // Ajax 요청 수행
    $.ajax({
      url: 'addCart',  
      method: 'POST',       
      data: {
        productId: productId,
        option1: option1,
        option2: option2
      },
      success: function(response) {
        
        if(response === 1) {
          console.log('등록완');  
          cartPopShow();

        }else{
          alert('관심상품 등록에 실패했습니다. 다시시도 해주세요');
        }
      },
      error: function(xhr, status, error) {
        
        console.error(error);  
      }
    });
  });
});

//Cart End----------------------------------------------------

//Options ----------------------------------------------------------------
function addOption() {
  // 선택한 옵션 값 가져오기
  var selectedOption = document.getElementById('option1').value;

  // 이미 선택된 옵션인지 확인
  var existingOptions = document.querySelectorAll('.option-tr .option-name span');
  for (var i = 0; i < existingOptions.length; i++) {
    if (existingOptions[i].textContent === '선택옵션: ' + selectedOption) {
      return; // 이미 선택된 옵션인 경우 함수 종료
    }
  }

  // option-tr 요소 생성
  var optionTr = document.createElement('div');
  optionTr.classList.add('option-tr');

  // option-name 요소 생성 및 옵션 값 설정
  var optionName = document.createElement('div');
  optionName.classList.add('option-name');

  var span = document.createElement('span');
  span.textContent = '선택옵션: ' + selectedOption;

  optionName.appendChild(span);

  // option-qty 요소 생성
  var optionQty = document.createElement('div');
  optionQty.classList.add('option-qty');

  var minusSpan = document.createElement('span');
  minusSpan.classList.add('minus');
  minusSpan.textContent = '-';

  var numSpan = document.createElement('span');
  numSpan.classList.add('num');
  numSpan.textContent = '01';

  var plusSpan = document.createElement('span');
  plusSpan.classList.add('plus');
  plusSpan.textContent = '+';

  optionQty.appendChild(minusSpan);
  optionQty.appendChild(numSpan);
  optionQty.appendChild(plusSpan);

  // goods-price 요소 생성
  var td2Value = document.querySelector('.td2').textContent;
  var numericValue = parseFloat(td2Value.replace(/,/g, '').replace('원', '')); // 상품가격
  console.log(numericValue);
  console.log(td2Value);

  var goodsPrice = document.createElement('div');
  goodsPrice.classList.add('goods-price');

  var priceSpan = document.createElement('span');
  priceSpan.textContent = calculateTotalPrice(numSpan.textContent, numericValue);
  goodsPrice.appendChild(priceSpan);
  
  var circleMinusIcon = document.createElement('i');
  circleMinusIcon.classList.add('fa-solid');
  circleMinusIcon.classList.add('fa-circle-minus');
  circleMinusIcon.style.color = '#CECDCB';
  goodsPrice.appendChild(circleMinusIcon);
  

  // option-tr에 요소 추가
  optionTr.appendChild(optionName);
  optionTr.appendChild(optionQty);
  optionTr.appendChild(goodsPrice);

  // option_wrapper에 option-tr 추가
  var optionWrapper = document.querySelector('.option_wrapper');
  optionWrapper.appendChild(optionTr);



  // optionQty 요소에 이벤트 리스너 등록
  let a = 1;
  // optionQty 요소에 이벤트 리스너 등록
  optionQty.addEventListener('click', (event) => {
    console.log('수량변경함수');
    if (event.target.classList.contains('plus')) {
      // + 버튼을 클릭한 경우
      console.log('Adding');
      a++;
      a = (a < 10) ? '0' + a : a;
      numSpan.innerText = a;
    } else if (event.target.classList.contains('minus')) {
      // - 버튼을 클릭한 경우
      console.log('Minus');
      if (a > 1) {
        a--;
        a = (a < 10) ? '0' + a : a;
        numSpan.innerText = a;
      }
    }
    priceSpan.textContent = calculateTotalPrice(numSpan.textContent, numericValue);
    updateTotalPrice();
  });
  

  function removeOption(event) {
    var optionTr = event.target.closest('.option-tr');
    if (optionTr) {
      optionTr.remove();
      updateTotalPrice();
    }
  }

  // circleMinusIcon에 이벤트 리스너 등록
  circleMinusIcon.addEventListener('click', removeOption);

  // 계산 로직: numSpan과 numericValue를 곱한 값을 반환하는 함수
  function calculateTotalPrice(quantity, price) {
    var totalPrice = parseFloat(quantity) * price;
    return totalPrice.toLocaleString() + '원';
  }

  // 총합계 구하는 함수
  updateTotalPrice();

}

//Options End----------------------------------------------------

//총 가격--------------------------------------------------------
function updateTotalPrice() {
  let totalSum = 0;
  let totalCount = 0;

  const numElements = document.getElementsByClassName('num');
  const td2Value = document.querySelector('.td2').textContent;
  const numericValue = parseFloat(td2Value.replace(/,/g, '').replace('원', ''));

  for (let i = 0; i < numElements.length; i++) {
    const num = parseInt(numElements[i].textContent);
    totalCount += num;
  }

  totalSum = totalCount * numericValue;

  const totalPriceElement = document.getElementById('sum');
  const totalCountElement = document.getElementById('count');

  totalPriceElement.textContent = totalSum.toLocaleString();
  totalCountElement.textContent = totalCount;
}


//수량변경 End--------------------------------------------------------
// optionQty 요소에 이벤트 리스너 등록
optionQty.addEventListener('click', (event) => {
  console.log('수량변경함수');
  if (event.target.classList.contains('plus')) {
    // + 버튼을 클릭한 경우
    console.log('Adding');
    a++;
    a = (a < 10) ? '0' + a : a;
    numSpan.innerText = a;
  } else if (event.target.classList.contains('minus')) {
    // - 버튼을 클릭한 경우
    console.log('Minus');
    if (a > 1) {
      a--;
      a = (a < 10) ? '0' + a : a;
      numSpan.innerText = a;
    }
  }
  priceSpan.textContent = calculateTotalPrice(numSpan.textContent, numericValue);

  // updateTotalPrice() 함수 실행
  updateTotalPrice();
});
