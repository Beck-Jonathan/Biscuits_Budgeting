$(document).ready(function() {
    var allbuttons = document.getElementsByClassName("category");
    var allLocks = document.getElementsByClassName("lock");
    for (var i = 0; i < allbuttons.length; i++) {
        allbuttons[i].addEventListener('change', function () {
            category = this.value;
            let id = this.getAttribute("id");
            var address = document.getElementById("addr").getAttribute("addr");
            let y = takevalues(id, address);
        });
}
    for (var i = 0; i < allLocks.length; i++) {
        allLocks[i].addEventListener('change', function () {
            console.log("here");
            let id = this.getAttribute("id");
            console.log("here1");
            var address = document.getElementById("addr").getAttribute("addr");
            console.log("here2");
            let y = lock(id, address);
            console.log("here3");
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
            //console.log(xhr.responseText);

            document.getElementById(x + "_status").innerHTML = "<h5>&#x2705</h5>";
            document.getElementById(x+"_status").style.color="green";
            $("#"+x+"_status").slideUp(2000);
        }
    }
    xhr.open("POST", url+"/UTS", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let params = "t_id=" + x + "&category=" + category; // probably use document.getElementById(...).value
    xhr.send(params);


}

    function lock(x,url) {
        console.log("here4")
        console.log(x);
        $("#"+x+"_lock").slideUp();

        document.getElementById(x+"_lock").innerHTML="<h5>&#9202</h5>";

        $("#"+x+"_lock").slideDown();
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                //console.log(xhr.responseText);

                document.getElementById(x + "_lock").innerHTML = "<h5>&#x2705</h5>";
                document.getElementById(x+"_lock").style.color="green";
                $("#"+x+"_lock").slideUp(2000);
            }
        }
        xhr.open("POST", url+"/LockTransaction", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let params = "t_id=" + x.substring(0,36) ; // probably use document.getElementById(...).value
        xhr.send(params);


    }


})

