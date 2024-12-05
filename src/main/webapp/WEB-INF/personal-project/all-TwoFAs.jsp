<%--************
Create the JSP  For Viewing All of The  TwoFA table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller TwoFAs</h1>
            <p>There ${TwoFAs.size() eq 1 ? "is" : "are"}&nbsp;${TwoFAs.size()} TwoFA${TwoFAs.size() ne 1 ? "s" : ""}</p>
            Add TwoFA   <a href="addTwoFA">Add</a>
            <c:if test="${TwoFAs.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">TwoFA_ID</th>
                        <th scope="col">User_ID</th>
                        <th scope="col">TwoFA_Code</th>
                        <th scope="col">DateSent</th>
                        <th scope="col">edit</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${TwoFAs}" var="twofa">
                        <tr>
                            <td>${twofa.twoFA_ID}</td>
                            <td>${twofa.user_ID}</td>
                            <td>${twofa.twoFA_Code}</td>
                            <td>${twofa.dateSent}</td>

                            <td><a href = "edittwofa?twofaid=${twofa.twoFA_ID}" > Edit </a></td>




                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

