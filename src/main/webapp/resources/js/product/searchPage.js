// loginMember 
let searchLoginMember = document.getElementById("searchLoginMember");

// 하트 
const emptyHeart = '<i class="fa-regular fa-heart"></i>';
const redHeart = '<i class="fa-solid fa-heart" style="color: #f42525;"></i>';

let urlParams = new URLSearchParams(new URL(window.location.href).search);
let keyword = urlParams.get('keyword');
let productCategory = urlParams.get('productCategory');
let resultContainer = document.querySelector(".contents-wrap search-result");

// contextPath 가져오기 
let contextPath = document.getElementById("searchContextPath").value;


console.log("productCategory: ", productCategory); 
console.log("keyword: ", keyword);


// 상품에 하트 삽입 
let searchHeartArea;
let searchWishProductId = [];
const findHeart = () => {

    console.log("findHeart 실행")

    setTimeout(() => {

        searchHeartArea = document.querySelectorAll(".searh-heart-area");

        // 로그인됨 
        if(searchLoginMember.value != "null"){

            // 1. 키워드 검색 시 
            if(keyword && productCategory === null) {
                console.log("하트 키워드검색 ajax")

                $.ajax({
                    url: "/art_stroke/product/searchKeywordHeart",
                    type: 'GET',
                    data: {
                        productName : keyword,
                        productType : keyword,
                        productArtist : keyword,
                        productCategory : keyword
                    },
                    success: function(response) {
                        searchWishProductId = response.map(obj => obj.productId);
    
                        for(let i = 0; i < searchHeartArea.length; i++){
                            
                            if(searchWishProductId.includes(parseInt(searchHeartArea[i].id))){
                                searchHeartArea[i].innerHTML = redHeart;
                            } else {
                                searchHeartArea[i].innerHTML = emptyHeart;
                            }
                        }
                    }, 
                    error : function(){
                        console.log("키워드 하트 에러 발생");
                    }
                });

            } else if(productCategory && keyword === null) {
                console.log("하트 카테고리검색 ajax")

                $.ajax({
                    url: "/art_stroke/product/searchCategoryHeart",
                    type: 'GET',
                    data: {
                        productCategory : productCategory
                    },
                    success: function(response) {
                        searchWishProductId = response.map(obj => obj.productId);
    
                        for(let i = 0; i < searchHeartArea.length; i++){
                            
                            if(searchWishProductId.includes(parseInt(searchHeartArea[i].id))){
                                searchHeartArea[i].innerHTML = redHeart;
                            } else {
                                searchHeartArea[i].innerHTML = emptyHeart;
                            }
                        }
                    }, 
                    error : function(){
                        console.log("카테고리 하트 에러 발생");
                    }
                });

            }

        // 로그인 안되어있음
        } else if(searchLoginMember.value === "null"){

            console.log("로그인 안됨 ")
            for(let i = 0; i < searchHeartArea.length; i++){
                searchHeartArea[i].innerHTML = emptyHeart;
            }
        }
    }, 500);
}




