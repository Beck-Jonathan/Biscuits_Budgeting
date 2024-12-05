/******************
Create the JSP  For adding to The  User table
Created By Jonathan Beck3/3/2024

***************/
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addUser" id = "addUser" >
        <!-- User_Name -->
        <div class ="row" id = "row0">
            <label for="inputuserUser_Name" class="form-label">User_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_Name" id="inputuserUser_Name" name="inputuserUser_Name" value="${results.User_Name}">
                <c:if test="${not empty results.userUser_Nameerror}">
                    <div class="invalid-feedback">${results.userUser_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- User_PW -->
        <div class ="row" id = "row1">
            <label for="inputuserUser_PW" class="form-label">User_PW</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_PWerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW" id="inputuserUser_PW" name="inputuserUser_PW" value="${results.User_PW}">
                <c:if test="${not empty results.userUser_PWerror}">
                    <div class="invalid-feedback">${results.userUser_PWerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Status_ID -->
        <div class ="row" id = "row2">
            <label for="inputuserStatus_ID" class="form-label">Status_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.userStatus_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Status_ID" id="inputuserStatus_ID" name="inputuserStatus_ID" value="${results.Status_ID}">
                    <c:forEach items="${statuss}" var="status">
                    <option value="${status.status_ID}">${status.name}   </option>
                    </c:forEach>

                    <c:if test="${not empty results.userStatus_IDerror}">
                    <div class="invalid-feedback">${results.userStatus_IDerror}</div>
                    </c:if>
                </select>
            </div>
        </div>
        <!-- Email -->
        <div class ="row" id = "row3">
            <label for="inputuserEmail" class="form-label">Email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userEmailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Email" id="inputuserEmail" name="inputuserEmail" value="${results.Email}">
                <c:if test="${not empty results.userEmailerror}">
                    <div class="invalid-feedback">${results.userEmailerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Language_ID -->
        <div class ="row" id = "row4">
            <label for="inputuserLanguage_ID" class="form-label">Language_ID</label>
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
        <!-- Privilege_ID -->
        <div class ="row" id = "row5">
            <label for="inputuserPrivilege_ID" class="form-label">Privilege_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.userPrivilege_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Privilege_ID" id="inputuserPrivilege_ID" name="inputuserPrivilege_ID">
                    <c:forEach items="${privileges}" var="privilege">
                    <option value="${privilege.privilege_ID}">${privilege.name}   </option>
                    </c:forEach>
                    <c:if test="${not empty results.userPrivilege_IDerror}">
                    <div class="invalid-feedback">${results.userPrivilege_IDerror}</div>
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

<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

