<%--************
Create the JSP  For adding to The  Album table
 Created By Jonathan Beck 10/10/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addAlbum" id = "addAlbum" >
        <!-- Album_Name -->
        <div class ="row" id = "row0">
            <label for="inputalbumAlbum_Name" class="form-label">Album_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.albumAlbum_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Album_Name" id="inputalbumAlbum_Name" name="inputalbumAlbum_Name" value="${fn:escapeXml(results.Album_Name)}">
                <c:if test="${not empty results.albumAlbum_Nameerror}">
                    <div class="invalid-feedback">${results.albumAlbum_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Album  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

