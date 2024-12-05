<%--************
Create the JSP  For Viewing All of The  Sponsor table
 Created By Jonathan Beck 11/14/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
<div class="row">
<div class="col-12">
<h1>All Roller Sponsors</h1>
<p>There ${Sponsors.size() eq 1 ? "is" : "are"}&nbsp;${Sponsors.size()} Sponsor${Sponsors.size() ne 1 ? "s" : ""}</p>
Add Sponsor   <a href="addSponsor">Add</a>
<c:if test="${Sponsors.size() > 0}">
<div class="table-responsive"><table class="table table-bordered">
<thead>
<tr>

<th scope="col"><a href="${appURL}/all-Sponsors?Order_By=Sponsor">Sponsor Name</a></th>
<th scope="col"><a href="${appURL}/all-Sponsors?Order_By=Tier">Tier</a></th>
<th scope="col">website</th>
<th scope="col">Description</th>
<th scope="col">is_active</th>
<th scope="col">Edit</th>
<th scope="col">Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${Sponsors}" var="sponsor">
<tr>
<td><a href = "editsponsor?sponsorid=${sponsor.sponsor_ID}&mode=view">${fn:escapeXml(sponsor.sponsor_ID)}</a></td><td>${fn:escapeXml(sponsor.tier_ID)}</td>
<td>${fn:escapeXml(sponsor.website)}</td>
<td>${fn:escapeXml(sponsor.description)}</td>
<td><input type="checkbox" disabled <c:if test="${sponsor.is_active}">checked</c:if>></td>
<td><a href = "editSponsor?sponsorid=${sponsor.sponsor_ID}&mode=edit" > Edit </a></td>
<td><a href = "deletesponsor?sponsorid=${sponsor.sponsor_ID}&mode=<c:choose><c:when test="${sponsor.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
<c:if test="${sponsor.is_active}">de</c:if>Activate </a></td>
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
<%@include file="/WEB-INF/crrg/foot.jsp"%>

