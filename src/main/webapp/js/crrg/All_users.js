modalButton = document.getElementById("modalButton")
modal=document.getElementById("myModal");
modalbg=document.getElementById("modalbg")
modalButton.onclick =function(){
    modal.style.display = "none";
    modalbg.style.display = "none";
}

$(document).ready(function() {

    var allDropDowns = document.getElementsByClassName("roleSelect");
    for (var i = 0; i < allDropDowns.length; i++) {
        allDropDowns[i].addEventListener('change', function () {
            var role = this.value;
            console.log(this);

            let id = this.getAttribute("id");
            var url = document.getElementById("addr").getAttribute("addr");


            let y = takevalues(id, role,url);
        });
    }
    function takevalues(id,role,url) {

        if (id == null) {
            return;
        }

        $("#"+id+"status").slideUp();
        console.log("test");
        console.log(id+"_status");
        document.getElementById(id+"_status").innerHTML="<h5>&#9202</h5>";
        console.log("test2")
        $("#"+id+"_status").slideDown();

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                console.log(xhr.responseText);

                document.getElementById(id + "_status").innerHTML = "<h5>&#x2705</h5>";
                document.getElementById(id+"_status").style.color="green";
                $("#"+id+"_status").slideUp(2000);
            }
        }

        console.log(id);
        xhr.open("POST", url+"/editrole", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let params = "objectID=" + id + "&mode="+ role; // probably use document.getElementById(...).value
        xhr.send(params);


    }})


