console.log("js was loaded");

const modal = document.getElementById("modal");
var card = document.querySelector('.product-review-card');
const closeModalBtn = document.getElementById("close-modal");

// 모달창 열기
card.addEventListener("click", () => {
  modal.style.display = "block";
  document.body.style.overflow = "hidden"; // 스크롤바 제거
});

// 모달창 닫기
closeModalBtn.addEventListener("click", () => {
  modal.style.display = "none";
  document.body.style.overflow = "auto"; // 스크롤바 보이기
});

// 모달 창 외부 영역 클릭 시 닫기
window.addEventListener("click", (event) => {
    if (event.target === modal) {
      closeModal();
    }
  });
  
  // 모달 닫기 함수
  function closeModal() {
    modal.style.display = "none";
    document.body.style.overflow = "auto"; // 스크롤바 보이기
  }

