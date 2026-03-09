<%--************
Create the JSP  For Viewing All of The  Category table
 Created By Jonathan Beck 7/31/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">

    <div class="row">
        <div class="col-12">
            <h1>All Budget Categories</h1>
            <p>There ${Categorys.size() eq 1 ? "is" : "are"}&nbsp;${Categories.size()} Category${Categories.size() ne 1 ? "s" : ""}</p>
            Add Category   <a href="addTransactionCategory">Add</a>
            <c:if test="${Categories.size() > 0}">
                <div class="row g-3"> <c:forEach items="${Categories}" var="category">
                    <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6">
                        <div class="card shadow-sm border-0 position-relative category-card-mini" style="border-left: 5px solid ${category.color_id} !important;">

                            <c:if test="${fn:toLowerCase(category.category_Name) != 'uncategorized' && fn:toLowerCase(category.category_Name) != 'savings'}">
                                <a href="#modal${category.category_ID}" rel="modal:open"
                                   class="btn-delete-corner"
                                   title="Delete ${fn:escapeXml(category.category_Name)}">
                                    &times;
                                </a>
                            </c:if>
                            <div class="card-body py-2 px-3 d-flex align-items-center">

                                <h6 class="mb-0 fw-bold">
                                    <a href="editCategory?categoryid=${category.category_ID}&mode=view" class="text-decoration-none text-dark stretched-link">
                                            ${fn:escapeXml(category.category_Name)}
                                    </a>
                                </h6>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>

                <c:forEach items="${Categories}" var="category">
                    <div id="modal${category.category_ID}" class="modal" style="max-width: 400px; border-radius: 8px;">
                        <div class="p-3 text-center">
                            <h5 class="fw-bold">Delete Category?</h5>
                            <p class="small text-muted">Delete <strong>${fn:escapeXml(category.category_Name)}</strong>? Transactions will become "Uncategorized".</p>
                            <div class="d-flex justify-content-center gap-2 mt-3">
                                <a href="deleteBudgetCategory?categoryid=${category.category_ID}" class="btn btn-sm btn-danger px-3">Delete</a>
                                <a href="#" rel="modal:close" class="btn btn-sm btn-light px-3">Cancel</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

