const slider = document.getElementById("amount");
const output = document.getElementById("amount-value");
output.innerHTML = slider.value;

slider.oninput = function () {
    output.innerHTML = this.value;
}

const uniqueCheckBox = document.getElementById("unique")
const matchingCheckBox = document.getElementById("matching")

var matchingCheckboxValue = matchingCheckBox.checked

uniqueCheckBox.oninput = function () {
    matchingCheckBox.disabled = uniqueCheckBox.checked;
    if (matchingCheckBox.disabled) {
        matchingCheckboxValue = matchingCheckBox.checked
        matchingCheckBox.checked = true;
    } else {
        matchingCheckBox.checked = matchingCheckboxValue
    }
}

