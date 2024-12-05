let wsProtocol = "ws://";
if(window.location.protocol === "https:") {
    wsProtocol = "wss://";
}
const wsUri = wsProtocol + document.location.host + document.location.pathname + "/endpoint";
const websocket = new WebSocket(wsUri);

websocket.onopen = function(event) {
    $("#errorText").addClass("d-none");
    console.log("WS Open: " + wsUri);
}

websocket.onclose = function(event) {
    displayError("Lost internet connection");
}

websocket.onerror= function(event) {
    displayError("ERROR: " + event.data + ". See browser's developer console for more information.");
}

websocket.onmessage = function(event) {
    //runs when received
    updateTextArea(event.data,"in")

}

function displayError(msg) {
    const errDiv = document.getElementById("errorText");
    errDiv.innerHTML = msg;
    $("#errorText").removeClass("d-none");
}

const messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", function(event) {
    event.preventDefault();
    // Remove any error text
    displayError("");
    $("#errorText").addClass("d-none");
    const username = document.getElementById("userName").value;
    const message = document.getElementById("message").value;
    if(username===""){
        displayError("Name Required");
        return;
    }
    if(message===""){
        displayError("message Required");
        return;
    }
    const json = JSON.stringify({
    "name":username,
    "message":message});
    sendJson(json);

});

function isOpen(websocket){
    return websocket.readyState ===websocket.OPEN;
}
function  sendJson(json){
    console.log(json)
    if (isOpen(websocket)){
        websocket.send(json);
    }
    updateTextArea(json,"out");
    prepMessageBox();
}

function updateTextArea(data, inOut) {
    // Parse the data as JSON so the fields can be accessed
    var json = JSON.parse(data);
    // Use the JSON notation to retrieve the data fields
    var name = json.name;
    var message = encodeURI(json.message);
    // Build the text to display then show it
    var out = (inOut == "in") ? "<div class=\"in\">" : "<div class=\"out\">";
    out += "<p>" + message + "</p><span>";
    out += (inOut == "in") ? name  : "Me";
    out += "</span></div>"
    var textArea = document.getElementById("messages");
    textArea.innerHTML = textArea.innerHTML + out;
    // Attempt to move the scrolling of the textarea to show the lowest item
    // The effectiveness of this varies by browser
    textArea.scrollTop = textArea.scrollHeight;
    // Logging only helps when you have the browser's developer tools open
    console.log("Writing: " + data);
}

function prepMessageBox() {
    const message = document.getElementById("message");
    message.value = "";
    message.focus();
}