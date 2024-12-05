<%--************
Create the JSP  For Viewing All of The  Team table as cards
 Created By Jonathan Beck 5/01/2024

**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>

<div class = "container">
<div class="row">
<div class="col-12">
<h1>All Roller Teams</h1>
<p>There ${Teams.size() eq 1 ? "is" : "are"}&nbsp;${Teams.size()} Team${Teams.size() ne 1 ? "s" : ""}</p>
<c:if test="${User.privilege_ID > 1}">
    Add Team   <a href="addTeam">Add</a>
</c:if>
<c:if test="${Teams.size() > 0}">
    <div >

    <c:forEach items="${Teams}" var="team">
        <div class="col-12">

            <div class="card shadow overflow-hidden p-2">
                <div class="row g-0">
                    <div class="col-md-5 overflow-hidden">
                        <img src="${appURL}/uploads/${team.logo}" class="rounded-2" alt="${team.name}">
                    </div>
                    <div class="col-md-7">
                        <div class="card-body">
                            <!-- Badge and rating -->
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <!-- Badge -->
                                <a href="${appURL}/courses?category=${team.league_ID}" class="badge text-bg-primary mb-2 mb-sm-0">${team.league_ID}</a>
                            </div>

                            <!-- Title -->
                            <h5 class="card-title"><a href="#" data-bs-toggle="modal" data-bs-target="#courseModal${team.team_ID}">${team.name}</a></h5>
                            <p class="text-truncate-2 d-none d-lg-block">${team.team_City}</p>

                            <!-- Info -->
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

                            <!-- Teacher and Enroll -->


                                <c:if test="${sessionScope.activeUser.privileges eq '1'}">
                                    <!-- Enroll -->
                                    <div class="mt-3 mt-sm-0">
                                        <c:choose>
                                            <c:when test="${!skaterTeams.containsKey(course)}">
                                                <a href="${appURL}/enroll?course=${team.id}" class="btn btn-dark">Enroll</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="#" class="btn btn-secondary btn-lg disabled" tabindex="-1" role="button" aria-disabled="true">enrolled</a>
                                            </c:otherwise>
                                        </c:choose>

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