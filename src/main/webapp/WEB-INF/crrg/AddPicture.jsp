<%--************
Create the JSP  For adding to The  Picture table
 Created By Jonathan Beck 10/10/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addPicture" id = "addPicture" enctype="multipart/form-data" >
        <!-- Album_ID -->
        <div class ="row" id = "row0">
            <label for="inputpictureAlbum_ID" class="form-label">Album_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.pictureAlbum_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Album_ID" id="inputpictureAlbum_ID" name="inputpictureAlbum_ID" value="${fn:escapeXml(results.Album_ID)}">
                    <c:forEach items="${Albums}" var="Album">
                        <option value="${Album.album_ID}">${Album.album_Name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.pictureAlbum_IDerror}">
                    <div class="invalid-feedback">${results.pictureAlbum_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Contributor_ID -->
        <div class ="row" id = "row1">
            <label for="inputpictureContributor_ID" class="form-label">Contributor_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.pictureContributor_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Contributor_ID" id="inputpictureContributor_ID" name="inputpictureContributor_ID" value="${fn:escapeXml(results.Contributor_ID)}">
                    <c:forEach items="${Contributors}" var="Contributor">
                        <option value="${Contributor.contributor_ID}">${Contributor.email}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.pictureContributor_IDerror}">
                    <div class="invalid-feedback">${results.pictureContributor_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- description -->
        <div class ="row" id = "row2">
            <label for="inputpicturedescription" class="form-label">description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.picturedescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="description" id="inputpicturedescription" name="inputpicturedescription" value="${fn:escapeXml(results.description)}">
                <c:if test="${not empty results.picturedescriptionerror}">
                    <div class="invalid-feedback">${results.picturedescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- is_Approved -->
        <div class ="row" id = "row3">
            <label for="inputpictureis_Approved" class="form-label">is_Approved</label>
            <div class="input-group input-group-lg">
                <input type="radio" property="notify" name="inputpictureis_Approved" id ="inputpictureis_Approved" value="true" checked>Yes
                <input type="radio" property="notify" name="inputpictureis_Approved" value="false" > No
            </div>
        </div>
        <!-- is_Active -->
        <div class ="row" id = "row4">
            <label for="inputpictureis_Active" class="form-label">is_Approved</label>
            <div class="input-group input-group-lg">
                <input type="radio" property="notify" name="inputpictureis_Active" id ="inputpictureis_Active" value="true" checked>Yes
                <input type="radio" property="notify" name="inputpictureis_Active" value="false" > No
            </div>
        </div>
        <!-- https://www.tutorialspoint.com/jsp/jsp_file_uploading.htm -->
        <div class ="row" id = "row5">
            <label for="inputpictureWeb_Address" class="form-label">Logo</label>
            <div class="input-group input-group-lg">
                <input type="file" size="50" accept=".jpg,.jpeg,.png" class="<c:if test="${not empty results.pictureWeb_Addresserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Logo" id="inputpictureWeb_Address" name="inputpictureWeb_Address" value="${fn:escapeXml(results.Web_Address)}" multiple>
                <c:if test="${not empty results.pictureWeb_Addresserror}">
                    <div class="invalid-feedback">${results.teamLogoerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Picture  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

