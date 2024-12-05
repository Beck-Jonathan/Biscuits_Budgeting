<%--************
Create the JSP  manully updating your own privlidges
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
<c:if test="${not empty User}">
    <form method="post" action="${appURL}/user_change_priv" id = "userEditPriv" >
        <!-- Priv Level -->
        <div class ="row" id = "row2">
            <label for="inputPivLevel" class="form-label">Type_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.PrivLevelerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Type_ID" id="inputPivLevel" name="inputPivLevel" value="${results.priv_ID}">
                    <c:forEach items="${privileges}" var="privilege">
                    <option value="${privilege.privilege_ID}" <c:if test="${User.privilege_ID eq privilege.privilege_ID}">selected</c:if>>${privilege.name} (level ${privilege.privilege_ID}  access)   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.PrivLevelerror}">
                    <div class="invalid-feedback">${results.PrivLevelerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit"> save edit</button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
    <p>level 1 can view leagues and teams <br/>
        level 2 can add and edit a team <br/>
        level 3 can  delete a team, and add/edit a league<br/>
        level 4 can delete a league<br/></p>
</c:if>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

