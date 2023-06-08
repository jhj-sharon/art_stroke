// 룰렛----------------------------------------------
let roulette = document.querySelector(".roulette");
let rouletteBtn = document.getElementById("roulette-btn");
let number = Math.ceil(Math.random() * 10000);

let rouletteItem = document.querySelector(".container div");


rouletteBtn.onclick = function () {
    roulette.style.transform = "rotate(" + number + "deg)";
    number += Math.ceil(Math.random() * 10000);
}