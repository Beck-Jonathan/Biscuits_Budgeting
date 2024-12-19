<%--************
Create the JSP For Viuw/Edit from the Picture table
 Created By Jonathan Beck 10/10/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editPicture" id = "editPicture" >
        <!-- Picture_ID -->
        <div class ="row" id = "row0">
            <h2>Picture_ID  :
                ${fn:escapeXml(picture.picture_ID)}</h2>
        </div>
        <!-- Album_ID -->
        <div class ="row" id = "row1">
            <label for="inputpictureAlbum_ID" class="form-label">Album_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.pictureAlbum_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputpictureAlbum_ID" name="inputpictureAlbum_ID" value="${fn:escapeXml(picture.album_ID)}">
                    <c:forEach items="${Albums}" var="Album">
                        <option value="${Album.album_ID}"<c:if test="${picture.album_ID eq Album.album_ID}"> selected </c:if>>${Album.album_Name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.pictureAlbum_IDerror}">
                    <div class="invalid-feedback">${results.pictureAlbum_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Contributor_ID -->
        <div class ="row" id = "row2">
            <label for="inputpictureContributor_ID" class="form-label">Contributor_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.pictureContributor_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputpictureContributor_ID" name="inputpictureContributor_ID" value="${fn:escapeXml(picture.contributor_ID)}">
                    <c:forEach items="${Contributors}" var="Contributor">
                        <option value="${Contributor.contributor_ID}"<c:if test="${picture.contributor_ID eq Contributor.contributor_ID}"> selected </c:if>>${Contributor.email}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.pictureContributor_IDerror}">
                    <div class="invalid-feedback">${results.pictureContributor_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Web_Address -->
        <div class ="row" id = "row3">
            <label for="inputpictureWeb_Address" class="form-label">Web_Address</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.pictureWeb_Addresserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Web_Address" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputpictureWeb_Address" name="inputpictureWeb_Address" value="${fn:escapeXml(picture.web_Address)}">
                <c:if test="${not empty results.pictureWeb_Addresserror}">
                    <div class="invalid-feedback">${results.pictureWeb_Addresserror}</div>
                </c:if>
            </div>
        </div>
        <!-- description -->
        <div class ="row" id = "row4">
            <label for="inputpicturedescription" class="form-label">description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.picturedescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputpicturedescription" name="inputpicturedescription" value="${fn:escapeXml(picture.description)}">
                <c:if test="${not empty results.picturedescriptionerror}">
                    <div class="invalid-feedback">${results.picturedescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- is_active -->
        <div class ="row" id = "row5">
            <label for="${picture.picture_ID}" class="form-label">is_Approved</label>
            <td><input type="checkbox" id="${picture.picture_ID}" class="Approval_Box" <c:if test="${picture.is_Active}">checked</c:if>><p  id="${picture.picture_ID}_status"></p></td>
        </div>
        <!-- is_Approved -->
        <div class ="row" id = "row6">
            <label for="${picture.picture_ID}_a" class="form-label">is_Approved</label>
            <td><input type="checkbox" id="${picture.picture_ID}_a" class="Approval_Box" <c:if test="${picture.is_Approved}">checked</c:if>><p  id="${picture.picture_ID}_a_status"></p></td>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Picture </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
    <div class="row">
        <div class="col-2">

        </div>
        <div class="col-3 container" >
            <p>Old Image</p>
            <img id="oldImage" src="${fn:escapeXml(picture.web_Address)}" />
        </div>
        <div class="col-2">

        </div>
        <div class="col-3">
            <p> new image</p>
            <img id="newImage" src="" />
        </div>
        <div class="col-2">

        </div>

    </div>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

