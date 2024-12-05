var textinput = document.getElementById("inputpictureWeb_Address");
var newImage = document.getElementById("newImage");
textinput.onkeyup=function(){
    newImage.src=textinput.value;
}