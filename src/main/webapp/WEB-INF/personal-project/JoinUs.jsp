<%--
Create the JSP for signing up for the league.
Created By Jonathan Beck3/10/2024

--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/joinus" id = "JoinUs" >
        <!-- User_Name -->
        <div class ="row" id = "row0">
            <label for="inputuserUser_Name" class="form-label">User Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror3}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_Name" id="inputuserUser_Name" name="inputuserUser_Name" value="${results.User_Name3}">
                <c:if test="${not empty results.userUser_Nameerror3}">
                    <div class="invalid-feedback">${results.userUser_Nameerror3}</div>
                </c:if>
            </div>
        </div>
        <!-- User_PW -->
        <div class ="row" id = "row1">
            <label for="inputuserUser_PW" class="form-label">Password</label>
            <div class="input-group input-group-lg">
                <input type="password" class="<c:if test="${not empty results.userUser_PWerror3}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW" id="inputuserUser_PW" name="inputuserUser_PW" value="${results.User_PW3}">
                <c:if test="${not empty results.userUser_PWerror3}">
                    <div class="invalid-feedback">${results.userUser_PWerror3}</div>
                </c:if>
            </div>
        </div>
        <!-- User_PW2 -->
        <div class ="row" id = "row2">
            <label for="inputuserUser_PW2" class="form-label">Confirm Password</label>
            <div class="input-group input-group-lg">
                <input type="password" class="<c:if test="${not empty results.userUser_PW2error}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW2" id="inputuserUser_PW2" name="inputuserUser_PW2" value="${results.User_PW2}">
                <c:if test="${not empty results.userUser_PW2error}">
                    <div class="invalid-feedback">${results.userUser_PW2error}</div>
                </c:if>
            </div>
        </div>


        <!-- Email -->
        <div class ="row" id = "row4">
            <label for="inputuserEmail" class="form-label">Email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userEmailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Email" id="inputuserEmail" name="inputuserEmail" value="${results.Email}">
                <c:if test="${not empty results.userEmailerror}">
                    <div class="invalid-feedback">${results.userEmailerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Language_ID -->
        <div class ="row" id = "row5">
            <label for="inputuserLanguage_ID" class="form-label">Language</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.userLanguage_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Language_ID" id="inputuserLanguage_ID" name="inputuserLanguage_ID" value="${results.Language_ID}">

                    <c:forEach items="${languages}" var="language">
                        <option value="${language.language_ID}">${language.name}   </option>
                    </c:forEach>
                    <c:if test="${not empty results.userLanguage_IDerror}">
                        <div class="invalid-feedback">${results.userLanguage_IDerror}</div>
                    </c:if>
                </select>
            </div>
        </div>

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

