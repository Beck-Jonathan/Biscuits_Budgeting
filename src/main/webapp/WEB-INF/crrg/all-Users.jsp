<%--************
Create the JSP  For Viewing All of The  User table
 Created By Jonathan Beck 10/15/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container" id="addr" addr="${appURL}">
    <div class="row">
        <div class="col-12">


                <c:if test="${not empty newUser}">
                    <div id="myModal" class="modal">

                        <div><h4>New User Created</h4></div>
                        <div > <p>User Name: ${results.User_ID}</p></div>
                        <div ><p>Password : ${results.Password}</p></div>
                        <div ><button id="modalButton">Close</button></div>

                    </div>
                    <c:remove var="newUser" scope="session"></c:remove>
                </c:if>

            <%-- <c:remove var="results" scope="session"></c:remove>--%>
            <h1>All Roller Users</h1>
            <p>There ${Users.size() eq 1 ? "is" : "are"}&nbsp;${Users.size()} User${Users.size() ne 1 ? "s" : ""}</p>
            Add User   <a href="addCrrgUser">Add</a>
            <c:if test="${Users.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">User_ID</th>
                        <th scope="col">Role_ID</th>
                        <th scope="col">First_Name</th>
                        <th scope="col">Last_Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Last_Logged_In</th>

                        <th scope="col">Edit</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Users}" var="user">
                        <tr>
                            <td><a href = "editCrrgUser?userid=${user.user_ID}&mode=view">${fn:escapeXml(user.user_ID)}</a></td>
                            <td>
                                <c:if test="${user.role_ID eq 'Jonathan'}"><p>Jonathan</p></c:if>
                                <c:if test="${user.role_ID ne 'Jonathan'}">
                                <select class="roleSelect" id="${user.user_ID}" >
                                    <c:forEach items="${Roles}" var="Role">
                                        <option value="${Role}" <c:if test="${user.role_ID eq Role}"> selected </c:if>   ">${Role}   </option>
                                    </c:forEach>
                                </select>
                                </c:if>
                                <p  id="${user.user_ID}_status"></p>


                            </td>
                            <td>${fn:escapeXml(user.first_Name)}</td>
                            <td>${fn:escapeXml(user.last_Name)}</td>
                            <td>${fn:escapeXml(user.email)}</td>
                            <td> <fmt:formatDate type="both" value="${user.last_Logged_In.toDate()}" /> </td>

                            <td><a href = "editCrrgUser?userid=${user.user_ID}&mode=edit" > Edit </a></td>

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
<%@include file="/WEB-INF/crrg/foot.jsp"%>

