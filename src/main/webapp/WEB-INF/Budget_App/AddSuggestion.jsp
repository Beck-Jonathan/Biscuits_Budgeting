<%--************
Create the JSP  For adding to The  Suggestion table
 Created By Jonathan Beck 5/1/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addSuggestion" id = "addSuggestion" >
        
        <!-- Application_Name -->
        <div class ="row" id = "row1">
            <label for="inputsuggestionApplication_Name" class="form-label">Application Name</label>
            <select  name ="inputsuggestionApplication_Name" id="inputsuggestionApplication_Name">
                <option selected value="Budgeting" >Budgeting</option>
                <option value="Homepage">Homepage</option>
                <option value="Scheduling">Scheduling</option>
                <option value="Derby">CRRD Site</option>
                <option value="Warriors">Fire Emblem App</option>
                <option value="Generator">My Generator</option>


                <option value="Other">Other (Please Explain)</option>
            </select>
        </div>
        <!-- content -->
        <div class ="row" id = "row2">
            <label for="inputsuggestioncontent" class="form-label">content</label>
            <div class="input-group input-group-lg">
                <textarea  style="height:200px; vertical-align: top" class="<c:if test="${not empty results.suggestioncontenterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" id="inputsuggestioncontent" name="inputsuggestioncontent" value="${fn:escapeXml(results.content)}"> </textarea>
                <c:if test="${not empty results.suggestioncontenterror}">
                    <div class="invalid-feedback">${results.suggestioncontenterror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Suggestion  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

