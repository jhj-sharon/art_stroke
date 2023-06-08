function termsValidate(){ 

  const checkbox=document.getElementsByClassName(".blind");

  if(!checkbox.checked){
    return false;

  }
 return true;


}

// const checkWrapList = document.querySelectorAll(".check_wrap");
// const allCheck = document.querySelector(".allCheck");

// let isIconSolid = false;

// allCheck.addEventListener("click", function() {
//   isIconSolid = !isIconSolid;

//   for (let i = 0; i < checkWrapList.length; i++) {
//     const iconSpan = checkWrapList[i].querySelector(".fa-regular");

//     if (iconSpan) {
//       if (isIconSolid) {
//         iconSpan.classList.replace("fa-regular", "fa-solid");
//       } else {
//         iconSpan.classList.replace("fa-solid", "fa-regular");
//       }
//     }
//   }
// });
const checkWrapList = document.querySelectorAll(".check_wrap");
const allCheck = document.querySelector(".allCheck");
let isIconSolid = false;

allCheck.addEventListener("click", function() {
  isIconSolid = !isIconSolid;

  for (let i = 0; i < checkWrapList.length; i++) {
    const iconSpan = checkWrapList[i].querySelector(".fa-regular");
    
    if (iconSpan) {
      if (isIconSolid) {
        iconSpan.classList.remove("fa-regular");
        iconSpan.classList.add("fa-solid");
      } else {
        iconSpan.classList.remove("fa-solid");
        iconSpan.classList.add("fa-regular");
      }
    }
  }
});
