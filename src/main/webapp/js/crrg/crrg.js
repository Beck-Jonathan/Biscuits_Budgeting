$(document).ready(function() {
    document.body.addEventListener('touchstart', function(e){ e.preventDefault(); });

    var i = 0;
    var modal = document.getElementById("myModal");
    var modalbg = document.getElementById("modalbg");
    var modalImg = document.getElementById("img01");
    var  images = document.getElementsByClassName("image");
    var sliders = document.getElementsByClassName('slider');
    var grayscaleSlider = document.getElementById("grayscale");
    var hueRotateSlider = document.getElementById("hueRotate");
    var slidersDiv = document.getElementById("sliders");

    var grayscale="grayscale(0%)";
    var blurry="blur(0px)";
    var contrast="contrast(100%)";
    var huerotate="hue-rotate(0deg)";
    var filters=grayscale+" "+blurry+" "+contrast+" "+huerotate;
    updateStyle(modalImg,filters);
    console.log(modalImg.filter);
    document.body.addEventListener('keypress',function() {
            updateStyle(modalImg,filters);
        }
    );


    grayscaleSlider.addEventListener('input',function(){

        var value = grayscaleSlider.value;
        grayscale="grayscale("+value+"%)";
        //updateStyle(modalImg,filters);
    })
    hueRotateSlider.addEventListener('input',function(){

        var value = hueRotateSlider.value;
        huerotate="hue-rotate("+value+"deg)";
        //updateStyle(modalImg,filters);
    })
    for (const slider of sliders) {
        slider.addEventListener('input',function(){
            modalImg.style.transitionDuration=".1s";
            console.log("slide2");
            filters=grayscale+" "+blurry+" "+contrast+" "+huerotate;

            updateStyle(modalImg,filters);
        })
    }

    document.body.addEventListener('keypress',function(e) {
        console.log(e.key);
        modalImg.style.transitionDuration="3s";


        if (modal.style.display === "block") {
            if (e.key === "q"){
                $('#sliders').slideToggle();
            }
            if (e.key === "a") {

                grayscale="grayscale(100%)";

                var current=getComputedStyle(modalImg).filter;
                var startOfPhrase=current.lastIndexOf('grayscale');
                var startOfLeftParen=startOfPhrase+10;
                var startofRightParen = current.indexOf(")",startOfLeftParen);
                var description = current.substring(startOfLeftParen,startofRightParen);
                grayscaleSlider.value=description*100;
            }
            if (e.key === "b") {
                blurry="blur(20px)";
            }
            if (e.key === "c") {
                contrast="contrast(300%)";
            }
            if (e.key === "d") {
                huerotate="hue-rotate(360deg)";
                var current_1=getComputedStyle(modalImg).filter;
                console.log("computed style: "+current_1);
                var startOfPhrase_1=current_1.lastIndexOf('hue-rotate');
                console.log("index start of phrase hue-rotate: "+startOfPhrase_1);
                var subString = current_1.substring(startOfPhrase_1);
                console.log(subString)
                var leftParen=subString.indexOf("(")
                var rightParen=subString.indexOf(")");

                var goal =subString.substring(leftParen+1,rightParen-3);
                console.log("goal: "+goal);


                hueRotateSlider.value=goal;
            }
            filters=grayscale+" "+blurry+" "+contrast+" "+huerotate;
            updateStyle(modalImg,filters);

        }
    });
    document.body.addEventListener('keyup',function(e){
        console.log(e.key);
        var filter="";


        if(modal.style.display==="block"){
            if (e.key === "a") {
                //grayscale="grayscale(0%)";
                modalImg.style.filter=getComputedStyle(modalImg).filter;
                //var current=getComputedStyle(modalImg).filter;
                //var startOfPhrase=current.lastIndexOf('grayscale');
                //var startOfLeftParen=startOfPhrase+10;
                //var startofRightParen = current.indexOf(")",startOfLeftParen);
                //var description = current.substring(startOfLeftParen,startofRightParen);
                //grayscaleSlider.value=description*100;



            }
            if (e.key === "b") {
                blurry="blur(0)";
                modalImg.style.filter=getComputedStyle(modalImg).filter;
            }
            if (e.key === "c") {
                contrast="contrast(100%)";
                modalImg.style.filter=getComputedStyle(modalImg).filter;
            }
            if (e.key === "d") {
                //huerotate="hue-rotate(0deg)";
                modalImg.style.filter=getComputedStyle(modalImg).filter;
            }
            //filters=grayscale+" "+blurry+" "+contrast+" "+huerotate;
            //updateStyle(modalImg,filters);
            if(e.key==="Escape"){
                closeModal(modal);
                return;
            }
            if(e.key==="ArrowLeft"&&i>0){
                i--;
                pageLeft(i,images,modalImg);
                offsetCalculate();
                checkArrows(i,images);
                return;
            }
            if(e.key==="ArrowRight" &&i<images.length-1){
                i++;
                pageRight(i,images,modalImg);
                offsetCalculate();
                checkArrows(i,images);

            }
        }
    });

    for (const image of images) {

        image.onclick=(event)=>{
            i=0;
            while (true){
                if (images[i].id===image.id){
                    break;
                }
                i++;
            }
            modal.style.display = "block";
            modalbg.style.display = "block";
            modalImg.src = image.src;
            checkArrows(i,images);
            offsetCalculate();
            modal.style.height = 10;
            //modalImg.style.height = Math.Min(image.height,95);
            //modalImg.style.width = Math.Min(image.width,95);
            //captionText.innerHTML = this.alt;
            }
        }

    var right = document.getElementsByClassName("right")[0];
    right.onclick = function() {
        i++;
        pageRight(i,images,modalImg);
        offsetCalculate();
        checkArrows(i,images);

    }

    var left = document.getElementsByClassName("left")[0];
    left.onclick = function(){
        i--;
        pageLeft(i,images,modalImg);
        offsetCalculate();
    checkArrows(i,images);
    };

    modal.onclick = function(){
        //closeModal(modal);
    }
    var close = document.getElementsByClassName("close")[0];
    close.onclick = function() {
        closeModal(modal);

    }

    const track = document.getElementById("image-track");

const handleOnDown = e => track.dataset.mouseDownAt = e.clientX;

const handleOnUp = () => {
    track.dataset.mouseDownAt = "0";
    track.dataset.prevPercentage = track.dataset.percentage;
}

const handleOnMove = e => {
    if(track.dataset.mouseDownAt === "0") return;

    const mouseDelta = parseFloat(track.dataset.mouseDownAt) - e.clientX,
        maxDelta = window.innerWidth / 2;

    var percentage = (mouseDelta / maxDelta) * -100;
    var    nextPercentageUnconstrained = parseFloat(track.dataset.prevPercentage) + percentage;
    var    nextPercentage = Math.max(Math.min(nextPercentageUnconstrained, 0), -100);
    if (isNaN(nextPercentage) ){
        nextPercentage=-50;
    }
    track.dataset.percentage = nextPercentage;

    track.animate({
        transform: `translate(${nextPercentage}%, -50%)`
    }, { duration: 1200, fill: "forwards" });

    for(const image of track.getElementsByClassName("image")) {
        image.animate({
            objectPosition: `${100 + nextPercentage}% center`
        }, { duration: 1200, fill: "forwards" });
    }

}

/* -- Had to add extra lines for touch events -- */

window.onmousedown = e => handleOnDown(e);

window.ontouchstart = e => handleOnDown(e.touches[0]);

window.onmouseup = e => handleOnUp(e);

window.ontouchend = e => handleOnUp(e.touches[0]);

window.onmousemove = e => handleOnMove(e);

window.ontouchmove = e => handleOnMove(e.touches[0]);

})

