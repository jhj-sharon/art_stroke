document.addEventListener('DOMContentLoaded', function() {
    const totalPrice = document.getElementById('order-price');
    const priceText = totalPrice.textContent;
    const priceValue = parseFloat(priceText.replace(/[^0-9.-]+/g,"")); // 숫자 값으로 변환
    const formattedPrice = priceValue.toLocaleString(); // 숫자 포맷팅
    totalPrice.textContent = formattedPrice+ '원';
  });
  