var name;
var price = 0;
var amount = 0;
var type = 0;

function setGOOD(name, type, amount, price) {
    this.name = name;
    this.type = type;
    this.amount = amount;
    this.price = price;
}

window.onload = function() {
    document.getElementById("goodName").innerHTML=name;
}