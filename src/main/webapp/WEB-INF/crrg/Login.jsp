<%--************
Create the JSP  For adding to The  User table
 Created By Jonathan Beck 10/15/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/crrgLogin" id = "addUser" >
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

        <!-- Password -->
        <div class ="row" id = "row6">
            <label for="inputuserPassword" class="form-label">Password</label>
            <div class="input-group input-group-lg">
                <input type="password" class="<c:if test="${not empty results.userPassworderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Password" id="inputuserPassword" name="inputuserPassword" value="${fn:escapeXml(results.Password)}">
                <c:if test="${not empty results.userPassworderror}">
                    <div class="invalid-feedback">${results.userPassworderror}</div>
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

