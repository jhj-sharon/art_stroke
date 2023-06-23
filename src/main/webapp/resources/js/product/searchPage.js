let urlParams = new URLSearchParams(new URL(window.location.href).search);
let keyword = urlParams.get('keyword');
let productCategory = urlParams.get('productCategory');
let resultContainer = document.querySelector(".contents-wrap search-result");

console.log("productCategory: ", productCategory); 
console.log("keyword: ", keyword);

//1. 키워드 검색 시 
if(keyword && productCategory === null) {
    console.log("키워드 검색 ajax")
    $.ajax({
        url: "/stroke/searchKeyword",
        type: 'GET',
        data: {
            productName : keyword,
            productType : keyword,
            productArtist : keyword,
            productCategory : keyword
        },
        success: function(result) {

            console.log("키워드 검색 결과: ", result)
            //1. 목록
            //2. 개수 

        },
        error: function(){
            console.log("키워드 검색 실패")
        }
    })

//2. 카테고리 검색 시 
} else if(productCategory && keyword === null) {
    console.log("카테고리 검색 ajax")

//3. 검색창에 아무것도 입력 안했을 때
} else if(keyword === "") {
    console.log("검색결과 없음 페이지")
    document.querySelector(".contents-wrap search-result-none").innerHTML = `<article class="searchpage-result-text">
                                                                                ""에 대한 검색결과가 없습니다.<br><br> 
                                                                                단어의 철자나 띄어쓰기가 정확한지 확인해주시기 바랍니다. 
                                                                                </article>
                                                                                
                                                                                <article class="searhpage-product-wrap">
                                                                                <span style="font-size: 18px; font-weight: 500;">추천상품</span>
                                                                                <ul class="product-list">
                                                                                    <li class="product-item">
                                                                                        <div class="product-item-img">
                                                                                        <a href="상세페이지">
                                                                                            <img src="https://tounou.co.kr/web/product/big/202305/4cbe20ff9320f120867373dcbe79a306.jpg" alt="">
                                                                                        </a>
                                                                                            <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                                                                                            
                                                                                        </div>
                                                                                
                                                                                        <div class="product-item-info">
                                                                                            <span>작가1</span>
                                                                                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                                                                                            <span>21,900원</span>
                                                                                        </div>
                                                                                    </li>
                                                                                    <li class="product-item">
                                                                                        <div class="product-item-img">
                                                                                        <a href="상세페이지">
                                                                                            <img src="https://tounou.co.kr/web/product/medium/202103/aa9ad40deea853298b00dd2b06133468.jpg" alt="">
                                                                                        </a>
                                                                                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                                                                                        </div>
                                                                                
                                                                                        <div class="product-item-info">
                                                                                            <span>작가2</span>
                                                                                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                                                                                            <span>21,900원</span>
                                                                                        </div>
                                                                                    </li>
                                                                                    <li class="product-item">
                                                                                        <div class="product-item-img">
                                                                                        <a href="상세페이지">
                                                                                            <img src="https://tounou.co.kr/web/product/medium/202305/fd1884123b1e8b4b152298d8d80ffd60.jpg" alt="">
                                                                                        </a>
                                                                                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                                                                                        </div>
                                                                                
                                                                                        <div class="product-item-info">
                                                                                            <span>작가3</span>
                                                                                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                                                                                            <span>21,900원</span>
                                                                                        </div>
                                                                                    </li>
                                                                                    <li class="product-item">
                                                                                        <div class="product-item-img">
                                                                                        <a href="상세페이지">
                                                                                            <img src="https://tounou.co.kr/web/product/medium/202305/436e4ab0f2ef629616d0c84ae9cb4d0b.jpg" alt="">
                                                                                        </a>
                                                                                        <span class="main-heart-area"> <i class="fa-regular fa-heart"></i></span>
                                                                                        </div>
                                                                                
                                                                                        <div class="product-item-info">
                                                                                            <span>작가4</span>
                                                                                            <span>눈뜨면 핑크캐년 스마트폰 케이스</span>
                                                                                            <span>21,900원</span>
                                                                                        </div>
                                                                                    </li>
                                                                                </ul>
                                                                                </article> `
}



