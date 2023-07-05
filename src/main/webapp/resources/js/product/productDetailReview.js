console.log("js was loaded");
window.onload = function() {
  const productReviewDetail = document.querySelector('.product-review-detail');
  productReviewDetail.focus();
};



var cards = document.querySelectorAll('.product-review-card');
var closeModalBtns = document.querySelectorAll('[id^="close-"]');


cards.forEach((card) => {
  card.addEventListener("click", (event) => {
    const modalId = card.getAttribute('id').replace("card-", "modal-");
    const modal = document.querySelector(`#${modalId}`);
    modal.style.display = "block";
    document.body.style.overflow = "hidden";
  });
});

closeModalBtns.forEach((btn) => {
  btn.addEventListener("click", () => {
    const modalId = btn.getAttribute('id').replace("close-", "modal-");
    const modal = document.querySelector(`#${modalId}`);
    modal.style.display = "none";
    document.body.style.overflow = "auto";
  });
});

window.addEventListener("click", (event) => {
  closeModalBtns.forEach((btn) => {
    const modalId = btn.getAttribute('id').replace("close-", "modal-");
    const modal = document.querySelector(`#${modalId}`);
    if (event.target === modal) {
      modal.style.display = "none";
      document.body.style.overflow = "auto";
    }
  });
});

