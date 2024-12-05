<%--************
Create the JSP  For Viewing All of The  Sponsor_Tag table
 Created By Jonathan Beck 11/14/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Sponsor_Tags</h1>
            <p>There ${Sponsor_Tags.size() eq 1 ? "is" : "are"}&nbsp;${Sponsor_Tags.size()}
                Sponsor_Tag${Sponsor_Tags.size() ne 1 ? "s" : ""}</p>
            Add Sponsor_Tag <a href="addSponsor_Tag">Add</a>
            <c:if test="${Sponsor_Tags.size() > 0}">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Sponsor_ID</th>
                            <th scope="col">Picture_ID</th>
                            <th scope="col">Description</th>
                            <th scope="col">is_Primary</th>
                            <th scope="col">Edit</th>
                            <th scope="col">Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Sponsor_Tags}" var="sponsor_tag">
                            <tr>
                                <td>
                                    <a href="editsponsor_tag?sponsor_tagid=${sponsor_tag.sponsor_ID}&mode=view">${fn:escapeXml(sponsor_tag.sponsor_ID)}</a>
                                </td>
                                <td>
                                    <a href="editsponsor_tag?sponsor_tagid=${sponsor_tag.sponsor_ID}&mode=view">${fn:escapeXml(sponsor_tag.sponsor_ID)}</a>
                                </td>
                                <td>${fn:escapeXml(sponsor_tag.description)}</td>
                                <td><input type="checkbox" disabled
                                           <c:if test="${sponsor_tag.is_Primary}">checked</c:if>></td>
                                <td><a href="editsponsor_tag?sponsor_tagid=${sponsor_tag.sponsor_ID}&mode=edit">
                                    Edit </a></td>
                                <td>
                                    <a href="deletesponsor_tag?sponsor_tagid=${sponsor_tag.sponsor_ID}&mode=<c:choose><c:when test="${sponsor_tag.is_Primary}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                        <c:if test="${!sponsor_tag.is_Primary}">un</c:if>Delete </a></td> test
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
<%@include file="/WEB-INF/crrg/foot.jsp" %>

