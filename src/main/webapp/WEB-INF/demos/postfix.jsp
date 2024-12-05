<%--************
Create the JSP  For adding to The  Status table
 Created By Jonathan Beck3/18/2024
**********--%>

<div class = "container">
    <form method="post" action="${appURL}/postFix" id = "addStatus" >"
        <!-- Name -->
        <div class ="row" id = "row0">
            <label for="postfix" class="form-label">PostFix Expression</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.statusNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="PostFix Equation" id="postfix" name="postfix" >
                <c:if test="${not empty results.statusNameerror}">
                    <div class="invalid-feedback">${results.statusNameerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">calcuate</button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
            <c:if test="${not empty results.response}">
            <p>${results.response}</p>
            </c:if>
        </div>
    </form>
</div>


