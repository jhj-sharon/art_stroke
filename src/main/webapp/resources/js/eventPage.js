(function(){

    // 룰렛, 버튼 가져오기 
    let roulette = document.querySelector(".roulette");
    let rouletteBtn = document.getElementById("roulette-btn");


    // loginMember 
    const eventLoginMember = document.getElementById("eventLoginMember");


    // 하루에 한 번만 이벤트 참여 
    // const eventToday = new Date();
    // const eventSaveDay = eventToday.getDate(); // day만 저장

    // 멤버 id 저장 
    // if(eventLoginMember.value != "null") {
    //     const memberIdMatch = eventLoginMember.value.match(/memberId=(\d+)/);
    //     const eventMemberId = memberIdMatch[1];
    // }


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
            <a href="">쿠폰함에서 확인하기</a>
        </div>
    </div>`

    // 쿠폰 당첨 모달 
    const eventModalOveraly = document.querySelector(".eventpage-modal-overlay");
    eventModalOveraly.innerHTML = eventModalContent;

    // input에 전달 
    let eventCouponRate = document.getElementById("event-coupon-rate");
    eventCouponRate.value = roulettePrizes[winningNumber];
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

        // loginMember의 값이 null이면 버튼 이벤트 비활성화, null이 아니면 버튼 이벤트 실행 
        if(eventLoginMember.value === "null"){
            alert("로그인 후 참여해주세요.");
            event.preventDefault();
            return false;

        } else {
                rouletteBtn.style.pointerEvents = 'none';
                deg = Math.floor(5000 + Math.random() * 5000);
                roulette.style.transition = `all 5s ease-out`;
                roulette.style.transform = `rotate(${deg}deg)`;

                // 룰렛 참여 시 localStorage에 일자, id 저장 
                // localStorage.setItem('today', eventSaveDay);
                // localStorage.setItem('id', eventMemberId);


            }
        });

    // 룰렛 회전 완료
    roulette.addEventListener("transitionend", () => {
        roulette.style.transition = "none";
        const actualDeg = deg % 360;
        roulette.style.transform = `rotate(${actualDeg}deg)`;
        handleWin(actualDeg);
        prizePopup();
    })

})();

