<%--
Create the JSP for signing up for the league.
Created By Jonathan Beck3/10/2024

--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/resetpw" id = "resetpw" >
        <!-- Email -->
        <div class ="row" id = "row0">
            <label for="inputuserEmail" class="form-label">Email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror3}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Email" id="inputuserEmail" name="inputuserEmail" value="${results.Email}">
                <c:if test="${not empty results.userEmailerror3}">
                    <div class="invalid-feedback">${results.userEmailerror3}</div>
                </c:if>
            </div>
        </div>
        <!-- Username -->
        <div class ="row" id = "row1">
            <label for="inputuserUserName" class="form-label">User Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror3}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User Name" id="inputuserUserName" name="inputuserUserName" value="${results.username3}">
                <c:if test="${not empty results.userUser_Nameerror3}">
                    <div class="invalid-feedback">${results.userUser_Nameerror3}</div>
                </c:if>
            </div>
        </div>


        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Password</button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