//1. 키워드 검색 시 
if(keyword && productCategory === null) {

    $.ajax({
        url: "/art_stroke/product/searchKeyword",
        type: 'GET',
        data: {
            productName : keyword,
            productType : keyword,
            productArtist : keyword,
            productCategory : keyword
        },
        success: function(result) {

            if(result.length > 0){

            // 검색 결과 span
            document.querySelector(".searchpage-result-text").innerHTML = `"<span>${keyword}</span>"에 대한 결과 (<span>${result.length}</span>)`

            // 상품 띄우기
            const productList = document.querySelector(".product-list");
            let productItem ='';

            for(let i = 0; i < result.length; i++){

                productItem += `<li class="product-item">
                                        <div class="product-item-img">
                                            <a href="${contextPath}/product/productDetail?product_id=${result[i].productId}">
                                                <img src="/art_stroke/${result[i].productImage}" alt="키워드 검색 썸네일">
                                            </a>
                                            <span class="searh-heart-area" id="${result[i].productId}" onclick="wishListHandler(event)"></span>
                                        </div>

                                        <div class="product-item-info">
                                            <span>${result[i].productArtist}</span>
                                            <span>${result[i].productName}</span>
                                            <span>${result[i].productPrice.toLocaleString()}원</span>
                                        </div>
                                    </li>`
            }

            productList.innerHTML = productItem;

            // 검색결과 없음
            } else {
                document.querySelector(".searchpage-result-text").innerHTML = `<div class="no-result">"${keyword}"에 대한 검색결과가 없습니다.<br><br> 
                                                                                단어의 철자나 띄어쓰기가 정확한지 확인해주시기 바랍니다. </div>`
            }
            

        },
        error: function(){
            console.log("키워드 검색 실패")
        }
    })

    findHeart();

//2. 카테고리 검색 시 
} else if(productCategory && keyword === null) {
    console.log("카테고리 검색 ajax")
    $.ajax({
        url: "/art_stroke/product/searchCategory",
        type: 'GET',
        data: {
            productCategory : productCategory
        },
        success: function(result) {

            // 검색 결과 span
            document.querySelector(".searchpage-result-text").innerHTML = `"<span>${productCategory}</span>"에 대한 결과 (<span>${result.length}</span>)`

            // 상품 띄우기
            const productList = document.querySelector(".product-list");
            let productItem ='';

            for(let i = 0; i < result.length; i++){

                productItem += `<li class="product-item">
                                        <div class="product-item-img">
                                            <a href="${contextPath}/product/productDetail?product_id=${result[i].productId}">
                                                <img src="/art_stroke/${result[i].productImage}" alt="카테고리 검색 썸네일">
                                            </a>
                                            <span class="searh-heart-area" id="${result[i].productId}" onclick="wishListHandler(event)"></span>
                                        </div>

                                        <div class="product-item-info">
                                            <span>${result[i].productArtist}</span>
                                            <span>${result[i].productName}</span>
                                            <span>${result[i].productPrice.toLocaleString()}원</span>
                                        </div>
                                    </li>`
            }

            productList.innerHTML = productItem;

            findHeart();

        },
        error: function(){
            console.log("키워드 검색 실패")
        }

    })


//3. 검색창에 아무것도 입력 안했을 때
} else if(keyword === "") {
    console.log("검색결과 없음 페이지")
    document.querySelector(".searchpage-result-text").innerHTML = `<div class="no-result">" "에 대한 검색결과가 없습니다.<br><br> 
                                                                   단어의 철자나 띄어쓰기가 정확한지 확인해주시기 바랍니다. </div>`                               
}



// 빈 하트를 누르면 INSERT 빨간 하트를 누르면 DELETE + 로그인이 안되어있으면 alret창 
const wishListHandler = (event) =>{ 
    searchHeartArea = document.querySelectorAll(".searh-heart-area");
  
    if(searchLoginMember.value != "null"){
        productId = event.target.parentNode.id;

        if(event.target.classList[0] === "fa-solid"){
            $.ajax({
                url: "/art_stroke/product/deleteSearchWishList",
                type: 'post',
                data:{productId : productId},
                success: function(result) {
                    console.log("삭제 성공")
                    event.target.parentNode.innerHTML = emptyHeart;
                },
                error: function(){
                    console.log("위시리스트 삭제 실패")
                }
            })

        } else {
            $.ajax({
                url: "/art_stroke/product/addSearchWishList",
                type: 'post',
                data:{productId : productId},
                success: function(result) {
                    console.log("추가 성공")
                    event.target.parentNode.innerHTML = redHeart;
                },
                error: function(){
                    console.log("위시리스트 추가 실패")
                }
            })
        }
    } else{
        alert("로그인이 필요한 기능입니다.");
        event.preventDefault();
        window.location.href = `${contextPath}/member/login`;
    }
}
    












