<%--************
Create the JSP  For Viewing All of The  User_Event_Line table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller User_Event_Lines</h1>
            <p>There ${User_Event_Lines.size() eq 1 ? "is" : "are"}&nbsp;${User_Event_Lines.size()} User_Event_Line${User_Event_Lines.size() ne 1 ? "s" : ""}</p>
            Add User_Event_Line   <a href="addUser_Event_Line">Add</a>
            <c:if test="${User_Event_Lines.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">User_ID</th>
                        <th scope="col">Event_ID</th>
                        <th scope="col">Date_assgined</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${User_Event_Lines}" var="user_event_line">
                        <tr>
                            <td>${user_event_line.user_ID}</td>
                            <td>${user_event_line.event_ID}</td>
                            <td>${user_event_line.date_assgined}</td>
                            <td>${user_event_line.is_active}</td>
                            <td><a href = "edituser_event_line?user_event_lineid=${user_event_line.user_event_line_ID}" > Edit </a></td>
                            <td><a href = "deleteuser_event_line?user_event_lineid=${user_event_line.user_event_line_ID}&mode=<c:choose><c:when test="${user_event_line.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!user_event_line.is_active}">un</c:if>Delete </a></td>
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

