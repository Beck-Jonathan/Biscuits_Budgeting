<%@ taglib prefix="sessionScope" uri="http://java.sun.com/jsp/jstl/core" %>

<div class = "container">
    <c:if test="${empty User_B}">
        <form method="post" action="${appURL}/budget_in" id = "JoinUs" >

            <!-- User_Name -->
            <div class ="row" id = "Loginrow0">
                <label for="inputuserUser_Name" class="form-label">UserName</label>
                <div class="input-group input-group-lg">
                    <input type="hidden" id="currentpage" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>
                    <input type="text" class="<c:if test="${not empty results.userUser_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_Name" id="inputuserUser_Name" name="inputuserUser_Name" value="${results.User_Name}">
                    <c:if test="${not empty results.userUser_Nameerror}">
                        <div class="invalid-feedback">${results.userUser_Nameerror}</div>
                    </c:if>
                </div>
            </div>
            <!-- User_PW -->
            <div class ="row" id = "Loginrow1">
                <label for="inputuserUser_PW" class="form-label">Password</label>
                <div class="input-group input-group-lg">
                    <input type="password" class="<c:if test="${not empty results.userUser_PWerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW" id="inputuserUser_PW" name="inputuserUser_PW" value="${results.User_PW}">
                    <c:if test="${not empty results.userUser_PWerror}">
                        <div class="invalid-feedback">${results.userUser_PWerror}</div>
                    </c:if>
                </div>
            </div>
            <!-- button and message-->
            <c:if test="${not empty loginFail}">
                <label for="button" class="form-label"><p>${loginFail}</p></label>
                <c:if test="${loginFail == 'Login Failed, please verify your username and password'}">
                    <a href="resetpw">Reset Password?</a>
                </c:if>
            </c:if>
            <div class="align-items-center mt-0">
                <div class="d-grid"><button class="btn btn-orange mb-0" id="button"type="submit">Sign in!</button></div>

            </div>


        </form>


    </c:if>
    <c:if test="${not empty User_B}">


        <p>${User_B.email}</p>
        <form method="post" action="${appURL}/signout_budget" id = "signout" >
            <div class="align-items-center mt-0">
                <input type="hidden" id="currentpage1" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>
                <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign out</button></div>

            </div>
        </form>
    </c:if>

</div>



