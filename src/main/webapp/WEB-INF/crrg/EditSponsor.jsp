<%--************
Create the JSP For Viuw/Edit from the Sponsor table
 Created By Jonathan Beck 11/14/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
<form method="post" action="${appURL}/editSponsor" id = "editSponsor" >
<!-- Sponsor_ID -->
<div class ="row" id = "row0">
<h2>Sponsor_ID  :
 ${fn:escapeXml(sponsor.sponsor_ID)}</h2>
</div>
<!-- Tier_ID -->
<div class ="row" id = "row1">
<label for="inputsponsorTier_ID" class="form-label">Tier_ID</label>
<div class="input-group input-group-lg">
<select  class="<c:if test="${not empty results.sponsorTier_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputsponsorTier_ID" name="inputsponsorTier_ID" value="${fn:escapeXml(sponsor.tier_ID)}">
<c:forEach items="${Tiers}" var="Tier">
<option value="${Tier}"<c:if test="${sponsor.tier_ID eq Tier}"> selected </c:if>>${Tier}   </option>
</c:forEach>
</select>
<c:if test="${not empty results.sponsorTier_IDerror}">
<div class="invalid-feedback">${results.sponsorTier_IDerror}</div>
</c:if>
</div>
</div>
<!-- website -->
<div class ="row" id = "row2">
<label for="inputsponsorwebsite" class="form-label">website</label>
<div class="input-group input-group-lg">
<input type="text" class="<c:if test="${not empty results.sponsorwebsiteerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="website" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputsponsorwebsite" name="inputsponsorwebsite" value="${fn:escapeXml(sponsor.website)}">
<c:if test="${not empty results.sponsorwebsiteerror}">
<div class="invalid-feedback">${results.sponsorwebsiteerror}</div>
</c:if>
</div>
</div>
<!-- Description -->
<div class ="row" id = "row3">
<label for="inputsponsorDescription" class="form-label">Description</label>
<div class="input-group input-group-lg">
<input type="text" class="<c:if test="${not empty results.sponsorDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputsponsorDescription" name="inputsponsorDescription" value="${fn:escapeXml(sponsor.description)}">
<c:if test="${not empty results.sponsorDescriptionerror}">
<div class="invalid-feedback">${results.sponsorDescriptionerror}</div>
</c:if>
</div>
</div>
<div class="align-items-center mt-0">
<div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Sponsor </button></div>
<c:if test="${not empty results.dbStatus}"
><p>${results.dbStatus}</p>
</c:if>
</div>
</form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

