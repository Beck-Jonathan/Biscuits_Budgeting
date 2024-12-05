/******************
Create the JSP  For Viewing All of The  User table
Created By Jonathan Beck3/3/2024

***************/
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Users</h1>
            <p>There ${Users.size() eq 1 ? "is" : "are"}&nbsp;${Users.size()} User${Users.size() ne 1 ? "s" : ""}</p>
            <c:if test="${Users.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">User_ID</th>
                        <th scope="col">User_Name</th>
                        <th scope="col">User_PW</th>
                        <th scope="col">Status_ID</th>
                        <th scope="col">Email</th>
                        <th scope="col">Language_ID</th>
                        <th scope="col">Privilege_ID</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Users}" var="user">
                        <tr>
                            <td>${user.user_ID}</td>
                            <td>${user.user_Name}</td>
                            <td>${user.user_PW}</td>
                            <td>${user.status_ID}</td>
                            <td>${user.email}</td>
                            <td>${user.language_ID}</td>
                            <td>${user.privilege_ID}</td>
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

