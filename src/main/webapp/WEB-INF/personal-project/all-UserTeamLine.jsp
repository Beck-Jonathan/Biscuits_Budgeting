<%--************
Create the JSP  For Viewing All of The  User_Team_Line table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller User_Team_Lines</h1>
            <p>There ${User_Team_Lines.size() eq 1 ? "is" : "are"}&nbsp;${User_Team_Lines.size()} User_Team_Line${User_Team_Lines.size() ne 1 ? "s" : ""}</p>
            Add User_Team_Line   <a href="addUser_Team_Line">Add</a>
            <c:if test="${User_Team_Lines.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">User_ID</th>
                        <th scope="col">Team_ID</th>
                        <th scope="col">Date_assgined</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${User_Team_Lines}" var="user_team_line">
                        <tr>
                            <td>${user_team_line.user_ID}</td>
                            <td>${user_team_line.team_ID}</td>
                            <td>${user_team_line.date_assgined}</td>
                            <td>${user_team_line.is_active}</td>
                            <td><a href = "edituser_team_line?user_team_lineid=${user_team_line.user_team_line_ID}" > Edit </a></td>
                            <td><a href = "deleteuser_team_line?user_team_lineid=${user_team_line.user_team_line_ID}&mode=<c:choose><c:when test="${user_team_line.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!user_team_line.is_active}">un</c:if>Delete </a></td>
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