function change(){
    const  images = document.getElementsByClassName("image");
    var selected = document.getElementById("selectevent").value;

    var credits = document.getElementById("credit");

    if(selected==="stpats"){

        credits.style.display= "none";
    }
    else {

        credits.style.display= "block";
    }
    for (const image of images) {
    let currentsource = image.src;

    let newsource = currentsource.replace("ames",selected);
         newsource = newsource.replace("wisconsin",selected);
         newsource = newsource.replace("stpats",selected)
        image.src=newsource;

    }

}

function replaceChar(origString, replaceChar, index) {
    let firstPart = origString.substr(0, index);
    let lastPart = origString.substr(index + 1);

    let newString = firstPart + replaceChar + lastPart;
    return newString;
}

function pageLeft(i,images,modalImg) {

    var newImage = images[i];
    modalImg.src = newImage.src;
}


    function pageRight(i,images,modalImg){

        var newImage=images[i];
        modalImg.src=newImage.src;

    }
    function checkArrows( i,images){
        var left = document.getElementsByClassName("left")[0];
        var right = document.getElementsByClassName("right")[0];
        if (i===0){
            left.style.display="none"
        }
        else {
            left.style.display="block"
        }
        if (i===images.length-1){
            right.style.display="none"

        }
        else {
            right.style.display="block"
        }

    }

function offsetCalculate(){
    var parent = document.getElementById('img01');
    console.log (parent);
    var xLeft = parent.x
    var width = parent.clientWidth;
    var padding=20;
    var leftLocation = xLeft+padding;
    var rightLocation =xLeft+width-padding;
    var leftString = leftLocation+"px";
    var rightString = rightLocation+"px";
    console.log(leftString);
    console.log(rightString);
   // $('#close').css({

        //'right': rightString,
   // });
   // $('#left').css({

        //'left': leftString,
   // });
   // $('#right').css({

     //   'right': rightString,
    //});
}

function closeModal(modal){
        modal.style.display = "none";
    modalbg.style.display = "none";
    }
function updateStyle(modalImg,filters){
    modalImg.style.filter=filters;
}

