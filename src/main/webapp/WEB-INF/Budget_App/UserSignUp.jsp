<%--************
Create the JSP  For adding to The  User table
 Created By Jonathan Beck 7/24/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/budgetwithus" id = "addUser" >
        <!-- User_Name -->
        <div class ="row" id = "row0">
            <label for="inputuserUser_Name" class="form-label">User_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror3}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_Name" id="inputuserUser_Name" name="inputuserUser_Name" value="${fn:escapeXml(results.User_Name)}">
                <c:if test="${not empty results.userUser_Nameerror3}">
                    <div class="invalid-feedback">${results.userUser_Nameerror3}</div>
                </c:if>
            </div>
        </div>
        <!-- User_PW -->
        <div class ="row" id = "row1">
            <label for="inputuserUser_PW" class="form-label">User_PW</label>
            <div class="input-group input-group-lg">
                <input type="password" class="<c:if test="${not empty results.userUser_PWerror3}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW" id="inputuserUser_PW" name="inputuserUser_PW" value="${fn:escapeXml(results.User_PW)}">
                <c:if test="${not empty results.userUser_PWerror3}">
                    <div class="invalid-feedback">${results.userUser_PWerror3}</div>
                </c:if>
            </div>
        </div>
        <!-- User_PW -->
        <div class ="row" id = "row2">
            <label for="inputuserUser_PW2" class="form-label">User_PW</label>
            <div class="input-group input-group-lg">
                <input type="password" class="<c:if test="${not empty results.userUser_PW2error}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW" id="inputuserUser_PW2" name="inputuserUser_PW2" value="${fn:escapeXml(results.User_PW)}">
                <c:if test="${not empty results.userUser_PW2error}">
                    <div class="invalid-feedback">${results.userUser_PW2error}</div>
                </c:if>
            </div>
        </div>
        <!-- Email -->
        <div class ="row" id = "row3">
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
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

