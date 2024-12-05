<%--************
Create the JSP  For Viewing All of The  Status table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Statuss</h1>
            <p>There ${Statuss.size() eq 1 ? "is" : "are"}&nbsp;${Statuss.size()} Status${Statuss.size() ne 1 ? "s" : ""}</p>
            Add Status   <a href="addStatus">Add</a>
            <c:if test="${Statuss.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Status_ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Statuss}" var="status">
                        <tr>
                            <td>${status.status_ID}</td>
                            <td>${status.name}</td>
                            <td><input type="checkbox" disabled <c:if test="${status.is_active}">checked</c:if>></td>
                            <td><a href = "editstatus?statusid=${status.status_ID}" > Edit </a></td>
                            <td><a href = "deletestatus?statusid=${status.status_ID}&mode=<c:choose><c:when test="${status.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!status.is_active}">un</c:if>Delete </a></td>
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

