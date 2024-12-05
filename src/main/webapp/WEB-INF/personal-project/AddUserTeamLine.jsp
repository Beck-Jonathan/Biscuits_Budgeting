<%--************
Create the JSP  For adding to The  User_Team_Line table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addUser_Team_Line" id = "addUser_Team_Line" >
        <!-- User_ID -->
        <div class ="row" id = "row0">
            <label for="inputuser_team_lineUser_ID" class="form-label">User_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.user_team_lineUser_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_ID" id="inputuser_team_lineUser_ID" name="inputuser_team_lineUser_ID" value="${results.User_ID}">
                    <c:forEach items="${Users}" var="User">
                        <option value="${User.user_ID}">${User.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.user_team_lineUser_IDerror}">
                    <div class="invalid-feedback">${results.user_team_lineUser_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Team_ID -->
        <div class ="row" id = "row1">
            <label for="inputuser_team_lineTeam_ID" class="form-label">Team_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.user_team_lineTeam_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Team_ID" id="inputuser_team_lineTeam_ID" name="inputuser_team_lineTeam_ID" value="${results.Team_ID}">
                    <c:forEach items="${Teams}" var="Team">
                        <option value="${Team.team_ID}">${Team.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.user_team_lineTeam_IDerror}">
                    <div class="invalid-feedback">${results.user_team_lineTeam_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Date_assgined -->
        <div class ="row" id = "row2">
            <label for="inputuser_team_lineDate_assgined" class="form-label">Date_assgined</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.user_team_lineDate_assginederror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Date_assgined" id="inputuser_team_lineDate_assgined" name="inputuser_team_lineDate_assgined" value="${results.Date_assgined}">
                <c:if test="${not empty results.user_team_lineDate_assginederror}">
                    <div class="invalid-feedback">${results.user_team_lineDate_assginederror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign Up</button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

