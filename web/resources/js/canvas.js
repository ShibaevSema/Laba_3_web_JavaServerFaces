var wsUri = "ws://" + document.location.host + "/Laba_3_semabaef/" + "socket";
var websocket = new WebSocket(wsUri);
websocket.onmessage = function (evt) {
    var data = JSON.parse(evt.data.toString());
    var x = data['x'] * 130 / data['r'] + 150;
    var y = 150 - 130 * data['y'] / data['r'];

    drawPoint('canvas', x, y, data['inArea']);

    document.getElementById("form:refresh_table").click()
};

document.addEventListener("DOMContentLoaded", function (event) {
    drawCanvas("canvas", document.getElementById("form:r").value);
});

function drawCanvas(id, r) {
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");
    //очистка
    context.clearRect(0, 0, canvas.width, canvas.height);

    //прямоугольник
    context.beginPath();
    context.rect(150, 150, 130, 65);
    context.closePath();
    context.strokeStyle = "#428d18";
    context.fillStyle = "#428d18";
    context.fill();
    context.stroke();

    // сектор
    context.beginPath();
    context.moveTo(150, 150);
    context.arc(150, 150, 130, Math.PI / 2, Math.PI, false);
    context.closePath();
    context.strokeStyle = "#428d18";
    context.fillStyle = "#428d18";
    context.fill();
    context.stroke();

    //треугольник
    context.beginPath();
    context.moveTo(150, 150);
    context.lineTo(150, 85);
    context.lineTo(150 + 130, 150);
    context.lineTo(150, 150);
    context.closePath();
    context.strokeStyle = "#428d18";
    context.fillStyle = "#428d18";
    context.fill();
    context.stroke();

    //отрисовка осей
    context.beginPath();
    context.font = "10px Verdana";
    context.moveTo(150, 0);
    context.lineTo(150, 300);
    context.moveTo(150, 0);
    context.lineTo(145, 15);
    context.moveTo(150, 0);
    context.lineTo(155, 15);
    context.strokeStyle = "#000000";
    context.fillStyle = "#000000";
    context.fillText("Y", 160, 10);
    context.moveTo(0, 150);
    context.lineTo(300, 150);
    context.moveTo(300, 150);
    context.lineTo(285, 145);
    context.moveTo(300, 150);
    context.lineTo(285, 155);
    context.fillText("X", 290, 135);

    // деления X
    context.moveTo(145, 20);
    context.lineTo(155, 20);
    context.fillText(r, 160, 20);
    context.moveTo(145, 85);
    context.lineTo(155, 85);
    context.fillText((r / 2), 160, 78);
    context.moveTo(145, 215);
    context.lineTo(155, 215);
    context.fillText(-(r / 2), 160, 215);
    context.moveTo(145, 280);
    context.lineTo(155, 280);
    context.fillText(-r, 160, 280);
    // деления Y
    context.moveTo(20, 145);
    context.lineTo(20, 155);
    context.fillText(-r, 20, 170);
    context.moveTo(85, 145);
    context.lineTo(85, 155);
    context.fillText(-(r / 2), 70, 170);
    context.moveTo(215, 145);
    context.lineTo(215, 155);
    context.fillText((r / 2), 215, 170);
    context.moveTo(280, 145);
    context.lineTo(280, 155);
    context.fillText(r, 280, 170);

    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();
}

function clickCanvas() {
    var elem = document.getElementById('canvas');
    var R = document.getElementById("form:r").value;

    var br = elem.getBoundingClientRect();
    var left = br.left;
    var top = br.top;
    var event = window.event;
    var x = event.clientX - left;
    var y = event.clientY - top;
    console.log(x, y, R);

    var transf_x = R * (x - 150) / 130;
    var transf_y = R * (150 - y) / 130;

    data = JSON.stringify({
        'x': transf_x,
        'y': transf_y,
        'r': R
    });
    websocket.send(data)
}

function drawPoint(id, x, y, isArea) {
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");
    console.log(canvas);

    context.beginPath();
    context.ellipse(x - 1, y - 1, 2, 2, 1, 0, 2 * Math.PI, true);
    context.closePath();
    if (isArea) {
        context.strokeStyle = "#030303";
        context.fillStyle = "#0a0a0a";
    } else {
        context.strokeStyle = "#ff0000";
        context.fillStyle = "#f10404";
    }
    context.fill();
    context.stroke();

}