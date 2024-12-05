<%--************
Create the JSP  For Viewing All of The  Team table as cards
 Created By Jonathan Beck 5/01/2024

**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>

<div class = "container">
    <div class="row">
        <div class="col-12">
            <%@include file="/WEB-INF/personal-project/partialTeams.jsp"%>
            <p>You ${Teams.size() eq 1 ? "are not on" : "are not on"}&nbsp;${NotMyTeams.size()} Team${NotMyTeams.size() ne 1 ? "s" : ""}</p>

            <c:if test="${NotMyTeams.size() > 0}">
                <div >

                <c:forEach items="${NotMyTeams}" var="team">
                    <div class="col-12">

                        <div class="card shadow overflow-hidden p-2">
                            <div class="row g-0">
                                <div class="col-md-5 overflow-hidden">
                                    <img src="${appURL}/uploads/${team.logo}" class="rounded-2" alt="${team.name}">
                                </div>
                                <div class="col-md-7">
                                    <div class="card-body">
                                        <!-- -->
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <!--  -->
                                            <a href="${appURL}/courses?category=${team.league_ID}" class="badge text-bg-primary mb-2 mb-sm-0">${team.league_ID}</a>
                                        </div>

                                        <!--  -->
                                        <h5 class="card-title"><a href="#" data-bs-toggle="modal" data-bs-target="#courseModal${team.team_ID}">${team.name}</a></h5>
                                        <p class="text-truncate-2 d-none d-lg-block">${team.team_City}, ${team.team_State}</p>

                                        <!--  -->
                                        <ul class="list-inline">
                                            <li class="list-inline-item h6 fw-light"><a href="${appURL}/courses?skill-level=${fn:toLowerCase(course.level)}"><i class="fas fa-signal
                                                <c:choose>
                                                <c:when test="${league.level eq 'Beginner'}">text-success</c:when>
                                                <c:when test="${league.level eq 'Intermediate'}">text-warning</c:when>
                                                <c:otherwise>text-danger</c:otherwise>
                                                </c:choose>
                                                 me-2"></i>${league.level}</a></li>

                                        </ul>
                                        <table>
                                            <th>
                                            <td style="background-color:#${team.team_Primary_Color};">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td style="background-color:#${team.team_Secondary_Color};">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td style="background-color:#${team.team_Tertiary_Color};">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

                                            </th>
                                        </table>

                                        <!--  -->

                                        <c:if test="${User.privilege_ID > 0}">
                                            <!--  -->
                                            <div class="mt-3 mt-sm-0">

                                                <a href="manage-teams?teamid=${team.team_ID}&mode=join" class="btn btn-secondary btn-lg " role="button" aria-disabled="false">Join</a>


                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                </c:forEach>
            </c:if>


        </div>
    </div>
</div>
</div>


<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>