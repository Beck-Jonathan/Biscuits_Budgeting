<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>


<div class = "container">
    <h3>Really delete your account?</h3>
    <c:if test="${not empty User}">
        <form method="post" action="${appURL}/deleteaccount" id = "deleteaccount" >
            <!-- user-->
            <div class ="row" id = "row2">

                <div class="input-group input-group-lg">
                    <input type="hidden" class="form-control border-0 bg-light rounded-end ps-1 hidden" name="inputuserID" value="${fn:escapeXml(User.user_ID)}">


                </div>
            </div>
            <div class="align-items-center mt-0">
                <div class="d-grid"><button class="btn btn-orange mb-0" type="submit"> Delete Account</button></div>
                <c:if test="${not empty results.dbStatus}"
                ><p>${results.dbStatus}</p>
                </c:if>
            </div>
        </form>

    </c:if>
</div>

<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>