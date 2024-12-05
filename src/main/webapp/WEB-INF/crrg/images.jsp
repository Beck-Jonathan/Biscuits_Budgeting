<%@include file="/WEB-INF/crrg/head.jsp"%>
<div>
    <select id="selectevent" onchange="change()">
    <option value="ames">Ames</option>
    <option value="wisconsin">Wisconsin</option>
    <option value="stpats">St. Patrick's!</option>
</select></div>
<div id="image-track" data-mouse-down-at="0" data-prev-percentage="0">
    <img class="image" id="image1" src ="images/crrg/ames/1.jpg" draggable="false"/>
    <img class="image" id="image2"  src ="images/crrg/ames/2.jpg" draggable="false"/>
    <img class="image" id="image3" src ="images/crrg/ames/3.jpg" draggable="false"/>
    <img class="image" id="image4" src ="images/crrg/ames/4.jpg" draggable="false"/>
    <img class="image" id="image5" src ="images/crrg/ames/5.jpg" draggable="false"/>
    <img class="image" id="image6" src ="images/crrg/ames/6.jpg" draggable="false"/>
    <img class="image" id="image7" src ="images/crrg/ames/7.jpg" draggable="false"/>
    <img class="image" id="image8" src ="images/crrg/ames/8.jpg" draggable="false"/>

</div>

<!-- The Modal -->
<div id ="modalbg">
<div id="myModal" class="modal">

    <!-- The Close Button -->

    <span id="close" class="close">&times;</span>
    <span id="left" class="left"><</span>
    <span id="right" class="right">></span>
    <!-- Modal Content (The Image) -->

    <img class="modal-content" id="img01">

    <!-- modal sliders -->
    <div  id="sliders">
    <div class="slidecontainer">
        <input type="range" min="0" max="100" value="0" class="slider" id="grayscale">
    </div>
    <div class="slidecontainer">
        <input type="range" min="0" max="360" value="0" class="slider" id="hueRotate">
    </div>
    </div>
    <!-- Modal Caption (Image Text) -->
    <div id="caption"></div>
</div>
</div>
<div id="credit">photos: Mark Young</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>