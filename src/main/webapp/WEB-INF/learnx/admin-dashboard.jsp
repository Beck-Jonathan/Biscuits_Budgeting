<%@include file="/WEB-INF/learnx/top.jsp" %>
<main>
    <%@include file="/WEB-INF/learnx/dashboard-header.jsp" %>

    <div class="container">
        <div class="row">
            <%@include file="/WEB-INF/learnx/dashboard-sidebar.jsp" %>

            <!-- Main content START -->
            <div class="col-xl-9">
                <jsp:include page="../Shared/flashMessage.jsp"></jsp:include>






                </div>
            </div>
            <!-- Main content END -->

        </div>
    </div>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp" %>