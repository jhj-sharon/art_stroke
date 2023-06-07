  function productApply() {
        var allButton = document.getElementById("allButton");
        var normalButton = document.getElementById("normalButton");
        var withdrawnButton = document.getElementById("withdrawnButton");
        var memberTable = document.getElementById("memberTable");
        var memberRows = memberTable.getElementsByTagName("tr");

        for (var i = 1; i < memberRows.length; i++) {
            var authCell = memberRows[i].cells[6];
            var secessionFlCell = memberRows[i].cells[7];
            var displayOption = "";

            if (normalButton.checked) {
                if (authCell.innerText === "1" && secessionFlCell.innerText === "N") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
            } else if (withdrawnButton.checked) {
                if (secessionFlCell.innerText === "Y") {
                    displayOption = "";
                } else {
                    displayOption = "none";
                }
            }

            memberRows[i].style.display = displayOption;
        }
    }