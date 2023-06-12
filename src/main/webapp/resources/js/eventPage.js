(function(){

    // 룰렛, 버튼 가져오기 
    let roulette = document.querySelector(".roulette");
    let rouletteBtn = document.getElementById("roulette-btn");

    // 룰렛의 시작 각도 
    let deg = 0;
    
    // 룰렛 각 아이템의 각도(6칸짜리 룰렛 = 360/60) 
    let zoneSize = 60;

    // 룰렛 상품
    const roulettePrizes = {
        1: "3%",
        2: "3%",
        3: "5%",
        4: "3%",
        5: "5%",
        6: "7%",
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
                <div>${roulettePrizes[winningNumber]}</div>
                <div>할인쿠폰</div>
            </div>
            <div>하루 한 번 매일매일 당첨! 내일도 다시 참여해주세요!</div>
            <a href="">쿠폰함에서 확인하기</a>
        </div>
    </div>`

    const eventModalOveraly = document.querySelector(".eventpage-modal-overlay");
    
    eventModalOveraly.innerHTML = eventModalContent;
    }


    // 룰렛 완료 시 모달창 
    const prizePopup = () => {
        $(".eventpage-modal-overlay").fadeIn();
        $(".eventpage-modal-overlay").css("display", "flex");
    }

    $(".eventpage-modal-overlay").on("click", ".eventpage-modal-close", function() {
        $(".eventpage-modal-overlay").fadeOut();
    });


    // 룰렛 회전
    rouletteBtn.addEventListener("click", ()=>{
        rouletteBtn.style.pointerEvents = 'none';
        deg = Math.floor(5000 + Math.random() * 5000);
        roulette.style.transition = `all 5s ease-out`;
        roulette.style.transform = `rotate(${deg}deg)`;
    })

    // 룰렛 회전 완료
    roulette.addEventListener("transitionend", () => {
        roulette.style.transition = "none";
        const actualDeg = deg % 360;
        roulette.style.transform = `rotate(${actualDeg}deg)`;
        handleWin(actualDeg);
        prizePopup();
    })

})();


