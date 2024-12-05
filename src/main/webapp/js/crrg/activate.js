$(document).ready(function() {
    var allbuttons = document.getElementsByClassName("Activation_Box");
    for (var i = 0; i < allbuttons.length; i++) {
        allbuttons[i].addEventListener('change', function () {
            var status = this.checked;
            console.log(this);
            var newStatus=0;
            if(status){
                newStatus=1;
            }
            let id = this.getAttribute("id");
            var address = document.getElementById("addr").getAttribute("addr");
            var objectType = document.getElementById("addr").getAttribute("objectType");
            console.log("status: "+newStatus);
            console.log("id: "+id);
            console.log("address "+address);
            console.log("objectType " + objectType);

            let y = takevalues(id, address,objectType,newStatus);
        });
    }
    function takevalues(id,url,typeOfObject,status) {

        if (id == null) {
            return;
        }
        $("#"+id+"_status").slideUp();
        document.getElementById(id+"_status").innerHTML="<h5>&#9202</h5>";

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
        xhr.open("POST", url+"/updateActivation", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let params = "objectID=" + id + "&mode=" + status+"&object="+typeOfObject; // probably use document.getElementById(...).value
        xhr.send(params);


    }})
