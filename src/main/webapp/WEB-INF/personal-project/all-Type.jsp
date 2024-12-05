<%--************
Create the JSP  For Viewing All of The  Type table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Types</h1>
            <p>There ${Types.size() eq 1 ? "is" : "are"}&nbsp;${Types.size()} Type${Types.size() ne 1 ? "s" : ""}</p>
            Add Type   <a href="addType">Add</a>
            <c:if test="${Types.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Type_ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Types}" var="type">
                        <tr>
                            <td>${type.type_ID}</td>
                            <td>${type.name}</td>
                            <td><input type="checkbox" disabled <c:if test="${type.is_active}">checked</c:if>></td>
                            <td><a href = "edittype?typeid=${type.type_ID}" > Edit </a></td>
                            <td><a href = "deletetype?typeid=${type.type_ID}&mode=<c:choose><c:when test="${type.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!type.is_active}">un</c:if>Delete </a></td>
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

