<%--************
Create the JSP For Viuw/Edit from the League table
 Created By Jonathan Beck 4/8/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editleague" id = "editleague" >
        <!-- League_ID -->
        <div class ="row" id = "row0">
            <h2>League_ID  :
                ${fn:escapeXml(league.league_ID)}</h2>
        </div>
        <!-- League_Name -->
        <div class ="row" id = "row1">
            <label for="inputleagueLeague_Name" class="form-label">League_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.leagueLeague_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="League_Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputleagueLeague_Name" name="inputleagueLeague_Name" value="${fn:escapeXml(league.league_Name)}">
                <c:if test="${not empty results.leagueLeague_Nameerror}">
                    <div class="invalid-feedback">${results.leagueLeague_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- League_Level -->
        <div class ="row" id = "row2">
            <label for="inputleagueLeague_Level" class="form-label">League_Level</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.leagueLeague_Levelerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="League_Level" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputleagueLeague_Level" name="inputleagueLeague_Level" value="${fn:escapeXml(league.league_Level)}">
                <c:if test="${not empty results.leagueLeague_Levelerror}">
                    <div class="invalid-feedback">${results.leagueLeague_Levelerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Monthly_Due -->
        <div class ="row" id = "row3">
            <label for="inputleagueMonthly_Due" class="form-label">Monthly_Due</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.leagueMonthly_Dueerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Monthly_Due" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputleagueMonthly_Due" name="inputleagueMonthly_Due" value="${fn:escapeXml(league.monthly_Due)}">
                <c:if test="${not empty results.leagueMonthly_Dueerror}">
                    <div class="invalid-feedback">${results.leagueMonthly_Dueerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Active Days -->
        <div class ="row" id = "row4">
            <label for="inputleagueActive_Days" class="form-label">Active Days</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.leagueActive_Dayserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Active Days" id="inputleagueActive_Days" name="inputleagueActive_Days" value="${fn:escapeXml(results.Active_Days)}">
                <c:if test="${not empty results.leagueActive_Dayserror}">
                    <div class="invalid-feedback">${results.leagueActive_Dayserror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class ="row" id = "row5">
            <label for="inputleagueDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.leagueDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputleagueDescription" name="inputleagueDescription" value="${fn:escapeXml(league.description)}">
                <c:if test="${not empty results.leagueDescriptionerror}">
                    <div class="invalid-feedback">${results.leagueDescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit League </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

