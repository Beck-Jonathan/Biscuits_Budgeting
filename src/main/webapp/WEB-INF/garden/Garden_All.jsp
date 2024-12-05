<%--************
Create the JSP  For Viewing All of The  Garden table
 Created By Jonathan Beck 4/13/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Gardens</h1>
            <p>There ${Gardens.size() eq 1 ? "is" : "are"}&nbsp;${Gardens.size()} Garden${Gardens.size() ne 1 ? "s" : ""}</p>
            Add Garden   <a href="addGarden">Add</a>
            <c:if test="${Gardens.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Garden_id</th>
                        <th scope="col">Garden_Name</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Gardens}" var="garden">
                        <tr>
                            <td><a href = "editgarden?gardenid=${garden.garden_id}&mode=view">${fn:escapeXml(garden.garden_id)}</a></td><td>${fn:escapeXml(garden.garden_Name)}</td>
                            <td><input type="checkbox" disabled <c:if test="${garden.is_active}">checked</c:if>></td>
                            <td><a href = "editgarden?gardenid=${garden.garden_id}&mode=edit" > Edit </a></td>
                            <td><a href = "deletegarden?gardenid=${garden.garden_id}&mode=<c:choose><c:when test="${garden.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!garden.is_active}">un</c:if>Delete </a></td>
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
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

