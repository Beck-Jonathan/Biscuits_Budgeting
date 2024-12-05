<%--************
Create the JSP For Viuw/Edit from the User table
 Created By Jonathan Beck 10/15/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editCrrgUser" id = "editUser" >
        <!-- User_ID -->
        <div class ="row" id = "row0">
            <h2>User_ID  :
                ${fn:escapeXml(_user.user_ID)}</h2>
        </div>
        <!-- Role_ID -->
        <div class ="row" id = "row1">
            <label for="inputuserRole_ID" class="form-label">Role_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.userRole_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuserRole_ID" name="inputuserRole_ID" value="${fn:escapeXml(user.role_ID)}">
                    <c:forEach items="${Roles}" var="Role">
                        <option value="${Role}"<c:if test="${_user.role_ID eq Role}"> selected </c:if>>${Role}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.userRole_IDerror}">
                    <div class="invalid-feedback">${results.userRole_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- First_Name -->
        <div class ="row" id = "row2">
            <label for="inputuserFirst_Name" class="form-label">First_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userFirst_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="First_Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuserFirst_Name" name="inputuserFirst_Name" value="${fn:escapeXml(_user.first_Name)}">
                <c:if test="${not empty results.userFirst_Nameerror}">
                    <div class="invalid-feedback">${results.userFirst_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Name -->
        <div class ="row" id = "row3">
            <label for="inputuserLast_Name" class="form-label">Last_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userLast_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuserLast_Name" name="inputuserLast_Name" value="${fn:escapeXml(_user.last_Name)}">
                <c:if test="${not empty results.userLast_Nameerror}">
                    <div class="invalid-feedback">${results.userLast_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Email -->
        <div class ="row" id = "row4">
            <label for="inputuserEmail" class="form-label">Email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userEmailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Email" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuserEmail" name="inputuserEmail" value="${fn:escapeXml(_user.email)}">
                <c:if test="${not empty results.userEmailerror}">
                    <div class="invalid-feedback">${results.userEmailerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Logged_In -->
        <div class ="row" id = "row5">
            <label for="inputuserLast_Logged_In" class="form-label">Last_Logged_In</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.userLast_Logged_Inerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Logged_In" disabled id="inputuserLast_Logged_In" name="inputuserLast_Logged_In" value="${fn:escapeXml(_user.last_Logged_In)}">
                <c:if test="${not empty results.userLast_Logged_Inerror}">
                    <div class="invalid-feedback">${results.userLast_Logged_Inerror}</div>
                </c:if>
            </div>
        </div>

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit User </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

