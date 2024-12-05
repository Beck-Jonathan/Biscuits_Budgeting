<%--************
Create the JSP  For adding to The  Sponsor_Tag table
 Created By Jonathan Beck 11/14/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp" %>
<div class="container">
    <form method="post" action="${appURL}/addSponsor_Tag" id="addSponsor_Tag" enctype="multipart/form-data">
        <!-- Sponsor_ID -->
        <div class="row" id="row0">
            <label for="inputsponsor_tagSponsor_ID" class="form-label">Sponsor_ID</label>
            <div class="input-group input-group-lg">
                <select class="<c:if test="${not empty results.sponsor_tagSponsor_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"
                        placeholder="Sponsor_ID" id="inputsponsor_tagSponsor_ID" name="inputsponsor_tagSponsor_ID"
                        value="${fn:escapeXml(results.Sponsor_ID)}">
                    <c:forEach items="${Sponsors}" var="Sponsor">
                        <option value="${Sponsor}">${Sponsor} </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.sponsor_tagSponsor_IDerror}">
                    <div class="invalid-feedback">${results.sponsor_tagSponsor_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Picture_ID -->
        <div class="row" id="row1">
            <label for="inputsponsor_tagPicture_ID" class="form-label">Logo</label>
            <div class="input-group input-group-lg">
                <input type="file" size="50" accept=".jpg,.jpeg,.png"
                       class="<c:if test="${not empty results.teamLogoerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"
                       placeholder="Logo" id="inputsponsor_tagPicture_ID" name="inputsponsor_tagPicture_ID"
                       value="${fn:escapeXml(results.Logo)}">
                <c:if test="${not empty results.teamLogoerror}">
                    <div class="invalid-feedback">${results.teamLogoerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class="row" id="row2">
            <label for="inputsponsor_tagDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text"
                       class="<c:if test="${not empty results.sponsor_tagDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"
                       placeholder="Description" id="inputsponsor_tagDescription" name="inputsponsor_tagDescription"
                       value="${fn:escapeXml(results.Description)}">
                <c:if test="${not empty results.sponsor_tagDescriptionerror}">
                    <div class="invalid-feedback">${results.sponsor_tagDescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- is_Primary
        <div class="row" id="row3">
            <label for="inputsponsor_tagis_Primary" class="form-label">is_Primary</label>
            <div class="input-group input-group-lg">
                <input type="text"
                       class="<c:if test="${not empty results.sponsor_tagis_Primaryerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"
                       placeholder="is_Primary" id="inputsponsor_tagis_Primary" name="inputsponsor_tagis_Primary"
                       value="${fn:escapeXml(results.is_Primary)}">
                <c:if test="${not empty results.sponsor_tagis_Primaryerror}">
                    <div class="invalid-feedback">${results.sponsor_tagis_Primaryerror}</div>
                </c:if>
            </div>
        </div>
        -->
        <div class="align-items-center mt-0">
            <div class="d-grid">
                <button class="btn btn-orange mb-0" type="submit">Create Sponsor_Tag</button>
            </div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp" %>

