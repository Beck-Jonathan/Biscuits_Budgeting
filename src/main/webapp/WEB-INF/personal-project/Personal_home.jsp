<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<c:if test="${User.privilege_ID > 5}">
    <p>Your current privilege level is ${User.privilege_ID}. For convenience during development
       use the edit privilege button below to grant yourself further access.
    This will be removed when we are ready for deployment</p>
</c:if>
<div class="table-responsive col-12">
<table class="table table-bordered">




    <c:if test="${empty User}">
        <!--<tr><td>Join Us</td><td><a href="joinus"> View </a> </td></tr> -->
    </c:if>

    <c:if test="${User.privilege_ID > 5}">
    <thead>
    <tr>
        <th scope="col">Table</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
        <tr><td>View all League</td><td><a href="all-Leagues"> View </a> </td></tr>
        <tr><td>View all Team</td><td><a href="all-Teams"> View </a> </td></tr>
        <tr><td>Edit privilege</td><td><a href="user_change_priv"> View </a> </td></tr>



    <tr><td>ADMIN SECTION<td>IGNORE FOR NOW</td></tr>
    <tr><td>View all Type</td><td><a href="all-Types"> View </a> </td></tr>
    <tr><td>View all Status</td><td><a href="all-Status"> View </a> </td></tr>
    <tr><td>View all Privilege</td><td><a href="all-Privileges"> View </a> </td></tr>
    <tr><td>View all Language</td><td><a href="all-Language"> View </a> </td></tr>
    <tr><td>View all Facility</td><td><a href="all-Facilitys"> View </a> </td></tr>
    <tr><td>View all Event</td><td><a href="all-Events"> View </a> </td></tr>
    <tr><td>View all League</td><td><a href="all-Leagues"> View </a> </td></tr>
    <tr><td>View all Team</td><td><a href="all-Teams"> View </a> </td></tr>
    <tr><td>View all User</td><td><a href="all-skaters"> View </a> </td></tr>
    <tr><td>View all TwoFA</td><td><a href="all-TwoFAs"> View </a> </td></tr>
    <tr><td>View all User_Event_Line</td><td><a href="all-User_Event_Lines"> View </a> </td></tr>
    <tr><td>View all User_Team_Line</td><td><a href="all-User_Team_Lines"> View </a> </td></tr>
</c:if>
    </tbody>
</table>

    </div>

<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h2 class="display-4 text-center">Welcome to Cedar Rapids Roller Derby</h2>
        <p class="lead text-center">Let's Skate</p>
        <div class="row">
            <div class="col col-md-4">
                <img src="images/Roller/1.png" />
            </div>
            <div class="col col-md-4">
                <img src="images/Roller/2.png" />
            </div>
            <div class="col col-md-4">
                <img src="images/Roller/3.png" />
            </div>
        </div>
    </div>
</div>


        <%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>
