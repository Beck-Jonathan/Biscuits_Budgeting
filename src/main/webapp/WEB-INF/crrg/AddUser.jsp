<%--************
Create the JSP  For adding to The  User table
 Created By Jonathan Beck 10/15/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addCrrgUser" id = "addCrrgUser" >
        <!-- User_ID -->
        <div class ="row" id = "row0">
            <label for="inputuserUser_ID" class="form-label">User_ID</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_ID" id="inputuserUser_ID" name="inputuserUser_ID" value="${fn:escapeXml(results.User_ID)}">
                <c:if test="${not empty results.userUser_IDerror}">
                    <div class="invalid-feedback">${results.userUser_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Role_ID -->
        <div class ="row" id = "row1">
            <label for="inputuserRole_ID" class="form-label">Role_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.userRole_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Role_ID" id="inputuserRole_ID" name="inputuserRole_ID" value="${fn:escapeXml(results.Role_ID)}">
                    <c:forEach items="${Roles}" var="Role">
                        <option value="${Role}">${Role}   </option>
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
                <input type="text" class="<c:if test="${not empty results.userFirst_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="First_Name" id="inputuserFirst_Name" name="inputuserFirst_Name" value="${fn:escapeXml(results.First_Name)}">
                <c:if test="${not empty results.userFirst_Nameerror}">
                    <div class="invalid-feedback">${results.userFirst_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Name -->
        <div class ="row" id = "row3">
            <label for="inputuserLast_Name" class="form-label">Last_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userLast_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Name" id="inputuserLast_Name" name="inputuserLast_Name" value="${fn:escapeXml(results.Last_Name)}">
                <c:if test="${not empty results.userLast_Nameerror}">
                    <div class="invalid-feedback">${results.userLast_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Email -->
        <div class ="row" id = "row4">
            <label for="inputuserEmail" class="form-label">Email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userEmailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Email" id="inputuserEmail" name="inputuserEmail" value="${fn:escapeXml(results.Email)}">
                <c:if test="${not empty results.userEmailerror}">
                    <div class="invalid-feedback">${results.userEmailerror}</div>
                </c:if>
            </div>
        </div>


        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create User  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

