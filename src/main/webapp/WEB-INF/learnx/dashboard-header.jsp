<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="card p-4">
                <c:choose>
                    <c:when test="${not empty activeUser.first_name and not empty activeUser.last_name}">
                        <div class="card-title"><h1>${fn:escapeXml(activeUser.first_name) }&nbsp;${fn:escapeXml(activeUser.last_name)}</h1></div>
                    </c:when>
                    <c:otherwise>
                        <div class="card-title"><h1>${activeUser.email}</h1></div>
                    </c:otherwise>
                </c:choose>
                <ul class="list-inline">
                    <li class="list-item"><i class="fas fa-star text-warning"></i> <fmt:message key="dashboard.membersince"/> <fmt:formatDate value="${activeUser.createdDate}" type="both" timeStyle="full" dateStyle="full"></fmt:formatDate><br/></li>
                    <li class="list-item"><i class="fas fa-cake text-success"></i> <fmt:message key="dashboard.birthday"/>  <fmt:formatDate value="${activeUser.birthdayDate}" type="date"  dateStyle="full"></fmt:formatDate></li>

                </ul>

            </div><%-- close card --%>
        </div><%-- close col --%>
        <!-- Advanced filter responsive toggler START -->
        <!-- Divider -->
        <hr class="d-xl-none">
        <div class="col-12 col-xl-3 d-flex justify-content-end align-items-center">
            <button class="btn btn-primary d-xl-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
                <i class="fas fa-bars"></i>
            </button>
        </div>
        <!-- Advanced filter responsive toggler END -->
    </div><%-- close row --%>
</div><%-- close container --%>