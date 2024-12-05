<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class="row">
    <div class="col-4">
        Good Afternoon ${User.user_ID}!
    </div>
    <div class="col-4">
        Your Current Role is: ${User.role_ID}
    </div>
    <div class="col-4">
        <a href="crrgLogout"><button>Logout</button></a>
    </div>
</div>

<div class="table-responsive"><table class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">Table</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <tr><td>View all Contributor</td><td><a href="all-Contributors"> View </a> </td></tr>
    <tr><td>View all Album</td><td><a href="all-Albums"> View </a> </td></tr>
    <tr><td>View all Picture</td><td><a href="all-Pictures"> View </a> </td></tr>
    <%-- <tr><td>View all Skater</td><td><a href="all-Skaters"> View </a> </td></tr> --%>
    <%-- <tr><td>View all Tag</td><td><a href="all-Tags"> View </a> </td></tr> --%>
    <tr><td>View all User</td><td><a href="allCrrgUser"> View </a> </td></tr>
    <tr><td>Manage Sponsors</td><td><a href="all-Sponsors"> View </a> </td></tr>
    <tr><td>Manage Sponsor Images</td><td><a href="all-Sponsor_Tags"> View </a> </td></tr>
    
    <%-- <tr><td>View all Role</td><td><a href="all-Roles"> View </a> </td></tr> --%>

    </tbody>
</table>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>