var slider = document.getElementById("amount");
var output = document.getElementById("amount-value");
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
}