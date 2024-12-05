


<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    Team Admin dash<br/>

    <div class ="row" id = "row0">
        <div class ="col-md-4" id = "r0c1">
            <a href="My-Teams">View and update my teams</a><br/>
            <a href="My-Events"> View our team's Events</a><br/>
            <a href="all-Events"> Sign up for an event</a><br/>
            <a href="all-Facilitys"> View the League Facilities</a><br/>
        </div>
        <div class ="col-md-8" id = "r0c2">
            <%@include file="/WEB-INF/personal-project/partialEvents.jsp"%>
        </div>
    </div>
    <div class ="row" id = "row1">
        <div class ="col-md-4" id = "r1c1">
            <%@include file="/WEB-INF/personal-project/UserProfile.jsp"%>
        </div>
        <div class ="col-md-8" id = "r1c2">
            <%@include file="/WEB-INF/personal-project/partialTeams.jsp"%>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>