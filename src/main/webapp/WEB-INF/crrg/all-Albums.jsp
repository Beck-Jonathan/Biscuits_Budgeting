<%--************
Create the JSP  For Viewing All of The  Album table
 Created By Jonathan Beck 10/10/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container" id="addr" addr="${appURL}" objectType="album">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Albums</h1>
            <p>There ${Albums.size() eq 1 ? "is" : "are"}&nbsp;${Albums.size()} Album${Albums.size() ne 1 ? "s" : ""}</p>
            Add Album   <a href="addAlbum">Add</a>
            <c:if test="${Albums.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Album_ID</th>
                        <th scope="col">Album_Name</th>
                        <th scope="col">Is_Active</th>
                        <th scope="col">Count</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Activate/Deactivate</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Albums}" var="album">
                        <tr>
                            <td><a href = "editAlbum?albumid=${album.album_ID}&mode=view">${fn:escapeXml(album.album_ID)}</a></td>
                            <td>${fn:escapeXml(album.album_Name)}</td>
                            <td><input type="checkbox" id=${album.album_ID} class="Activation_Box" <c:if test="${album.is_Active}">checked</c:if>> <p  id="${album.album_ID}_status"></p></td>
                            <td>${album.album_size}</td>
                            <td><a href = "editAlbum?albumid=${album.album_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deletealbum?albumid=${album.album_ID}&mode=<c:choose><c:when test="${album.is_Active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${album.is_Active}">De</c:if>activate </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

