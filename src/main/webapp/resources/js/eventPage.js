(function(){

    // 룰렛, 버튼 가져오기 
    let roulette = document.querySelector(".roulette");
    let rouletteBtn = document.getElementById("roulette-btn");

    // contextPath 가져오기 
    let contextPath = document.getElementById("eventContextPath").value;

    // loginMember 
    let eventLoginMember = document.getElementById("eventLoginMember");

    // 멤버 id 저장 
    let eventMemberId
    if(eventLoginMember.value != "null") {
        let memberIdMatch = eventLoginMember.value.match(/memberId=(\d+)/);
        eventMemberId = memberIdMatch[1];
    }

    // 쿠폰id 생성 
    let eventCouponId
    function generateCouponId() {
        eventCouponId = Math.floor(10000000 + Math.random() * 90000000);
        return eventCouponId.toString().substring(0, 8);
      }


    // 룰렛의 시작 각도 
    let deg = 0;
    
    // 룰렛 각 아이템의 각도(6칸짜리 룰렛 = 360/60) 
    let zoneSize = 60;

    // 룰렛 상품
    const roulettePrizes = {
        1: "3",
        2: "3",
        3: "5",
        4: "3",
        5: "5",
        6: "7",
    }

    // 모달 내용 
    let eventModalContent;


    // 룰렛 완료 시 실행될 함수 
    const handleWin = (actualDeg) => {
        const winningNumber = Math.ceil(actualDeg / zoneSize);
        console.log("roulettePrizes: ", roulettePrizes[winningNumber])

        eventModalContent = `<div class="eventpage-modal-container">
        <div class="eventpage-modal-close"><button>&times;</button></div>
        <div class="eventpage-modal-content">
            <div></div>
            <div>
                <div>${roulettePrizes[winningNumber]}%</div>
                <div>할인쿠폰</div>
            </div>
            <div>하루 한 번 매일매일 당첨! 내일도 다시 참여해주세요!</div>
            <a href="${contextPath}/myPage/myPageCouponList">쿠폰함에서 확인하기</a>
        </div>
    </div>`

    // 쿠폰 당첨 모달 
    const eventModalOveraly = document.querySelector(".eventpage-modal-overlay");
    eventModalOveraly.innerHTML = eventModalContent;


    // input에 전달 
    let eventCouponRate = document.getElementById("event-coupon-rate");
    eventCouponRate.value = roulettePrizes[winningNumber];

    $.ajax({
        url: "rouletteEvent",
        type: 'post',
        dataType: 'json',
        data: {
            couponId : eventCouponId,
            discountRate : eventCouponRate.value
        },
        success: function(response) {
            console.log("성공", response)
        }, 
        error : function(){
            console.log("insert 에러 발생");
        }
    });

    }


    // 룰렛 완료 시 모달창 
    const prizePopup = () => {
        $(".eventpage-modal-overlay").fadeIn();
        $(".eventpage-modal-overlay").css("display", "flex");
    }

    $(".eventpage-modal-overlay").on("click", ".eventpage-modal-close", function() {
        $(".eventpage-modal-overlay").fadeOut();
    });

   


    rouletteBtn.addEventListener("click", function(event){

        // id 중복검사하는 함수 
        const couponCheckId = () => {
            $.ajax({
                url: "rouletteEventIdCheck",
                type: 'GET',
                data: {
                    couponId : eventCouponId
                },
                success: function(result) {
                    
                    console.log("couponId 성공 result: ", result)

                    if(result > 0) {
                        console.log("couponId 성공 result: ", result)
                        console.log("couponId 성공 cId: ", eventCouponId)
                        generateCouponId();
                        couponCheckId();
                    } else{
                        // 룰렛 회전 완료
                    roulette.addEventListener("transitionend", () => {
                        roulette.style.transition = "none";
                        const actualDeg = deg % 360;
                        roulette.style.transform = `rotate(${actualDeg}deg)`;
                        handleWin(actualDeg);
                        prizePopup();
                        })
                    }
                    
                }, 
                error : function(){
                    console.log("아이디 중복체크 에러 발생");
                    console.log("아이디 중복체크 에러 발생: ", eventCouponId);
                }
            });
        }

        // 로그인한 회원만 참여 가능 
        if(eventLoginMember.value === "null"){
            alert("로그인 후 참여해주세요.");
            event.preventDefault();
            window.location.href = `${contextPath}/member/login`;
            return false;

        } else {
            // 하루 한 번만 참여 가능 
            $.ajax({
                url: "rouletteEventCheck",
                type: 'GET',
                success: function(result) {
                    // 참여한 기록이 없으면 룰렛 이벤트 시작 
                    console.log("하루 한 번 참여 성공: ", result)
                    if(result === 0) {
                        rouletteBtn.style.pointerEvents = 'none';
                        deg = Math.floor(5000 + Math.random() * 5000);
                        roulette.style.transition = `all 5s ease-out`;
                        roulette.style.transform = `rotate(${deg}deg)`;
                        
                    } else if(result > 0){ 
                        alert("하루에 한 번만 참여 가능합니다. 내일 다시 참여해주세요.");
                        event.preventDefault();
                        return false;
                        }
                    }, 
                error : function(){
                    console.log("참여 체크 에러 발생");
                }
            })


        // 클릭과 동시에 쿠폰 id 생성 후 0.3초 뒤 id 중복체크 
        generateCouponId();
        setTimeout(() => {
            couponCheckId();
        }, 300);
          
                
           
        }

    })

     

})();

