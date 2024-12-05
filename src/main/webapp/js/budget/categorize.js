$(document).ready(function() {
    var allbuttons = document.getElementsByClassName("category");
    for (var i = 0; i < allbuttons.length; i++) {
        allbuttons[i].addEventListener('change', function () {

            category = this.value;
            let id = this.getAttribute("id");
            var address = document.getElementById("addr").getAttribute("addr");


            let y = takevalues(id, address);


        });
}
function takevalues(x,url) {


    if (category == null) {
        return;
    }
    $("#"+x+"_status").slideUp();
    document.getElementById(x+"_status").innerHTML="<h5>&#9202</h5>";

    $("#"+x+"_status").slideDown();


    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log(xhr.responseText);

            document.getElementById(x + "_status").innerHTML = "<h5>&#x2705</h5>";
            document.getElementById(x+"_status").style.color="green";
            $("#"+x+"_status").slideUp(2000);
        }
    }



    xhr.open("POST", url+"/UTS", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let params = "t_id=" + x + "&category=" + category; // probably use document.getElementById(...).value
    xhr.send(params);


}})

