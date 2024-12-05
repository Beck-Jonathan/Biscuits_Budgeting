<%--************
Create the JSP  For adding to The  Garden table
 Created By Jonathan Beck 4/13/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addGarden" id = "addGarden" >
        <!-- Garden_Name -->
        <div class ="row" id = "row0">
            <label for="inputgardenGarden_Name" class="form-label">Garden_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.gardenGarden_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Garden_Name" id="inputgardenGarden_Name" name="inputgardenGarden_Name" value="${fn:escapeXml(results.Garden_Name)}">
                <c:if test="${not empty results.gardenGarden_Nameerror}">
                    <div class="invalid-feedback">${results.gardenGarden_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Garden  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

