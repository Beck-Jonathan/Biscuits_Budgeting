
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    League Admin dash<br/>

    <div class ="row" id = "row0">
        <div class ="col-md-4" id = "r1c1">
            <a href="all-Facilitys"> Manage Facilities</a> <br/>
            <a href="all-Events">Manage Events</a> <br/>
            <a href="all-users">Manage Users</a> <br/>
        </div>
        <div class ="col-md-8" id = "r1c2">
            <%@include file="/WEB-INF/personal-project/partialEvents.jsp"%>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>