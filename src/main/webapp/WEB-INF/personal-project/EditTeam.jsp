<%--************
Create the JSP For Viuw/Edit from the Team table
 Created By Jonathan Beck 4/6/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editteam" id = "editteam"  enctype="multipart/form-data" >
        <!-- Team_ID -->
        <div class ="row" id = "row0">
            <div class ="col col-sm-4" >
            <h2>Team_ID  :
                ${fn:escapeXml(team.team_ID)}</h2>
            </div>
            <div class ="col col-sm-4">
            </div>
            <div class ="col col-sm-4">
                <img src="${displayTeamLogo}" style="height: 100px; margin: 10px;
                        border: 4px solid #${team.team_Primary_Color};
                        box-shadow: 2px 2px 5px 5px #${team.team_Secondary_Color}, 6px 6px 1px 1px #${team.team_Tertiary_Color};"
                     class="<c:if test="${not empty results.teamLogoerror}">is-invalid</c:if> " placeholder="Logo" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="displayTeamLogo" name="displayTeamLogo" value="${fn:escapeXml(team.logo)}">

            </div>

        </div>
        <!-- League_ID -->
        <div class ="row" id = "row1">
            <label for="inputteamLeague_ID" class="form-label">League_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.teamLeague_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamLeague_ID" name="inputteamLeague_ID" value="${fn:escapeXml(team.league_ID)}">
                    <c:forEach items="${Leagues}" var="League">
                        <option value="${League.league_ID}"<c:if test="${team.league_ID eq League.league_ID}"> selected </c:if>>${League.league_Name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.teamLeague_IDerror}">
                    <div class="invalid-feedback">${results.teamLeague_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Name -->
        <div class ="row" id = "row2">
            <label for="inputteamName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.teamNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamName" name="inputteamName" value="${fn:escapeXml(team.name)}">
                <c:if test="${not empty results.teamNameerror}">
                    <div class="invalid-feedback">${results.teamNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- start colors -->
        <div class ="row" id = "row3">
        <!-- Team_Primary_Color -->
        <div class ="col col-sm-3" >
            <label for="inputteamTeam_Primary_Color" class="form-label">Team_Primary_Color</label>
            <div class="input-group input-group-lg">
                <input type="color" class="<c:if test="${not empty results.teamTeam_Primary_Colorerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Team_Primary_Color" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamTeam_Primary_Color" name="inputteamTeam_Primary_Color" value="#${fn:escapeXml(team.team_Primary_Color)}">
                <c:if test="${not empty results.teamTeam_Primary_Colorerror}">
                    <div class="invalid-feedback">${results.teamTeam_Primary_Colorerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Team_Secondary_Color -->
        <div class ="col col-sm-3" >
            <label for="inputteamTeam_Secondary_Color" class="form-label">Team_Secondary_Color</label>
            <div class="input-group input-group-lg">
                <input type="color" class="<c:if test="${not empty results.teamTeam_Secondary_Colorerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Team_Secondary_Color" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamTeam_Secondary_Color" name="inputteamTeam_Secondary_Color" value="#${fn:escapeXml(team.team_Secondary_Color)}">
                <c:if test="${not empty results.teamTeam_Secondary_Colorerror}">
                    <div class="invalid-feedback">${results.teamTeam_Secondary_Colorerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Team_Tertiary_Color -->
        <div class ="col col-sm-3" >
            <label for="inputteamTeam_Tertiary_Color" class="form-label">Team_Tertiary_Color</label>
            <div class="input-group input-group-lg">
                <input type="color" class="<c:if test="${not empty results.teamTeam_Tertiary_Colorerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Team_Tertiary_Color" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamTeam_Tertiary_Color" name="inputteamTeam_Tertiary_Color" value="#${fn:escapeXml(team.team_Tertiary_Color)}">
                <c:if test="${not empty results.teamTeam_Tertiary_Colorerror}">
                    <div class="invalid-feedback">${results.teamTeam_Tertiary_Colorerror}</div>
                </c:if>
            </div>
        </div>
        </div> <!-- end colors -->
        <!-- Team_City -->
        <div class ="row" id = "row6">
            <label for="inputteamTeam_City" class="form-label">Team_City</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.teamTeam_Cityerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Team_City" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamTeam_City" name="inputteamTeam_City" value="${fn:escapeXml(team.team_City)}">
                <c:if test="${not empty results.teamTeam_Cityerror}">
                    <div class="invalid-feedback">${results.teamTeam_Cityerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Team_State -->
        <div class ="row" id = "row7">
            <label for="inputteamTeam_State" class="form-label">Team_State</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.teamTeam_Stateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Team_State" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputteamTeam_State" name="inputteamTeam_State" value="${fn:escapeXml(team.team_State)}">
                <c:if test="${not empty results.teamTeam_Stateerror}">
                    <div class="invalid-feedback">${results.teamTeam_Stateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Logo -->
        <div class ="row" id = "row8">
            <label for="inputteamLogo" class="form-label">Logo</label>
            <div class="input-group input-group-lg">
                <input type="file" size="50" accept=".jpg,.jpeg,.png" class="<c:if test="${not empty results.teamLogoerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Logo" id="inputteamLogo" name="inputteamLogo" value="${fn:escapeXml(team.logo)}">
                <c:if test="${not empty results.teamLogoerror}">
                    <div class="invalid-feedback">${results.teamLogoerror}</div>
                </c:if>
            </div>
        </div>

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Team </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

