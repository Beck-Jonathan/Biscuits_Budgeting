<%@include file="/WEB-INF/crrg/head.jsp"%>

<div class ="row" id = "row0">
    <label for="selectevent" class="form-label">Album_ID</label>
    <div class="input-group input-group-lg">
        <select  class="" onchange="change()" placeholder="Album_ID" id="selectevent" name="inputpictureAlbum_ID" value="${fn:escapeXml(results.Album_ID)}">
            <c:forEach items="${Albums}" var="Album">
                <option value="${Album.album_ID}">${Album.album_Name}   </option>
            </c:forEach>
        </select>
        <c:if test="${not empty results.pictureAlbum_IDerror}">
            <div class="invalid-feedback">${results.pictureAlbum_IDerror}</div>
        </c:if>
    </div>
</div>
<div id="image-track" data-mouse-down-at="0" data-prev-percentage="0">
<c:forEach items="${Pictures}" var="picture" varStatus="counter">
    <img class="image" id="image${counter.count}" src ="${picture.web_Address}" draggable="false"/>
</c:forEach>



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
    <div id="caption">Caption Test</div>
</div>
</div>
<div id="credit">photos: Mark Young</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>