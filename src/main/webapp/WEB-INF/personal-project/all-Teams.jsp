<%--************
Create the JSP  For Viewing All of The  Team table
 Created By Jonathan Beck3/18/2024
 Edited 4/6 to include colors
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Teams</h1>
            <p>There ${Teams.size() eq 1 ? "is" : "are"}&nbsp;${Teams.size()} Team${Teams.size() ne 1 ? "s" : ""}</p>
<c:if test="${User.privilege_ID > 1}">
            Add Team   <a href="addTeam">Add</a>
</c:if>
            <c:if test="${Teams.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Team_ID</th>
                        <th scope="col">League Name</th>
                        <th scope="col">Team Name</th>
                        <th scope="col">Primary</th>
                        <th scope="col">Secondary</th>
                        <th scope="col">Tertiary</th>
                        <th scope="col">Team_City</th>
                        <th scope="col">Team_State</th>
                        <th scope="col">Logo</th>
                        <th scope="col">is_active</th>
                <c:if test="${User.privilege_ID > 1}">
                        <th scope="col">Edit</th>
                </c:if>
                        <c:if test="${User.privilege_ID > 2}">
                        <th scope="col">Delete</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Teams}" var="team">
                    <tr>
                            <td><a href = "editteam?teamid=${team.team_ID}&mode=view">${fn:escapeXml(team.team_ID)}</a></td>
                            <td>${fn:escapeXml(team.league_name)}</td>
                            <td>${fn:escapeXml(team.name)}</td>
                            <td style="background-color:#${team.team_Primary_Color};">color</td>
                            <td style="background-color:#${team.team_Secondary_Color};">color</td>
                            <td style="background-color:#${team.team_Tertiary_Color};">color</td>
                            <td>${fn:escapeXml(team.team_City)}</td>
                            <td>${fn:escapeXml(team.team_State)}</td>
                            <td>${fn:escapeXml(team.logo)}</td>
                            <td><input type="checkbox" disabled <c:if test="${team.is_active}">checked</c:if>></td>
                        <c:if test="${User.privilege_ID > 1}">
                            <td><a href = "editteam?teamid=${team.team_ID}&mode=edit" > Edit </a></td>
                        </c:if>
                            <c:if test="${User.privilege_ID > 2}">
                            <td><a href = "deleteteam?teamid=${team.team_ID}&mode=<c:choose><c:when test="${team.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!team.is_active}">un</c:if>Delete </a></td>
                        </c:if>
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

