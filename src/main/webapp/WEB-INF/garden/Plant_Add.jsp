<%--************
Create the JSP  For adding to The  Plant table
 Created By Jonathan Beck 4/13/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addPlant" id = "addPlant" >
        <!-- Plant_Name -->
        <div class ="row" id = "row0">
            <label for="inputplantPlant_Name" class="form-label">Plant_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.plantPlant_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Plant_Name" id="inputplantPlant_Name" name="inputplantPlant_Name" value="${fn:escapeXml(results.Plant_Name)}">
                <c:if test="${not empty results.plantPlant_Nameerror}">
                    <div class="invalid-feedback">${results.plantPlant_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Garden_ID -->
        <div class ="row" id = "row1">
            <label for="inputplantGarden_ID" class="form-label">Garden_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.plantGarden_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Garden_ID" id="inputplantGarden_ID" name="inputplantGarden_ID" value="${fn:escapeXml(results.Garden_id)}">
                    <c:forEach items="${Gardens}" var="Garden">
                        <option value="${Garden.garden_id}">${Garden.garden_Name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.plantGarden_IDerror}">
                    <div class="invalid-feedback">${results.plantGarden_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Plant_depth -->
        <div class ="row" id = "row2">
            <label for="inputplantPlant_depth" class="form-label">Plant_depth</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.plantPlant_deptherror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Plant_depth" id="inputplantPlant_depth" name="inputplantPlant_depth" value="${fn:escapeXml(results.Plant_depth)}">
                <c:if test="${not empty results.plantPlant_deptherror}">
                    <div class="invalid-feedback">${results.plantPlant_deptherror}</div>
                </c:if>
            </div>
        </div>
        <!-- Plant_Direction -->
        <div class ="row" id = "row3">
            <label for="inputplantPlant_Direction" class="form-label">Plant_Direction</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.plantPlant_Directionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Plant_Direction" id="inputplantPlant_Direction" name="inputplantPlant_Direction" value="${fn:escapeXml(results.Plant_Direction)}">
                <c:if test="${not empty results.plantPlant_Directionerror}">
                    <div class="invalid-feedback">${results.plantPlant_Directionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Germination_Time -->
        <div class ="row" id = "row4">
            <label for="inputplantGermination_Time" class="form-label">Germination_Time</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.plantGermination_Timeerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Germination_Time" id="inputplantGermination_Time" name="inputplantGermination_Time" value="${fn:escapeXml(results.Germination_Time)}">
                <c:if test="${not empty results.plantGermination_Timeerror}">
                    <div class="invalid-feedback">${results.plantGermination_Timeerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Plant  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

