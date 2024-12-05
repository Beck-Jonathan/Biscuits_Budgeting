<%--************
Create the JSP  For Viewing All of The  Plant table
 Created By Jonathan Beck 4/13/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Plants</h1>
            <p>There ${Plants.size() eq 1 ? "is" : "are"}&nbsp;${Plants.size()} Plant${Plants.size() ne 1 ? "s" : ""}</p>
            Add Plant   <a href="addPlant">Add</a>
            <c:if test="${Plants.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Plant_ID</th>
                        <th scope="col">Plant_Name</th>
                        <th scope="col">Garden_ID</th>
                        <th scope="col">Plant_depth</th>
                        <th scope="col">Plant_Direction</th>
                        <th scope="col">Germination_Time</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Plants}" var="plant">
                        <tr>
                            <td><a href = "editplant?plantid=${plant.plant_ID}&mode=view">${fn:escapeXml(plant.plant_ID)}</a></td><td>${fn:escapeXml(plant.plant_Name)}</td>
                            <td>${fn:escapeXml(plant.garden_ID)}</td>
                            <td>${fn:escapeXml(plant.plant_depth)}</td>
                            <td>${fn:escapeXml(plant.plant_Direction)}</td>
                            <td>${fn:escapeXml(plant.germination_Time)}</td>
                            <td><input type="checkbox" disabled <c:if test="${plant.is_active}">checked</c:if>></td>
                            <td><a href = "editplant?plantid=${plant.plant_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deleteplant?plantid=${plant.plant_ID}&mode=<c:choose><c:when test="${plant.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!plant.is_active}">un</c:if>Delete </a></td>
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

