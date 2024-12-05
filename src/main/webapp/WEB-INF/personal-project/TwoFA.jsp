<!--
Create the JSP for signing up for the league.
Created By Jonathan Beck3/10/2024
-->
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/twoFA" id = "twoFA" >
        <!-- Two FA Code -->
        <div class ="row" id = "row0">
            <label for="inputuser2faCode" class="form-label">TwoFA Code</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="TwoFA Code" id="inputuser2faCode" name="inputuser2faCode" value="${results.inputuser2faCode}">
                <c:if test="${not empty results.inputuser2faCode}">
                    <div class="invalid-feedback">${results.inputuser2faCode}</div>
                </c:if>
            </div>
        </div>
        <!-- button -->

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign Up</button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

